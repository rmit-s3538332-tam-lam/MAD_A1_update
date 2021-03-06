package s3538332.mad_s3538332_assignemt1.View;

import android.content.Intent;
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

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class ViewAnttendeeActivity extends AppCompatActivity {
    Button addAttendeeBtn,completeBtn;
    ListView attendeeListView;
    int REQ_CODE = 1;
    Controller controller;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_anttendee);
        addAttendeeBtn = (Button) findViewById(R.id.addAttendeeBtn);
        completeBtn = (Button) findViewById(R.id.completeBtn);

        attendeeListView = (ListView) findViewById(R.id.attendeeListView);
        controller = new Controller(this);

        setListItemListener();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, AddAttendeeActivity.class);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(controller.LOG_TAG, "getting result");

        if (requestCode == REQ_CODE && resultCode == RESULT_OK && data != null) {
            id = data.getIntExtra("ID", 0);
            Log.i(controller.LOG_TAG, "ID " + id);
            if(controller.existInTempList(id)) {
                Toast.makeText(this,  controller.getNameById(id)+" is already added", Toast.LENGTH_SHORT).show();

            } else{
                controller.addToTempList(id);
                Toast.makeText(this, "Added " + controller.getNameById(id), Toast.LENGTH_SHORT).show();

            }

        }
    }
    public void setListItemListener(){
        attendeeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(controller.LOG_TAG,"Long clicked");
                controller.removeFromTemp(i);
                populateListView();
                return true;
            }
        });
    }

    public void populateListView() {
        ArrayList<String> nameOnlyTempList = controller.nameOnlyTempList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.itemlistview,
                nameOnlyTempList);
        attendeeListView.setAdapter(adapter);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        populateListView();

    }

    public void completeBtnOnClick(View view){
        finish();
    }
}
