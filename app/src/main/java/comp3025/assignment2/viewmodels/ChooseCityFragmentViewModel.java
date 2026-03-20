package comp3025.assignment2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.CityOptions;
import comp3025.assignment2.retrieval.CityRetrievalCode;

/**
 * This class is the ViewModel for ChooseCityFragment.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class ChooseCityFragmentViewModel extends ViewModel {
    private MutableLiveData<CityOptions> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<CityOptions> getMutableLiveData() {
        return mutableLiveData;
    }

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
