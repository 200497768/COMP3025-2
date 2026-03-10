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
        //Prepare the view binding class.
        this.binding = FragmentChooseCityBinding.bind(view);

        ChooseCityFragment chooseCityFragment = this;
        this.binding.changeCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}