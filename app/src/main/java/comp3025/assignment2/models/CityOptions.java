package comp3025.assignment2.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a model that includes multiple city option models.
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class CityOptions {

    /**
     * This field is the city option models that have been added.
     * CityOptions starts with no models.
     * A city option model can be added using the addCityOption method.
     */
    private List<CityOption> cityOptions = new ArrayList<>();

    /**
     * This method returns the city option models that have been added.
     */
    public List<CityOption> getCityOptions() {
        return cityOptions;
    }

    /**
     * This method adds a city option model.
     * This method only adds a model if a model has been provided.
     * If a model isn't provided, the method won't add it.
     */
    public void addCityOption(CityOption cityOption) {
        //Only add the city option model if it was provided to this method.
        if (cityOption == null) {
            //A city option model wasn't provided to this method.
            Log.i("200594802 and 200497768", "A city option model wasn't added because the model wasn't provided to CityOptions.");
        } else {
            //Add the city option model.
            this.cityOptions.add(cityOption);
        }


    }
}
