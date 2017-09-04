package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import s3538332.mad_s3538332_assignemt1.Model.FriendList;
import s3538332.mad_s3538332_assignemt1.View.FriendListActivity;

/**
 * Created by Tam on 4/9/17.
 */

public class longClickListener implements AdapterView.OnItemLongClickListener {
    private FriendList friendList;
    private Context context;

    Bundle extra;
    private static String LOG_TAG = "MAD_A1";
    public longClickListener(Context context, Intent intent){
        friendList = FriendList.getInstance();
        this.context = context;

    }
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View viewClicked, int position, long id) {
        Log.i(LOG_TAG,"Long clicked");

        Toast.makeText(context,"Long clicked",Toast.LENGTH_SHORT).show();
        return true;
    }
}
