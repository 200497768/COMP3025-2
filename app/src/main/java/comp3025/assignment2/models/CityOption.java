package comp3025.assignment2.models;

/**
 * This model is a city option that has been retrieved.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class CityOption {

    /**
     * This is the city name.
     */
    private String city = "No city available";

    /**
     * This is the province name.
     */
    private String province = "No province available";

    /**
     * This is the country name.
     */
    private String country = "No country available";

    /**
     * This field needs to be provided in order to retrieve the WeatherInformation model.
     * We'll need to provide it when we retrieve the model.
     * Since we only need to provide it, we don't need to understand what this field means.
     */
    private double lat = 1;

    /**
     * This field needs to be provided in order to retrieve the WeatherInformation model.
     * We'll need to provide it when we retrieve the model.
     * Since we only need to provide it, we don't need to understand what this field means.
     */
    private double lon = 1;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
