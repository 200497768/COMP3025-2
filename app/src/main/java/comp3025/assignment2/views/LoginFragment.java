package comp3025.assignment2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }
}