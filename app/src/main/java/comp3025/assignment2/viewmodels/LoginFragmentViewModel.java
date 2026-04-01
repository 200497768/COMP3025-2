package comp3025.assignment2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.LoginInformation;
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

    /**
     * This method returns the completed action field that was provided to the ViewModel.
     */
    public MutableLiveData<CompletedAction> getCompletedAction() {
        return completedAction;
    }

    /**
     * This method changes the completed action field.
     * If a completed action isn't provided to this method, the field won't be changed.
     * If the fragment doesn't provide a completed action to change the field to, the existing field will be used.
     * This can happen if the fragment is being created again.
     */
    public void changeCompletedAction(CompletedAction completedAction) {
        //Change the completed action field for the ViewModel, if it was provided to this fragment.
        if (completedAction == null) {
//The completed action wasn't provided to this fragment.
            //This can happen if the fragment is being created again.
            //Since this fragment was created before, the completed action field was changed for the existing ViewModel.
            //The field for the ViewModel won't be changed, so the existing completed action will be used.
        } else {
            //Change the completed action field.
            this.completedAction.postValue(completedAction);
        }
    }

    /**
     * This method creates a LoginInformation model.
     */
    public LoginInformation createLoginInformationModel(){

    }

    /**
     * This method happens when the student number has changed.
     */
    public void studentNumberChanged(String studentNumber) {
    }
}
