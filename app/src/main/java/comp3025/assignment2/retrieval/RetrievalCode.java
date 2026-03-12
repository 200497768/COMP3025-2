package comp3025.assignment2.retrieval;

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
 * The retrieval code includes the retrieve method, and the retrieved method.
 * The retrieve method is responsible for retrieving weather information, and creating the model.
 * The retrieved method is responsible for using the WeatherInformation model that has been created by the retrieve method.
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Hao Tian
 */
public class RetrievalCode {

    /**
     * This field is the WeatherInformation model, with information about the chosen city.
     * This field will only be available after the retrieve method has finished.
     * Accessing this field using the retrieved method is recommended.
     * The retrieve method will cause the retrieved method to happen after the model has been created, and this field has been changed.
     * If this field is accessed another way, the retrieve method might not have changed it to the model.
     */
    public WeatherInformation weatherInformation;

    /**
     * This method retrieves the model, and changes the field.
     * If the retrieval code is extended, when the model is available, this method must change the field and use the retrieved method.
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

                //Produce the WeatherInformation model by using information from responseData.
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

    /**
     * This method changes responseData to the WeatherInformation model.
     * When this method starts, this method needs to create the model.
     * This method is responsible for changing the fields to match responseData.
     * The repeated code is needed so that if a single field can't be retrieved, the remaining fields won't be affected.
     * A field might not be able to be retrieved if it's not included in responseData.
     * This method has been written so that for every field that needs to be changed, the code retrieves part of responseData.
     * The code retrieves the field from responseData, and changes the corresponding field for the model.
     */
    public WeatherInformation getModelFromResponseData(String responseData) {
        //Create the WeatherInformation model.
        //At this time, no fields have been changed.
        WeatherInformation weatherInformation = new WeatherInformation();

        Log.i("tag", "The retrieval code will start changing fields for the model.");

        //Change the field for name of the city to match responseData.
        try {
            //Retrieve the location part.
            JSONObject json = new JSONObject(responseData);
            JSONObject location = json.getJSONObject("location");

            //Retrieve name of the city.
            String cityName = location.getString("name");

            //Change the field for the model.
            weatherInformation.setCityName(cityName);
            Log.i("tag", "The name of the city field has been retrieved as " + weatherInformation.getCityName());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the name of the city field.");
        }

        //Change the field for name of the country to match responseData.
        try {
            //Retrieve the location part.
            JSONObject json = new JSONObject(responseData);
            JSONObject location = json.getJSONObject("location");

            //Retrieve name of the country.
            String countryName = location.getString("country");

            //Change the field for the model.
            weatherInformation.setCountryName(countryName);
            Log.i("tag", "The name of the country field has been retrieved as " + weatherInformation.getCountryName());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the name of the country field.");
        }

        //Change the field for current temperature C to match responseData.
        try {
            //Retrieve the current part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");

            //Retrieve current temperature C.
            double currentTemperatureC = current.getDouble("temp_c");

            //Change the field for the model.
            weatherInformation.setCurrentTemperatureC(currentTemperatureC);
            Log.i("tag", "The current temperature C field has been retrieved as " + weatherInformation.getCurrentTemperatureC());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the current temperature C field.");
        }

        //Change the field for current temperature F to match responseData.
        try {
            //Retrieve the current part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");

            //Retrieve current temperature F.
            double currentTemperatureF = current.getDouble("temp_f");

            //Change the field for the model.
            weatherInformation.setCurrentTemperatureC(currentTemperatureF);
            Log.i("tag", "The current temperature F field has been retrieved as " + weatherInformation.getCurrentTemperatureF());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the current temperature F field.");
        }

        //Change the field for condition text to match responseData.
        try {
            //Retrieve condition part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");
            JSONObject condition = current.getJSONObject("condition");

            //Retrieve condition text.
            String text = condition.getString("text");

            //Change the field for the model.
            weatherInformation.setWeatherConditionText(text);
            Log.i("tag", "The condition text field has been retrieved as " + weatherInformation.getWeatherConditionText());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the condition text field.");
        }

        //Change the field for condition picture to match responseData.
        try {
            //Retrieve condition part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");
            JSONObject condition = current.getJSONObject("condition");

            //Retrieve condition picture.
            String picture = condition.getString("icon");

            //Change the field for the model.
            weatherInformation.setWeatherConditionPicture(picture);
            Log.i("tag", "The condition picture field has been retrieved as " + weatherInformation.getWeatherConditionPicture());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the condition picture field.");
        }

        //Change the field for feels like C to match responseData.
        try {
            //Retrieve the current part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");

            //Retrieve the feels like C field.
            double feelsLikeC = current.getDouble("feelslike_c");
            Log.i("tag", "The feels like C field has been retrieved as " + weatherInformation.getFeelsLikeC());

            //Change the field for the model.
            weatherInformation.setFeelsLikeC(feelsLikeC);
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the feels like C field.");
        }

        //Change the field for feels like F to match responseData.
        try {
            //Retrieve the current part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");

            //Retrieve the feels like F field.
            double feelsLikeF = current.getDouble("feelslike_c");

            //Change the field for the model.
            weatherInformation.setFeelsLikeC(feelsLikeF);
            Log.i("tag", "The feels like F field has been retrieved as " + weatherInformation.getFeelsLikeF());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the feels like F field.");
        }

        //Change the field for humidity to match responseData.
        try {
            //Retrieve the current part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");

            //Retrieve the humidity field.
            int humidity = current.getInt("humidity");

            //Change the field for the model.
            weatherInformation.setHumidityPercentage(humidity);
            Log.i("tag", "The humidity field has been retrieved as " + weatherInformation.getHumidityPercentage());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the humidity field.");
        }

        //Change the field for wind speed to match responseData.
        try {
            //Retrieve the current part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");

            //Retrieve the wind speed field.
            double windSpeed = current.getDouble("wind_kph");

            //Change the field for the model.
            weatherInformation.setWindSpeed(windSpeed);
            Log.i("tag", "The wind speed field has been retrieved as " + weatherInformation.getWindSpeed());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the wind speed field.");
        }

        //Change the field for wind direction text to match responseData.
        try {
            //Retrieve the current part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");

            //Retrieve the wind direction text.
            String windDirectionText = current.getString("wind_dir");

            //Change the field for the model.
            weatherInformation.setWindDirectionText(windDirectionText);
            Log.i("tag", "The wind direction text field has been retrieved as " + weatherInformation.getWindDirectionText());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the wind direction text field.");
        }

        //Change the field for wind direction angle to match responseData.
        try {
            //Retrieve the current part.
            JSONObject json = new JSONObject(responseData);
            JSONObject current = json.getJSONObject("current");

            //Retrieve the wind direction angle.
            int windDirectionAngle = current.getInt("wind_degree");

            //Change the field for the model.
            weatherInformation.setWindDirectionAngle(windDirectionAngle);
            Log.i("tag", "The wind direction angle field has been retrieved as " + weatherInformation.getWindDirectionAngle());
        } catch (JSONException e) {
            Log.i("tag", "JSONException when retrieving the wind direction angle field.");
        }

        Log.i("tag", "The retrieval code has finished changing fields for the model.");

        return weatherInformation;

    }
}
