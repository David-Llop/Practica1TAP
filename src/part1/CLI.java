package part1;

import java.io.IOException;
import java.util.Scanner;

public class CLI {

    private boolean logged = false;
    private User currentUser;
    private MailBox currentMailBox;

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



    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        MailSystem mailSystem = MailSystem.getMailSystem();
        String options;
        CLI cli = new CLI();
        System.out.println("Welcome to AnnaJu-Llop mail client");
        do {
            System.out.println("Please, select what kind of Mail Store you want to use: (F)ile/(M)emory");
            options = keyboard.next();
            //keyboard.next();

        }while (!"FfMm".contains(options));
        if (options.equalsIgnoreCase("f"))
            mailSystem.setMailStore(OnFileMailStore.getInstance());
        else
            mailSystem.setMailStore(InMemoryMailStore.getInstance());
        keyboard.nextLine();
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
                    // Do filter
                    break;
                case "logas":
                    // Do login
                    break;
                default:
                    System.out.println("Not in admin mode, please close the current user to use the "+ command[0]+" command");
            }
        }
    }
}
