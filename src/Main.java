import System.*;
import java.util.Scanner;

public class Main{

    private static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CitySystem cs = new CitySystemClass();
        executeCommand(in,cs);
        in.close();
    }


    /**
     * Contains all enum types
     */
    private enum Command {
        addCity("addCity:  Adds a new city to the directory."),
        removeCity("removeCity: Removes an existing city from the directory."),
        population("population:  Returns the number of inhabitants of a city"),
        listCities("listCities:  Lists all cities currently stored in the directory."),
        help(" help: Displays the list of available commands."),
        quit("quit: Terminates the application."),
        unknown("");
        private String msg;
        Command (String msg){
            this.msg = msg;
        }
        String getMsg(){
            return this.msg;
        }

        private static void executeHelpCmd(){
            Command [] help = Command.values();
            for(int i = 0; i< help.length-1;i++){
                System.out.println(help[i].getMsg());
            }
        }
    }

    /**
     * Returns Command
     * @param in Scanner
     * @return Command value
     */
    private static Command getCommand (Scanner in){
        try{
            String cmd = in.nextLine();
            return Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return Command.unknown;
        }
    }

    /**
     * Processes command interpreter
     * @param in Scanner input
     * @param cmd Command
     */
    private static void processCommand(CitySystem cs, Scanner in,Command cmd){
        switch (cmd){
            case help -> Command.executeHelpCmd();
        }

    }

    /**
     * Executes command interpreter
     * @param in Scanner input
     * @param cs City System
     */
    private static void executeCommand(Scanner in,CitySystem cs){
        Command command;
        do{
            command = getCommand(in);
            processCommand(cs,in,command);
        }while(!command.equals(Command.quit));
    }

}

