package comp3025.assignment2.views;

import androidx.lifecycle.ViewModel;

/**
 * This class is the ViewModel for MainActivity.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class MainActivityViewModel extends ViewModel {

    /**
     * This field maintains information about whether the action that's available is to show the chosen city, or to show the list.
     */
    private ChooseCityOrShowListAction chooseCityOrShowListAction = new ChooseCityOrShowListAction();

    public ChooseCityOrShowListAction getChooseCityOrShowListAction() {
        return chooseCityOrShowListAction;
    }
}
