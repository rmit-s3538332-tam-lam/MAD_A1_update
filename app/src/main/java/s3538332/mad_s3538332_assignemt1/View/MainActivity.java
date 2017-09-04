package s3538332.mad_s3538332_assignemt1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class MainActivity extends AppCompatActivity {
    Button friendsBtn, meetingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friendsBtn = (Button) findViewById(R.id.friendsBtn);
        meetingsBtn = (Button) findViewById(R.id.meetingsBtn);

        popActListener friendsBtnListener = new popActListener(this, FriendListActivity.class);
        popActListener meetingsBtnListener = new popActListener(this,MeetingListActivity.class);
        friendsBtn.setOnClickListener(friendsBtnListener);
        meetingsBtn.setOnClickListener(meetingsBtnListener);

    }
}
