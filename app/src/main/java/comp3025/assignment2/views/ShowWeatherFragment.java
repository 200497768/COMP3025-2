package comp3025.assignment2.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.FragmentShowWeatherBinding;
import comp3025.assignment2.models.WeatherInformation;

/**
 * This fragment shows weather information for the city that has been chosen.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class ShowWeatherFragment extends Fragment {

    /**
     * This field is the view binding class.
     */
    private FragmentShowWeatherBinding binding;

    /**
     * This field is the ViewModel for this fragment.
     */
    private ShowWeatherFragmentViewModel viewModel;

    /**
     * This field is the WeatherInformation model that this fragment was created with.
     * When this fragment is being created, this field might be changed, depending on if this fragment is being created by MainActivity.
     * If this field has been changed to a WeatherInformation model, the code for this fragment will provide it to the ViewModel.
     * If a WeatherInformation model isn't provided, the ViewModel will provide the WeatherInformation model instead.
     */
    private WeatherInformation createdWithWeatherInformation = null;

    public ShowWeatherFragment() {
//This is needed.
        //MainActivity must not use this.
        //Instead, MainActivity must provide the WeatherInformation model.
    }

    public ShowWeatherFragment(WeatherInformation weatherInformation) {

        Log.i("200594802 and 200497768", "ShowWeatherFragment has received a model with " + weatherInformation.getCityName() + " as the city name.");

        //Change the field for the WeatherInformation model that this fragment was created with.
        //This model will be provided to the ViewModel.
        this.createdWithWeatherInformation = weatherInformation;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_weather, container, false);
    }

    /**
     * This method prepares the view binding class, and shows every field from the model.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //The view binding class can only be accessed after it has been created, like during this method.
        //We're changing text during this method because the view binding class can't be accessed before this method.

        //Prepare the view binding class.
        this.binding = FragmentShowWeatherBinding.bind(view);

        //Create the ViewModel for this fragment.
        this.viewModel = new ViewModelProvider(this).get(ShowWeatherFragmentViewModel.class);

        //2026-03-20 14:41:26.927 11360-11400 200594802 and 200497768 comp3025.assignment2                 I  The condition text field has been retrieved as Fog
        //2026-03-20 14:41:26.931 11360-11400 200594802 and 200497768 comp3025.assignment2                 I  The condition picture field has been retrieved as //cdn.weatherapi.com/weather/64x64/day/248.png
        //2026-03-20 14:41:26.935 11360-11400 200594802 and 200497768 comp3025.assignment2                 I  The feels like C field has been retrieved as 1.0

        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable();

        //Change what happens when the model changes.
        //This code must happen before providing the WeatherInformation model that this fragment was created with to the ViewModel.
        ShowWeatherFragment showWeatherFragment = this;
        this.viewModel.getWeatherInformationMutableLiveData().observe(getViewLifecycleOwner(), new Observer<WeatherInformation>() {
            @Override
            public void onChanged(WeatherInformation weatherInformation) {
                //Retrieve the changed WeatherInformation model.
                MutableLiveData<WeatherInformation> weatherInformationMutableLiveData = showWeatherFragment.viewModel.getWeatherInformationMutableLiveData();
                WeatherInformation changedWeatherInformation = weatherInformationMutableLiveData.getValue();

                //Show every field from the model.
                showWeatherFragment.binding.cityNameTextView.setText("" + changedWeatherInformation.getCityName());
                showWeatherFragment.binding.provinceNameTextView.setText("" + changedWeatherInformation.getProvince());
                showWeatherFragment.binding.countryNameTextView.setText("" + changedWeatherInformation.getCountryName());
                showWeatherFragment.binding.currentTemperatureCTextView.setText("" + changedWeatherInformation.getCurrentTemperatureC() + "°C");
                showWeatherFragment.binding.currentTemperatureFTextView.setText("" + changedWeatherInformation.getCurrentTemperatureF() + "°F");
                showWeatherFragment.binding.conditionTextTextView.setText("" + changedWeatherInformation.getWeatherConditionText());
                showWeatherFragment.binding.feelsLikeCTextView.setText("Feels like " + changedWeatherInformation.getFeelsLikeC() + "°C");
                showWeatherFragment.binding.humidityTextView.setText("Humidity " + changedWeatherInformation.getHumidityPercentage() + "%");
                showWeatherFragment.binding.windSpeedTextView.setText("Wind speed " + changedWeatherInformation.getWindSpeed() + " km/h");
                showWeatherFragment.binding.windDirectionTextTextView.setText("" + changedWeatherInformation.getWindDirectionText());
            }
        });

        //This fragment was created with a WeatherInformation model.
        //Provide the WeatherInformation model that this fragment was created with to the ViewModel.
        //This code must only happen after the ViewModel has been created.
        this.viewModel.weatherInformationChanged(this.createdWithWeatherInformation);

    }
}