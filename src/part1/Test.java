package part1;

import java.text.ParseException;
import java.util.Date;
// MAIN
public class Test
{
    public static void main (String[] Args) throws ParseException {


        MailSystem mailSystem = MailSystem.getMailSystem();
        mailSystem.setMailStore(OnFileMailStore.getInstance());
        User AnnaJu128 = new User("AnnaJu128", "Anna", User.formatter.parse("18-10-1999"), mailSystem.getMailStore());
        User Llop00 = new User("Llop00", "David", User.formatter.parse("07-11-2000"), mailSystem.getMailStore());
        mailSystem.addUser(AnnaJu128);  mailSystem.addUser(Llop00);
        Message num1 = new Message("AnnaJu128", "Llop00", "Cafe", "Hola! Vols anar a fer un cafe aquesta tarda??");
        MailStore mailStore = mailSystem.getMailStore();
        AnnaJu128.sendMail(num1);
        mailStore.getMail("Llop00");
        Message num2 = new Message("Llop00", "AnnaJu128", "Cafe", "Si perfecte! pero a partir de les 5. On vols quedar? al bar de la uni?");
        Llop00.sendMail(num2);
        mailStore.getMail("AnnaJu128");
        Message num3 = new Message("AnnaJu128", "Llop00", "Cafe", "Vale a les 5! pero, podriem anar al coffebook que hi han donuts :P");
        AnnaJu128.sendMail(num3);
        mailStore.getMail("Llop00");
        Message num4 = new Message("Llop00", "AnnaJu128", "Cafe", "Bueno vale, a les 5 al CoffeBook.");
        Llop00.sendMail(num4);
        mailStore.getMail("AnnaJu128");
    }
}
