package comp3025.assignment2;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import comp3025.assignment2.models.WeatherInformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class is code that's used to retrieve weather information.
 * In order to write this code, we've started with the example code from the week 8 class (A. Perdikoulias, personal communication, March 6, 2026).
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Hao Tian
 */
public class RetrievalCode {

    private WeatherInformation weatherInformation;

    /**
     * This method retrieves the model, and changes the field.
     */
    public void retrieve() {
        String urlString = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,JPY,EUR";

        Request request = new Request.Builder().url(urlString).build();

        OkHttpClient client = new OkHttpClient();

        RetrievalCode retrievalCode = this;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Objects.requireNonNull(response.body());

                String responseData = response.body().string();
                Log.i("tag", responseData);

                //Use the method to change responseData to the WeatherInformation model.
                WeatherInformation weatherInformation = retrievalCode.getModelFromResponseData(responseData);

                //Change the field.
                retrievalCode.weatherInformation = weatherInformation;

                //The retrieved method needs to happen after the model has been created.
                retrievalCode.retrieved();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    /**
     * The retrieve method causes this method to happen after the model has been retrieved and created.
     * During this method, the weatherInformation field will be the model.
     */
    public void retrieved() {
        Log.i("tag", "Retrieval has completed.");
    }

    public WeatherInformation getModelFromResponseData(String responseData) {
        try {
            //Create the WeatherInformation model.
            //At this time, no fields have been changed.
            //This method is responsible for changing the fields to match responseData.
            WeatherInformation weatherInformation = new WeatherInformation();

            JSONObject json = new JSONObject(responseData);

            //Retrieve the location part.
            JSONObject location = json.getJSONObject("location");

            //Retrieve name of the city.
            String cityName = location.getString("name");
            weatherInformation.setCityName(cityName);

            //Retrieve name of the country.
            String countryName = location.getString("country");
            weatherInformation.setCountryName(countryName);

            //Retrieve the current part.
            JSONObject current = json.getJSONObject("current");

            //Retrieve current temperature C.
            double currentTemperatureC = current.getDouble("temp_c");
            weatherInformation.setCurrentTemperatureC(currentTemperatureC);

            //Retrieve current temperature F.
            double currentTemperatureF = current.getDouble("temp_f");
            weatherInformation.setCurrentTemperatureF(currentTemperatureF);

            //Retrieve condition part.
            JSONObject condition = current.getJSONObject("condition");

            //Retrieve condition text.
            String text = condition.getString("text");
            weatherInformation.setWeatherConditionText(text);

            //Retrieve condition picture.
            String picture = condition.getString("icon");
            weatherInformation.setWeatherConditionPicture(picture);

            //Retrieve the feels like C field.
            double feelsLikeC = current.getDouble("feelslike_c");
            weatherInformation.setFeelsLikeC(feelsLikeC);

            //Retrieve the feels like F field.
            double feelsLikeF = current.getDouble("feelslike_f");
            weatherInformation.setFeelsLikeF(feelsLikeF);

            //Retrieve the humidity field.
            int humidity = current.getInt("humidity");
            weatherInformation.setHumidityPercentage(humidity);

            //Retrieve the wind speed field.
            double windSpeed = current.getDouble("wind_kph");
            weatherInformation.setWindSpeed(windSpeed);

            //Retrieve the wind direction text.
            String windDirectionText = current.getString("wind_dir");
            weatherInformation.setWindDirectionText(windDirectionText);

            //Retrieve the wind direction angle.
            int windDirectionAngle = current.getInt("wind_degree");
            weatherInformation.setWindDirectionAngle(windDirectionAngle);

            return weatherInformation;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
