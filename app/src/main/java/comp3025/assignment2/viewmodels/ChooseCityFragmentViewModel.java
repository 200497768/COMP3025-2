package comp3025.assignment2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.CityOptions;
import comp3025.assignment2.retrieval.CityRetrievalCode;

/**
 * This class is the ViewModel for ChooseCityFragment.
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class ChooseCityFragmentViewModel extends ViewModel {

    /**
     * This field is the city options model that the fragment is showing.
     * When the city retrieval code has finished, this field will be changed.
     * The onChanged method for this fragment includes code that will show the city options after this field has been changed.
     */
    private MutableLiveData<CityOptions> mutableLiveData = new MutableLiveData<>();

    /**
     * This method allows the fragment to retrieve the city options model.
     * In addition, this method allows the fragment to provide code that will show the city options after the model has been changed.
     */
    public MutableLiveData<CityOptions> getMutableLiveData() {
        return mutableLiveData;
    }

    /**
     * This method retrieves the city option models, using the name of the city that has been written.
     * When the name of the city has been changed, the fragment provides the name of the city that has been written to this method.
     * This method uses the city retrieval code to retrieve city options.
     * When retrieval has finished, the field from this ViewModel will be changed, causing the fragment to show the models that have been retrieved.
     */
    public void retrieve(String cityName) {
        //Provide the city name to the retrieval code.
        //The action that needs to happen when city option models have been retrieved needs to be provided.
        ChooseCityFragmentViewModel chooseCityFragmentViewModel = this;
        CityRetrievalCode cityRetrievalCode = new CityRetrievalCode(cityName) {

            /**
             * The retrieval code has produced the retrieved city option models.
             * This method provides the models to ChooseCityFragment.
             */
            @Override
            public void retrieved() {
                //Change the field.
                //ChooseCityFragment includes code that will happen when this field has been changed.
                chooseCityFragmentViewModel.mutableLiveData.postValue(this.cityOptions);
            }
        };

        //Retrieve the city option models.
        cityRetrievalCode.retrieve();
    }

    /**
     * This method happens when the code from CreatedViewHolder happens because a RecyclerView has been chosen.
     * This method is responsible for showing ShowWeatherFragment for the chosen city.
     */
    public void cityChosen() {

    }
}
