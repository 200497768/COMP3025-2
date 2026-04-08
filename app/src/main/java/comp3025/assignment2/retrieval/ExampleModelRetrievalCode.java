package comp3025.assignment2.retrieval;

import comp3025.assignment2.models.WeatherInformation;

/**
 * This retrieval code produces the example WeatherInformation model.
 * The same example model will be produced if this code is used to retrieve it repeatedly.
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class ExampleModelRetrievalCode extends RetrievalCode {
    /**
     * This method will change the field to an example WeatherInformation model.
     */
    @Override
    public void retrieve() {
        //Produce the WeatherInformation model by using information from exampleResponseData.
        WeatherInformation weatherInformation = this.produceExampleWeatherInformation();

        //Change the field.
        this.weatherInformation = weatherInformation;

        //The retrieved method needs to happen after the model has been created.
        this.retrieved();
    }

    /**
     * This method produces an example WeatherInformation model.
     * We can use this method to determine whether we've written the retrieval code correctly.
     * We can also use this method to retrieve a WeatherInformation model with fields.
     */
    private WeatherInformation produceExampleWeatherInformation() {
        String exampleResponseData = "{\n" +
                "  \"location\": {\n" +
                "    \"name\": \"London\",\n" +
                "    \"region\": \"City of London, Greater London\",\n" +
                "    \"country\": \"United Kingdom\",\n" +
                "    \"lat\": 51.52,\n" +
                "    \"lon\": -0.11,\n" +
                "    \"tz_id\": \"Europe/London\",\n" +
                "    \"localtime_epoch\": 1613896955,\n" +
                "    \"localtime\": \"2021-02-21 8:42\"\n" +
                "  },\n" +
                "  \"current\": {\n" +
                "    \"last_updated_epoch\": 1613896210,\n" +
                "    \"last_updated\": \"2021-02-21 08:30\",\n" +
                "    \"temp_c\": 11,\n" +
                "    \"temp_f\": 51.8,\n" +
                "    \"is_day\": 1,\n" +
                "    \"condition\": {\n" +
                "      \"text\": \"Partly cloudy\",\n" +
                "      \"icon\": \"//cdn.weatherapi.com/weather/64x64/day/116.png\",\n" +
                "      \"code\": 1003\n" +
                "    },\n" +
                "    \"wind_mph\": 3.8,\n" +
                "    \"wind_kph\": 6.1,\n" +
                "    \"wind_degree\": 220,\n" +
                "    \"wind_dir\": \"SW\",\n" +
                "    \"pressure_mb\": 1009,\n" +
                "    \"pressure_in\": 30.3,\n" +
                "    \"precip_mm\": 0.1,\n" +
                "    \"precip_in\": 0,\n" +
                "    \"humidity\": 82,\n" +
                "    \"cloud\": 75,\n" +
                "    \"feelslike_c\": 9.5,\n" +
                "    \"feelslike_f\": 49.2,\n" +
                "    \"vis_km\": 10,\n" +
                "    \"vis_miles\": 6,\n" +
                "    \"uv\": 1,\n" +
                "    \"gust_mph\": 10.5,\n" +
                "    \"gust_kph\": 16.9,\n" +
                "    \"air_quality\": {\n" +
                "      \"co\": 230.3,\n" +
                "      \"no2\": 13.5,\n" +
                "      \"o3\": 54.3,\n" +
                "      \"so2\": 7.9,\n" +
                "      \"pm2_5\": 8.6,\n" +
                "      \"pm10\": 11.3,\n" +
                "      \"us-epa-index\": 1,\n" +
                "      \"gb-defra-index\": 1\n" +
                "    }\n" +
                "  }\n" +
                "}";

        //Provide this to the retrieval code, and use the retrieval code to produce the WeatherInformation model.
        WeatherInformation weatherInformation = this.getModelFromResponseData(exampleResponseData);

        return weatherInformation;
    }
}
