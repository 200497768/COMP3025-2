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
 * This class is code that retrieves a picture.
 * In order to use this class, a string must be provided.
 * For the assignment, the string that will be provided to this class is the picture string from the WeatherInformation model.
 * This class can still be used outside of the assignment to retrieve a picture.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class PictureRetrievalCode {

    /**
     * This field is the string that will be used to retrieve the picture.
     * We must ensure that this string is correct.
     * If this string isn't correct, the retrieval code will cause a problem when providing the string to okhttp.
     */
    private String urlString;

    /**
     * This field is the picture that has been retrieved.
     * This field is only available during the retrieved method, or after it has finished.
     * Accessing this field during the retrieved method is recommended.
     */
    public Bitmap bitmap;

    public PictureRetrievalCode(String urlString) {
        this.urlString = urlString;
    }

    /**
     * This method retrieves the picture using the string that was provided.
     * The method from okhttp provides the picture as InputStream (Martin, 2026).
     * This needs to be changed to Bitmap in order to show it in a view.
     * "The decodeStream() method from the BitmapFactory class" can be used in order to change InputStream to Bitmap (DiMarzio, 2016, p. 357).
     */
    public void retrieve() {

        //Create a request.
        Request request = new Request.Builder().url(this.urlString).build();

        OkHttpClient client = new OkHttpClient();

        PictureRetrievalCode retrievalCode = this;
        client.newCall(request).enqueue(new Callback() {

            /**
             * This method creates the picture, and causes the retrieved method to happen.
             */
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Objects.requireNonNull(response.body());

                //Create the picture by retrieving it as InputStream (Martin, 2026).
                InputStream inputStream = response.body().byteStream();

                //Change InputStream to Bitmap (DiMarzio, 2016, p. 357).
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                //Change the field.
                retrievalCode.bitmap = bitmap;

                //The retrieved method needs to happen after the picture has been created, and the field has been changed to the picture.
                retrievalCode.retrieved();
            }

            /**
             * This method doesn't create the picture.
             */
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("200594802 and 200497768", "The picture wasn't retrieved.");
                Log.i("200594802 and 200497768", "" + e);
            }
        });
    }

    /**
     * The retrieve method causes this method to happen after the picture has been retrieved and created.
     * During this method, the weatherInformation bitmap field will be the picture.
     */
    public void retrieved() {
        Log.i("200594802 and 200497768", "Retrieval has completed.");
    }

}
