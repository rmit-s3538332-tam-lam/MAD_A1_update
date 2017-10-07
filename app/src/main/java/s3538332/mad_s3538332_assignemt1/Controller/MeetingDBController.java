package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Scanner;

import s3538332.mad_s3538332_assignemt1.Model.Friend;
import s3538332.mad_s3538332_assignemt1.Model.FriendList;
import s3538332.mad_s3538332_assignemt1.Model.MeetingList;
import s3538332.mad_s3538332_assignemt1.Model.TempAttendeeList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tamlam on 7/10/17.
 */

public class MeetingDBController {
    SQLiteDatabase meetingDB;
    MeetingList meetingList;
    FriendList friendList;
    TempAttendeeList tempAList;
    Context context;

    public MeetingDBController(Context context) {
        this.context = context;
        this.friendList = FriendList.getInstance();
        this.tempAList = TempAttendeeList.getInstance();
        meetingList = MeetingList.getInstance();
        try {
            meetingDB = context.openOrCreateDatabase("MeetingDB", MODE_PRIVATE, null);
            meetingDB.execSQL("CREATE TABLE IF NOT EXISTS meetingTable (title VARCHAR, location VARCHAR, startTime VARCHAR, endTime VARCHAR, attendeeList VARCHAR)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStart() {
        //onStart import meetings from database to temporary meetingList.
        Log.i("SQL", "onStart: Update temp MeetingList");

        try {
            Cursor c = meetingDB.rawQuery("SELECT * FROM meetingTable", null);
            int mTitle = c.getColumnIndex("title");
            int mLocation = c.getColumnIndex("location");
            int mStartTime = c.getColumnIndex("startTime");
            int mEndTime = c.getColumnIndex("endTime");
            int mAttendeeList = c.getColumnIndex("attendeeList");
            c.moveToFirst();
            while (c != null) {
                String title = c.getString(mTitle);
                String location = c.getString(mLocation);
                String startTime = c.getString(mStartTime);
                String endTime = c.getString(mEndTime);

                String atteendeeList = c.getString(mAttendeeList);
                //read id string and add friend base on id to attendee list
                Scanner sc = new Scanner(atteendeeList);
                while (sc.hasNext()) {
                    addToTempList(sc.nextInt());
                }

                meetingList.addMeeting(title, location, startTime, endTime, tempAList);
                tempAList.clear();
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("SQL", "Exception");
        }

    }

    public void onStop() {
        //onStop delete current database, save temp meetingList to database.
        Log.i("SQL", "onStop: Saving meetingList to database....");

        if (meetingList != null && meetingList.size() > 0) {
            deleteMeetingTable();
            for (int i = 0; i < meetingList.size(); i++) {
                String title = meetingList.get(i).getTitle();
                String location = meetingList.get(i).getLocation();
                String startTime = meetingList.get(i).getStartTime();
                String endTime = meetingList.get(i).getEndTime();
                String idString = tempAList.attendeeIdString();
                addEntryToMeetingDB(title,location,startTime,endTime,idString);
            }
            Log.i("SQL", "Saved successfully.");
        } else {
            Log.i("SQL", "No entry in temporary friendlist to save");
        }

    }

    public void deleteMeetingTable() {
        meetingDB.delete("meetingTable", null, null);
        Log.i("SQL", "meetingTable is deleted");
    }

    public void addToTempList(int id) {
        if (friendList.size() > id) {
            Friend friend = friendList.get(id);
            tempAList.add(friend);
            Log.i("SQL", "Added to TempList: " + friend.getName());
        }
    }

    public void addEntryToMeetingDB(String title, String location, String startTime,
                                    String endTime, String attendeeIdString) {

        String value = "('" + title + "','" + location + "','" + startTime + "','" + endTime
                +"','"+ attendeeIdString+ "')";
        try {
            meetingDB.execSQL("CREATE TABLE IF NOT EXISTS MeetingDB (title VARCHAR, location VARCHAR, startTime VARCHAR, endTime VARCHAR, attendeeList VARCHAR)");
            meetingDB = context.openOrCreateDatabase("meetingTable", MODE_PRIVATE, null);
            meetingDB.execSQL("INSERT INTO friendTable (title,location,startTime,endTime,attendeeList) VALUES " + value);
            Log.i("SQL","Added meeting title: "+ title);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void logMeetingDB(){
        Log.i("SQL", "-----------------------------------");
        try {
            Cursor c = meetingDB.rawQuery("SELECT * FROM meetingTable", null);
            int mTitle = c.getColumnIndex("title");
            int mlocation = c.getColumnIndex("location");
            int mStartTime= c.getColumnIndex("startTime");
            int mEndTime = c.getColumnIndex("endTime");
            int mAttendeeList = c.getColumnIndex("attendeeList");
            c.moveToFirst();
            while (c != null) {
                Log.i("SQL", "title: " +c.getString(mTitle)+ "\tattendeeList: "+c.getString(mAttendeeList));
                c.moveToNext();
            }
            Log.i("SQL", "-----------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
