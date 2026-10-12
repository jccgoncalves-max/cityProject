package System;
import dataStructures.*;
import dataStructures.exceptions.*;

public class CitySystemClass implements CitySystem{

    SortedList<City> cities;

    public CitySystemClass(){

    cities = new SortedLinkedList<>(new CitiesComparator());

    }

    @Override
    public void addCity(String cityName, String countryName, int population) throws CityAlreadyExistsException {
        City newCity = new CityClass(cityName,countryName,population);
        if(cities.contains(newCity))
            throw new CityAlreadyExistsException();
        cities.add(newCity);
        //todo: verificar;
    }

    @Override
    public void removeCity(String cityName, String countryName) throws CityDoesNotExistException {
        CityClass newCity = new CityClass(cityName,countryName);
        if(!cities.contains(newCity))
            throw new CityDoesNotExistException();
        cities.remove(newCity);
    }
}
