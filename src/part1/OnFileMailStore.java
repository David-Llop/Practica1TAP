package part1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class OnFileMailStore implements MailStore{

    private final File file;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    /**
     * @param file {@link File} from where to retrieve and send {@link Message}
     */
    public OnFileMailStore(File file) {
        this.file = file;
    }

    /**
     * @param filepath path to the file from where to retrieve and send {@link Message}
     */
    public OnFileMailStore(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Function that sends a given Message
     * @param mail {@link Message} to be send
     */
    @Override
    public void sendMail(Message mail) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(mail.toString());
            writer.close();
            //MailSystem.addMessage(mail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that, given a {@link User}, returns the {@link Message} [] intended for it
     * @param user {@link User} for whom the mails are intended
     * @return {@link Message} [] of mails intended for the given user
     */
    @Override
    public Message[] getMail(String user) {
        Stream<Message> result = Arrays.stream(new Message[0]);
        ArrayList<Message> aux = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String[] fields = scanner.nextLine().split(";");
                if (fields.length == 5){
                    aux.add(new Message(fields[0],fields[1],fields[2],fields[3], formatter.parse(fields[4])));
                }
            }

        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
            aux.add(null);
        }
        return aux.stream().filter(t->t.getTo().equals(user)).toArray(Message[] :: new);
    }
}
