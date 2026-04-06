package comp3025.assignment2.retrieval;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import comp3025.assignment2.models.CityOption;
import comp3025.assignment2.models.CityOptions;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This is the retrieval code that's responsible for retrieving the city options.
 * This code is similar to the retrieval code that retrieves the WeatherInformation model for a city.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class CityRetrievalCode {

    /**
     * This field is the city option models that have been retrieved.
     * When CityRetrievalCode has been created, no models exist.
     * During the retrieve method, CityRetrievalCode retrieves the city option models, and adds the models to this field.
     * When retrieval has completed, CityRetrievalCode causes the retrieved method to happen.
     * This field can be accessed by extending this class, and providing code for the retrieved method.
     * Accessing this field from the retrieved method will avoid problems that can happen if the field is accessed before CityRetrievalCode has finished retrieval.
     * CityRetrievalCode ensures that the retrieved method only happens after the retrieval has completed, and not during or before retrieval has finished.
     */
    public CityOptions cityOptions = new CityOptions();

    /**
     * This field is the city name that was written, and needs to be retrieved.
     * The city retrieval code needs to retrieve city option models with a city name that's similar to this name.
     */
    private String neededCityName;

    public CityRetrievalCode(String neededCityName) {
        this.neededCityName = neededCityName;
    }

    /**
     * This method retrieves the city option models.
     */
    public void retrieve() {
        String urlString = "http://api.weatherapi.com/v1/search.json?key=b47d3ee63f574764af5163148261303&q=" + this.neededCityName;

        Request request = new Request.Builder().url(urlString).build();

        OkHttpClient client = new OkHttpClient();

        CityRetrievalCode retrievalCode = this;
        client.newCall(request).enqueue(new Callback() {

            /**
             * This method creates the WeatherInformation model.
             */
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Objects.requireNonNull(response.body());

                String responseData = response.body().string();
                Log.i("200594802 and 200497768", responseData);

                //Produce the WeatherInformation model by using information from responseData.

                //Retrieve the city model part.
                try {
                    JSONArray json = new JSONArray(responseData);

                    for (int number = 0; number < json.length(); number = number + 1) {
                        //Retrieve the city with this number.
                        JSONObject cityFromResponseData = json.getJSONObject(number);

                        //Retrieve city name.
                        String cityName = cityFromResponseData.getString("name");

                        //Retrieve province.
                        String province = cityFromResponseData.getString("region");

                        //Retrieve country.
                        String country = cityFromResponseData.getString("country");

                        //Retrieve lat.
                        double lat = cityFromResponseData.getDouble("lat");

                        //Retrieve lon.
                        double lon = cityFromResponseData.getDouble("lon");

                        //Create the city option model.
                        CityOption cityOption = new CityOption();
                        cityOption.setCity(cityName);
                        cityOption.setProvince(province);
                        cityOption.setCountry(country);
                        cityOption.setLat(lat);
                        cityOption.setLon(lon);

                        //Add the city option model to the list.
                        retrievalCode.cityOptions.addCityOption(cityOption);
                    }

                } catch (JSONException e) {
                    Log.i("200594802 and 200497768", "Couldn't retrieve city option models.");
                    Log.i("200594802 and 200497768", "" + e);
                }

                //Change the field.
                retrievalCode.cityOptions = cityOptions;

                //The retrieved method needs to happen after the model has been created.
                retrievalCode.retrieved();
            }

            /**
             * This method doesn't create the WeatherInformation model.
             */
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("200594802 and 200497768", "The WeatherInformation model wasn't retrieved.");
                Log.i("200594802 and 200497768", "" + e);
            }
    });
}

    /**
     * This method happens when the city option models have been created, and added to the field.
     * The retrieve method causes this method to happen.
     * The class that's extending the CityRetrievalCode class can provide code for this method.
     * The code for this method can access the city option models through the cityOptions field.
     */
    public void retrieved(){

    }
}
