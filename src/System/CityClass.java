package System;

public class CityClass implements City{


    private String name;
    private String country;
    private int population;

    public CityClass(String name, String country, int population){
        this.name = name;
        this.country = country;
        this.population = population;
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
        return (otherCity != null && name.equals(otherCity.getName()) && country.equals(otherCity.getCountry()));
    }

}
