package part1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User{
    private String username, name;
    private Date birthdate;
    public static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public User(String username, String name, Date birthdate) {
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}