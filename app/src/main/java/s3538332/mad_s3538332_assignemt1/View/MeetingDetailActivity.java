package s3538332.mad_s3538332_assignemt1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import s3538332.mad_s3538332_assignemt1.R;

public class MeetingDetailActivity extends AppCompatActivity {
    private TextView dateTV,startTimeTV,endTimeTV;
    private Button locationBtn,saveBtn,discardBtn;
    private EditText titleTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_detail);
        titleTF = (EditText) findViewById(R.id.titleTF);
        dateTV = (TextView) findViewById(R.id.dateTV);
        startTimeTV = (TextView) findViewById(R.id.startTimeTV);
        endTimeTV = (TextView) findViewById(R.id.endTimeTV);
        locationBtn = (Button) findViewById(R.id.locationBtn);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        discardBtn = (Button) findViewById(R.id.discardBtn);

    }
}
