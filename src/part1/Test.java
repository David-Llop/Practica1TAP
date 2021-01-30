package part1;

import part3.FileMailStoreFactory;

import java.text.ParseException;

// MAIN
public class Test
{
    public static void main (String[] Args) throws ParseException, NoSuchMethodException {

        //Initialize the mail system with an in-memory mail store.
        MailSystem mailSystem = new MailSystem(new FileMailStoreFactory());
        mailSystem.setMailStore(new InMemoryMailStore());

        // Create at least 3 users, two have the same name but different username.
        User AnnaJu128 = new User("AnnaJu128", "Anna", User.formatter.parse("18-10-1999"));
        User Llop00 = new User("Llop00", "David", User.formatter.parse("07-11-2000"));
        User Buzzerage = new User("Buzzerage", "David", User.formatter.parse("01-01-2000"));
        MailBox llopMailbox = mailSystem.addUser(Llop00);
        MailBox annajuMailbox = mailSystem.addUser(AnnaJu128);
        MailBox buzzerageMailbox = mailSystem.addUser(Buzzerage);

        // Then, use the mailboxes to send a few emails between them. Make some of them share the
        // same subject and make enough so that the following tests have results.
        Message num1 = new Message("AnnaJu128", "Llop00", "Cafe", "Hola! Vols anar a fer un cafe aquesta tarda??");
        IMailStore mailStore = mailSystem.getMailStore();
        annajuMailbox.sendMail(num1);
        Message num2 = new Message("Llop00", "AnnaJu128", "Cafe", "Si perfecte! pero a partir de les 5. On vols quedar? al bar de la uni?");
        llopMailbox.sendMail(num2);
        mailStore.getMail("AnnaJu128");
        Message num3 = new Message("AnnaJu128", "Llop00", "Cafe", "Vale a les 5! pero, podriem anar al coffebook que hi han donuts :P");
        annajuMailbox.sendMail(num3);
        mailStore.getMail("Llop00");
        Message num4 = new Message("Llop00", "AnnaJu128", "Cafe", "Bueno vale, a les 5 al CoffeBook.");
        llopMailbox.sendMail(num4);
        mailStore.getMail("AnnaJu128");
        Message num5 = new Message("Buzzerage", "Llop00", "Among Us", "Hey tio, un among?");
        buzzerageMailbox.sendMail(num5);
        Message num6 = new Message("Buzzerage", "AnnaJu128", "Among Us", "Hey AnnaJuuu, un among?");
        buzzerageMailbox.sendMail(num6);
        Message num7 = new Message("Llop00", "Buzzerage", " ", "Bro, que estic molt liat amb TAP, ja dema");
        llopMailbox.sendMail(num7);
        Message num8 = new Message("Buzzerage", "Llop00", " ", "Auba, pos re");
        buzzerageMailbox.sendMail(num8);
        Message num9 = new Message("AnnaJu128", "Buzzerage", "OhYes", "Buah si, qui mes s'apunta??");
        annajuMailbox.sendMail(num9);
        Message num10 = new Message("Buzzerage", "AnnaJu128", "Sad", "Ningu tio, estan tots molt liats");
        buzzerageMailbox.sendMail(num10);
        Message num11 = new Message("AnnaJu128", "Buzzerage", "Ploro", "Patata, dons dema");
        annajuMailbox.sendMail(num11);

        //Get one of the mailboxes and update its mail.
        llopMailbox.update();

        //List the mailbox messages in the console. (Sorted by newer first.) Use the iterable capabilities
        //of the mailbox!
        llopMailbox.sort(new Sort.SortNewFirst()).forEach(System.out::println);

        //Now list the messages by sender username using the mailbox feature.
        llopMailbox.sort(new Sort.SortSenderAsc()).forEach(System.out::println);

        //Filter the messages with the following conditions:
        //The message subject contains a certain word.
        llopMailbox.filter(new Filtrate.ContainsSubject("Cafe"));
        //The message sender is a certain user.
        llopMailbox.filter(new Filtrate.SenderIs("AnnaJu128"));

        //Use the mail system object to retrieve all messages and print them.
        mailSystem.getAllMessages().forEach(System.out::println);

        //Filter messages globally that fulfill the following conditions:
        //The message subject is a single word.
        mailSystem.filtrate(m -> m.getSubject().split(" ").length<2);
        //The sender was born after year 2000.
        mailSystem.toBornBefore(2000).forEach(System.out::println);

        //Get the count of messages in the system and print it.
        System.out.println(mailSystem.getTotalMessages());

        //Get the average number of messages received per user and print it
        System.out.println(mailSystem.getAverageUserMessages());

        //Group the messages per subject in a Map<String, List<Message>> and print it.

        //Count the words of all messages sent by users with a certain real name.

        //Use the name that you used on two users. Print the result.
        //Print the messages received by users born before year 2000.



        //Now change the mail store to the file implementation.
    }
}
