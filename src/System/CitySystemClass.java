package System;
import dataStructures.exceptions.*;
import dataStructures.*;

import java.io.*;

/**
 * System implementation for managing a sorted directory of cities.
 * Space Complexity of Structure:
 * Best case: O(1) - Holds a reference attribute to the sorted list.
 * Worse case: O(N) - Storing N cities requires linear space in dynamic memory.
 */
public class CitySystemClass implements CitySystem{
    private static final long serialVersionUID = 1L;


    SortedList<City> cities;

    /**
     * Constructs a new CitySystemClass instance initializing an empty SortedLinkedList with CitiesComparator.
     * Best Case: O(1) - Instantiates empty sorted list structure in constant time.
     * Worst Case: O(1) - Constant time initialization.
     */
    public CitySystemClass(){

        cities = new SortedLinkedList<>(new CitiesComparator());

    }

    /**
     * Adds a new city to the system after verifying that it does not already exist.
     *Time Complexity:
     * Best Case: O(1) - Directory is empty, findCity and insert complete in O(1).
     * Worst Case: O(N) - Traverses through N cities to verify uniqueness and locate insertion point in the sorted list.
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     * @param population Population number.
     * @throws CityAlreadyExistsException
     */
    @Override
    public void addCity(String cityName, String countryName, int population) throws CityAlreadyExistsException {
        if (findCity(cityName,countryName) != null){
            throw new CityAlreadyExistsException();
        }

        City newCity = new CityClass(cityName,countryName,population);
        cities.add(newCity);

    }

    /**
     * Removes an existing city from the system.
     * Time Complexity:
     * Best Case: O(1) - Target city is located at the head of the sorted list.
     * Worst Case: O(N) - Traverses N cities to locate the city in findCity and remove it from the list.
     *
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     * @throws CityDoesNotExistException
     */
    @Override
    public void removeCity(String cityName, String countryName) throws CityDoesNotExistException {
        City city = findCity(cityName,countryName);
        if(city == null)
            throw new CityDoesNotExistException();
        cities.remove(city);
    }

    /**
     * Retrieves the population of a specific city
     * Time Complexity:
     * Best Case: O(1) - Target city is found at the head of the sorted list.
     * Worst Case: O(N) - Traverses N cities in findCity before returning population or throwing an exception.
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     * @return Number of inhabitants
     * @throws CityDoesNotExistException
     */

    @Override
    public int population(String cityName, String countryName) throws CityDoesNotExistException {
        City city = findCity(cityName,countryName);
        if (city == null){
            throw new CityDoesNotExistException();
        }
        return city.getPopulation();
    }

    /**
     * Returns an iterator over all cities stored in sorted order
     *Time Complexity:
     * Best Case: O(1) - Creates and returns a list iterator in constant time.
     * Worst Case: O(1) - Constant time iterator creation.
     * @return Iterator of City elements
     */
    @Override
    public Iterator<City> listCities(){
        return cities.iterator();
    }

    /**
     * Serializes and writes the current CitySystem object state to a persistence file.
     * Time Complexity:
     * Best Case: O(1) - Directory is empty, serializes empty state instantly.
     * Worst Case: O(N) - Serializes all N cities in the directory to disk.
     * @param fileName Name of the file.
     */
    @Override
public void storeCities(String fileName){
        try{
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(fileName));
            file.writeObject(this);
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new OutputWriteException();
        }
    }

    /**
     *Loads and deserializes a saved CitySystem object from a file.
     * Time Complexity:
     * Best Case: O(1) - File is missing or empty, failing in constant time.
     * Worst Case: O(N) - Reads and deserializes N saved cities into memory.
     * @param name
     * @return Loaded CitySystem object, or null if loading fails
     */
    public static CitySystem load(String name) {
        CitySystem cs = null ;
        try{
            ObjectInputStream file = new ObjectInputStream(
                    new FileInputStream(name) );
            cs = (CitySystem) file.readObject();
            file.close();
        }
        catch (IOException e ) {
            cs = null;
        }
        catch (ClassNotFoundException e) {
            cs = null;
        }
        return cs;
    }

    /**
     * Helper method that searches for a city matching both city name and country name (case-insensitive).
     Time Complexity:
     * Best Case: O(1) - Target city is located at the head of the iterator sequence.
     * Worst Case: O(N) - Sequentially compares all N cities in the list.
     * Returns city by its name and country
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     * @return City if found or null if not found
     */
    private City findCity (String cityName, String countryName){

        Iterator<City> it = cities.iterator();
        while (it.hasNext()){
            City city = it.next();
            if (city.getCountry().equalsIgnoreCase(countryName) && city.getName().equalsIgnoreCase(cityName)){
                return city;
            }
        }
        return null;
    }



}