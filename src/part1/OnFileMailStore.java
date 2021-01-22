package part1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OnFileMailStore implements IMailStore{

    private File file;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public OnFileMailStore(String file) {
        this.file = new File(file);
    }

    public void setFile(String filepath){
        File newFile = new File(filepath);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile, false));
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                bufferedWriter.write(scanner.nextLine());
            }
            scanner.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = newFile;
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

    public File getFile() {
        return file;
    }

    public static SimpleDateFormat getFormatter() {
        return formatter;
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
            scanner.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
            aux.add(null);
        }
         aux2.addAll(aux.stream().filter(t->t.getTo().equals(user)).collect(Collectors.toList()));
        return aux2 ;

    }

}
