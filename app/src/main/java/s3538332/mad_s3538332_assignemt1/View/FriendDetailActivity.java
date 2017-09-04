package s3538332.mad_s3538332_assignemt1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import s3538332.mad_s3538332_assignemt1.R;

public class FriendDetailActivity extends AppCompatActivity {
    EditText nameTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        nameTF = (EditText) findViewById(R.id.nameTF);

        nameTF.setText("Aasfad", TextView.BufferType.EDITABLE);
    }
}
