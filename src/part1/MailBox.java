package part1;

import java.util.*;

public class MailBox implements Iterable<Message> {

    private MailStore mailStore;
    private ArrayList<Message> messages = new ArrayList<>();
    private User user;
    private MailSystem mailSystem;

    public MailBox(MailStore mailStore, User user) {
        this.mailStore = mailStore;
        this.user = user;
        //Collections.addAll(messages, mailStore.getMail(user.getUsername()));
        mailSystem = MailSystem.getMailSystem();
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }

    public void update(){
        Collections.addAll(messages, mailStore.getMail(user.getUsername()));
    }

    public ArrayList<Message> getMailList(){
        return messages;
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
        return Filtrate.contains(word, (ArrayList<Message>) messages);
    }

    public ArrayList<Message> lessThan(int max_Words){
        return Filtrate.lessThan(max_Words, (ArrayList<Message>) messages);
    }

}
