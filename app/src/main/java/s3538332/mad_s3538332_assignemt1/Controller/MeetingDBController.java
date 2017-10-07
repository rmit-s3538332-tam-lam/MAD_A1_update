package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Scanner;

import s3538332.mad_s3538332_assignemt1.Model.Friend;
import s3538332.mad_s3538332_assignemt1.Model.FriendList;
import s3538332.mad_s3538332_assignemt1.Model.Meeting;
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

    Context context;

    public MeetingDBController(Context context) {
        this.context = context;
        friendList = FriendList.getInstance();
        meetingList = MeetingList.getInstance();
        try {
            meetingDB = context.openOrCreateDatabase("MeetingDB", MODE_PRIVATE, null);
            meetingDB.execSQL("CREATE TABLE IF NOT EXISTS meetingTable (title VARCHAR, location VARCHAR, startTime VARCHAR, endTime VARCHAR, idString VARCHAR)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStart(){
        try {
            Cursor c = meetingDB.rawQuery("SELECT * FROM meetingTable", null);
            int mTitle = c.getColumnIndex("title");
            int mLocation = c.getColumnIndex("location");
            int mStartTime = c.getColumnIndex("startTime");
            int mEndTime = c.getColumnIndex("endTime");
            int mIdString = c.getColumnIndex("idString");
            c.moveToFirst();
            while (c != null) {
                String title = c.getString(mTitle);
                String location = c.getString(mLocation);
                String startTime = c.getString(mStartTime);
                String endTime = c.getString(mEndTime);
                String idString = c.getString(mIdString);
                ArrayList<Friend> friendArray = new ArrayList<Friend>();
                Log.i("SQL","Title: " + title + "\t\tidString: "+idString);
                for(String id: idString.split(" ")){
                    Friend friend = friendList.get(Integer.parseInt(id));
                    friendArray.add(friend);
                }
                meetingList.addMeeting(title,location,startTime,endTime,friendArray);

                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("SQL", "Exception");
        }
    }

    public void onStop(){
        if(meetingList!= null && meetingList.size()>0){
            meetingDB.delete("meetingTable",null,null);
            for(int i = 0; i<meetingList.size();i++){
                String title = meetingList.get(i).getTitle();
                String location = meetingList.get(i).getLocation();
                String startTime = meetingList.get(i).getStartTime();
                String endTime = meetingList.get(i).getEndTime();
                String idString = meetingList.get(i).AttendeeIdString();
                addEntryToMeetingTable(title,location,startTime,endTime,idString);
                Log.i("MeetingDB","Save sucessfully");
                logMeetingTable();
            }
        } else{
            Log.i("MeetingDB","No entry in temporary MeetingList to be saved");
        }
    }
    public void addEntryToMeetingTable(String title, String location, String startTime, String endTime, String idString) {
        try {
            String value = "('" + title + "','" + location + "','" + startTime + "','" + endTime + "','" + idString + "')";
            meetingDB = context.openOrCreateDatabase("MeetingDB", MODE_PRIVATE, null);
            meetingDB.execSQL("CREATE TABLE IF NOT EXISTS meetingTable (title VARCHAR, location VARCHAR, startTime VARCHAR, endTime VARCHAR, idString VARCHAR)");
            meetingDB.execSQL("INSERT INTO meetingTable (title, location, startTime, endTime,idString) VALUES " + value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void logMeetingTable(){
        Log.i("SQL", "-----------------------------------");
        try {
            Cursor c = meetingDB.rawQuery("SELECT * FROM meetingTable", null);
            int mTitle = c.getColumnIndex("title");
            int mLocation = c.getColumnIndex("location");
            int mStartTime = c.getColumnIndex("startTime");
            int mEndTime = c.getColumnIndex("endTime");
            int mIdString = c.getColumnIndex("idString");
            c.moveToFirst();
            while (c != null) {
                Log.i("SQL","title: "+ c.getString(mTitle)+"\tLocation: "+ c.getString(mLocation)+
                "\tStartTime: "+ c.getString(mStartTime)+ "\t idString: "+ c.getString(mIdString));
                c.moveToNext();
            }
        } catch (Exception e) {
            Log.i("SQL", "-----------------------------------");
            e.printStackTrace();
        }
    }

    public void deleteMeetingDB(){
        meetingDB.delete("meetingTable",null,null);
    }
}
