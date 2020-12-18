package part2;

import part1.MailStore;
import part1.Message;
import part1.User;

import java.util.ArrayList;

public class MailStoreEncode implements MailStore {

    MailStore mailStore;
    Strategy encodingStrategy;

    public MailStoreEncode(MailStore mailStore, Strategy encodingStrategy) {
        this.mailStore = mailStore;
        this.encodingStrategy = encodingStrategy;
    }

    /**
     * Function that sends a given Message
     *
     * @param mail {@link Message} to be send
     */
    @Override
    public void sendMail(Message mail) {
        mail.setText(encodingStrategy.encode(mail.getText()));
        mailStore.sendMail(mail);
    }

    /**
     * Function that, given a {@link User}, returns the {@link Message} [] intended for it
     *
     * @param user {@link User} for whom the mails are intended
     * @return {@link Message} [] of mails intended for the given user
     */
    @Override
    public ArrayList<Message> getMail(String user) {
        ArrayList<Message> mails = mailStore.getMail(user);
        for (Message mail : mails) {
            mail.setText(encodingStrategy.decode(mail.getText()));
        }
        return mails;
    }
}
