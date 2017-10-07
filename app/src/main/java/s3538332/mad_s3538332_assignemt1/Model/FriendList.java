package s3538332.mad_s3538332_assignemt1.Model;

import android.util.Log;

import java.util.ArrayList;

import s3538332.mad_s3538332_assignemt1.Controller.DatabaseController;

/**
 * Created by Tam on 4/9/17.
 */

public class FriendList extends ArrayList<Friend> {
    static String LOG_TAG = "MAD_A1";
    static FriendList friendList = new FriendList();
    DatabaseController dbController;

    private FriendList() {
    }

    public static FriendList getInstance() {

        return friendList;
    }

    @Override
    public boolean add(Friend friend) {
        boolean boo = false;
        if (super.add(friend)) {
            friend.setId(friendList.indexOf(friend));
            boo = true;
        }
        return boo;
    }

    public boolean addFriend(String name, String email) {
        Boolean boo = false;
        if (friendList.size() == 0) {
            Friend f = new Friend(name, email);
            friendList.add(f);
            boo = true;
            return boo;
        }
        if (friendList.size() > 0) {
            for (int i = 0; i < friendList.size(); i++) {
                if (name.equals(friendList.get(i).getName()) && email.equals(friendList.get(i).getEmail())) {
                    Log.i(LOG_TAG, "Friend already exist");
                    boo = false;
                    return boo;
                }
            }
            Log.i(LOG_TAG, "Added new friend");
            Friend newFriend = new Friend(name, email);
            friendList.add(newFriend);
            boo = true;
        }
        return boo;
    }

    public ArrayList<String> nameOnlyList() {
        ArrayList<String> nameOnlyList = new ArrayList<String>();
        for (int i = 0; i < this.size(); i++) {
            String name = this.get(i).getName();
            nameOnlyList.add(name);
        }
        return nameOnlyList;
    }

}
