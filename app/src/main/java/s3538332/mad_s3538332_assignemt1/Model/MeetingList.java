package s3538332.mad_s3538332_assignemt1.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Tam on 4/9/17.
 */

public class MeetingList extends ArrayList<Meeting> {
    private static final String LOG_TAG = "MAD_A1";
    static MeetingList meetingsList = new MeetingList();

    private MeetingList() {

    }

    public static MeetingList getInstance() {
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

    public boolean addMeeting(String title, String location, String startTime, String endTime, ArrayList<Friend> tempFriendList) {
        Boolean boo = false;
        if (meetingsList.size() == 0) {
            Meeting meeting = new Meeting(title, location, startTime, endTime, tempFriendList);
            meetingsList.add(meeting);
            boo = true;
            return boo;
        }
        if (meetingsList.size() > 0) {

            for (int i = 0; i < meetingsList.size(); i++) {
                if (title.equals(meetingsList.get(i).getTitle()) && location.equals(meetingsList.get(i).getLocation())) {
                    Log.i(LOG_TAG, "Meeting is already existed");
                    boo = false;
                    return boo;
                }
            }
            Meeting meeting = new Meeting(title, location, startTime, endTime, tempFriendList);
            meetingsList.add(meeting);
            Log.i(LOG_TAG, title + " is added to meeting list");

            boo = true;
        }
        return boo;
    }

    public String attendeeNameOnlyString(int id) {
        return meetingsList.get(id).attendeNameOnlyString();
    }

    public ArrayList<String> nameOnlyList() {
        ArrayList<String> nameOnlyList = new ArrayList<String>();
        for (int i = 0; i < this.size(); i++) {
            String name = this.get(i).getTitle();
            nameOnlyList.add(name);
        }
        return nameOnlyList;
    }
}
