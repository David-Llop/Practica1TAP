package part3;

import part1.IMailStore;
import part1.Message;
import part1.OnFileMailStore;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JedisAddapter implements IMailStore {

    private static JedisAddapter jedisAddapter = new JedisAddapter();
    private Jedis jedisClient = new Jedis("localhost");

    private JedisAddapter() {
    }

    public static JedisAddapter getInstance() {
        return jedisAddapter;
    }

    @Override
    public void sendMail(Message mail) {
        jedisClient.append(mail.getTo(), mail.toString()+"\n");
    }

    @Override
    public ArrayList<Message> getMail(String user) {
        String mails = jedisClient.get(user);
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
