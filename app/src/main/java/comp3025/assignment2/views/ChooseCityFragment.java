package comp3025.assignment2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.FragmentChooseCityBinding;

/**
 * This fragment allows a city to be chosen.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class ChooseCityFragment extends Fragment {
    private FragmentChooseCityBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //The view binding class can only be accessed after it has been created, like during this method.
        //We're changing text during this method because the view binding class can't be accessed before this method.

        //Prepare the view binding class.
        this.binding = FragmentChooseCityBinding.bind(view);

    }
}