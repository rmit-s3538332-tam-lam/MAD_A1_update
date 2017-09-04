package s3538332.mad_s3538332_assignemt1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.R;

public class FriendDetailActivity extends AppCompatActivity {
    private EditText nameTF, emailTF;
    private Button saveBtn, deleteBtn;
    private TextView birthdayText;
    private Intent intent;
    private Controller controller;
    static String LOG_TAG = "MAD_A1";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new Controller(this);
        setContentView(R.layout.activity_friend_detail);
        nameTF = (EditText) findViewById(R.id.nameTF);
        emailTF = (EditText) findViewById(R.id.emailTF);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        birthdayText = (TextView) findViewById(R.id.birthdayText);
        intent = getIntent();
        String idString = intent.getExtras().getString("ID");
        if(!idString.isEmpty()) {
            Log.i(LOG_TAG, "Received ID: " + idString);
            id = Integer.parseInt(idString);
        }
        populateDetail(id);

    }

    public void populateDetail(int id) {
        String name = controller.getNameById(id);
        String email = controller.getEmailById(id);
        String birthDay = controller.getBirthDayById(id);
        if (!name.isEmpty()) {
            nameTF.setText(name, TextView.BufferType.EDITABLE);
        }
        if (!email.isEmpty()) {
            emailTF.setText(email, TextView.BufferType.EDITABLE);
        }
        if (!birthDay.isEmpty()) {
            birthdayText.setText(birthDay);
        }
    }
}
