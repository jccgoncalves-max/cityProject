package System;

public class CityClass implements City{

    //todo: Debater com o lenine sobre a possibilidade de criarmos uma classe que terá dois construtores;

    private String name;
    private String country;
    private int population;

    public CityClass(String name, String country, int population){
        this.name = name;
        this.country = country;
        this.population = population;
    }
    public CityClass(String name, String country){
        this.name = name;
        this.country = country;
    }

    public String getName(){
        return name;
    }
    public String getCountry(){
        return country;
    }
    public int getPopulation(){
        return population;
    }


    public boolean equals(Object other){
        CityClass otherCity = (CityClass)other;
        return (otherCity instanceof CityClass && name.equals(otherCity.getName()) && country.equals(otherCity.getCountry()));
    }

}
