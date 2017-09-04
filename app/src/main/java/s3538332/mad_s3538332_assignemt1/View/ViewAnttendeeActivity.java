package s3538332.mad_s3538332_assignemt1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import s3538332.mad_s3538332_assignemt1.Controller.Controller;
import s3538332.mad_s3538332_assignemt1.Controller.popActListener;
import s3538332.mad_s3538332_assignemt1.R;

public class ViewAnttendeeActivity extends AppCompatActivity {
    Button addAttendeeBtn;
    ListView attendeeListView;
    int REQ_CODE = 1;
    Controller controller;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_anttendee);
        addAttendeeBtn = (Button) findViewById(R.id.addAttendeeBtn);
        attendeeListView = (ListView) findViewById(R.id.attendeeListView);
        controller = new Controller(this);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, AddAttendeeActivity.class);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(controller.LOG_TAG, "getting result");

        if (requestCode == REQ_CODE && resultCode == RESULT_OK && data != null) {
            id = data.getIntExtra("ID", 0);
            Log.i(controller.LOG_TAG, "ID " + id);
            controller.addToTempList(id);

            Toast.makeText(this, "ID: " + id, Toast.LENGTH_SHORT).show();
        }
    }
}
