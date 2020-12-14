package part1;

import java.text.ParseException;
import java.util.Date;
// MAIN
public class Test
{
    public static void main (String[] Args) throws ParseException {

        User AnnaJu128 = new User("AnnaJu128", "Anna", User.formatter.parse("18-10-1999"));
        User Llop00 = new User("Llop00", "David", User.formatter.parse("07-11-2000"));
        MailSystem mailSystem = MailSystem.getMailSystem();
        mailSystem.setMailStore(OnFileMailStore.getInstance());
        mailSystem.addUser(AnnaJu128);  mailSystem.addUser(Llop00);
        Message num1 = new Message("AnnaJu128", "Llop00", "Cafe", "Hola! Vols anar a fer un cafe aquesta tarda??");
        MailStore mailStore = mailSystem.getMailStore();
        mailStore.sendMail(num1);
        mailStore.getMail("Llop00");
        Message num2 = new Message()
    }
}
