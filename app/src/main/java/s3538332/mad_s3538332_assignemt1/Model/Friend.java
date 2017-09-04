package s3538332.mad_s3538332_assignemt1.Model;

/**
 * Created by Tam on 4/9/17.
 */

public class Friend {
    private String name;
    private String email;
    private String birthday;
    private int id;

    public Friend(String name, String email, String birthday){
        setName(name);
        setEmail(email);
        setBirthday(birthday);
    }

    public Friend(String name, String email){
        setName(name);
        setEmail(email);
        setBirthday("");
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setId(int id) {
        this.id = id;
    }
}
