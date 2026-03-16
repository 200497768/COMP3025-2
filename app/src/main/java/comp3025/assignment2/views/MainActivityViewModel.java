package comp3025.assignment2.views;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.WeatherInformation;
import comp3025.assignment2.retrieval.RetrievalCode;

/**
 * This class is the ViewModel for MainActivity.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<WeatherInformation> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<WeatherInformation> getMutableLiveData() {
        return mutableLiveData;
    }

    /**
     * This method starts retrieving the WeatherInformation model.
     * The code for this method changes the WeatherInformation model field when the retrieving code has finished.
     */
    public void retrieve() {
        //Create the WeatherInformation model, and show ShowWeatherFragment.
        //We can write RetrievalCode, or ExampleModelRetrievalCode.
        MainActivityViewModel mainActivityViewModel = this;
        RetrievalCode retrievalCode = new RetrievalCode() {

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
