package part1;

import java.util.ArrayList;

public interface IMailStore {

    /**
     * Function that sends a given Message
     * @param mail {@link Message} to be send
     */
    void sendMail(Message mail);

    /**
     * Function that, given a {@link User}, returns the {@link ArrayList}<{@link Message}> intended for it
     * @param user {@link User} for whom the mails are intended
     * @return {@link ArrayList}<{@link Message}> of mails intended for the given user
     */
    ArrayList<Message> getMail(String user);

}
