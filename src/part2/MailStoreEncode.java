package part2;

import part1.IMailStore;
import part1.Message;
import part1.User;

import java.util.ArrayList;

/**
 * Mail store decorator to encore the mail store with the given Strategy
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class MailStoreEncode implements IMailStore {

    IMailStore mailStore;
    IStrategy encodingStrategy;

    public MailStoreEncode(IMailStore mailStore, IStrategy encodingStrategy) {
        this.mailStore = mailStore;
        this.encodingStrategy = encodingStrategy;
    }

    /**
     * Function that encodes and sends a given Message
     *
     * @param mail {@link Message} to be send
     */
    @Override
    public void sendMail(Message mail) {
        mail.setText(encodingStrategy.encode(mail.getText()));
        mailStore.sendMail(mail);
    }

    /**
     * Function that, given a {@link User}, returns the array list of messages intended for it and decodes them
     *
     * @param user {@link User} for whom the mails are intended
     * @return Array list of decoded mails intended for the given user
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
