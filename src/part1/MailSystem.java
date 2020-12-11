package part1;

import java.util.ArrayList;

public class MailSystem {
    MailStore mailStore;
    ArrayList<User> usersList;

    public MailBox addUser(User user){
        if (!usersList.contains(user)){
            usersList.add(user);
        }
        return new MailBox(mailStore, user);
    }

}
