package part1;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class CLI {

    private boolean logged = false;
    private User currentUser;
    private MailBox currentMailBox;
    private MailSystem mailSystem = MailSystem.getMailSystem();

    private static void cleanScrean(CLI cli) throws IOException {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" AnnaJu-Llop mail client                                                                                        q: quit");
        if (cli.logged)
            System.out.println(" "+cli.currentUser.getUsername());
        else System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }



    public static void main(String[] args) throws IOException, ParseException {
        Scanner keyboard = new Scanner(System.in);
        String options;
        CLI cli = new CLI();
        System.out.println("Welcome to AnnaJu-Llop mail client");
        do {
            System.out.println("Please, select what kind of Mail Store you want to use: (F)ile/(M)emory");
            options = keyboard.next();
            //keyboard.next();

        }while (!"FfMm".contains(options));
        if (options.equalsIgnoreCase("f"))
            cli.mailSystem.setMailStore(OnFileMailStore.getInstance());
        else
            cli.mailSystem.setMailStore(InMemoryMailStore.getInstance());
        keyboard.nextLine();
        User AnnaJu128 = new User("AnnaJu128", "Anna", User.formatter.parse("18-10-1999"));
        User Llop00 = new User("Llop00", "David", User.formatter.parse("07-11-2000"));
        cli.mailSystem.addUser(AnnaJu128);
        cli.mailSystem.addUser(Llop00);
        do {
            cleanScrean(cli);
            options = keyboard.nextLine();
            if (!options.equalsIgnoreCase("q")) {
                cli.decodeCommand(options.split(" "));
                keyboard.nextLine();
            }
        }while (!options.equalsIgnoreCase("q"));
        System.out.println("Closing mail system");
    }

    public void decodeCommand(String[] command) throws ParseException {
        if (logged) {
            switch (command[0]){
                case "send":
                    sendMail(command);
                    break;
                case "filter":
                    filter(command);
                    break;
                case "update":
                    currentMailBox.update();
                    System.out.println("Mailbox updated");
                    break;
                case "list":
                    currentMailBox.forEach(System.out::println);
                    break;
                case "sort":
                    sort(command);
                    break;
                default:
                    System.out.println("Not in admin mode, please close the current user to use the "+ command[0]+" command");
            }
        }
        else {
            switch (command[0]){
                case "createuser":
                    createUser(command);
                    break;
                case "filter":
                    filter(command);
                    break;
                case "logas":
                    login(command);
                    break;
                default:
                    System.out.println("Not logged as user, please login as user to use the "+command[0]+" command");
            }
        }
        System.out.println("Press enter to enter a new command");
    }

    private void createUser(String[] command) throws ParseException {
        if (command.length>4){
            System.out.println("Too many arguments");
            return;
        }
        if (command.length<4){
            System.out.println("Missing arguments");
            return;
        }
        if (mailSystem.findUser(command[1])!= null){
            System.out.println("Username in use");
            return;
        }
        mailSystem.addUser(new User(command[1], command[2], User.formatter.parse(command[3])));
        System.out.println("User created and added");
    }

    private void sendMail(String[] command) {
        if (command.length < 4){
            System.out.println("Missing arguments");
            return;
        }
        String text = "";
        for (int i = 3; i < command.length-1; i++) {
            text+=command[i];
            text+=" ";
        }
        text+=command[command.length-1];
        currentMailBox.sendMail(new Message(currentUser.getUsername(), command[1], command[2], text));
        System.out.println("Mail sent");
    }

    private void sort(String[] command) {
        if (command.length>3){
            System.out.println("Too many parameters");
            return;
        }
        int sorting;
        if (command[1].equalsIgnoreCase("date"))
            sorting = Sorting.BY_DATE;
        else if (command[1].equalsIgnoreCase("subject"))
            sorting = Sorting.BY_SUBJECT;
        else if (command[1].equalsIgnoreCase("words"))
            sorting = Sorting.BY_WORDS;
        else if (command[1].equalsIgnoreCase("sender"))
            sorting = Sorting.BY_SENDER;
        else {
            System.out.println("Unknown parameter");
            return;
        }
        if (command.length == 3 && command[2].equals("1"))
            System.out.println(currentMailBox.sort(sorting, true));
        else
            System.out.println(currentMailBox.sort(sorting, false));
    }

    private void filter(String[] command) {
        System.out.println("asdhjfjkasdhfjhasd");
        if (command.length>5){
            System.out.println("Too many arguments");
            return;
        }
        if (command.length < 3){
            System.out.println("Missing arguments");
            return;
        }
        if (command.length == 4){
            System.out.println("Missing parameter of the second filter or too many arguments");
            return;
        }

        ArrayList<Message>result = new ArrayList<>();
        ArrayList<Message> aux;
        if (logged){
            aux = currentMailBox.getMailList();
        }
        else
            aux= mailSystem.getAllMessages();
        if (command[1].equals("contains")){
            result = Filtrate.filter(new Filtrate.ContainsPredicate(command[2]), aux);
        }
        if (command[1].equals("lessthan")){
            result = Filtrate.filter(new Filtrate.LessThanPredicate(Integer.parseInt(command[2])), aux);
        }
        if (command.length==3){
            System.out.println("Resultats de la cerca:");
            System.out.println(result);
            return;
        }
        aux = result;
        if (command[3].equals("contains")){
            result = Filtrate.filter(new Filtrate.ContainsPredicate(command[4]), aux);
        }
        if (command[3].equals("lessthan")){
            result = Filtrate.filter(new Filtrate.LessThanPredicate(Integer.parseInt(command[4])), aux);
        }
        System.out.println("Resultats de la cerca:");
        System.out.println(result);
    }

    private void login(String[] command) {
        if (command.length > 2) {
            System.out.println("Too many arguments");
            logged = false;
            return;
        }
        if (command.length < 2){
            System.out.println("Missing username");
            logged = false;
            return;
        }
        currentUser = mailSystem.findUser(command[1]);
        if (currentUser == null){
            System.out.println("This user doesn't exist");
            logged = false;
            return;
        }
        currentMailBox = mailSystem.addUser(currentUser);
        logged = true;
        System.out.println("Successfully logged as " + currentUser.getUsername());
    }
}
