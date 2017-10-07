package s3538332.mad_s3538332_assignemt1.Model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Tam on 5/9/17.
 */

public class TempAttendeeList extends ArrayList<Friend> {
    public static TempAttendeeList tempAList = new TempAttendeeList();
    private TempAttendeeList(){

    }

    public static TempAttendeeList getInstance(){
        return tempAList;
    }

    public ArrayList<String> nameOnlyList() {
        ArrayList<String> nameOnlyList = new ArrayList<String>();
        for (int i = 0; i < this.size(); i++) {
            String name = this.get(i).getName();
            nameOnlyList.add(name);
        }
        return nameOnlyList;
    }

    public String attendeeIdString(){
        String attendeeIdString = "";
        if(tempAList!= null && tempAList.size()>0) {
            for (int i = 0; i < tempAList.size(); i++) {
                String id = Integer.toString(tempAList.get(i).getId());
                attendeeIdString += id + " ";
            }
        }
        return attendeeIdString;
    }

    @Override
    public void clear() {
        super.clear();
        Log.i("SQL:","Deleting tempAlist...");
    }
}
