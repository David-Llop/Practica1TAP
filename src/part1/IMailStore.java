package part1;

import java.util.ArrayList;

/**
 * Interface containing the definition of the basic functions of a MailStore
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public interface IMailStore {

    /**
     * Function that sends a given Message
     * @param mail {@link Message} to be send
     */
    void sendMail(Message mail);

    /**
     * Function that, given a user, returns the Array list of messages intended for it
     * @param user user for whom the mails are intended
     * @return Array list of mails intended for the given user
     */
    ArrayList<Message> getMail(String user);

}
