package System;

import dataStructures.*;

/**
 * Space Complexity of Class:
 * : O(1) - The comparator is stateless and contains no fields or dynamic memory.
 * Comparator for City objects based on country name, population, and city name.
 * Ordering strategy:
 *   1. Country name ascending (lexicographical order)
 *   2. Population descending (higher population first)
 *   3. City name ascending (case-insensitive lexicographical order)
 *
 */
public class CitiesComparator implements Comparator<City> {

    /**
     *Time Complexity:
     *Best Case: O(1) - Countries are different; compareTo completes in O(1) for short/differing prefixes.
     *Worst Case: O(n) - Countries and populations are equal, requiring full case-insensitive string comparison of city names of length n.
     * @param x First City object to compare
     * @param y Second City object to compare
     * @return Negative integer, zero, or positive integer if x is less than,
     *equal to, or greater than y respectively.
     */
    @Override
    public int compare(City x, City y) {
        int comCountry = x.getCountry().compareTo(y.getCountry());
        if (comCountry != 0)
            return comCountry;
        int compPopulation = y.getPopulation() - x.getPopulation();
        if (compPopulation !=0)
            return compPopulation;

        return x.getName().compareToIgnoreCase(y.getName());

    }
}
