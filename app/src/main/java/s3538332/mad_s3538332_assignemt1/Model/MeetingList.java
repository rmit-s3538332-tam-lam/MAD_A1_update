package s3538332.mad_s3538332_assignemt1.Model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Tam on 4/9/17.
 */

public class MeetingList extends ArrayList<Meeting> {
    private static final String LOG_TAG = "MAD_A1";
    static MeetingList meetingsList = new MeetingList();
    private MeetingList(){

    }

    public static MeetingList getInstance(){
        return meetingsList;
    }

    @Override
    public boolean add(Meeting meeting) {
        boolean boo = false;
        if (super.add(meeting)) {
            meeting.setId(meetingsList.indexOf(meeting));
            boo = true;
        }
        return boo;

    }

    public boolean addMeeting(String title, String location,String startTime, String endTime) {
        Boolean boo = false;
        if (meetingsList.size() == 0) {
            Meeting meeting = new Meeting(title, location,startTime,endTime);
            meetingsList.add(meeting);
            boo = true;
            return boo;
        }
        if (meetingsList.size() > 0) {
            for (int i = 0; i < meetingsList.size(); i++) {
                if(title.equals(meetingsList.get(i).getTitle()) && location.equals(meetingsList.get(i).getLocation())){
                    Log.i(LOG_TAG,"Meeting is already existed");
                    boo = false;
                    return boo;
                }
            }
            Log.i(LOG_TAG, "Added new meeting");
            Meeting meeting = new Meeting(title,location,startTime,endTime);
            meetingsList.add(meeting);
            boo = true;
        }
        return boo;
    }
}
