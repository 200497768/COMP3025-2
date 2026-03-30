package comp3025.assignment2.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.CityOption;
import comp3025.assignment2.models.WeatherInformation;
import comp3025.assignment2.retrieval.RetrievalCode;
import comp3025.assignment2.views.CityOptionChosenAction;

/**
 * This class is the ViewModel for MainActivity.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class MainActivityViewModel extends ViewModel {

    /**
     * This field is the WeatherInformation model that MainActivity is showing at this time.
     */
    private MutableLiveData<WeatherInformation> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<WeatherInformation> getMutableLiveData() {
        return mutableLiveData;
    }

    /**
     * This field is the city option model that has been chosen.
     * This field is for the ViewModel to use.
     * When this field changes, the ViewModel needs to retrieve the WeatherInformation model, and change the fragment area.
     */
    private MutableLiveData<CityOption> cityOptionMutableLiveData = new MutableLiveData<>();

    private CityOptionChosenAction cityOptionChosenAction;

    public CityOptionChosenAction getCityOptionChosenAction() {
        return cityOptionChosenAction;
    }

    public MainActivityViewModel() {
        //Create the city option chosen action.
        MainActivityViewModel viewModel = this;
        CityOptionChosenAction cityOptionChosenAction = new CityOptionChosenAction() {

            /**
             * This method happens when ChooseCityFragment has produced a city option model.
             * In other words, this method happens when a city has been chosen.
             * This method is responsible for providing the chosen city to the ViewModel for MainActivity.
             * The ViewModel will retrieve the WeatherInformation model, and change the fragment area.
             */
            @Override
            public void cityOptionChosen(CityOption cityOption) {
//Provide this city to the ViewModel.
                viewModel.cityOptionChosen(cityOption);
            }
        };

        //Change the field.
        this.cityOptionChosenAction = cityOptionChosenAction;
    }

    /**
     * This method happens when the ViewModel receives the city option model for the chosen city.
     * In other words, this method happens when a city has been chosen.
     * This method needs to change the city option model to the WeatherInformation model.
     * In addition, this method needs to show the WeatherInformation model by changing the field from the ViewModel.
     */
    private void cityOptionChosen(CityOption cityOption) {
        Log.i("200497768", "The city option model that the ViewModel received is " + cityOption.getCity());

        //Retrieve the WeatherInformation model for the chosen city, and change the field to the WeatherInformation model.
        this.retrieveWeatherInformation(cityOption);
    }

    public MutableLiveData<CityOption> getCityOptionMutableLiveData() {
        return cityOptionMutableLiveData;
    }

    /**
     * This method starts retrieving the WeatherInformation model.
     * The code for this method changes the WeatherInformation model field when the retrieving code has finished.
     */
    public void retrieveWeatherInformation(CityOption cityOption) {
        //Create the WeatherInformation model, and show ShowWeatherFragment.
        //We can write RetrievalCode, or ExampleModelRetrievalCode.
        MainActivityViewModel mainActivityViewModel = this;
        RetrievalCode retrievalCode = new RetrievalCode(cityOption) {

            /**
             * The retrieval code has produced the WeatherInformation model.
             * This method provides the model to ShowWeatherFragment.
             */
            @Override
            public void retrieved() {
                //Change the field.
                //MainActivity includes code that will happen when this field has been changed.
                mainActivityViewModel.mutableLiveData.postValue(this.weatherInformation);
            }
        };

        //Use the retrieval code to retrieve the model.
        //When the retrieval code has completed, the retrieved method will happen.
        retrievalCode.retrieve();
    }
}
