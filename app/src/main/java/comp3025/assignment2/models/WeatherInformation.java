package comp3025.assignment2.models;

/**
 * This class is weather information that has been retrieved.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class WeatherInformation {

    /**
     * This field is the name of the city that this weather information model is for.
     */
    private String cityName;

    /**
     * This field is the name of the country that this weather information model is for.
     */
    private String countryName;

    /**
     * This field is current temperature C.
     */
    private double currentTemperatureC;

    /**
     * This field is current temperature F.
     */
    private double currentTemperatureF;

    /**
     * This field is condition as text.
     */
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

    /**
     * This field is condition as picture.
     */
    private String weatherConditionPicture;

    /**
     * This field is feels like C.
     */
    private double feelsLikeC;

    /**
     * This field is feels like F.
     */
    private double feelsLikeF;

    /**
     * This field is humidity.
     */
    private int humidityPercentage;

    /**
     * This field is wind speed.
     */
    private double windSpeed;

    /**
     * This field is wind direction written as letters, like SW.
     */
    private String windDirectionText;

    /**
     * This field is wind direction as an angle.
     */
    private int windDirectionAngle;

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
        this.windDirectionText = windDirectionText;
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
