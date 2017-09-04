package s3538332.mad_s3538332_assignemt1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import s3538332.mad_s3538332_assignemt1.R;

public class AddFriendActivity extends AppCompatActivity {
    ListView friendListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        friendListView = (ListView) findViewById(R.id.friendListView);
    }
}
