package comp3025.assignment2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.views.CompletedAction;

/**
 * This class is the ViewModel for LoginFragment.
 *
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class LoginFragmentViewModel extends ViewModel {

    /**
     * This field is the action that needs to happen after the login task has completed.
     */
    private MutableLiveData<CompletedAction> completedAction = new MutableLiveData<>();

    public MutableLiveData<CompletedAction> getCompletedAction() {
        return completedAction;
    }

    public void setCompletedAction(CompletedAction completedAction) {
        this.completedAction.postValue(completedAction);
    }
}
