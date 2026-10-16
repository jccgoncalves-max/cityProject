package System;

import java.io.Serializable;
/**
 * Represents a City entity with serialization support.
 *
 * Space Complexity of Structure:
 *   - Best case: O(1) - Occupies constant space per instance to store attributes.
 *   - Worse case: O(N) - Storing N city instances in memory requires linear space.
 */
public class CityClass implements City, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String country;
    private int population;

    /**
     * Time Complexity:
     *- Best Case: O(1) - Primitive and object reference assignment runs in constant time.
     *- Worst Case: O(1) - Constant time initialization.
     * Constructs a new City object with name, country, and population.
     * @param name
     * @param country
     * @param population
     */
    public CityClass(String name, String country, int population){
        this.name = name;
        this.country = country;
        this.population = population;
    }

    /**
     * Returns the name of the city.
     *Time Complexity:
     *- Best Case: O(1) - Accesses reference attribute in constant time.
     *- Worst Case: O(1) - Constant time getter execution.
     * @return Name of the city
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the country of the city.
     *Time Complexity:
     *- Best Case: O(1) - Accesses reference attribute in constant time.
     *- Worst Case: O(1) - Constant time getter execution.
     * @return Country name
     */
    public String getCountry(){
        return country;
    }

    /**
     * Time Complexity:
     *-Best Case: O(1) - Accesses primitive integer attribute in constant time.
     *- Worst Case: O(1) - Constant time getter execution.
     *Returns the population of the city.
     * @return Total inhabitants
     */
    public int getPopulation(){
        return population;
    }

    /**
     * Compares equality between this city and another object based on name and country.
     *Time Complexity:
     *- Best Case: O(1) - Returns false immediately if target object is null or not of type CityClass.
     *- Worst Case: O(n) - Performs string equality comparisons up to maximum string length n of city/country names.
     *
     * @param other   the reference object with which to compare.
     * @return True if name and country match, false otherwise
     */
    public boolean equals(Object other){
        CityClass otherCity = (CityClass)other;
        return (otherCity != null && name.equals(otherCity.getName()) && country.equals(otherCity.getCountry()));
    }

}
