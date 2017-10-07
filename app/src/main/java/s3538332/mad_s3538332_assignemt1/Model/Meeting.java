package s3538332.mad_s3538332_assignemt1.Model;

import java.util.ArrayList;

/**
 * Created by Tam on 4/9/17.
 */

public class Meeting {
    private int id;
    private String title;
    private String startTime;
    private String endTime;
    private ArrayList<Friend> attendeeFriendList;
    private String location;


    public Meeting(String title, String location, String startTime, String endTime, ArrayList<Friend> attendeeFriendList) {
        setTitle(title);
        setLocation(location);
        setStartTime(startTime);
        setEndTime(endTime);
        setAttendeeFriendList(attendeeFriendList);

    }

    public String AttendeeIdString() {
        String str = "";
        for (int i = 0; i < attendeeFriendList.size(); i++) {
            str += Integer.toString(attendeeFriendList.get(i).getId()) + " ";
        }
        return str;
    }

    public String attendeNameOnlyString() {
        String nameString = "";
        if(attendeeFriendList.size()>0) {
            for (Friend friend : attendeeFriendList) {
                nameString += friend.getName() + "   ";
            }
        }
        return nameString;
    }

    public ArrayList<Friend> getAttendeeFriendList() {
        return attendeeFriendList;
    }

    public void setAttendeeFriendList(ArrayList<Friend> attendeeFriendList) {
        this.attendeeFriendList = attendeeFriendList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }


    public String getLocation() {
        return location;
    }
}
