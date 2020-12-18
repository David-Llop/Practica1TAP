package part1;

import java.util.*;
import java.util.function.Predicate;

public class MailBox implements Iterable<Message> {

    private MailStore mailStore;
    private ArrayList<Message> messages = new ArrayList<>();
    private User user;

    public MailBox(MailStore mailStore, User user) {
        this.mailStore = mailStore;
        this.user = user;
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

    public ArrayList<Message> filter(Predicate<Message> predicate){
        return Filtrate.filter(predicate, (ArrayList<Message>) messages);
    }

}
