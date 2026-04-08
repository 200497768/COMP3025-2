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
import comp3025.assignment2.databinding.FragmentRegistrationBinding;
import comp3025.assignment2.viewmodels.RegistrationFragmentViewModel;

/**
 * This fragment includes views for registration.
 * Registration refers to adding a person's login information to the system for the first time.
 * This can be thought of as registering courses.
<<<<<<< HEAD
 *
=======
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class RegistrationFragment extends Fragment {

    /**
     * This field is the view binding class.
     */
    private FragmentRegistrationBinding binding;

    /**
     * This field is the ViewModel for this fragment.
     */
    private RegistrationFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    /**
     * This method prepares the view binding class, and shows every field from the model.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //The view binding class can only be accessed after it has been created, like during this method.
        //We're changing text during this method because the view binding class can't be accessed before this method.

        //Prepare the view binding class.
        this.binding = FragmentRegistrationBinding.bind(view);

        //Create the ViewModel for this fragment.
        this.viewModel = new ViewModelProvider(this).get(RegistrationFragmentViewModel.class);

    }
}