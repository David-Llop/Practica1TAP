package part1;

import part3.FileWithWrappersFileMailStoreFactory;
import part3.MemoryMailStoreFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This class implements a CLI program to use the Mail Service
 * @author David Llop Roig
 * @author Anna Julia Naval
 * */

public class CLI {

    private boolean logged = false;
    private User currentUser;
    private MailBox currentMailBox;
    private MailSystem mailSystem;

    /**
     * Program to clean the screen and prints the CLI header, only works in Windows
     */
    private static void cleanScreen(CLI cli) throws IOException {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" AnnaJu-Llop Mail Service                                                                                       q: quit");
        if (cli.logged)
            System.out.println(" "+cli.currentUser.getUsername());
        else System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }


    /**
     * Main function, basically a do ... while(not quit)
     * @param args main ars
     * @throws IOException thrown when unable to use the desired file as a mail store file
     * @throws IOException thrown when unable to use the desired file as a mail store file
     * @throws ParseException thrown when unable to parse from {@code String} to {@code int}
     * @throws NoSuchMethodException thrown when unable to run windows command "cls"
     */
    public static void main(String[] args) throws IOException, ParseException, NoSuchMethodException {
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
            cli.mailSystem = new MailSystem(new FileWithWrappersFileMailStoreFactory());
        else
            cli.mailSystem = new MailSystem(new MemoryMailStoreFactory());
        keyboard.nextLine();
        User AnnaJu128 = new User("AnnaJu128", "Anna", User.formatter.parse("18-10-1999"));
        User Llop00 = new User("Llop00", "David", User.formatter.parse("07-11-2000"));
        cli.mailSystem.addUser(AnnaJu128);
        cli.mailSystem.addUser(Llop00);
        do {
            cleanScreen(cli);
            options = keyboard.nextLine();
            if (!options.equalsIgnoreCase("q")) {
                cli.decodeCommand(options.split(" "));
                keyboard.nextLine();
            }
        }while (!options.equalsIgnoreCase("q"));
        System.out.println("Closing mail system");
    }

    /**
     * This function calls the execution of the requested command, if it exists and is available
     * @param command User input split by spaces
     * @throws ParseException thrown when unable to parse from {@code String} to {@code int}
     */
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

    /**
     * Creates user and adds it to the Mail System
     * @param command command given by the user
     * @throws ParseException thrown when unable to parse from {@code String} to {@code int}
     */
    private void createUser(String[] command) throws ParseException {
        if (command.length>4){
            System.out.println("Too many arguments");
            return;
        }
        if (command.length<4){
            System.out.println("Missing arguments");
            return;
        }
        if (mailSystem.addUser(new User(command[1], command[2], User.formatter.parse(command[3])))){
            System.out.println("User created and added");
            return;
        }
        System.out.println("Username in use");
    }

    /**
     * Sends a mail
     * @param command command given by the user
     */
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

    /**
     * Sorts and shows the messages in the mailbox
     * @param command command given by the user
     */
    private void sort(String[] command) {
        if (command.length > 3) {
            System.out.println("Too many parameters");
            return;
        }
        Comparator<Message> sorting;
        if (command[1].equalsIgnoreCase("date")) {
            if (command[2].equals("0"))
                sorting = new Sort.SortOldFirst();
            else
                sorting = new Sort.SortNewFirst();
        }else if (command[1].equalsIgnoreCase("subject")){
            if (command[2].equals("1"))
                sorting = new Sort.SortSubjectDesc();
            else
                sorting = new Sort.SortSubjectAsc();
        }else if (command[1].equalsIgnoreCase("words")){
            if (command[2].equals("1"))
                sorting = new Sort.SortWordsDesc();
            else
                sorting = new Sort.SortWordsAsc();
    }   else if (command[1].equalsIgnoreCase("sender")) {
            if (command[2].equals("1"))
                sorting = new Sort.SortSenderDesc();
            else
                sorting = new Sort.SortSenderAsc();
        }else {
            System.out.println("Unknown parameter");
            return;
        }
        System.out.println(currentMailBox.sort(sorting));

    }

    /**
     * Filters and shows the mails in the mailbox
     * @param command command given by the user
     */
    private void filter(String[] command) {
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
            result = aux.stream().filter(new Filtrate.ContainsPredicate(command[2])).collect(Collectors.toCollection(ArrayList::new));
        }
        if (command[1].equals("lessthan")){
            result = aux.stream().filter(new Filtrate.LessThanPredicate(Integer.parseInt(command[2]))).collect(Collectors.toCollection(ArrayList::new));
        }
        if (command.length==3){
            System.out.println("Resultats de la cerca:");
            System.out.println(result);
            return;
        }
        aux = result;
        if (command[3].equals("contains")){
            result = aux.stream().filter(new Filtrate.ContainsPredicate(command[4])).collect(Collectors.toCollection(ArrayList::new));
        }
        if (command[3].equals("lessthan")){
            result = aux.stream().filter(new Filtrate.LessThanPredicate(Integer.parseInt(command[4]))).collect(Collectors.toCollection(ArrayList::new));
        }
        System.out.println("Resultats de la cerca:");
        System.out.println(result);
    }


    /**
     * Allows the user to log as a user and access its mailbox.
     * @param command command given by the user
     */
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
        logged = mailSystem.addUser(currentUser);
        if (logged){
            currentMailBox = mailSystem.retrieveMailBox(currentUser.getUsername());
            System.out.println("Successfully logged as " + currentUser.getUsername());
        }
        else
            System.out.println("This user doesn't exist");
    }
}
