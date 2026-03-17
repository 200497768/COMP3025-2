package comp3025.assignment2.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a model that includes multiple city option models.
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class CityOptions {
    private List<CityOption> cityOptions = new ArrayList<>();

    public List<CityOption> getCityOptions() {
        return cityOptions;
    }

    public void addCityOption(CityOption cityOption) {
        this.cityOptions.add(cityOption);
    }
}
