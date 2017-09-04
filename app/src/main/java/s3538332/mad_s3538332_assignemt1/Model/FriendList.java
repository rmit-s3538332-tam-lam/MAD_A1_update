package s3538332.mad_s3538332_assignemt1.Model;

import java.util.ArrayList;

/**
 * Created by Tam on 4/9/17.
 */

public class FriendList extends ArrayList<Friend> {

    static FriendList friendList =  new FriendList();
    private FriendList(){

    }
    public static FriendList getInstance(){
        return friendList;
    }

    @Override
    public boolean add(Friend friend) {
        boolean boo = false ;
        if(super.add(friend)){
            friend.setId(friendList.indexOf(friend));
            boo = true;
        }
        return boo;
    }
}
