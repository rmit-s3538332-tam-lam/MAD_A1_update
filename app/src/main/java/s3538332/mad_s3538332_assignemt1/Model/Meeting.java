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
    private ArrayList<Friend> attendeeList;
    private String location;

    public Meeting(){

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

    public void setAttendeeList(ArrayList<Friend> attendeeList) {
        this.attendeeList = attendeeList;
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

    public ArrayList<Friend> getAttendeeList() {
        return attendeeList;
    }

    public String getLocation() {
        return location;
    }
}
