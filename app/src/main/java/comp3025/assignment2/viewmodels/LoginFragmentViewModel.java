package comp3025.assignment2.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.LoginInformation;
import comp3025.assignment2.views.CompletedAction;

/**
 * This class is the ViewModel for LoginFragment.
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
     * This field is text that has been written for student number.
     */
    private MutableLiveData<String> studentNumber = new MutableLiveData<>();

    /**
     * This field is text that has been written for password.
     */
    private MutableLiveData<String> password = new MutableLiveData<>();

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
     * This method happens when any login information has changed.
     * This includes student number or password.
     * This method is responsible for creating the login information model.
     */
    public void loginInformationChanged() {
        //Retrieve the student number field.
        String studentNumber = this.studentNumber.getValue();

        //Retrieve the password field.
        String password = this.password.getValue();

        //Create the LoginInformation model.
        LoginInformation loginInformation = new LoginInformation(studentNumber, password);

        Log.i("200497768", "The LoginInformation model has been created using " + studentNumber + " and " + password);
    }

    /**
     * The code from LoginFragment causes this method to happen when text for student number has been changed.
     */
    public void studentNumberChanged(String studentNumber) {
        //Change the field.
        this.studentNumber.postValue(studentNumber);

        //Show a message explaining that the field has been changed.
        Log.i("200497768", "Student number has been changed to " + this.studentNumber.getValue());

        //Another method needs to happen when any login information has changed.
        this.loginInformationChanged();
    }

    /**
     * The code from LoginFramgent causes this method to happen when text for password has been changed.
     */
    public void passwordChanged(String password) {
        //Change the field.
        this.password.postValue(password);

        //Show a message explaining that the field has been changed.
        Log.i("200497768", "Password has been changed to " + this.password.getValue());

        //Another method needs to happen when any login information has changed.
        this.loginInformationChanged();
    }
}
