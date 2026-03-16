package comp3025.assignment2.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.FragmentChooseCityBinding;
import comp3025.assignment2.models.CityOptions;
import comp3025.assignment2.retrieval.CityRetrievalCode;
import comp3025.assignment2.views.choose.CreatedAdapter;

/**
 * This fragment allows a city to be chosen.
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class ChooseCityFragment extends Fragment {

    /**
     * This field is needed in order for this fragment to create LinearLayoutManager.
     * LinearLayoutManager is needed when showing RecyclerView items.
     * Another activity can provide this field by using the getApplicationContext method.
     */
    private Context applicationContext;

    public ChooseCityFragment() {
        //This is needed.
    }

    public ChooseCityFragment(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * This field is the view binding class for this fragment.
     */
    private FragmentChooseCityBinding binding;

    /**
     * This field is the ViewModel for this fragment.
     */
    private ChooseCityFragmentViewModel viewModel;

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

        //Create the ViewModel for this fragment.
        this.viewModel = new ViewModelProvider(this).get(ChooseCityFragmentViewModel.class);

        //Add the code that will happen when the City model from the ViewModel changes.
        this.viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<CityOptions>() {

            /**
             * This method happens when the CityOptions model from the ViewModel have been changed.
             * The code for this method changes RecyclerView to show the CityOptions model.
             */
            @Override
            public void onChanged(CityOptions cityOptions) {

            }
        });

        ChooseCityFragment chooseCityFragment = this;
        //Change what happens when choosing the option to the retrieve city option models.
        this.binding.retrieveCityOptionModelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the name of the city that was written.
                EditText cityEditText = chooseCityFragment.binding.cityEditText;
                String cityName = "" + cityEditText.getText();

                //Provide the city name to the retrieval code.
                //The action that needs to happen when city option models have been retrieved needs to be provided.
                CityRetrievalCode cityRetrievalCode = new CityRetrievalCode(cityName) {

                    /**
                     * The retrieval code has produced the retrieved city option models.
                     * This method provides the models to ChooseCityFragment.
                     */
                    @Override
                    public void retrieved() {
                        //In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
                        LinearLayoutManager layoutManager = new LinearLayoutManager(chooseCityFragment.applicationContext);
                        chooseCityFragment.binding.recyclerView.setLayoutManager(layoutManager);

//In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
                        CreatedAdapter createdAdapter = new CreatedAdapter(this.cityOptions);
                        chooseCityFragment.binding.recyclerView.setAdapter(createdAdapter);
                    }
                };

                //Retrieve the city option models.
                cityRetrievalCode.retrieve();
            }
        });
    }
}