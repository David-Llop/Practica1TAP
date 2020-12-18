package part1;

import java.util.ArrayList;
import java.util.Hashtable;

public class InMemoryMailStore implements MailStore{

    private Hashtable<String, ArrayList<Message>> mailHashTable = new Hashtable<>();
    private static InMemoryMailStore mailStore = new InMemoryMailStore();

    private InMemoryMailStore(){

    }

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
    public ArrayList<Message> getMail(String user) {
        return mailHashTable.get(user);
    }

    public static MailStore getInstance() {
        return mailStore;
    }
}
