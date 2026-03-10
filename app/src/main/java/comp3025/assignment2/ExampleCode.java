package comp3025.assignment2;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class includes the example code from the week 8 class (A. Perdikoulias, personal communication, March 6, 2026).
 * We've change code slightly.
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Hao Tian
 */
public class ExampleCode {
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(callback);
    }

    public void retrieve() {
        String urlString = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,JPY,EUR";

        this.get(urlString, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Objects.requireNonNull(response.body());

                String responseData = response.body().string();
                Log.i("tag", responseData);

                try {
                    JSONObject json = new JSONObject(responseData);
                    String firstNumber = json.getString("USD");
                    Log.i("tag", "The first number retrieved is " + firstNumber);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }
}
