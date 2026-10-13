package System;

import java.io.Serializable;

public interface City extends Serializable {
    /**
     * Returns city name.
     * @return city name.
     */
    String getName();

    /**
     * Returns country name.
     * @return country name.
     */
    String getCountry();

    /**
     * Returns population number.
     * @return population number.
     */
    int getPopulation();
}
