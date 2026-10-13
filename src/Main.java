import System.*;
import dataStructures.exceptions.*;
import dataStructures.*;
import java.util.Scanner;

public class Main {
    //Constants
    private static final String ADDED_CITY_MSG = "City successfully added.";
    private static final String CITY_ALREADY_EXISTS_MSG = "City already exists.";
    private static final String CITY_REMOVED_MSG = "City successfully removed.";
    private static final String CITY_NOT_FOUND_MSG = "City not found.";
    private static final String POPULATION_MSG = "Population: %d\n";
    private static final String LIST_CITY_MSG = "%s - %d - %s\n";
    private static final String NO_CITIES_MSG="No cities available.";
    private static final String SAVED_DIRECTORY_MSG = "City directory saved.";
    private static final String QUIT_MSG = "Goodbye.";
    private static final String FILE_NAME = "file.dat";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CitySystem cs = CitySystemClass.load(FILE_NAME);
        if(cs == null){
            cs = new CitySystemClass();
            System.out.println("City directory not found.");
            System.out.println("Creating an empty city directory.");
        }else{
            System.out.println("City directory restored successfully.");
        }
        executeCommand(in, cs);
        in.close();
    }


    /**
     * Contains all enum types
     */
    private enum Command {
        addCity("addCity: Adds a new city to the directory."),
        removeCity("removeCity: Removes an existing city from the directory."),
        population("population: Returns the number of inhabitants of a city."),
        listCities("listCities: Lists all cities currently stored in the directory."),
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
        Command finalCmd = null;
            String cmd = in.nextLine().trim();
            for(int i = 0; i<Command.values().length;i++){
                if(Command.values()[i].toString().equalsIgnoreCase(cmd))
                    finalCmd = Command.values()[i];
            }
            return finalCmd;
    }

    /**
     * Processes command interpreter
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
            case quit -> quitCmd(cs);
        }

    }

    /**
     * Executes command interpreter
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
     * @param in Scanner input
     * @param cs City System
     */
    private static void addCityCmd(Scanner in, CitySystem cs) {
        try {
            String cityName = in.nextLine().trim();
            String countryName = in.nextLine().trim();
            int population = in.nextInt();
            in.nextLine().trim();
            cs.addCity(cityName, countryName, population);
            System.out.println(ADDED_CITY_MSG);
        } catch (CityAlreadyExistsException e) {
            System.out.println(CITY_ALREADY_EXISTS_MSG);
        }
    }

    /**
     * Removes an existing city from the directory.
     * @param in Scanner input
     * @param cs City System
     */
    private static void removeCityCmd(Scanner in, CitySystem cs) {
        try {
            String cityName = in.nextLine().trim();
            String countryName = in.nextLine().trim();
            cs.removeCity(cityName, countryName);
            System.out.println(CITY_REMOVED_MSG);

        } catch (CityDoesNotExistException e) {
            System.out.println(CITY_NOT_FOUND_MSG);

        }
    }

    /**
     * Returns the number of inhabitants of the city
     * @param in Scanner input
     * @param cs City System
     */
    private static void populationCmd(Scanner in, CitySystem cs){
        try {
            String cityName = in.nextLine().trim();
            String countryName = in.nextLine().trim();
            int numPopulation = cs.population(cityName,countryName);
            System.out.printf(POPULATION_MSG, numPopulation);

        }catch (CityDoesNotExistException e){
            System.out.println(CITY_NOT_FOUND_MSG);
        }
    }

    /**
     * List all cities currently stored in the directory.
     * @param cs City System
     */
    private  static  void listCitiesCmd(CitySystem cs){
            Iterator<City> it = cs.listCities();
            if(!it.hasNext())
                System.out.println(NO_CITIES_MSG);
            while (it.hasNext()){
                City city = it.next();
                System.out.printf(LIST_CITY_MSG,city.getCountry(),city.getPopulation(),city.getName());
            }
    }

    /**
     * Terminates de application.
     * @param cs City System
     */
    private static void quitCmd(CitySystem cs){
        try{
            cs.storeCities(FILE_NAME);
        }catch (RuntimeException e){
        }
        System.out.println(SAVED_DIRECTORY_MSG);
        System.out.println(QUIT_MSG);
    }
}