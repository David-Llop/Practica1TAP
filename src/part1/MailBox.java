package part1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MailBox {

    private MailStore mailStore;
    private List<Message> messages;

    public MailBox(MailStore mailStore, User user) {
        this.mailStore = mailStore;
        messages= Arrays.asList(mailStore.getMail(user.getUsername()));
        System.out.println(messages);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("asdfa");
        MailBox mailBox = new MailBox(new OnFileMailStore(new File("prova.txt")), user);
        mailBox.mailStore.sendMail(new Message("asd", user.getUsername(),"PROVA", "asdfjsdiofjiasdh"));
        mailBox.mailStore.sendMail(new Message("asd", user.getUsername(),"PROVA", "asdfjsdiofjiasdh"));
        mailBox.mailStore.sendMail(new Message("asd", user.getUsername(),"PROVA", "asdfjsdiofjiasdh"));
        mailBox.mailStore.sendMail(new Message("asd", user.getUsername(),"PROVA", "asdfjsdiofjiasdh"));
        System.out.println(mailBox.messages);
    }
}
