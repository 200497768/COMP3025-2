package comp3025.assignment2.models;

/**
 * This class is weather information that has been retrieved.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class WeatherInformation {
    private String cityName;

    private String countryName;

    private double currentTemperatureC;

    private double currentTemperatureF;

    private String weatherConditionText;

    public String getWeatherConditionPicture() {
        return weatherConditionPicture;
    }

    public void setWeatherConditionPicture(String weatherConditionPicture) {
        this.weatherConditionPicture = weatherConditionPicture;
    }

    public String getWeatherConditionText() {
        return weatherConditionText;
    }

    public void setWeatherConditionText(String weatherConditionText) {
        this.weatherConditionText = weatherConditionText;
    }

    private String weatherConditionPicture;

    private double feelsLike;

    private int humidityPercentage;

    private int windSpeed;

    private String windDirection;

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getHumidityPercentage() {
        return humidityPercentage;
    }

    public void setHumidityPercentage(int humidityPercentage) {
        this.humidityPercentage = humidityPercentage;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
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
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
