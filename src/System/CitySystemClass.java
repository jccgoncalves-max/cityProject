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
    public void addCity(String cityName, String countryName, int population) throws CityAlreadyExistsException {//fixme: testar e considerar mudar para iterador
        //fixme: a classe usa o comparador em vez do equals;
        // Se o find devolver != null é porque encontrou alguma coisa e lança exceção.
        if (findCity(cityName,countryName) != null){
            throw new CityAlreadyExistsException();
        }

        City newCity = new CityClass(cityName,countryName,population);
        cities.add(newCity);

    }
    private City findCity (String cityName, String countryName){

        Iterator<City> it = cities.iterator();
        while (it.hasNext()){
          City cite = it.next();
          if (cite.getCountry().equals(countryName) && cite.getName().equalsIgnoreCase(cityName)){
              return cite;
          }
        }
        return null;
    }

    @Override
    public void removeCity(String cityName, String countryName) throws CityDoesNotExistException {
        City cite = findCity(cityName,countryName);
        if(cite == null)
            throw new CityDoesNotExistException();
        cities.remove(cite);
    }

    @Override
    public int population(String cityName, String countryName) throws CityDoesNotExistException {
        City cite = findCity(cityName,countryName);
      if (cite == null){
          throw new CityDoesNotExistException();
      }
      return cite.getPopulation();
    }

    @Override
    public Iterator<City> listCities() throws NoCitiesAvailableException {
        return cities.iterator();
    }
}

