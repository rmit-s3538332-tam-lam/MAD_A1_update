package s3538332.mad_s3538332_assignemt1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class AddMeetingActivity extends AppCompatActivity {
    EditText titleTF, locationTF;
    Button addAnttendeeBtn, createBtn, discardBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        titleTF = (EditText) findViewById(R.id.titleTF);
        locationTF = (EditText) findViewById(R.id.locationTF);

        addAnttendeeBtn = (Button) findViewById(R.id.addAttendeeBtn);
        createBtn = (Button) findViewById(R.id.createBtn);
        discardBtn = (Button) findViewById(R.id.discardBtn);

        addAnttendeeBtn.setOnClickListener(new popActListener(this,ViewAnttendeeActivity.class));

    }

    public void discardBtnOnClick(View view){
        finish();
    }

}
