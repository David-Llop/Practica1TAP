package part2;

import part1.*;

import java.util.*;

public class MailBox implements Iterable<Message> {

    private MailStore mailStore;
    private ArrayList<Message> messages = new ArrayList<>();
    private User user;
    private List<Observer> observers = new ArrayList<>();

    public MailBox(MailStore mailStore, User user) {
        this.mailStore = mailStore;
        this.user = user;
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }

    public void update(){
        Collections.addAll(messages, mailStore.getMail(user.getUsername()));
        notifyAllObservers();
    }

    public ArrayList<Message> getMailList(){
        return messages;
    }

    public void sendMail(Message mail){
        mailStore.sendMail(mail);
    }

    public ArrayList<Message> sort(int sorting, boolean reverse){
        ArrayList<Message> sortedList = (ArrayList<Message>) messages.clone();
        switch (sorting){
            case Sorting.BY_DATE:
                if (!reverse)
                    Collections.sort(sortedList, Comparator.comparing(Message::getSendDate).reversed());
                else
                    Collections.sort(sortedList, Comparator.comparing(Message::getSendDate));
                break;
            case Sorting.BY_SUBJECT:
                if (reverse)
                    Collections.sort(sortedList, Comparator.comparing(Message::getSubject).reversed());
                else
                    Collections.sort(sortedList, Comparator.comparing(Message::getSubject));
                break;
            case Sorting.BY_WORDS:
                if (reverse)
                    Collections.sort(sortedList, Comparator.comparing(Message::getWordCount).reversed());
                else
                    Collections.sort(sortedList, Comparator.comparing(Message::getWordCount));
                break;
            case Sorting.BY_SENDER:
                if (reverse)
                    Collections.sort(sortedList, Comparator.comparing(Message::getFrom).reversed());
                else
                    Collections.sort(sortedList, Comparator.comparing(Message::getFrom));
                break;
        }
        return sortedList;
    }

    public ArrayList<Message> contains(String word){
        return Filtrate.contains(word, (ArrayList<Message>) messages);
    }

    public ArrayList<Message> lessThan(int max_Words){
        return Filtrate.lessThan(max_Words, (ArrayList<Message>) messages);
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update(messages);
        }
    }

}
