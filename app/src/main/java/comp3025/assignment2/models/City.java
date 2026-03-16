package comp3025.assignment2.models;

/**
 * This model is a city option that has been retrieved.
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class City {

    /**
     * This field is the name of the city that's available.
     */
    private String name;

    /**
     * This field needs to be provided in order to retrieve the WeatherInformation model.
     * We'll need to provide it when we retrieve the model.
     * Since we only need to provide it, we don't need to understand what this field means.
     */
    private double lat;

    /**
     * This field needs to be provided in order to retrieve the WeatherInformation model.
     * We'll need to provide it when we retrieve the model.
     * Since we only need to provide it, we don't need to understand what this field means.
     */
    private double lon;
}
