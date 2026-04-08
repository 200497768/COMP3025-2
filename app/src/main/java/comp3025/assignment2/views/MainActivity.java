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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
