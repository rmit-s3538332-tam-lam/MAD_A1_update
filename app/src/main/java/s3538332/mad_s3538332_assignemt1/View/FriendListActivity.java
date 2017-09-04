package s3538332.mad_s3538332_assignemt1.View;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import s3538332.mad_s3538332_assignemt1.Controller.ContactDataManager;
import s3538332.mad_s3538332_assignemt1.R;

public class FriendListActivity extends AppCompatActivity {
    Button addFriendBtn;
    ListView friendListView;
    Intent contactPickerIntent;
    protected static final int PICK_CONTACTS = 100;
    private static final String LOG_TAG = MainActivity.class.getName();
    public String name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        addFriendBtn =(Button) findViewById(R.id.addFriendBtn);
        friendListView = (ListView) findViewById(R.id.friendListView);


    }

    public void addFriendBtnOnClick(View view){
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
                    Toast.makeText(this,name+ " "+email, Toast.LENGTH_LONG).show();

                } catch (ContactDataManager.ContactQueryException e) {
                    Log.e(LOG_TAG, e.getMessage());
                    Toast.makeText(this,"Require permission to use Contact details", Toast.LENGTH_LONG).show();

                }
            }
        }
    }
}
