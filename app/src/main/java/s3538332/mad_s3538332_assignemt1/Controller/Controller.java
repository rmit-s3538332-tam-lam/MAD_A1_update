package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

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
        if(friendList.addFriend(name,email) == true){
            Log.i(LOG_TAG,"Added friend name: "+ name);
            Toast.makeText(context,name+ " is added to your friend list!",Toast.LENGTH_SHORT).show();
        }  else{
            Toast.makeText(context,name+ " is already existed in friend list.",Toast.LENGTH_SHORT).show();

        }
    }
    public void removeFriend(int position){
        friendList.remove(position);
    }
    public ArrayList<String> nameOnlyList(){
        ArrayList<String> nameOnlyList = friendList.nameOnlyList();
        return nameOnlyList;
    }
}
