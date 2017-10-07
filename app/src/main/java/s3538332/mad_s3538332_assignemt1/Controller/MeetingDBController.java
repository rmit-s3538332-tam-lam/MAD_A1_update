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




}
