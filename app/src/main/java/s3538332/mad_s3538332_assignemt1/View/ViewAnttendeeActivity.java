package s3538332.mad_s3538332_assignemt1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class ViewAnttendeeActivity extends AppCompatActivity {
    Button addAttendeeBtn;
    ListView attendeeListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_anttendee);
        addAttendeeBtn = (Button) findViewById(R.id.addAttendeeBtn);
        attendeeListView = (ListView) findViewById(R.id.attendeeListView);

        addAttendeeBtn.setOnClickListener(new popActListener(this, AddAttendeeActivity.class));
    }
}
