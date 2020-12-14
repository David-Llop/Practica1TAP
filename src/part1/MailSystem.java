package part1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class with the Mail System functions, follows the singleton pattern
 */
public class MailSystem {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private MailStore mailStore;
    private ArrayList<User> usersList;
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
            count+=mailStore.getMail(user.getUsername()).length;
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
            Collections.addAll(messages, mailStore.getMail(user.getUsername()));
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
        for (Message message: (ArrayList<Message>) getAllMessages().stream().filter(t->t.getFrom().equals(name))) {
            count+=message.getWordCount();
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
            Collections.addAll(messagesBornBefore, mailStore.getMail(user.getUsername()));
        }
        return messagesBornBefore;
    }

    /**
     * Function that returns the list of messages containing the given word in the subject or the body
     * @param word Word the message has to contain
     * @param messages List of messages from where to filter, if {@code null}, filters all the messages in the system
     * @return list of messages containing the given word in the subject or the body
     */
    public ArrayList<Message> contains(String word, ArrayList<Message> messages){
        ArrayList<Message> result = new ArrayList<>();
        ArrayList<Message> aux;
        if (messages == null)
            aux = getAllMessages();
        else
            aux = messages;
        Collections.addAll(result, getAllMessages().stream().filter(t->t.getSubject().contains(word) || t.getText().contains(word)).toArray(Message[]::new));
        return result;
    }

    /**
     * Function that returns the list of messages with less than {@code max_Words} in the body
     * @param max_Words maximum words in the body of the message
     * @param messages List of messages from where to filter, if {@code null}, filters all the messages in the system
     * @return List of messages with less than the given words in the body
     */
    public ArrayList<Message> lessThan(int max_Words, ArrayList<Message> messages){
        ArrayList<Message> result = new ArrayList<>();
        ArrayList<Message> aux;
        if (messages == null)
            aux = getAllMessages();
        else
            aux = messages;
        Collections.addAll(result, aux.stream().filter(t->t.getWordCount() < max_Words).toArray(Message[]::new));
        return result;
    }
}
