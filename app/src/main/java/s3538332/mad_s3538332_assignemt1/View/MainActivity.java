package s3538332.mad_s3538332_assignemt1.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.renderscript.Double2;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import s3538332.mad_s3538332_assignemt1.Controller.FriendDBController;
import s3538332.mad_s3538332_assignemt1.Controller.MeetingDBController;
import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class MainActivity extends AppCompatActivity {
    Button friendsBtn, meetingsBtn, yourLocationBtn;
    MeetingDBController meetingDBController;
    FriendDBController friendDBController;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meetingDBController = new MeetingDBController(this);
        friendDBController = new FriendDBController(this);

        yourLocationBtn = (Button) findViewById(R.id.yourLocationBtn);
        friendsBtn = (Button) findViewById(R.id.friendsBtn);
        meetingsBtn = (Button) findViewById(R.id.meetingsBtn);

        popActListener friendsBtnListener = new popActListener(this, FriendListActivity.class);
        popActListener meetingsBtnListener = new popActListener(this, MeetingListActivity.class);
        popActListener yourLocationBtnListener = new popActListener(this, YourLocationActivity.class);
        friendsBtn.setOnClickListener(friendsBtnListener);
        meetingsBtn.setOnClickListener(meetingsBtnListener);
        yourLocationBtn.setOnClickListener(yourLocationBtnListener);


    }

    @Override
    protected void onStart() {
        super.onStart();
        friendDBController.onStart();
        meetingDBController.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }


}
