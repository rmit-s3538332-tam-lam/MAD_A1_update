package s3538332.mad_s3538332_assignemt1.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class AddMeetingActivity extends AppCompatActivity {
    EditText titleTF, locationTF;
    Button addAnttendeeBtn, createBtn, discardBtn;
    Controller controller;
    TextView startTimeTV, endTimeTV,dateTV;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        controller = new Controller(this);
        titleTF = (EditText) findViewById(R.id.titleTF);
        locationTF = (EditText) findViewById(R.id.locationTF);
        startTimeTV = (TextView) findViewById(R.id.endTimeTV);
        endTimeTV = (TextView) findViewById(R.id.locationTF);
        dateTV = (TextView) findViewById(R.id.dateTV);

        addAnttendeeBtn = (Button) findViewById(R.id.addAttendeeBtn);
        createBtn = (Button) findViewById(R.id.createBtn);
        discardBtn = (Button) findViewById(R.id.discardBtn);

        addAnttendeeBtn.setOnClickListener(new popActListener(this,ViewAnttendeeActivity.class));


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);




    }
    public void dateTVOnClick(View view) {
        showDialog(1);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };
    private void showDate(int year, int month, int day) {
        dateTV.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    public void discardBtnOnClick(View view){
        finish();
    }

}
