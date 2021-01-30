package part1;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class implementing a mail box
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class MailBox implements Iterable<Message> {

    protected IMailStore mailStore;
    protected ArrayList<Message> messages = new ArrayList<>();
    protected User user;

    public MailBox(IMailStore mailStore, User user) {
        this.mailStore = mailStore;
        this.user = user;
    }

    public IMailStore getMailStore() {
        return mailStore;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }

    /**
     * Updates the mails in the mail box from the mail store
     */
    public void update(){
        messages.addAll(mailStore.getMail(user.getUsername()));
    }

    public ArrayList<Message> getMailList(){
        return messages;
    }

    /**
     * Function that sends a mail by calling function sendMail() of {@link IMailStore}
     * @param mail mail to be send
     */
    public void sendMail(Message mail){
        mailStore.sendMail(mail);
    }

    /**
     * funcion that sorts the messages according to the given comparator
     * @param messageComparator indicates how to sort the messages
     * @return sorted list of messages
     */
    public ArrayList<Message> sort(Comparator<Message> messageComparator){
        ArrayList<Message> sortedList = (ArrayList<Message>) messages.clone();

        Collections.sort(sortedList, messageComparator);
        return sortedList;
    }

    /**
     * funcion that filters the messages according to the given predicate
     * @param predicate gives the condition to filter
     * @return filtered list of messages
     */
    public ArrayList<Message> filter(Predicate<Message> predicate){
        return messages.stream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
    }

}
