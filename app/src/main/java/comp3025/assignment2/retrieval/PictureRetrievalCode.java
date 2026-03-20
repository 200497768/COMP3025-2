package comp3025.assignment2.retrieval;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class is code
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Hao Tian
 */
public class PictureRetrievalCode {

    private String urlString;

    public Bitmap bitmap;


    public PictureRetrievalCode(String urlString) {
        this.urlString = urlString;
    }

    /**
     * This method retrieves the model, and changes the field.
     * If the retrieval code is extended, when the model is available, this method must change the field and use the retrieved method.
     */
    public void retrieve() {

        //Create a request.
        Request request = new Request.Builder().url("http:" + this.urlString).build();

        OkHttpClient client = new OkHttpClient();

        PictureRetrievalCode retrievalCode = this;
        client.newCall(request).enqueue(new Callback() {

            /**
             * This method creates the
             */
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Objects.requireNonNull(response.body());

                InputStream inputStream = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//need APA

                //Change the field.
                retrievalCode.bitmap = bitmap;

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
     * The retrieve method causes this method to happen after the model has been retrieved and created.
     * During this method, the weatherInformation field will be the model.
     */
    public void retrieved() {
        Log.i("200594802 and 200497768", "Retrieval has completed.");
    }

}
