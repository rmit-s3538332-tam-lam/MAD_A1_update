package s3538332.mad_s3538332_assignemt1.Model;

import java.util.ArrayList;

/**
 * Created by Tam on 4/9/17.
 */

public class MeetingList extends ArrayList<Meeting> {
    static MeetingList meetingsList = new MeetingList();
    private MeetingList(){

    }

    public static MeetingList getInstance(){
        return meetingsList;
    }

}
