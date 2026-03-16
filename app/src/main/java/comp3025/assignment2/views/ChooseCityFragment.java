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
import comp3025.assignment2.retrieval.CityRetrievalCode;

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

        //Change what happens when choosing the option to the retrieve city option models.
        this.binding.retrieveCityOptionModelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the name of the city that was written.
                String cityName = "Barrie";

                //Provide the city name to the retrieval code.
                //The action that needs to happen when city option models have been retrieved needs to be provided.
                CityRetrievalCode cityRetrievalCode = new CityRetrievalCode(cityName) {

                    /**
                     * The retrieval code has produced the retrieved city option models.
                     * This method provides the models to ChooseCityFragment.
                     */
                    @Override
                    public void retrieved() {

                    }
                };

                //Retrieve the city option models.
                cityRetrievalCode.retrieve();
            }
        });
    }
}