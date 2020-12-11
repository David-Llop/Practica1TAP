package part1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MailBox implements Iterable<Message> {

    private MailStore mailStore;
    private List<Message> messages;

    public MailBox(MailStore mailStore, User user) {
        this.mailStore = mailStore;
        messages= Arrays.asList(mailStore.getMail(user.getUsername()));
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }

    /*public static void main(String[] args) {
        User user = new User();
        user.setUsername("asdfa");
        MailBox mailBox = new MailBox(OnFileMailStore.getInstance(), user);
        mailBox.mailStore.sendMail(new Message("asd", user.getUsername(),"PROVA1", "asdfjsdiofjiasdh"));
        mailBox.mailStore.sendMail(new Message("asd", user.getUsername(),"PROVA2", "asdfjsdiofjiasdh"));
        mailBox.mailStore.sendMail(new Message("asd", user.getUsername(), "PROVA3", "asdfjsdiofjiasdh"));
        mailBox.mailStore.sendMail(new Message("asd", user.getUsername(),"PROVA4", "asdfjsdiofjiasdh"));
        mailBox.messages = Arrays.asList(mailBox.mailStore.getMail(user.getUsername()));
        mailBox.forEach(System.out::println);
        //System.out.println(mailBox.messages);
    }*/


}
