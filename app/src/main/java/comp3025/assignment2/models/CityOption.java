package comp3025.assignment2.models;

import android.util.Log;

/**
 * This model is a city option that has been retrieved.
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
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
        //Only change the field if it was provided to the model.
        if (country == null) {
            //Don't change the field.
            Log.i("200594802 and 200497768", "The country field wasn't changed because it wasn't provided to the CityOption model.");
        } else {
            //Change the field.
            this.country = country;
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        //Only change the field if it was provided to the model.
        if (province == null) {
            //Don't change the field.
            Log.i("200594802 and 200497768", "The province field wasn't changed because it wasn't provided to the CityOption model.");
        } else {
            //Change the field.
            this.province = province;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        //Only change the field if it was provided to the model.
        if (city == null) {
            //Don't change the field.
            Log.i("200594802 and 200497768", "The city field wasn't changed because it wasn't provided to the CityOption model.");
        } else {
            //Change the field.
            this.city = city;
        }
    }
}
