package s3538332.mad_s3538332_assignemt1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.R;

public class MeetingDetailActivity extends AppCompatActivity {
    private TextView dateTV, startTimeTV, endTimeTV;
    private Button locationBtn, saveBtn, discardBtn;
    private EditText titleTF;
    String title,date, startTime, endTime, location, idString;
    Controller controller;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_detail);
        id = Integer.parseInt(getIntent().getExtras().getString("ID"));
        titleTF = (EditText) findViewById(R.id.titleTF);
        dateTV = (TextView) findViewById(R.id.dateTV);
        startTimeTV = (TextView) findViewById(R.id.startTimeTV);
        endTimeTV = (TextView) findViewById(R.id.endTimeTV);
        locationBtn = (Button) findViewById(R.id.locationBtn);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        discardBtn = (Button) findViewById(R.id.discardBtn);
        controller = new Controller(this);
        populateDetail();

    }

    public void populateDetail() {
        title = controller.getTitlebyId(id);
        startTime = controller.getStartTime(id);
        endTime = controller.getEndTime(id);
        idString = controller.getAttendeeIdString(id);

        if (!title.isEmpty()) {
            titleTF.setText(title, TextView.BufferType.EDITABLE);
        }
        if (!startTime.isEmpty()) {
            startTimeTV.setText(startTime);
        }
        if (!endTime.isEmpty()) {
            endTimeTV.setText(endTime);
        }
        Scanner sc = new Scanner(startTime);
        date = sc.next();
        if(!date.isEmpty()){
            dateTV.setText(date);
        }
    }

    public void discardOnClick(View view){
        controller.removeMeeting(id);
        finish();
    }
    public void saveBtnOnClick(View view){
        title = titleTF.getText().toString();
        startTime = startTimeTV.getText().toString();
        endTime = endTimeTV.getText().toString();
        controller.saveMeeting(id, title,startTime,endTime);
        finish();
    }

}
