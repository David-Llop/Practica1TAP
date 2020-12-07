package part1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class InMemoryMailStore implements MailStore{

    /* TODO: taula[usuaris][missatges] on cada usuari té una llista amb els missatges que
        van destinats a ell?? --> List<List<Message>>
     */

    ArrayList<ArrayList<Message>> mailsTable = new ArrayList<>();

    /**
     * Function that sends a given Message
     * @param mail {@link Message} to be send
     */
    @Override
    public void sendMail(Message mail) {
        for (ArrayList<Message> userMails:
             mailsTable) {
            Message user = userMails.get(0);
            if (user.getTo().equals(mail.getTo())){
                userMails.add(mail);
                return;
            }
        }
        mailsTable.add(new ArrayList<>());
        ArrayList<Message> userMails = mailsTable.get(mailsTable.size()-1);
        userMails.add(new Message(null, mail.getTo(), null, null));
        userMails.add(mail);
    }

    /**
     *Function that, given a {@link User}, returns the {@link Message} [] intended for it
     * @param user {@link User} for whom the mails are intended
     * @return {@link Message} [] of mails intended for the given user
     */
    @Override
    public Message[] getMail(String user) {

        for (ArrayList <Message> userMails :
                mailsTable) {
            Message user1 = userMails.get(0);
            if (user1.getTo().equals(user)){
                ArrayList<Message> mails = (ArrayList<Message>) userMails.clone();
                mails.remove(0);

                return mails.toArray(Message[]::new);
            }
        }
        return null;
    }
}
