package comp3025.assignment2.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.FragmentShowWeatherBinding;
import comp3025.assignment2.models.WeatherInformation;
import comp3025.assignment2.viewmodels.AuthViewModel;
import comp3025.assignment2.viewmodels.ShowWeatherFragmentViewModel;

/**
 * Shows weather information for the chosen city.
 * Part 2.1: bookmark button saves/updates city in Firestore.
 * Amber ★ = saved, grey ☆ = not saved.
 */
public class ShowWeatherFragment extends Fragment {

    private FragmentShowWeatherBinding binding;
    private ShowWeatherFragmentViewModel viewModel;
    private AuthViewModel authViewModel;
    private WeatherInformation createdWithWeatherInformation = null;

    public ShowWeatherFragment() {}

    public ShowWeatherFragment(WeatherInformation weatherInformation) {
        Log.i("200594802 and 200497768", "ShowWeatherFragment has received a model with "
                + weatherInformation.getCityName() + " as the city name.");
        this.createdWithWeatherInformation = weatherInformation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.binding   = FragmentShowWeatherBinding.bind(view);
        this.viewModel = new ViewModelProvider(this).get(ShowWeatherFragmentViewModel.class);
        this.authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        setupBookmark();
        observeAuthViewModel();

        ShowWeatherFragment showWeatherFragment = this;
        this.viewModel.getWeatherInformationMutableLiveData().observe(
                getViewLifecycleOwner(), new Observer<WeatherInformation>() {
            @Override
            public void onChanged(WeatherInformation weatherInformation) {
                MutableLiveData<WeatherInformation> liveData =
                        showWeatherFragment.viewModel.getWeatherInformationMutableLiveData();
                WeatherInformation w = liveData.getValue();

                showWeatherFragment.binding.cityNameTextView.setText("" + w.getCityName());
                showWeatherFragment.binding.provinceNameTextView.setText("" + w.getProvince());
                showWeatherFragment.binding.countryNameTextView.setText("" + w.getCountryName());
                showWeatherFragment.binding.currentTemperatureCTextView.setText("" + w.getCurrentTemperatureC() + "°C");
                showWeatherFragment.binding.currentTemperatureFTextView.setText("" + w.getCurrentTemperatureF() + "°F");
                showWeatherFragment.binding.conditionTextTextView.setText("" + w.getWeatherConditionText());
                showWeatherFragment.binding.feelsLikeCTextView.setText("Feels like " + w.getFeelsLikeC() + "°C");
                showWeatherFragment.binding.humidityTextView.setText("Humidity " + w.getHumidityPercentage() + "%");
                showWeatherFragment.binding.windSpeedTextView.setText("Wind speed " + w.getWindSpeed() + " km/h");
                showWeatherFragment.binding.windDirectionTextTextView.setText("Wind direction " + w.getWindDirectionText());
                showWeatherFragment.binding.imageView.setImageBitmap(w.getWeatherConditionPictureBitmap());

                // Check if this city is already saved
                authViewModel.checkIfCitySaved(w.getCityName(), w.getCountryName());
            }
        });

        this.viewModel.weatherInformationChanged(this.createdWithWeatherInformation);
    }

    private void setupBookmark() {
        binding.bookmarkButton.setOnClickListener(v -> {
            WeatherInformation w = viewModel.getWeatherInformationMutableLiveData().getValue();
            if (w == null) return;
            authViewModel.saveCity(w.getCityName(), w.getProvince(), w.getCountryName());
        });
    }

    private void observeAuthViewModel() {
        // Toggle star amber = saved, grey = not saved
        authViewModel.getIsCitySaved().observe(getViewLifecycleOwner(), saved -> {
            if (Boolean.TRUE.equals(saved)) {
                binding.bookmarkButton.setText("\u2605"); // ★ filled
                binding.bookmarkButton.setTextColor(0xFFF59E0B); // amber
            } else {
                binding.bookmarkButton.setText("\u2606"); // ☆ outline
                binding.bookmarkButton.setTextColor(0xFFAAC8E0); // grey-blue
            }
        });

        // Show save confirmation message
        authViewModel.getSaveMessage().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null) {
                binding.saveMessageTextView.setText(msg);
                binding.saveMessageTextView.setVisibility(View.VISIBLE);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (binding != null) binding.saveMessageTextView.setVisibility(View.GONE);
                }, 2500);
                authViewModel.consumeSaveMessage();
            }
        });
    }

    @Override
    public void onDestroyView() {
        authViewModel.resetCitySaved();
        binding = null;
        super.onDestroyView();
    }
}
