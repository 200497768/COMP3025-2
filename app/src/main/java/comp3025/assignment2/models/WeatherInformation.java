package comp3025.assignment2.models;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * This is the model that includes weather information that has been retrieved.
 * The model includes a field for every piece of information that we need to show in ShowWeatherFragment.
 * When a city has been chosen, the code from MainActivity starts the retrieval code.
 * The retrieval code creates the model for that city, and changes the fields to match responseData.
 * The code from MainActivity provides the model to ShowWeatherFragment, and ShowWeatherFragment shows the individual fields.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class WeatherInformation {

    /**
     * This field is the name of the city that this weather information model is for.
     */
    private String cityName = "No city name available";

    /**
     * This field is the name of the province that this weather information model is for.
     */
    private String province = "No province available";

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        //Only change the field if it was provided to the model.
        if (province == null) {
            //Don't change the field.
            Log.i("200594802 and 200497768", "The province field wasn't changed because it wasn't provided to the WeatherInformation model.");
        } else {
            //Change the field.
            this.province = province;
        }
    }

    /**
     * This field is the name of the country that this weather information model is for.
     */
    private String countryName = "No country name available";

    /**
     * This field is current temperature C.
     */
    private double currentTemperatureC = 1;

    /**
     * This field is current temperature F.
     */
    private double currentTemperatureF = 1;

    /**
     * This field is condition as text.
     */
    private String weatherConditionText = "No condition text available";

    public String getWeatherConditionPictureString() {
        return weatherConditionPictureString;
    }

    public void setWeatherConditionPictureString(String weatherConditionPictureString) {
        //Only change the field if it was provided to the model.
        if (weatherConditionPictureString == null) {
            //Don't change the field.
            Log.i("200594802 and 200497768", "The weather condition picture string field wasn't changed because it wasn't provided to the WeatherInformation model.");
        } else {
            //Change the field.
            this.weatherConditionPictureString = weatherConditionPictureString;
        }
    }

    public String getWeatherConditionText() {
        return weatherConditionText;
    }

    public void setWeatherConditionText(String weatherConditionText) {
        this.weatherConditionText = weatherConditionText;
    }

    /**
     * This field is condition picture string.
     */
    private String weatherConditionPictureString = "";

    private Bitmap weatherConditionPictureBitmap;

    public Bitmap getWeatherConditionPictureBitmap() {
        return weatherConditionPictureBitmap;
    }

    public void setWeatherConditionPictureBitmap(Bitmap weatherConditionPictureBitmap) {
        this.weatherConditionPictureBitmap = weatherConditionPictureBitmap;
    }

    /**
     * This field is feels like C.
     */
    private double feelsLikeC = 1;

    /**
     * This field is feels like F.
     */
    private double feelsLikeF = 1;

    /**
     * This field is humidity.
     */
    private int humidityPercentage = 1;

    /**
     * This field is wind speed.
     */
    private double windSpeed = 1;

    /**
     * This field is wind direction written as letters, like SW.
     */
    private String windDirectionText = "No wind direction text available";

    /**
     * This field is wind direction as an angle.
     */
    private int windDirectionAngle = 1;

    public int getWindDirectionAngle() {
        return windDirectionAngle;
    }

    public void setWindDirectionAngle(int windDirectionAngle) {
        this.windDirectionAngle = windDirectionAngle;
    }

    public String getWindDirectionText() {
        return windDirectionText;
    }

    public void setWindDirectionText(String windDirectionText) {
        //Only change the field if it was provided to the model.
        if (windDirectionText == null) {
            //Don't change the field.
            Log.i("200594802 and 200497768", "The wind direction text field wasn't changed because it wasn't provided to the WeatherInformation model.");
        } else {
            //Change the field.
            this.windDirectionText = windDirectionText;
        }
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidityPercentage() {
        return humidityPercentage;
    }

    public void setHumidityPercentage(int humidityPercentage) {
        this.humidityPercentage = humidityPercentage;
    }

    public double getFeelsLikeF() {
        return feelsLikeF;
    }

    public void setFeelsLikeF(double feelsLikeF) {
        this.feelsLikeF = feelsLikeF;
    }

    public double getFeelsLikeC() {
        return feelsLikeC;
    }

    public void setFeelsLikeC(double feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public double getCurrentTemperatureF() {
        return currentTemperatureF;
    }

    public void setCurrentTemperatureF(double currentTemperatureF) {
        this.currentTemperatureF = currentTemperatureF;
    }

    public double getCurrentTemperatureC() {
        return currentTemperatureC;
    }

    public void setCurrentTemperatureC(double currentTemperatureC) {
        this.currentTemperatureC = currentTemperatureC;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        //Only change the field if it was provided to the model.
        if (countryName == null) {
            //Don't change the field.
            Log.i("200594802 and 200497768", "The country name field wasn't changed because it wasn't provided to the WeatherInformation model.");
        } else {
            //Change the field.
            this.countryName = countryName;
        }
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        //Only change the field if it was provided to the model.
        if (cityName == null) {
            //Don't change the field.
            Log.i("200594802 and 200497768", "The city name field wasn't changed because it wasn't provided to the WeatherInformation model.");
        } else {
            //Change the field.
            this.cityName = cityName;
        }
    }
}
