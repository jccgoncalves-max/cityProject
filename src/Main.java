import System.*;
import dataStructures.exceptions.*;
import dataStructures.*;
import java.util.Scanner;

/**
 * Main application class responsible for handling console input and command execution.
 * Space Complexity of Class:
 *- Auxiliary Space: O(1) - Uses a constant amount of stack memory for Scanner, primitive types, and local references.
 *- Storage Space: O(n) - Holds the CitySystem reference in memory, which dynamically stores n City objects in the Heap.
 */
public class Main {
    //Constant
    private static final String CITY_DIRECTORY_NOT_FOUND_MSG = "City directory not found.";
    private static final String CITY_CREATING_MSG = "Creating an empty city directory.";
    private static final String CITY_DIRECTORY_RESTORED_MSG = "City directory restored successfully.";
    private static final String ADDED_CITY_MSG = "City successfully added.";
    private static final String CITY_ALREADY_EXISTS_MSG = "City already exists.";
    private static final String CITY_REMOVED_MSG = "City successfully removed.";
    private static final String CITY_NOT_FOUND_MSG = "City not found.";
    private static final String POPULATION_MSG = "Population: %d\n";
    private static final String LIST_CITY_MSG = "%s - %d - %s\n";
    private static final String NO_CITIES_MSG = "No cities available.";
    private static final String SAVED_DIRECTORY_MSG = "City directory saved.";
    private static final String QUIT_MSG = "Goodbye.";
    private static final String FILE_NAME = "file.dat";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CitySystem cs = CitySystemClass.load(FILE_NAME);
        if(cs == null){
            cs = new CitySystemClass();
            System.out.println(CITY_DIRECTORY_NOT_FOUND_MSG);
            System.out.println(CITY_CREATING_MSG);
        }else{
            System.out.println(CITY_DIRECTORY_RESTORED_MSG);
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

        /**
         * prints the message from the commands


         */
        private static void executeHelpCmd() {
            Command[] help = Command.values();
            for (int i = 0; i < help.length; i++) {
                System.out.println(help[i].getMsg());
            }
        }
    }

    /**
     * Returns Command
     ** Time Complexity:
     *   - Best Case: O(1) - The entered command matches the first enum element immediately.
     *   - Worst Case: O(1) - Iterates through the entire fixed-size enum array (constant size C = 6).
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
     * Time Complexity:
     *- Best Case: O(1) - The user immediately enters the 'quit' command on the first iteration.
     *- Worst Case: O(N) - The user executes N commands.
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
     * Time Complexity:
     *- Best Case: O(1) - The city directory is empty, so duplicate check completes instantly.
     *- Worst Case: O(n) - Scans all n existing cities to ensure no duplicate city exists before adding.
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
     *Time Complexity:
     *- Best Case: O(1) - The target city to remove is located at the head of the directory.
     *- Worst Case: O(n) - Traverses through all n cities to locate the target city or confirm its absence.
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
     * Time Complexity:
     *- Best Case: O(1) - The specified city is found at the head of the list.
     *- Worst Case: O(n) - Iterates through all n stored cities to find the target.
     *  @param in Scanner input
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
     *Time Complexity:
     *- Best Case: O(1) - The city directory is empty.
     *- Worst Case: O(n) - Iterates over and prints all K cities stored in the directory.
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
     * Time Complexity:
     *- Best Case: O(1) - The directory is empty, writing an empty structure to disk.
     *- Worst Case: O(n) - Serializes and saves all n cities to the persistence file.
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