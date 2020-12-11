package part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public ArrayList<Message> getAllMessages(){
        ArrayList<Message> messages = new ArrayList<>();
        for (User user : usersList) {
            Collections.addAll(messages, mailStore.getMail(user.getUsername()));
        }
        return messages;
    }

    public Map<String, List<Message>> groupBySubject(){
        return getAllMessages().stream().collect(Collectors.groupingBy(w -> w.getSubject()));
    }
}
