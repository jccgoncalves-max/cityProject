package System;
import dataStructures.exceptions.*;
import dataStructures.*;
import dataStructures.exceptions.*;

public class CitySystemClass implements CitySystem{

    SortedList<City> cities;

    public CitySystemClass(){

        cities = new SortedLinkedList<>(new CitiesComparator());

    }

    @Override
    public void addCity(String cityName, String countryName, int population) throws CityAlreadyExistsException {
        if (findCity(cityName,countryName) != null){
            throw new CityAlreadyExistsException();
        }

        City newCity = new CityClass(cityName,countryName,population);
        cities.add(newCity);

    }

    /**
     * Returns city by its name and country
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     * @return City if found or null if not found
     */
    private City findCity (String cityName, String countryName){

        Iterator<City> it = cities.iterator();
        while (it.hasNext()){
            City city = it.next();
            if (city.getCountry().equals(countryName) && city.getName().equalsIgnoreCase(cityName)){
                return city;
            }
        }
        return null;
    }

    @Override
    public void removeCity(String cityName, String countryName) throws CityDoesNotExistException {
        City city = findCity(cityName,countryName);
        if(city == null)
            throw new CityDoesNotExistException();
        cities.remove(city);
    }

    @Override
    public int population(String cityName, String countryName) throws CityDoesNotExistException {
        City city = findCity(cityName,countryName);
        if (city == null){
            throw new CityDoesNotExistException();
        }
        return city.getPopulation();
    }

    @Override
    public Iterator<City> listCities(){
        return cities.iterator();
    }
}