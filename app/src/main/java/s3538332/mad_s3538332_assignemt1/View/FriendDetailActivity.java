package s3538332.mad_s3538332_assignemt1.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.R;

public class FriendDetailActivity extends AppCompatActivity {
    private EditText nameTF, emailTF;
    private Button saveBtn, deleteBtn;
    private TextView birthdayText;
    private Intent intent;
    private Controller controller;
    static String LOG_TAG = "MAD_A1";
    String name, email, birthday;
    int id;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        controller = new Controller(this);
        nameTF = (EditText) findViewById(R.id.nameTF);
        emailTF = (EditText) findViewById(R.id.emailTF);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        birthdayText = (TextView) findViewById(R.id.birthdayText);
        intent = getIntent();
        populateDetail();

    }

    public void populateDetail() {
        String idString = intent.getExtras().getString("ID");
        if (!idString.isEmpty()) {
            Log.i(LOG_TAG, "Received ID: " + idString);
            id = Integer.parseInt(idString);
        }
        name = controller.getNameById(id);
        email = controller.getEmailById(id);
        birthday = controller.getBirthDayById(id);
        if (!name.isEmpty()) {
            nameTF.setText(name, TextView.BufferType.EDITABLE);
        }
        if (!email.isEmpty()) {
            emailTF.setText(email, TextView.BufferType.EDITABLE);
        }
        if (!birthday.isEmpty()) {
            birthdayText.setText(birthday);
        }
    }

    public void deleteBtnOnClick(View view) {
        controller.removeFriend(id);
        finish();
    }

    public void saveBtnOnClick(View view) {
        name = nameTF.getText().toString();
        email = emailTF.getText().toString();
        birthday = birthdayText.getText().toString();

        controller.saveFriend(id, name, email, birthday);
        finish();
    }

    public void birthdayTextOnClick(View view) {
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
        birthdayText.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

}
