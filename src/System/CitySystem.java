package System;

public interface CitySystem {
    /**
     * Adds new city to the directory
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     * @param population Population number.
     */
    void addCity(String cityName, String countryName, int population);

    /**
     * Removes existing city from the directory.
     * @param cityName Name of the city.
     * @param countryName Name of the country.
     */
    void removeCity(String cityName, String countryName);
}
