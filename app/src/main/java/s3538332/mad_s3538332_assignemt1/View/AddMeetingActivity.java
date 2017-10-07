package s3538332.mad_s3538332_assignemt1.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.Controller.MeetingDBController;
import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class AddMeetingActivity extends AppCompatActivity {
    static int START_TIME_ID = 2, END_TIME_ID = 3;
    MeetingDBController meetingDBController;
    EditText titleTF, locationTF;
    Button addAnttendeeBtn, createBtn, discardBtn;
    Controller controller;
    TextView startTimeTV, endTimeTV, dateTV;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private int startHour, startMinutes, endHour, endMinutes;
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };
    private TimePickerDialog.OnTimeSetListener startTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            startHour = hour;
            endHour = minute;
            setStartTimeText(hour, minute);
        }
    };
    private TimePickerDialog.OnTimeSetListener endTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            endHour = i;
            endMinutes = i1;
            setEndTimeText(endHour, endMinutes);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        meetingDBController = new MeetingDBController(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        controller = new Controller(this);
        titleTF = (EditText) findViewById(R.id.titleTF);
        locationTF = (EditText) findViewById(R.id.locationTF);
        startTimeTV = (TextView) findViewById(R.id.startTimeTV);
        endTimeTV = (TextView) findViewById(R.id.endTimeTV);
        dateTV = (TextView) findViewById(R.id.dateTV);

        addAnttendeeBtn = (Button) findViewById(R.id.addAttendeeBtn);
        createBtn = (Button) findViewById(R.id.createBtn);
        discardBtn = (Button) findViewById(R.id.discardBtn);

        addAnttendeeBtn.setOnClickListener(new popActListener(this, ViewAnttendeeActivity.class));


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


    }

    public void dateTVOnClick(View view) {
        showDialog(1);
    }

    public void startTimeTVOnClick(View view) {
        showDialog(START_TIME_ID);
    }

    public void endTimeTVOnClick(View view) {
        showDialog(END_TIME_ID);
    }

    public void createBtnOnClick(View view) {

        String title = titleTF.getText().toString();
        String location = locationTF.getText().toString();

        String defaultStartText = getResources().getString(R.string.startTime);
        String defaultEndText = getResources().getString(R.string.endTime);
        String defaultDateText = getResources().getString(R.string.date);
        String stText = startTimeTV.getText().toString();
        String etText = endTimeTV.getText().toString();
        String dText = dateTV.getText().toString();

        if (title.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please enter title and location", Toast.LENGTH_SHORT).show();
            return;
        }

        if (stText.equals(defaultStartText) || etText.equals(defaultEndText)
                || dText.equals(defaultDateText)) {
            Toast.makeText(this, "Please select date and time", Toast.LENGTH_SHORT).show();
            return;
        }
        if (controller.isTempListEmpty()) {
            Toast.makeText(this, "Add at least one attendee", Toast.LENGTH_SHORT).show();
            return;
        }
        String date = day + "/" + month + "/" + year;
        String startTime = date + "  " + startHour + ":" + startMinutes;
        String endTime = date + "  " + endHour + ":" + endMinutes;
        controller.addMeeting(title,location,startTime,endTime);
        finish();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return new DatePickerDialog(this,
                        myDateListener, year, month, day);
            case 2:
                return new TimePickerDialog(this, startTimePickerListener, startHour, startMinutes, false);
            case 3:
                return new TimePickerDialog(this, endTimePickerListener, endHour, endMinutes, false);

        }

        return null;
    }

    private void setEndTimeText(int hour, int minute) {
        endTimeTV.setText(new StringBuilder().append(hour).append(":").append(minute));
    }

    private void setStartTimeText(int hour, int minute) {
        startTimeTV.setText(new StringBuilder().append(hour).append(":").append(minute));
    }

    private void showDate(int year, int month, int day) {
        dateTV.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void discardBtnOnClick(View view) {
        controller.emptyAttendeeList();
        finish();
    }

}
