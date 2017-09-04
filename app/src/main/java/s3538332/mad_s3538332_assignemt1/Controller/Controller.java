package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.util.Log;

import s3538332.mad_s3538332_assignemt1.Model.Friend;
import s3538332.mad_s3538332_assignemt1.Model.FriendList;

/**
 * Created by Tam on 4/9/17.
 */

public class Controller {
    public static String LOG_TAG = "MAD_A1";
    FriendList friendList;
    Context context;
    public Controller (Context context){
        friendList = FriendList.getInstance();
        this.context = context;
    }

    public void addFriend(String name, String email){
        Friend friend = new Friend(name, email);
        friendList.add(friend);
        Log.i(LOG_TAG,"Added friend name: "+ name);
    }
}
