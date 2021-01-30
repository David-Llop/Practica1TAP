package part3;

import part1.IMailStore;
import part1.Message;
import part1.OnFileMailStore;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adapts the jedis client to a Mail store. As only one jedis client is allowed, it follows the Singleton pattern
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
public class JedisAdapter implements IMailStore {

    private static JedisAdapter jedisAdapter = new JedisAdapter();
    private Jedis jedisClient = new Jedis("localhost");

    private JedisAdapter() {
    }

    public static JedisAdapter getInstance() {
        return jedisAdapter;
    }

    /**
     * Function that sends a given Message
     * @param mail {@link Message} to be send
     */
    @Override
    public void sendMail(Message mail) {
        jedisClient.append(mail.getTo(), mail.toString()+"\n");
    }

    /**
     * Function that, given a user, returns the Array list of messages intended for it
     * @param user user for whom the mails are intended
     * @return Array list of mails intended for the given user
     */
    @Override
    public ArrayList<Message> getMail(String user) {
        String mails = jedisClient.get(user);
        if (mails == null)
            return new ArrayList<>();
        List<String> mailList = new ArrayList<String>(Arrays.asList(mails.split("\n")));
        ArrayList<Message> result = new ArrayList<>();
        mailList.stream().map(str -> str.split(";")).filter(fields -> fields.length == 5).forEach(fields -> {
            try {
                Message mail = new Message(fields[0], fields[1], fields[2], fields[3], OnFileMailStore.getFormatter().parse(fields[4]));
                result.add(mail);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

}
