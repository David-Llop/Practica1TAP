package part1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnFileMailStore implements MailStore{

    private final File file;
    private final Pattern regexMessage = Pattern.compile("(\\w+);(\\w+);([\\w\\h\\.]+);([\\w\\h]+)\n");

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
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(mail.getFrom() + ";" + mail.getTo() + ";"
                    + mail.getSubject() + ";" + mail.getText() + "\n");
            writer.close();
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
    public Message[] getMail(User user) {
        Message[] result = new Message[0];
        ArrayList<Message> aux = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                Matcher matcher = regexMessage.matcher(scanner.nextLine());
                Message message = new Message(matcher.group(1), matcher.group(2),
                        matcher.group(3), matcher.group(4));
                aux.add(message);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            aux.add(null);
        }
        result = aux.toArray(result);
        return result;
    }
}
