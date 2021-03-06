package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import s3538332.mad_s3538332_assignemt1.Model.Friend;
import s3538332.mad_s3538332_assignemt1.Model.FriendList;
import s3538332.mad_s3538332_assignemt1.Model.Meeting;
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
    public String getFriendLocationById(int id){
        String location ="";
        if(friendList.get(id).getLocation()!=null){
            return friendList.get(id).getLocation();
        }
        return location;
    }

    public void saveMeeting(int id, String title, String startTime, String endTime) {
        Meeting meeting = meetingList.get(id);
        meeting.setTitle(title);
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);

    }


    public void saveFriend(int id, String name, String email, String birthday,String location) {
        Friend friend = friendList.get(id);
        friend.setName(name);
        friend.setEmail(email);
        friend.setBirthday(birthday);
        friend.setLocation(location);
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
        ArrayList<Friend> tempFriendList = new ArrayList<Friend>();
        if (tempAList != null && tempAList.size() > 0) {
            for (int i = 0; i < tempAList.size(); i++) {
                tempFriendList.add(tempAList.get(i));
            }
        }
        tempAList.clear();
        if (meetingList.addMeeting(title, location, startTime, endTime, tempFriendList) == true) {
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

    public String getTitlebyId(int id) {
        String title = "";
        if (meetingList.get(id) != null) {
            title = meetingList.get(id).getTitle();
        }
        return title;
    }

    public String getStartTime(int id) {
        String startTime = "";
        if (meetingList.get(id) != null) {
            startTime = meetingList.get(id).getStartTime();
        }
        return startTime;
    }

    public String getEndTime(int id) {
        String endTime = "";
        if (meetingList.get(id) != null) {
            endTime = meetingList.get(id).getEndTime();
        }
        return endTime;
    }

    public String getAttendeeIdString(int id) {
        String idString = "";
        if (meetingList.get(id) != null) {
            idString = meetingList.get(id).AttendeeIdString();
        }
        return idString;
    }

    public String attendeeOnlyString(int id) {
        return meetingList.attendeeNameOnlyString(id);

    }

    public void emptyAttendeeList() {
        tempAList.clear();
    }

    //Create calendar for alarm
    public Calendar meetingCalendar(String startTime) {
        String[] splitStr = startTime.split(" ");
        String dateStr = splitStr[0];
        String timeStr = splitStr[1];

        //Time split
        String[] timeSplit = timeStr.split(":");
        Integer hour = Integer.parseInt(timeSplit[0]);
        Integer minute = Integer.parseInt(timeSplit[1]);

        //Date split
        String[] dateSplit = dateStr.split("/");
        Integer date = Integer.parseInt(dateSplit[0]);
        Integer month = Integer.parseInt(dateSplit[1]);
        Integer year = Integer.parseInt(dateSplit[2]);
        return meetingCalendar(date, month, year, hour, minute);
    }

    public Calendar meetingCalendar(int day, int month, int year, int startHour, int startMinute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, startHour);
        cal.set(Calendar.MINUTE, startMinute);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }
}




