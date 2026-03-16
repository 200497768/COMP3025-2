package comp3025.assignment2.retrieval;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import comp3025.assignment2.models.City;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This is the retrieval code that's responsible for retrieving the city options.
 * This code is similar to the retrieval code that retrieves the WeatherInformation model for a city.
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class CityRetrievalCode {
    public void retrieve() {
        String city = "Barrie";
        String urlString = "http://api.weatherapi.com/v1/search.json?key=b47d3ee63f574764af5163148261303&q=" + city;

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

                    List<City> cityOptions = new ArrayList<>();

                    for (int number = 0; number < json.length(); number = number + 1) {
                        //Retrieve the city with this number.
                        JSONObject cityFromResponseData = json.getJSONObject(number);

                        //Retrieve
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                //Change the field.


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
    public void retrieved(){

    }
}
