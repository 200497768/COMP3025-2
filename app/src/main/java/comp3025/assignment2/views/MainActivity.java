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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.ActivityMainBinding;
import comp3025.assignment2.models.City;
import comp3025.assignment2.models.WeatherInformation;


/**
 * This is the code for assignment 2.
 * MainActivity is responsible for changing the fragment area to ChooseCityFragment or ShowWeatherFragment.
 * ChooseCityFragment is used to show a list, and allow a city to be chosen.
 * ShowWeatherFragment is used to show weather information for a particular city.
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
     * The code that we write will access fields through the ViewModel.
     * The ViewModel allows us to maintain the fields, even if the activity needs to be created again.
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

        //Add the code that will happen when the WeatherInformation model from the ViewModel changes.
        MainActivity mainActivity = this;
        MainActivityViewModel viewModel = this.viewModel;
        this.viewModel.getMutableLiveData().observe(this, new Observer<WeatherInformation>() {

            /**
             * This method happens when the WeatherInformation model from the ViewModel has been changed.
             * The code for this method changes the fragment area to show ShowWeatherFragment.
             */
            @Override
            public void onChanged(WeatherInformation weatherInformation) {
                //Change the fragment area to show ShowWeatherFragment.
                //Retrieve the WeatherInformation model from the ViewModel, and provide it to ShowWeatherFragment.
                WeatherInformation changedWeatherInformation = viewModel.getMutableLiveData().getValue();
                mainActivity.changeFragmentAreaShowWeatherFragment(changedWeatherInformation);
            }
        });

        //Change the action so that another city can be chosen.
        mainActivity.binding.chooseAnotherCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change the fragment area to show ChooseCityFragment.
                mainActivity.changeFragmentAreaChooseCityFragment();
            }
        });

        //Change the action that will happen when choosing the action to show a city.
        this.binding.chooseCityButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method needs to show ShowWeatherFragment.
             * ShowWeatherFragment receives a WeatherInformation model, with information for the chosen city.
             * This method is responsible for creating that model, including retrieving information, and providing it to ShowWeatherFragment.
             */
            @Override
            public void onClick(View v) {
                City city = new City();
                city.setCity("Barrie");

                //Use the ViewModel to retrieve the WeatherInformation model for this city.
                //The ViewModel will change the WeatherInformation model field when this method has finished retrieving.
                //When the ViewModel changes the field, that will cause the onChanged method to happen.
                mainActivity.viewModel.retrieveWeatherInformation(city);
            }
        });


        //Show ChooseCityFragment when starting.
        this.changeFragmentAreaChooseCityFragment();
    }

    /**
     * This method changes the fragment area to show ShowWeatherFragment.
     * ShowWeatherFragment will show the fields from a WeatherInformation model.
     * The WeatherInformation model for the city that has been chosen needs to be provided to this method.
     */
    public void changeFragmentAreaShowWeatherFragment(WeatherInformation weatherInformation) {
        //Show the action to choose another city.
        this.binding.chooseAnotherCityButton.setVisibility(View.VISIBLE);

        //Create ShowWeatherFragment, and provide the model.
        Fragment showWeatherFragment = new ShowWeatherFragment(weatherInformation);

        //Change the fragment area to show ShowWeatherFragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, showWeatherFragment);
        fragmentTransaction.commit();
    }

    /**
     * This method changes the fragment area to show ChooseCityFragment.
     * ChooseCityFragment allows another city to be chosen.
     */
    public void changeFragmentAreaChooseCityFragment() {
        //Don't show the action to choose another city.
        this.binding.chooseAnotherCityButton.setVisibility(View.INVISIBLE);

        //Create ChooseCityFragment, and provide the model.

        //We need to create CityOptionChosenAction first.
        //This is the action that needs to happen when a city has been chosen during ChooseCityFragment.

        Fragment chooseCityFragment = new ChooseCityFragment(getApplicationContext(), this.viewModel.getCityOptionChosenAction());

        //Change the fragment area to show ChooseCityFragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, chooseCityFragment);
        fragmentTransaction.commit();
    }

}

//In order to fix "Permission denied (missing INTERNET permission?)", we added some code from a book.
//We needed to add "<uses-permission android:name="android.permission.INTERNET" />" to the code (Elenkov, 2016, pp. 33–34).

//In order to fix a problem with communication, we needed to add some code.
//"    android:usesCleartextTraffic="true">" (Solar2D, 2020).

//For CityRetrevialCode, I needed to refer to (Piwowarek, 2025) in order to understand how I can access each city.
//The example code provided by this source shows similar data, and in the example, the family item is retrieved.

//References
//Elenkov, N. (2016). Android security internals: An in-depth guide to android’s security architecture (1st edition). No Starch Press.
//Piwowarek, G. (2025). Getting a Value in JSONObject. https://www.baeldung.com/java-jsonobject-get-value
//Solar2D. (2020). Network Security Configuration - Clear text traffic permitted. https://forums.solar2d.com/t/network-security-configuration-clear-text-traffic-permitted/350414
