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

    public int getTotalMessages(){
        int count = 0;
        for (User user: usersList) {
            count+=mailStore.getMail(user.getUsername()).length;
        }
        return count;
    }

    public float getAverageUserMessages(){
        return getTotalMessages()/usersList.size();
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }
}
