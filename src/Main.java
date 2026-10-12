import System.*;
import dataStructures.exceptions.CityAlreadyExistsException;
import dataStructures.exceptions.CityDoesNotExistException;
import dataStructures.exceptions.NoCitiesAvailableException;
import dataStructures.Iterator;
import java.util.Scanner;

public class Main {
    //Constants
    private static final String ADDED_CITY_MSG = "City successfully added.";
    private static final String CITY_ALREADY_EXISTS_MSG = "City already exists.";
    private static final String CITY_REMOVED_MSG = "City successfully removed.";
    private static final String CITY_NOT_FOUND_MSG = "City not found.";
    private static final String POPULATION_MSG = "Population: %d";
    private static final String LIST_CITY_MSG = "%s - %d - %s\n";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CitySystem cs = new CitySystemClass();
        executeCommand(in, cs);
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
        quit("quit: Terminates the application.");
        private String msg;

        Command(String msg) {
            this.msg = msg;
        }

        String getMsg() {
            return this.msg;
        }

        private static void executeHelpCmd() {
            Command[] help = Command.values();
            for (int i = 0; i < help.length; i++) {
                System.out.println(help[i].getMsg());
            }
        }
    }

    /**
     * Returns Command
     *
     * @param in Scanner
     * @return Command value
     */
    private static Command getCommand(Scanner in) {
        try {
            String cmd = in.nextLine();
            return Command.valueOf(cmd);
        } catch (IllegalArgumentException e) {
           // throw new IllegalArgumentException(); // fixme: estava a lançar exceção por isso fiz return null
           return null;
        }
    }

    /**
     * Processes command interpreter
     *
     * @param in  Scanner input
     * @param cmd Command
     */
    private static void processCommand(CitySystem cs, Scanner in, Command cmd) {
        switch (cmd) {
            case addCity -> addCityCmd(in, cs);
            case removeCity -> removeCityCmd(in, cs);
            case population -> populationCmd(in,cs);
            case listCities -> listCitiesCmd(cs);
            case help -> Command.executeHelpCmd();
        }

    }

    /**
     * Executes command interpreter
     *
     * @param in Scanner input
     * @param cs City System
     */
    private static void executeCommand(Scanner in, CitySystem cs) {
        Command command;
        do {
            command = getCommand(in);
            processCommand(cs, in, command);
        } while (!command.equals(Command.quit));
    }

    /**
     * Adds a new city into the directory;
     *
     * @param in Scanner input
     * @param cs City System
     */
    private static void addCityCmd(Scanner in, CitySystem cs) {
        try {
            String cityName = in.nextLine();
            String countryName = in.nextLine();
            int population = in.nextInt();
            in.nextLine();
            cs.addCity(cityName, countryName, population);
            System.out.println(ADDED_CITY_MSG);
        } catch (CityAlreadyExistsException e) {
            System.out.println(CITY_ALREADY_EXISTS_MSG);
        }
    }

    /**
     * Removes an existing city from the directory.
     *
     * @param in Scanner input
     * @param cs City System
     */
    private static void removeCityCmd(Scanner in, CitySystem cs) {
        try {
            String cityName = in.nextLine();
            String countryName = in.nextLine();
            cs.removeCity(cityName, countryName);
            System.out.println(CITY_REMOVED_MSG);

        } catch (CityDoesNotExistException e) {
            System.out.println(CITY_NOT_FOUND_MSG);

        }
    }

    private static void populationCmd(Scanner in, CitySystem cs){
        try {
            String cityName = in.nextLine();
            String countryName = in.nextLine();
         int numPopulation = cs.population(cityName,countryName);
         System.out.printf(POPULATION_MSG, numPopulation);

        }catch (CityDoesNotExistException e){
            System.out.println(CITY_NOT_FOUND_MSG);
        }
    }

    private  static  void listCitiesCmd( CitySystem cs){
        try {
            Iterator<City> it = cs.listCities();

            while (it.hasNext()){
                City city = it.next();
                System.out.printf(LIST_CITY_MSG,city.getCountry(),city.getPopulation(),city.getName());
            }
        }catch (NoCitiesAvailableException e){
            System.out.println();
        }

    }


}