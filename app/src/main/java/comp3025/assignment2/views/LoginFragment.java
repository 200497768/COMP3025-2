package comp3025.assignment2.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.FragmentLoginBinding;
import comp3025.assignment2.viewmodels.LoginFragmentViewModel;

/**
 * This fragment includes views for writing login information.
 *
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class LoginFragment extends Fragment {

    /**
     * This field is the view binding class.
     */
    private FragmentLoginBinding binding;

    /**
     * This field is the ViewModel for this fragment.
     */
    private LoginFragmentViewModel viewModel;

    /**
     * This field is the action that needs to happen after the login task has completed.
     * The fragment is responsible for providing this field to the ViewModel.
     * This field must only be accessed during the onViewCreated method.
     * After that method, this fragment is supposed to access the completed action through the ViewModel, instead of this field.
     */
    private CompletedAction completedAction;

    public LoginFragment() {
//This is needed.
        //LoginRegistrationActivity must not use this.
        //Instead, LoginRegistrationActivity must provide LoginCompletedAction.
    }

    public LoginFragment(CompletedAction completedAction) {
        this.completedAction = completedAction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    /**
     * This method prepares the view binding class, and shows every field from the model.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //The view binding class can only be accessed after it has been created, like during this method.
        //We're changing text during this method because the view binding class can't be accessed before this method.

        //Prepare the view binding class.
        this.binding = FragmentLoginBinding.bind(view);

        //Create the ViewModel for this fragment.
        this.viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);

//Provide the completed action to the ViewModel.
        //If a completed action wasn't provided to this fragment when it was created, the field for the ViewModel won't be changed.
        this.viewModel.changeCompletedAction(this.completedAction);

        //Change the action that happens when the login task has been completed.
        LoginFragment loginFragment = this;
        this.binding.completeButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method happens when the login task has been completed.
             * This method needs to cause the completed action for LoginFragment to happen.
             */
            @Override
            public void onClick(View v) {
                //Retrieve the completed action from the ViewModel.
                CompletedAction completedAction = loginFragment.viewModel.getCompletedAction().getValue();

                //Use the completed method.
                completedAction.completed();
            }
        });

        //Change the field for the ViewModel when studentNumberEditText has changed.
        this.binding.studentNumberEditText.addTextChangedListener(new TextWatcher() {

            /**
             * This method happens when studentNumberEditText has changed.
             * This method provides text from studentNumberEditText to the ViewModel.
             */
            @Override
            public void afterTextChanged(Editable s) {
                //Retrieve the view.
                EditText studentNumberEditText=loginFragment.binding.studentNumberEditText;

                //Retrieve text that was written.
                String studentNumber=""+studentNumberEditText.getText();

                //Provide text that was written to the ViewModel.
                loginFragment.viewModel.studentNumberChanged(studentNumber);
            }

            /**
             * No action needs to happen at this time.
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * No action needs to happen at this time.
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        //Change the field for the ViewModel when passwordEditText has changed.
        this.binding.passwordEditText.addTextChangedListener(new TextWatcher() {

            /**
             * This method happens when studentNumberEditText has changed.
             * This method provides text from studentNumberEditText to the ViewModel.
             */
            @Override
            public void afterTextChanged(Editable s) {
                //Retrieve the view.
                EditText passwordEditText = loginFragment.binding.passwordEditText;

                //Retrieve text that was written.
                String password = "" + passwordEditText.getText();

                //Provide text that was written to the ViewModel.
                loginFragment.viewModel.passwordChanged(password);
            }

            /**
             * No action needs to happen at this time.
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * No action needs to happen at this time.
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        //Don't show output text until it's needed.
        this.binding.outputTextView.setVisibility(View.GONE);
    }

    public boolean checkLoginInformation(String studentNumber, String password) {
        return studentNumber.length() > 0;
    }
}