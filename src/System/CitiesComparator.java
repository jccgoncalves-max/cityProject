package System;

import dataStructures.*;

public class CitiesComparator implements Comparator<City> {
    @Override
    public int compare(City x, City y) {//todo: terminar de implementar comparator;
        int result = x.getCountry()-y.getCountry();
        if(result ==0)
        return ;
    }
}
