package System;

import dataStructures.*;

public class CitiesComparator implements Comparator<City> {
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
