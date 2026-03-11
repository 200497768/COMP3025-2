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

    public RetrievalCode() {
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

                WeatherInformation weatherInformation = new WeatherInformation();

                try {
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
                    JSONObject condition = json.getJSONObject("condition");

                    //Retrieve condition text.
                    String text = condition.getString("text");
                    weatherInformation.setWeatherConditionText(text);

                    //Retrieve condition picture.
                    String picture=condition.getString("icon");
                    weatherInformation.setWeatherConditionPicture(picture);

                    //Retrieve the feels like C field.
                    double feelsLikeC = json.getDouble("feelslike_c");
                    weatherInformation.setFeelsLikeC(feelsLikeC);

                    //Retrieve the feels like F field.
                    double feelsLikeF = json.getDouble("feelslike_f");
                    weatherInformation.setFeelsLikeC(feelsLikeF);

                    //Retrieve the humidity field.
                    int humidity = json.getInt("humidity");
                    weatherInformation.setHumidityPercentage(humidity);

                    //Retrieve the wind speed field.
                    double windSpeed = json.getDouble("wind_kph");
                    weatherInformation.setWindSpeed(windSpeed);

                    //Retrieve the wind direction text.
                    String windDirectionText = json.getString("wind_dir");
                    weatherInformation.setWindDirectionText(windDirectionText);

                    //Retrieve the wind direction angle.
                    int windDirectionAngle = json.getInt("wind_degree");
                    weatherInformation.setWindDirectionAngle(windDirectionAngle);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                retrievalCode.weatherInformation = weatherInformation;
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }
}
