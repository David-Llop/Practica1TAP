package part1;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Class implementing a in memory Mail Store
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class InMemoryMailStore implements IMailStore{

    private Hashtable<String, ArrayList<Message>> mailHashTable = new Hashtable<>();

    public InMemoryMailStore() {
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
     * Function that, given a user, returns the Array list of messages intended for it
     * @param user user for whom the mails are intended
     * @return Array list of mails intended for the given user
     */
    @Override
    public ArrayList<Message> getMail(String user) {
        return mailHashTable.get(user);
    }

}
