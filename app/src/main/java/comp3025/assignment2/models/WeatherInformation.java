package comp3025.assignment2.models;

/**
 * This is the model that includes weather information that has been retrieved.
 * The model includes a field for every piece of information that we need to show in ShowWeatherFragment.
 * When a city has been chosen, the code from MainActivity starts the retrieval code.
 * The retrieval code creates the model for that city, and changes the fields to match responseData.
 * The code from MainActivity provides the model to ShowWeatherFragment, and ShowWeatherFragment shows the individual fields.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class WeatherInformation {

    /**
     * This field is the name of the city that this weather information model is for.
     */
    private String cityName = "No city name";

    /**
     * This field is the name of the country that this weather information model is for.
     */
    private String countryName = "No country name";

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
    private String weatherConditionText = "No condition text";

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
    private String weatherConditionPicture = "";

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
    private String windDirectionText = "No wind direction text";

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
