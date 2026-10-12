package System;

import dataStructures.Iterator;
import dataStructures.exceptions.CityAlreadyExistsException;
import dataStructures.exceptions.CityDoesNotExistException;
import dataStructures.exceptions.NoCitiesAvailableException;

public interface CitySystem {
    /**
     * Adds new city to the directory
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     * @param population Population number.
     */
    void addCity(String cityName, String countryName, int population) throws CityAlreadyExistsException;

    /**
     * Removes existing city from the directory.
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     */
    void removeCity(String cityName, String countryName)throws CityDoesNotExistException;

    /**
     * Return the number of inhabitants of a city.
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     * @return Number of inhabitants
     * @throws CityDoesNotExistException
     */
    int population( String cityName, String countryName)throws CityDoesNotExistException;

    /**
     * Iterator of a cities collection
     * @return City Iterator
     * @throws NoCitiesAvailableException
     */
    Iterator<City> listCities() ;

}
