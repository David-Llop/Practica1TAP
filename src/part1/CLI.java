package part1;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("AnnaJu-Llop mail client                                                                                         q: quit");
        if (cli.logged)
            System.out.println(cli.currentUser.getUsername());
        else System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
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

    public void decodeCommand(String[] command){
        if (logged) {
            switch (command[0]){
                case "send":
                    // Do send
                    break;
                case "filter":
                    // Do filter
                    break;
                case "update":
                    // Do update
                    break;
                case "list":
                    // Do print
                    break;
                case "sort":
                    // Do sort
                    break;
                default:
                    System.out.println("Not logged as user, please login as user to use the "+command[0]+" command");
            }
        }
        else {
            switch (command[0]){
                case "createuser":
                    // Do create user
                    break;
                case "filter":
                    filter(command);
                    break;
                case "logas":
                    logged = login(command);
                    break;
                default:
                    System.out.println("Not in admin mode, please close the current user to use the "+ command[0]+" command");
            }
        }
    }

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
            result = Filtrate.contains(command[2], aux);
        }
        if (command[1].equals("lessthan")){
            result = Filtrate.lessThan(Integer.parseInt(command[2]), aux);
        }
        if (command.length==3){
            System.out.println("Resultats de la cerca:");
            System.out.println(result);
            return;
        }
        aux = result;
        if (command[3].equals("contains")){
            result = Filtrate.contains(command[4], aux);
        }
        if (command[3].equals("lessthan")){
            result = Filtrate.lessThan(Integer.parseInt(command[4]), aux);
        }
        System.out.println("Resultats de la cerca:");
        System.out.println(result);
    }

    private boolean login(String @NotNull [] command) {
        if (command.length > 2) {
            System.out.println("Too many arguments");
            return false;
        }
        if (command.length < 2){
            System.out.println("Missing username");
            return false;
        }
        currentUser = mailSystem.findUser(command[1]);
        if (currentUser == null){
            System.out.println("This user doesn't exist");
            return false;
        }
        currentMailBox = mailSystem.addUser(currentUser);
        return true;
    }
}
