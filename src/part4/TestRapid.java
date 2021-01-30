package part4;

import part1.MailSystem;
import part1.User;
import java.util.Date;

/**
 * Tests the functionality of the {@link Config} annotation and the proxy
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class TestRapid {

    public static void main(String[] args) {
        MailSystem mailSystem = new StoreUsingProxy();
        mailSystem.addUser(new User("a", "a", new Date()));
        mailSystem.getUsersList().forEach(u -> System.out.println(u.getUsername()));
        System.out.println(mailSystem.getMailStore());
    }
}
