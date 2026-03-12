package comp3025.assignment2.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}