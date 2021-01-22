package part1;

import java.text.ParseException;

// MAIN
public class Test
{
    public static void main (String[] Args) throws ParseException {


        MailSystem mailSystem = new MailSystem(new OnFileMailStore("prova.txt"));
        User AnnaJu128 = new User("AnnaJu128", "Anna", User.formatter.parse("18-10-1999"));
        User Llop00 = new User("Llop00", "David", User.formatter.parse("07-11-2000"));
        mailSystem.setMailStore(new InMemoryMailStore());
        MailBox llopMailbox = mailSystem.addUser(Llop00);
        MailBox annajuMailbox = mailSystem.addUser(AnnaJu128);
        Message num1 = new Message("AnnaJu128", "Llop00", "Cafe", "Hola! Vols anar a fer un cafe aquesta tarda??");
        MailStore mailStore = mailSystem.getMailStore();
        annajuMailbox.sendMail(num1);
        llopMailbox.update();
        Message num2 = new Message("Llop00", "AnnaJu128", "Cafe", "Si perfecte! pero a partir de les 5. On vols quedar? al bar de la uni?");
        llopMailbox.sendMail(num2);
        mailStore.getMail("AnnaJu128");
        Message num3 = new Message("AnnaJu128", "Llop00", "Cafe", "Vale a les 5! pero, podriem anar al coffebook que hi han donuts :P");
        annajuMailbox.sendMail(num3);
        mailStore.getMail("Llop00");
        Message num4 = new Message("Llop00", "AnnaJu128", "Cafe", "Bueno vale, a les 5 al CoffeBook.");
        llopMailbox.sendMail(num4);
        mailStore.getMail("AnnaJu128");
        annajuMailbox.forEach(System.out::println);
        System.out.println("\n\n\n");
        annajuMailbox.update();
        System.out.println("Default");
        annajuMailbox.forEach(System.out::println);
        System.out.println("New First");
        annajuMailbox.sort(new Sort.SortNewFirst()).forEach(System.out::println);
        annajuMailbox.forEach(System.out::println);
        System.out.println("Old first");
        annajuMailbox.sort(new Sort.SortOldFirst()).forEach(System.out::println);
        mailSystem.setMailStore(new OnFileMailStore("prova.txt"));
        mailSystem.getAllMessages().stream().sorted(new Sort.SortNewFirst()).forEach(System.out::println);

    }
}
