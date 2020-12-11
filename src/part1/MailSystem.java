package part1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MailSystem {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
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
        return (float) getTotalMessages()/usersList.size();
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
        return getAllMessages().stream().collect(Collectors.groupingBy(Message::getSubject));
    }

    public int wordsByName(String name){
        int count=0;
        for (Message message: (ArrayList<Message>) getAllMessages().stream().filter(t->t.getFrom().equals(name))) {
            count+=message.getWordCount();
        }
        return count;
    }

    public ArrayList<Message> toBornBefore(int year) throws ParseException {
        String dateString = "01-01-"+year;
        Date date = formatter.parse(dateString);
        ArrayList<User> usersBefore = (ArrayList<User>) usersList.stream().filter(t->t.getBirthdate().before(date));
        ArrayList<Message> messagesBornBefore = new ArrayList<>();
        for (User user :
                usersBefore) {
            Collections.addAll(messagesBornBefore, mailStore.getMail(user.getUsername()));
        }
        return messagesBornBefore;
    }
}
