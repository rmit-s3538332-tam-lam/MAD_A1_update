package s3538332.mad_s3538332_assignemt1.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tamlam on 7/10/17.
 */

public class DatabaseController {
    SQLiteDatabase friendDB;
    Context context;

    public DatabaseController (Context context) {
        this.context = context;
        try {
            friendDB = context.openOrCreateDatabase("FriendDB", MODE_PRIVATE, null);
            friendDB.execSQL("CREATE TABLE IF NOT EXISTS friendTable (name VARCHAR, email VARCHAR, birthday VARCHAR, location VARCHAR)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFriendTable(){
        friendDB.delete("friendTable",null,null);
        Log.i("SQL","friendTable is deleted");
    }
    public void logFriendTable() {
        Log.i("SQL","-----------------------------------");
        try {
            Cursor c = friendDB.rawQuery("SELECT * FROM friendTable", null);
            int fName = c.getColumnIndex("name");
            int fEmail = c.getColumnIndex("email");
            c.moveToFirst();
            while (c != null) {
                Log.i("SQL", "name: "+ c.getString(fName)+"     email: "+c.getString(fEmail));
                c.moveToNext();
            }
            Log.i("SQL","-----------------------------------");
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
            Log.i("SQL","add a friend name "+ name + " to friendDB");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


