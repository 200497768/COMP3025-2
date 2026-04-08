package comp3025.assignment2.views;

import android.content.Intent;
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
import comp3025.assignment2.models.CityOption;
import comp3025.assignment2.models.WeatherInformation;
<<<<<<< HEAD
import comp3025.assignment2.viewmodels.AuthViewModel;
import comp3025.assignment2.viewmodels.MainActivityViewModel;

/**
 * Main activity: handles Search / Saved / Settings bottom navigation,
 * sign-out, and fragment switching.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private AuthViewModel authViewModel;

=======
import comp3025.assignment2.viewmodels.MainActivityViewModel;


/**
 * This is the code for assignment 2.
 * MainActivity is responsible for changing the fragment area to ChooseCityFragment or ShowWeatherFragment.
 * ChooseCityFragment is used to show a list, and allow a city to be chosen.
 * ShowWeatherFragment is used to show weather information for a particular city.
 * MainActivity also includes references for the rest of the code.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
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
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

<<<<<<< HEAD
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

=======
        //Prepare the view binding class.
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());

        //Change the view for this activity.
        //We'll access the view through the view binding class, instead of the R.layout.activity_main.
        setContentView(this.binding.getRoot());

        //Order is important, and the setOnApplyWindowInsetsListener method must be after the setContentView method.
        //If the setContentView method hasn't completed, the view will be null.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

<<<<<<< HEAD
        this.viewModel     = new ViewModelProvider(this).get(MainActivityViewModel.class);
        this.authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // Observe weather data changes
        MainActivity mainActivity = this;
        MainActivityViewModel vm = this.viewModel;
        this.viewModel.getMutableLiveData().observe(this, new Observer<WeatherInformation>() {
            @Override
            public void onChanged(WeatherInformation weatherInformation) {
                WeatherInformation w = vm.getMutableLiveData().getValue();
                mainActivity.changeFragmentAreaShowWeatherFragment(w);
            }
        });

        // Legacy buttons (kept functional)
        this.binding.chooseAnotherCityButton.setOnClickListener(v ->
                mainActivity.changeFragmentAreaChooseCityFragment());

        this.binding.chooseCityButton.setOnClickListener(v -> {
            CityOption cityOption = new CityOption();
            cityOption.setCity("Barrie");
            cityOption.setLon(44.38);
            cityOption.setLat(-79.7);
            mainActivity.viewModel.retrieveWeatherInformation(cityOption);
        });

        // Sign out button
        this.binding.logoffButton.setOnClickListener(v -> {
            authViewModel.signOut();
            Intent intent = new Intent(MainActivity.this, LoginRegistrationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // Bottom navigation
        this.binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_search) {
                changeFragmentAreaChooseCityFragment();
                return true;
            } else if (id == R.id.nav_saved) {
                changeFragmentAreaSavedCities();
                return true;
            } else if (id == R.id.nav_sign_out) {
                authViewModel.signOut();
                Intent signOutIntent = new Intent(MainActivity.this, LoginRegistrationActivity.class);
                signOutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(signOutIntent);
                return true;
            }
            return false;
        });

        // Start on Search tab
        this.changeFragmentAreaChooseCityFragment();
    }

    public void changeFragmentAreaShowWeatherFragment(WeatherInformation weatherInformation) {
        this.binding.chooseAnotherCityButton.setVisibility(View.VISIBLE);
        Fragment fragment = new ShowWeatherFragment(weatherInformation);
        replaceFragment(fragment);
    }

    public void changeFragmentAreaChooseCityFragment() {
        this.binding.chooseAnotherCityButton.setVisibility(View.INVISIBLE);
        Fragment fragment = new ChooseCityFragment(
                getApplicationContext(), this.viewModel.getCityOptionChosenAction());
        replaceFragment(fragment);
    }

    public void changeFragmentAreaSavedCities() {
        this.binding.chooseAnotherCityButton.setVisibility(View.INVISIBLE);
        replaceFragment(new SavedCitiesFragment());
    }

    // Called by SavedCitiesFragment when tapping a saved city
    public void showWeatherTabFromSaved() {
        this.binding.bottomNavigation.setSelectedItemId(R.id.nav_search);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    // Legacy
    public void logoffRequested() {
        startActivity(new Intent(MainActivity.this, DispositionActivity.class));
    }
}
=======
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
                CityOption cityOption = new CityOption();
                cityOption.setCity("Barrie");
                cityOption.setLon(44.38);
                cityOption.setLat(-79.7);

                //Use the ViewModel to retrieve the WeatherInformation model for this city.
                //The ViewModel will change the WeatherInformation model field when this method has finished retrieving.
                //When the ViewModel changes the field, that will cause the onChanged method to happen.
                mainActivity.viewModel.retrieveWeatherInformation(cityOption);
            }
        });

        //Change the action that will happen when choosing the LOGOFF option.
        this.binding.logoffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use the method to request LOGOFF.
                mainActivity.logoffRequested();
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

    /**
     * This method happens when LOGOFF has been requested.
     */
    public void logoffRequested() {
        //Create an explicit intent that refers to DispositionActivity.
        Intent intent = new Intent(MainActivity.this, DispositionActivity.class);

        startActivity(intent);
    }
}

//In order to fix "Permission denied (missing INTERNET permission?)", we added some code from a book.
//We needed to add "<uses-permission android:name="android.permission.INTERNET" />" to the code (Elenkov, 2016, pp. 33–34).

//In order to fix a problem with communication, we needed to add some code.
//"    android:usesCleartextTraffic="true">" (Solar2D, 2020).

//For CityRetrevialCode, I needed to refer to (Piwowarek, 2025) in order to understand how I can access each city.
//The example code provided by this source shows similar data, and in the example, the family item is retrieved.

//I learned how I can cause code to happen when a Textview item is changed.
//This was from (CodePath, n.d.).

//We used code from (All for Android, Android for All, 2015) and (Petzl, 2024) to change how the CityOption items appear.

//References
//All for Android, Android for All. (2015). How to create a layout with rounded corner borders in Android ? https://www.ssaurel.com/blog/how-to-create-a-layout-with-rounded-corner-borders-in-android/
//CodePath. (n.d.). Basic Event Listeners. https://guides.codepath.org/android/Basic-Event-Listeners
//DiMarzio, J. (2016). Beginning Android Programming with Android Studio (4th ed.). Wrox.
//Elenkov, N. (2016). Android security internals: An in-depth guide to android’s security architecture (1st edition). No Starch Press.
//Martin, E. (2026). Decode an OkHttp JSON Response. https://www.baeldung.com/okhttp-json-response
//Petzl, S. (2024). How to Put a Border Around an Android TextView. https://www.repeato.app/how-to-put-a-border-around-an-android-textview/
//Piwowarek, G. (2025). Getting a Value in JSONObject. https://www.baeldung.com/java-jsonobject-get-value
//Solar2D. (2020). Network Security Configuration - Clear text traffic permitted. https://forums.solar2d.com/t/network-security-configuration-clear-text-traffic-permitted/350414

>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
