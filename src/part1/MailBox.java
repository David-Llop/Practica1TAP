package part1;

import java.util.*;
import java.util.function.Predicate;

public class MailBox implements Iterable<Message> {

    protected IMailStore mailStore;
    protected ArrayList<Message> messages = new ArrayList<>();
    protected User user;

    public MailBox(IMailStore mailStore, User user) {
        this.mailStore = mailStore;
        this.user = user;
    }

    public MailBox() {

    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }

    public void update(){
        messages.addAll(mailStore.getMail(user.getUsername()));
    }

    public ArrayList<Message> getMailList(){
        return messages;
    }

    public void sendMail(Message mail){
        mailStore.sendMail(mail);
    }

    public ArrayList<Message> sort(Comparator<Message> messageComparator){
        ArrayList<Message> sortedList = (ArrayList<Message>) messages.clone();

        Collections.sort(sortedList, messageComparator);
        return sortedList;
    }

    public ArrayList<Message> filter(Predicate<Message> predicate){
        return Filtrate.filter(predicate, (ArrayList<Message>) messages);
    }

}
