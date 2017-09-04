package s3538332.mad_s3538332_assignemt1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.R;

public class AddAttendeeActivity extends AppCompatActivity {
    ListView friendListView;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendee);
        friendListView = (ListView) findViewById(R.id.friendListView);
        controller = new Controller(this);
        populateListView();
        setListItemListener();
    }

    public void populateListView() {
        ArrayList<String> nameOnlyList = controller.nameOnlyList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.itemlistview,
                nameOnlyList);
        friendListView.setAdapter(adapter);

    }
    public void setListItemListener(){
        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("ID",i);
                Log.i(controller.LOG_TAG, "ID: " + i);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }
}
