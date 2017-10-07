package s3538332.mad_s3538332_assignemt1.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import s3538332.mad_s3538332_assignemt1.Controller.ContactDataManager;
import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.Controller.DatabaseController;
import s3538332.mad_s3538332_assignemt1.R;

public class FriendListActivity extends AppCompatActivity {
    Button addFriendBtn;
    ListView friendListView;
    Intent contactPickerIntent;
    Controller controller;
    Intent viewDetailIntent;
    DatabaseController dBController;
    protected static final int PICK_CONTACTS = 100;
    private static final String LOG_TAG = MainActivity.class.getName();
    public String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        dBController = new DatabaseController(this);
        controller = new Controller(this);
        addFriendBtn = (Button) findViewById(R.id.addFriendBtn);
        friendListView = (ListView) findViewById(R.id.friendListView);
        viewDetailIntent = new Intent(this, FriendDetailActivity.class);

        setListItemListener();

    }

    @Override
    protected void onStart() {
        super.onStart();
        dBController.logFriendTable();
        dBController.onStart();
    }

    public void setListItemListener(){
        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClicked,
                                    int position, long id) {
                Log.i(LOG_TAG,"Clicked on "+ position);
                viewDetailIntent.putExtra("ID",Integer.toString(position));
                startActivity(viewDetailIntent);
            }
        });
        friendListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View viewClicked, int position, long id) {
                controller.removeFriend(position);
                populateListView();
                return true;
            }
        });

    }
    public void addFriendBtnOnClick(View view) {
        contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, PICK_CONTACTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACTS) {
            if (resultCode == RESULT_OK) {
                ContactDataManager contactsManager = new ContactDataManager(this, data);
                name = "";
                email = "";
                try {
                    name = contactsManager.getContactName();
                    email = contactsManager.getContactEmail();
                    if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
                        Toast.makeText(this, "Contact must have at least an email",
                                Toast.LENGTH_LONG).show();

                    } else {
                        controller.addFriend(name, email);
                    }
                } catch (ContactDataManager.ContactQueryException e) {
                    Log.e(LOG_TAG, e.getMessage());
                    Toast.makeText(this, "Require permission to use Contact details",
                            Toast.LENGTH_LONG).show();

                }
            }
        }
    }


    public void populateListView() {
        ArrayList<String> nameOnlyList = controller.nameOnlyList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.itemlistview,
                nameOnlyList);
        friendListView.setAdapter(adapter);

    }


    @Override
    public void onResume() {
        super.onResume();
        populateListView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dBController.onStop();
    }

}
