package comp3025.assignment2.views;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.WeatherInformation;

/**
 * This class is the ViewModel for ShowWeatherFragment.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class ShowWeatherFragmentViewModel extends ViewModel {

    /**
     * This field is the WeatherInformation model that ShowWeatherFragment has received.
     * ShowWeatherFragment is responsible for showing the fields from this model.
     */
    private MutableLiveData<WeatherInformation> weatherInformationMutableLiveData = new MutableLiveData<>();

    /**
     * This method changes the WeatherInformation model for this ViewModel.
     */
    public void weatherInformationChanged(WeatherInformation weatherInformation) {
        if (weatherInformation == null) {
//ShowWeatherFragment is being created again, and isn't providing the WeatherInformation model to the ViewModel.
            //The field from this ViewModel will be used.
        } else {
            this.weatherInformationMutableLiveData.postValue(weatherInformation);
        }
    }

    public MutableLiveData<WeatherInformation> getWeatherInformationMutableLiveData() {
        return weatherInformationMutableLiveData;
    }
}
