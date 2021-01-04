package part1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OnFileMailStore implements MailStore{

    private File file;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    private static OnFileMailStore mailStore = new OnFileMailStore();

    private OnFileMailStore(){
        file = new File("MailStore.txt");
    }

    public void setFile(String filepath){
        file = new File(filepath);
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
    public ArrayList<Message> getMail(String user) {
        Stream<Message> result = Arrays.stream(new Message[0]);
        ArrayList<Message> aux = new ArrayList<>();
        ArrayList<Message> aux2 = new ArrayList<>();

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
         aux2.addAll(aux.stream().filter(t->t.getTo().equals(user)).collect(Collectors.toList()));
        return aux2 ;
    }


    public static MailStore getInstance() {
        return mailStore;
    }
}
