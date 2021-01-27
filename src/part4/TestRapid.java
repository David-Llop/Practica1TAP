package part4;

import part1.MailSystem;
import part1.User;

import java.util.Date;



public class TestRapid {

    public static void main(String[] args) {
        MailSystem mailSystem = new StoreUsingProxy();
        mailSystem.addUser(new User("a", "a", new Date()));
        mailSystem.getUsersList().forEach(u -> System.out.println(u.getUsername()));
        System.out.println(mailSystem.getMailStore());
    }
}
