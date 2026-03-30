package comp3025.assignment2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.WeatherInformation;

/**
 * This class is the ViewModel for ShowWeatherFragment.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
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
     * If another WeatherInformation model isn't provided, the existing WeatherInformation model won't be changed.
     */
    public void weatherInformationChanged(WeatherInformation weatherInformation) {
        if (weatherInformation == null) {
//ShowWeatherFragment is being created again, and isn't providing the WeatherInformation model to the ViewModel.
            //The field from this ViewModel will be used.

            //The field only needs to be changed if a WeatherInformation model was provided to this method.
            //When ShowWeatherFragment is being created, the code will provide the model that it received to this method.
            //Depending on how the code is being started, the model might not be provided.
            //The model will be provided if the code was started by MainActivity, but won't be provided if it's being created again.
        } else {
            //Change the field to this WeatherInformation model.
            this.weatherInformationMutableLiveData.postValue(weatherInformation);
        }
    }

    /**
     * This method allows the WeatherInformation model to be accessed.
     */
    public MutableLiveData<WeatherInformation> getWeatherInformationMutableLiveData() {
        return weatherInformationMutableLiveData;
    }
}
