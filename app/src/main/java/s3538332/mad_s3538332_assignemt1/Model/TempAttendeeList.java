package s3538332.mad_s3538332_assignemt1.Model;

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
}
