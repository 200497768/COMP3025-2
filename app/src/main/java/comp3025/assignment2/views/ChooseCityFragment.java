package comp3025.assignment2.views;

import android.content.Context;
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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.FragmentChooseCityBinding;
import comp3025.assignment2.models.CityOption;
import comp3025.assignment2.models.CityOptions;
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

    private CityOptionChosenAction cityOptionChosenAction;

    public ChooseCityFragment() {
        //This is needed.
    }

    public ChooseCityFragment(Context applicationContext, CityOptionChosenAction cityOptionChosenAction) {
        this.applicationContext = applicationContext;
        this.cityOptionChosenAction = cityOptionChosenAction;
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

        //Remove the message explaining that no city options could be retrieved.
        this.binding.cityNotExistTextView.setVisibility(View.GONE);

        //Add the code that will happen when the City option model from the ViewModel changes.
        ChooseCityFragment chooseCityFragment = this;
        this.viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<CityOptions>() {

            /**
             * This method happens when the CityOptions model from the ViewModel have been changed.
             * The code for this method changes the RecyclerView items to the City models.
             */
            @Override
            public void onChanged(CityOptions cityOptions) {
                //Retrieve the CityOptions model.
                MutableLiveData<CityOptions> mutableLiveData = chooseCityFragment.viewModel.getMutableLiveData();
                CityOptions cityOptionsModel = mutableLiveData.getValue();

                //Retrieve the individual CityOption models.
                List<CityOption> cityOptionModels = cityOptionsModel.getCityOptions();

                //Change the RecyclerView items to the City models.

                //In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
                LinearLayoutManager layoutManager = new LinearLayoutManager(chooseCityFragment.applicationContext);
                chooseCityFragment.binding.recyclerView.setLayoutManager(layoutManager);

//In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
                CreatedAdapter createdAdapter = new CreatedAdapter(cityOptionModels, chooseCityFragment.cityOptionChosenAction);
                chooseCityFragment.binding.recyclerView.setAdapter(createdAdapter);

                if (cityOptionModels.size() == 0) {
                    //Show a message explaining that no city option models could be retrieved.
                    chooseCityFragment.binding.cityNotExistTextView.setVisibility(View.VISIBLE);
                }
            }
        });

        //Change what happens when choosing the option to the retrieve city option models.
        this.binding.retrieveCityOptionModelsButton.setOnClickListener(new View.OnClickListener() {

            /**
             * The code for this method happens when text for this TextView item has changed.
             * This method provides the name of the city to the ViewModel, and retrieves the CityOption models.
             */
            @Override
            public void onClick(View v) {
                chooseCityFragment.cityNameWritten();
            }
        });


//Change what happens when text for this TextView item is changed (CodePath, n.d.).
        this.binding.cityEditText.addTextChangedListener(new TextWatcher() {

            /**
             * The code for this method happens when text for this TextView item has changed.
             * This method provides the name of the city to the ViewModel, and retrieves the CityOption models.
             */
            @Override
            public void afterTextChanged(Editable s) {
                chooseCityFragment.cityNameWritten();
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
    }

    /**
     * The code for this method happens when text for this TextView item has changed.
     * This method provides the name of the city to the ViewModel, and retrieves the CityOption models.
     */
    public void cityNameWritten() {
        //Remove the message explaining that no city options could be retrieved.
        this.binding.cityNotExistTextView.setVisibility(View.GONE);

        //Retrieve the name of the city that was written.
        EditText cityEditText =this.binding.cityEditText;
        String cityName = "" + cityEditText.getText();

        //Use the ViewModel to retrieve the city options.
        this.viewModel.retrieve(cityName);
    }
}