package part2;

import part1.*;

import java.util.*;

/**
 * Mail Box with listeners
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class MailBoxSpam  extends MailBox implements Iterable<Message> {

    private List<Observer> observers = new ArrayList<>();
    private ArrayList<Message> spam = new ArrayList<>();

    public MailBoxSpam(IMailStore mailStore, User user) {
        super(mailStore, user);
    }

    public MailBoxSpam(MailBox mailBox){
        super(mailBox.getMailStore(), mailBox.getUser());
    }


    /**
     * Updates the mail box and notifies the observers
     */
    @Override
    public void update(){
        messages = new ArrayList<>();
        spam = new ArrayList<>();
        messages.addAll(mailStore.getMail(user.getUsername()));
        notifyAllObservers();
    }

    public ArrayList<Message> getSpam() {
        return spam;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update(messages, spam);
        }
    }

}
