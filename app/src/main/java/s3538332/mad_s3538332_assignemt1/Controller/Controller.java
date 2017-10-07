package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import s3538332.mad_s3538332_assignemt1.Model.Friend;
import s3538332.mad_s3538332_assignemt1.Model.FriendList;
import s3538332.mad_s3538332_assignemt1.Model.MeetingList;
import s3538332.mad_s3538332_assignemt1.Model.TempAttendeeList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Tam on 4/9/17.
 */

public class Controller {
    public static String LOG_TAG = "MAD_A1";
    FriendList friendList;
    Context context;
    TempAttendeeList tempAList;
    MeetingList meetingList;
    SQLiteDatabase friendDB;
    public Controller(Context context) {
        friendList = FriendList.getInstance();
        this.context = context;
        tempAList = TempAttendeeList.getInstance();
        meetingList = MeetingList.getInstance();

    }


    public boolean addFriend(String name, String email) {
        boolean boo = false;
        if (friendList.addFriend(name, email) == true) {
            Log.i(LOG_TAG, "Added friend name: " + name);
            boo = true;
            Toast.makeText(context, name + " is added to your friend list!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, name + " is already existed in friend list.", Toast.LENGTH_SHORT).show();

        }
        return boo;
    }

    public void removeFriend(int position) {
        if (friendList.size() > position) {
            friendList.remove(position);
        }
    }

    public ArrayList<String> nameOnlyList() {
        ArrayList<String> nameOnlyList = friendList.nameOnlyList();
        return nameOnlyList;
    }

    public String getNameById(int id) {
        String name = "";
        if (friendList.get(id) != null) {
            name = friendList.get(id).getName();
        }
        return name;
    }

    public String getEmailById(int id) {
        String email = "";
        if (friendList.get(id) != null) {
            email = friendList.get(id).getEmail();
        }
        return email;
    }

    public String getBirthDayById(int id) {
        String birthday = "";
        if (friendList.get(id) != null) {
            birthday = friendList.get(id).getBirthday();
        }
        return birthday;
    }

    public void saveFriend(int id, String name, String email, String birthday) {
        Friend friend = friendList.get(id);
        friend.setName(name);
        friend.setEmail(email);
        friend.setBirthday(birthday);
    }

    public void addToTempList(int id) {
        if (friendList.size() > id) {
            Friend friend = friendList.get(id);
            tempAList.add(friend);
            Log.i(LOG_TAG, "Added to TempList: " + friend.getName());
        }
    }

    public ArrayList<String> nameOnlyTempList() {
        ArrayList<String> nameOnlyTempList = tempAList.nameOnlyList();
        return nameOnlyTempList;
    }

    public boolean existInTempList(int id) {
        boolean boo = false;
        if (friendList.size() > id) {
            Friend friend = friendList.get(id);
            String name = friend.getName();
            String email = friend.getEmail();
            for (int i = 0; i < tempAList.size(); i++) {
                Friend temp = tempAList.get(i);
                if (name.equals(temp.getName()) && email.equals(temp.getEmail())) {
                    boo = true;
                    return boo;
                }
            }

        }
        return boo;
    }

    public void removeFromTemp(int id) {
        if (tempAList.size() > id) {
            tempAList.remove(id);
            Log.i(LOG_TAG, id + " is removed from TempList");
        }
    }

    public boolean isTempListEmpty() {
        if (tempAList.isEmpty()) {
            return true;
        }
        return false;
    }

    public void addMeeting(String title, String location, String startTime, String endTime) {
        if (meetingList.addMeeting(title, location, startTime, endTime, tempAList) == true) {
            Toast.makeText(context, "New meeting is created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, " Meetings is already exist", Toast.LENGTH_SHORT).show();

        }
    }

    public ArrayList<String> titleOnlyList() {
        return meetingList.nameOnlyList();

    }

    public void removeMeeting(int id) {
        if (meetingList.size() > id) {
            meetingList.remove(id);
            Log.i(LOG_TAG, "Item removed");
        }
    }
}
