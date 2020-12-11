package part1;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class InMemoryMailStore implements MailStore{

    Hashtable<String, ArrayList<Message>> mailHashTable = new Hashtable<>();

    /**
     * Function that sends a given Message
     * @param mail {@link Message} to be send
     */
    @Override
    public void sendMail(Message mail) {

        if (mailHashTable.get(mail.getTo()) == null){
            ArrayList<Message> aux = new ArrayList<>();
            aux.add(mail);
            mailHashTable.put(mail.getTo(), aux);
        }
        else {
            mailHashTable.get(mail.getTo()).add(mail);
        }
    }

    /**
     *Function that, given a {@link User}, returns the {@link Message} [] intended for it
     * @param user {@link User} for whom the mails are intended
     * @return {@link Message} [] of mails intended for the given user
     */
    @Override
    public Message[] getMail(String user) {
        return mailHashTable.get(user).toArray(new Message[0]);
    }
}
