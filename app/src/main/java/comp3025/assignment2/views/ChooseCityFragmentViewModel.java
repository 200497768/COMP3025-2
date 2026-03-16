package comp3025.assignment2.views;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.City;

/**
 * This class is the ViewModel for ChooseCityFragment.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class ChooseCityFragmentViewModel extends ViewModel {
    private MutableLiveData<City> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<City> getMutableLiveData() {
        return mutableLiveData;
    }
}
