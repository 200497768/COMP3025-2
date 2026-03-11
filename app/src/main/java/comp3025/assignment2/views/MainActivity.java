package comp3025.assignment2.views;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.ExampleCode;
import comp3025.assignment2.R;
import comp3025.assignment2.RetrievalCode;
import comp3025.assignment2.databinding.ActivityMainBinding;
import comp3025.assignment2.models.WeatherInformation;


/**
 * This is the code for assignment 2.
 * The MainActivity class can be repeatedly created in order to create views.
 * Any fields will not be maintained when the MainActivity class is created again.
 * We need to use the ViewModel in order to maintain fields, and avoid needing to retrieve information again.
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This is the view binding class for this activity.
     * The view binding class allows us to refer to views as fields, instead of using the findViewById method.
     * This field will be changed during the onCreate method.
     */
    private ActivityMainBinding binding;

    /**
     * This is the ViewModel for this activity.
     * The ViewModel allows us to access fields, even if the activity needs to be created again.
     */
    private MainActivityViewModel viewModel;

    /**
     * The onCreate method adds views to show retrieved information.
     * This method might happen repeatedly.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Prepare the view binding class.
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());

        //Change the view for this activity.
        //We'll access the view through the view binding class, instead of the R.layout.activity_main.
        setContentView(this.binding.getRoot());

        //Order is important, and the setOnApplyWindowInsetsListener method must be after the setContentView method.
        //If the setContentView method hasn't completed, the view will be null.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Create the ViewModel for this activity.
        this.viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //Use the example code to retrieve some information.
        //We'll ensure that we've brought the example code to this assignment correctly.
        //If it's able to retrieve, we'll know that we've added it correctly.
        ExampleCode exampleCode = new ExampleCode();
        exampleCode.retrieve();
        //Permission denied (missing INTERNET permission?)

        // code to load fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ChooseCityFragment())
                    .commit();
        }

        MainActivity mainActivity = this;
        this.binding.changeCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the WeatherInformation model with example information.
                //This model will be provided to ShowWeatherFragment.
                WeatherInformation exampleWeatherInformation = mainActivity.retrieveExampleWeatherInformation();

                //Create ShowWeatherFragment, and provide the model.
                Fragment firstFragment = new ShowWeatherFragment(exampleWeatherInformation);

                //Change the fragment area to show ShowWeatherFragment.
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, firstFragment);
                fragmentTransaction.commit();
            }
        });
    }

    /**
     * This method produces an example WeatherInformation model.
     * We can use this method to determine whether we've written the retrieval code correctly.
     * We can also use this method to retrieve a WeatherInformation model with fields.
     */
    private WeatherInformation retrieveExampleWeatherInformation() {
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
        RetrievalCode retrievalCode = new RetrievalCode();
        WeatherInformation weatherInformation = retrievalCode.getModelFromResponseData(exampleResponseData);

        return weatherInformation;
    }
}

//In order to fix Permission denied (missing INTERNET permission?), we used example code from this book.
//Elenkov, N. (2016). Android security internals: An in-depth guide to android’s security architecture (1st edition). No Starch Press.
//pp. 33 34
