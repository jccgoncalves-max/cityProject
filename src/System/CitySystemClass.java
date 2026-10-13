package System;
import dataStructures.exceptions.*;
import dataStructures.*;

import java.io.*;

public class CitySystemClass implements CitySystem{
    private static final long serialVersionUID = 1L;


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
            if (city.getCountry().equalsIgnoreCase(countryName) && city.getName().equalsIgnoreCase(cityName)){
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

    public static CitySystem load(String name) {
        CitySystem cs = null ;
        try{
            ObjectInputStream file = new ObjectInputStream(
                    new FileInputStream(name) );
            cs= (CitySystem) file.readObject();
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



}