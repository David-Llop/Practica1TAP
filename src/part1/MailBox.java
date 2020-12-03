package part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MailBox {

    private MailStore mailStore;
    private List<Message> messages;

    public MailBox(MailStore mailStore, User user) {
        this.mailStore = mailStore;
        messages= Arrays.asList(mailStore.getMail(user));
    }
}
