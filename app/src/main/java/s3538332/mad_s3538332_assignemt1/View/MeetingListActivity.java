package s3538332.mad_s3538332_assignemt1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class MeetingListActivity extends AppCompatActivity {
    Button addMeetingBtn;
    ListView meetingListView;
    Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        controller = new Controller(this);
        addMeetingBtn = (Button) findViewById(R.id.addMeetingBtn);
        meetingListView = (ListView) findViewById(R.id.meetingListView);
        addMeetingBtn.setOnClickListener( new popActListener(this,AddMeetingActivity.class));
        populateListView();
        setListItemListener();
    }

    public void populateListView() {
        ArrayList<String> nameOnlyList = controller.titleOnlyList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.itemlistview,
                nameOnlyList);
        meetingListView.setAdapter(adapter);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        populateListView();
    }

    public void setListItemListener(){
        meetingListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                controller.removeMeeting(i);
                populateListView();
                return true;
            }
        });
        meetingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
