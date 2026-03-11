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

    private double feelsLikeC;

    private double feelsLikeF;

    private int humidityPercentage;

    private double windSpeed;

    private String windDirection;

    private int windAngle;

    public int getWindAngle() {
        return windAngle;
    }

    public void setWindAngle(int windAngle) {
        this.windAngle = windAngle;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
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
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
