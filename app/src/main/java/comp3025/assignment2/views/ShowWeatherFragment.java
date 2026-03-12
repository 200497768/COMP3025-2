package comp3025.assignment2.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
     * This field is the WeatherInformation model that ShowWeatherFragment has received.
     * ShowWeatherFragment is responsible for showing the fields from this model.
     */
    private WeatherInformation weatherInformation;

    /**
     * This field is the view binding class.
     */
    private FragmentShowWeatherBinding binding;

    public ShowWeatherFragment() {
//This is needed.
    }

    public ShowWeatherFragment(WeatherInformation weatherInformation) {
        this.weatherInformation = weatherInformation;

        Log.i("200497768", "ShowWeatherFragment has received a model with " + this.weatherInformation.getCityName() + " as the city name.");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Prepare the view binding class.
        this.binding = FragmentShowWeatherBinding.bind(view);

        //Show every field from the model.
        //The view binding class can only be accessed after it has been created, like during this method.
        //we're changing text during this method because the view binding class can't be accessed before this method.
        Log.i("200497768", "The view binding class is " + this.binding);
        this.binding.cityNameTextView.setText("" + this.weatherInformation.getCityName());
        this.binding.countryNameTextView.setText("" + this.weatherInformation.getCountryName());
        this.binding.currentTemperatureCTextView.setText("" + this.weatherInformation.getCurrentTemperatureC());
        this.binding.currentTemperatureFTextView.setText("" + this.weatherInformation.getCurrentTemperatureF());
        this.binding.conditionTextTextView.setText("" + this.weatherInformation.getWeatherConditionText());
        this.binding.feelsLikeCTextView.setText("" + this.weatherInformation.getFeelsLikeC());
        this.binding.humidityTextView.setText("" + this.weatherInformation.getHumidityPercentage());
        this.binding.windSpeedTextView.setText("" + this.weatherInformation.getWindSpeed());
        this.binding.windDirectionTextTextView.setText("" + this.weatherInformation.getWindDirectionText());

    }
}