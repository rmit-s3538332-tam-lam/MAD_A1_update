package s3538332.mad_s3538332_assignemt1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class MeetingListActivity extends AppCompatActivity {
    Button addMeetingBtn;
    ListView meetingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        addMeetingBtn = (Button) findViewById(R.id.addMeetingBtn);
        meetingListView = (ListView) findViewById(R.id.meetingListView);
        addMeetingBtn.setOnClickListener( new popActListener(this,AddMeetingActivity.class));

    }



}
