package part1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class with the Mail System functions, follows the singleton pattern
 */
public class MailSystem {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private MailStore mailStore;
    private ArrayList<User> usersList = new ArrayList<>();
    private static MailSystem mailSystem = new MailSystem();

    private MailSystem(){}


    public MailStore getMailStore() {
        return mailStore;
    }

    public static MailSystem getMailSystem() {
        return mailSystem;
    }

    public void setMailStore(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    public User findUser(String username){
        for (User user :
                usersList) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    /**
     * Function to add a user
     * @param user user to add or login
     * @return Mailbox with the user messages
     */
    public MailBox addUser(User user){
        if (!usersList.contains(user)){
            usersList.add(user);
        }
        return new MailBox(mailStore, user);
    }

    /**
     * Function to calculate the total number of messages in the MailSystem
     * @return total numer of messages in th system
     */
    public int getTotalMessages(){
        int count = 0;
        for (User user: usersList) {
            count+=mailStore.getMail(user.getUsername()).size();
        }
        return count;
    }

    /**
     * Function to calculate the average messages received/sent by each user
     * @return average received/sent messages per user
     */
    public float getAverageUserMessages(){
        return (float) getTotalMessages()/usersList.size();
    }

    /**
     * Function to get the list of users in the system
     * @return list of users in the system
     */
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    /**
     * Function to get all the messages in the system
     * @return list with all the messages in the system
     */
    public ArrayList<Message> getAllMessages(){
        ArrayList<Message> messages = new ArrayList<>();
        for (User user : usersList) {
            messages.addAll(mailStore.getMail(user.getUsername()));
        }
        return messages;
    }

    /**
     * Function to group all the messages in the system by subject
     * @return Map with the messages grouped by subject
     */
    // TODO: Afegir camp messages igual que amb els filtres???
    public Map<String, List<Message>> groupBySubject(){
        return getAllMessages().stream().collect(Collectors.groupingBy(Message::getSubject));
    }

    /**
     * Function to count the total words of the messages sent by a user with a real name
     * @param name user's real name
     * @return count of words in the messages send by the user
     */
    public int wordsByName(String name){
        int count=0;
        ArrayList<String> usernames = new ArrayList<>();
        for (User user :
                usersList) {
            if (user.getName().equals(name))
                for (Message message: filtrate(message -> message.getFrom().equals(user.getUsername()))) {
                    count+=message.getWordCount();
                }
        }
        return count;
    }

    /**
     * Function to get all the messages sent to users born before the given year
     * @param year Year before which the receiver must have been born
     * @return list of messages sent to users born before the given year
     * @throws ParseException as dd-mm-yyyy date format is used and only the year is given, it has to be parsed to Date
     */
    public ArrayList<Message> toBornBefore(int year) throws ParseException {
        String dateString = "01-01-"+year;
        Date date = formatter.parse(dateString);
        ArrayList<User> usersBefore = (ArrayList<User>) usersList.stream().filter(t->t.getBirthdate().before(date));
        ArrayList<Message> messagesBornBefore = new ArrayList<>();
        for (User user :
                usersBefore) {
            messagesBornBefore.addAll(mailStore.getMail(user.getUsername()));
        }
        return messagesBornBefore;
    }

    /**
     * Function that returns the list of messages according to the given predicate
     * @param predicate predicate to filter the messages
     * @return List of messages with less than the given words in the body
     */
    public ArrayList<Message> filtrate(Predicate<Message> predicate){
        return Filtrate.filter(predicate, getAllMessages());
    }
}
