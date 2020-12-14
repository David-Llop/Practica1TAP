package part1;

import java.util.*;

public class MailBox implements Iterable<Message> {

    private MailStore mailStore;
    private List<Message> messages;
    private User user;
    private MailSystem mailSystem;

    public MailBox(MailStore mailStore, User user) {
        this.mailStore = mailStore;
        this.user = user;
        messages= Arrays.asList(mailStore.getMail(user.getUsername()));
        mailSystem = MailSystem.getMailSystem();
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }

    public void update(){
        messages = Arrays.asList(mailStore.getMail(user.getUsername()));
    }

    public ArrayList<Message> getMailList(){
        return (ArrayList<Message>) messages;
    }

    public void sendMail(Message mail){
        mailStore.sendMail(mail);
    }

    public void sortByDate(boolean newFirst){
        if (newFirst)
            messages.sort(Comparator.comparing(x->x.getSendDate(), Comparator.reverseOrder()));
        else
            messages.sort(Comparator.comparing(x->x.getSendDate()));
    }

    public ArrayList<Message> contains(String word){
        return mailSystem.contains(word, (ArrayList<Message>) messages);
    }

    public ArrayList<Message> lessThan(int max_Words){
        return mailSystem.lessThan(max_Words, (ArrayList<Message>) messages);
    }

}
