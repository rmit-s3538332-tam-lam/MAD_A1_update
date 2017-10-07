package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import s3538332.mad_s3538332_assignemt1.Model.FriendList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tamlam on 7/10/17.
 */

public class DatabaseController {
    SQLiteDatabase friendDB;
    FriendList friendList;
    Context context;

    public DatabaseController(Context context) {
        friendList = FriendList.getInstance();
        this.context = context;
        try {
            friendDB = context.openOrCreateDatabase("FriendDB", MODE_PRIVATE, null);
            friendDB.execSQL("CREATE TABLE IF NOT EXISTS friendTable (name VARCHAR, email VARCHAR, birthday VARCHAR, location VARCHAR)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStart() {
        //onStart import friends from database to temporary friendList object.
        Log.i("SQL", "onStart: Update temp FriendList");

        try {
            Cursor c = friendDB.rawQuery("SELECT * FROM friendTable", null);
            int fName = c.getColumnIndex("name");
            int fEmail = c.getColumnIndex("email");
            int fBirthday = c.getColumnIndex("birthday");
            int fLocation = c.getColumnIndex("location");
            c.moveToFirst();
            while (c != null) {
                Log.i("SQL", "name: " + c.getString(fName) + "     email: " + c.getString(fEmail));
                String newName = c.getString(fName);
                String newEmail = c.getString(fEmail);
                String newBirthday = c.getString(fBirthday);
                String newLocation = c.getString(fLocation);
                friendList.addFriend(newName, newEmail, newBirthday, newLocation);
                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("SQL", "Exception");
        }

    }

    public void onStop() {
        //onStop delete current database, save new friendlist to database.
        Log.i("SQL", "onStop: Saving friendList to database....");

        if (friendList != null && friendList.size() > 0) {
            deleteFriendTable();
            for (int i = 0; i < friendList.size(); i++) {
                String name = friendList.get(i).getName();
                String email = friendList.get(i).getEmail();
                String birthday = friendList.get(i).getBirthday();
                String location = friendList.get(i).getLocation();
                addEntryToFriendDB(name, email, birthday, location);
            }
            Log.i("SQL", "Saved successfully.");
        } else {
            Log.i("SQL", "No entry in temporary friendlist to save");
        }

    }

    public void deleteFriendTable() {
        friendDB.delete("friendTable", null, null);
        Log.i("SQL", "friendTable is deleted");
    }

    public void logFriendTable() {
        Log.i("SQL", "-----------------------------------");
        try {
            Cursor c = friendDB.rawQuery("SELECT * FROM friendTable", null);
            int fName = c.getColumnIndex("name");
            int fEmail = c.getColumnIndex("email");
            c.moveToFirst();
            while (c != null) {
                Log.i("SQL", "name: " + c.getString(fName) + "     email: " + c.getString(fEmail));
                c.moveToNext();
            }
            Log.i("SQL", "-----------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEntryToFriendDB(String name, String email, String birthday, String location) {
        String value = "('" + name + "','" + email + "','" + birthday + "','" + location + "')";
        try {
            friendDB.execSQL("CREATE TABLE IF NOT EXISTS friendTable (name VARCHAR, email VARCHAR, birthday VARCHAR, location VARCHAR)");
            friendDB = context.openOrCreateDatabase("FriendDB", MODE_PRIVATE, null);
            friendDB.execSQL("INSERT INTO friendTable (name,email,birthday,location) VALUES " + value);
            Log.i("SQL", "add a friend name " + name + " to friendDB");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


