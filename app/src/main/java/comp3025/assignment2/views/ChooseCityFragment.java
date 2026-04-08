package comp3025.assignment2.views;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import comp3025.assignment2.viewmodels.ChooseCityFragmentViewModel;
import comp3025.assignment2.views.choose.CreatedAdapter;

/**
 * This fragment allows a city to be chosen.
<<<<<<< HEAD
=======
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class ChooseCityFragment extends Fragment {

    /**
     * This field is needed in order for this fragment to create LinearLayoutManager.
     * LinearLayoutManager is needed when showing RecyclerView items.
     * Another activity can provide this field by using the getApplicationContext method.
     */
    private Context applicationContext;

<<<<<<< HEAD
    private CityOptionChosenAction cityOptionChosenAction;

    public ChooseCityFragment() {
        //This is needed.
    }

    public ChooseCityFragment(Context applicationContext, CityOptionChosenAction cityOptionChosenAction) {
        this.applicationContext = applicationContext;
=======
    /**
     * This field the action that needs to happen when a city option has been chosen.
     * When MainActivity creates ChooseCityFragment, an action will be provided to this fragment.
     * This fragment will provide the action to CreatedAdapter.
     * When a city option has been chosen, CreatedAdapter will cause the action to happen, and provide the model that corresponds with the option that was chosen.
     */
    private CityOptionChosenAction cityOptionChosenAction;

    public ChooseCityFragment() {
        //This is needed so that the fragment can be created again.
        //When the fragment needs to be created again, the fields that we wrote won't be provided to the fragment.
        //We can avoid this problem by maintaining any fields that we still need using the ViewModel.
    }

    public ChooseCityFragment(Context applicationContext, CityOptionChosenAction cityOptionChosenAction) {
        //This field needs to be maintained until the onViewCreated method.
        //During that method, this field will be provided to LinearLayoutManager.
        this.applicationContext = applicationContext;

        //The city option chosen action needs to be maintained until the onViewCreated method.
        //During that method, this field will be provided to CreatedAdapter.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
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
<<<<<<< HEAD
=======
             * In other words, this method happens when new city option models have been retrieved.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
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
                String cityName = "" + chooseCityFragment.binding.cityEditText.getText();
                Log.i("200594802 and 200497768", "During the afterTextChanged method, the name of the city has been written as " + cityName);
                chooseCityFragment.cityNameWritten();
            }

            /**
             * No action needs to happen at this time.
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String cityName = "" + chooseCityFragment.binding.cityEditText.getText();
                Log.i("200594802 and 200497768", "During the beforeTextChanged method, the name of the city has been written as " + cityName);
            }

            /**
             * No action needs to happen at this time.
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cityName = "" + chooseCityFragment.binding.cityEditText.getText();
                Log.i("200594802 and 200497768", "During the onTextChanged method, the name of the city has been written as " + cityName);
            }
        });
    }

    /**
<<<<<<< HEAD
     * The code for this method happens when text for this TextView item has changed.
=======
     * The code for this method happens when the name of the city has changed.
     * In other words, text for the cityEditText item has been changed.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
     * This method provides the name of the city to the ViewModel, and retrieves the CityOption models.
     */
    public void cityNameWritten() {
        //Remove the message explaining that no city options could be retrieved.
<<<<<<< HEAD
=======
        //We decided to change the view to GONE, as opposed to INVISIBLE, since we don't want it to use any space.
        //The fragment will show the city options, instead of this view.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
        this.binding.cityNotExistTextView.setVisibility(View.GONE);

        //Retrieve the name of the city that was written.
        EditText cityEditText =this.binding.cityEditText;
        String cityName = "" + cityEditText.getText();

<<<<<<< HEAD
        //Use the ViewModel to retrieve the city options.
        this.viewModel.retrieve(cityName);
=======
        //Provide the name of the city to the ViewModel.
        //The method from ViewModel will use the CityRetrievalCode to retrieve the city options.
        this.viewModel.retrieve(cityName);

        //When retrieval has finished, the ViewModel will change the city options model field.
        //When the ViewModel changes the model field, this fragment will change the city options that the fragment is showing.
        //During the onViewCreated method, this fragment provided the code that needs to happen when the city options have been changed.
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
    }
}