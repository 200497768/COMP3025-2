package comp3025.assignment2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import comp3025.assignment2.models.LoginInformation;

/**
 * This class is the ViewModel for DispositionActivity.
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class DispositionActivityViewModel extends ViewModel {

    /**
     * This field the LoginInformation model that this ViewModel was created with.
     */
    private LoginInformation loginInformation = new LoginInformation();

    /**
     * This field is text explaining whether the disposition task has been completed.
     */
    private MutableLiveData<String> completedText = new MutableLiveData<>();

    /**
     * This method changes the LoginInformation model.
     * If the model hasn't been provided to this method, this method won't change the field.
     */
    public void changeLoginInformation(LoginInformation loginInformation) {
        //Change the loginInformation field, but only if the field was provided.
        if (loginInformation == null) {
            //Don't change the field.
        } else {
            //Change the field.
            this.loginInformation = loginInformation;
        }
    }

    /**
     * This method returns the completed text field.
     */
    public MutableLiveData<String> getCompletedText() {
        return completedText;
    }

    /**
     * The code from DispositionActivity causes this to happen when the no record option has been chosen.
     */
    public void noRecordChosen() {
        this.completedText.postValue("Completed");
    }
}
