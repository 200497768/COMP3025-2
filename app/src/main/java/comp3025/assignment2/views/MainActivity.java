package comp3025.assignment2.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.ActivityMainBinding;
import comp3025.assignment2.models.WeatherInformation;
import comp3025.assignment2.retrieval.ExampleModelRetrievalCode;
import comp3025.assignment2.retrieval.RetrievalCode;


/**
 * This is the code for assignment 2.
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Hao Tian
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This is the view binding class for this activity.
     * The view binding class allows us to refer to views as fields, instead of using the findViewById method.
     * This field will be changed during the onCreate method.
     */
    private ActivityMainBinding binding;

    /**
     * This is the ViewModel for this activity.
     * The code that we write will access fields through the ViewModel.
     * The ViewModel allows us to maintain the fields, even if the activity needs to be created again.
     */
    private MainActivityViewModel viewModel;

    /**
     * The onCreate method adds views to show retrieved information.
     * This method might happen repeatedly.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Prepare the view binding class.
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());

        //Change the view for this activity.
        //We'll access the view through the view binding class, instead of the R.layout.activity_main.
        setContentView(this.binding.getRoot());

        //Order is important, and the setOnApplyWindowInsetsListener method must be after the setContentView method.
        //If the setContentView method hasn't completed, the view will be null.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Create the ViewModel for this activity.
        this.viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // code to load fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ChooseCityFragment())
                    .commit();
        }

        MainActivity mainActivity = this;
        this.binding.changeCityButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method needs to show ShowWeatherFragment.
             * ShowWeatherFragment receives a WeatherInformation model, with information for the chosen city.
             * This method is responsible for creating that model, including retrieving information, and providing it to ShowWeatherFragment.
             */
            @Override
            public void onClick(View v) {
                //Create the WeatherInformation model, and show ShowWeatherFragment.
                //We can write RetrievalCode, or ExampleModelRetrievalCode.
                RetrievalCode retrievalCode = new ExampleModelRetrievalCode() {

                    /**
                     * The retrieval code has produced the WeatherInformation model.
                     * This method provides the model to ShowWeatherFragment.
                     */
                    @Override
                    public void retrieved() {
                        //Show the fields from this WeatherInformation model.
                        mainActivity.changeFragmentAreaShowWeatherFragment(this.weatherInformation);
                    }
                };

                //Use the retrieval code to retrieve the model.
                //When the retrieval code has completed, the retrieved method will happen.
                retrievalCode.retrieve();

                //Retrieve the actions.
                ChooseCityOrShowListAction chooseCityOrShowListAction = mainActivity.viewModel.getChooseCityOrShowListAction();

                //Change the action that's available to the other action.
                chooseCityOrShowListAction.availableActionChosen();

                //Change text for the action that's available.
                mainActivity.binding.changeCityButton.setText(chooseCityOrShowListAction.getActionText());

                //Add an action to the actions area.
                Button chooseAnotherCityButton = new Button(mainActivity);
                chooseAnotherCityButton.setText("Choose another city");
                chooseAnotherCityButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Show the list in order to allow choosing another city.
                        mainActivity.changeFragmentAreaChooseCityFragment();
                    }
                });
                LinearLayout actionsArea = mainActivity.binding.actionsArea;
                actionsArea.addView(chooseAnotherCityButton);
                chooseAnotherCityButton.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * This method changes the fragment area to show ShowWeatherFragment.
     * ShowWeatherFragment will show the fields from a WeatherInformation model.
     * The WeatherInformation model for the city that has been chosen needs to be provided to this method.
     */
    public void changeFragmentAreaShowWeatherFragment(WeatherInformation weatherInformation) {
        //Create ShowWeatherFragment, and provide the model.
        Fragment showWeatherFragment = new ShowWeatherFragment(weatherInformation);

        //Change the fragment area to show ShowWeatherFragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, showWeatherFragment);
        fragmentTransaction.commit();
    }

    /**
     * This method changes the fragment area to show ChooseCityFragment.
     * ChooseCityFragment allows another city to be chosen.
     */
    public void changeFragmentAreaChooseCityFragment() {
        //Create ChooseCityFragment, and provide the model.
        Fragment chooseCityFragment = new ChooseCityFragment();

        //Change the fragment area to show ChooseCityFragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, chooseCityFragment);
        fragmentTransaction.commit();
    }

}

//In order to fix "Permission denied (missing INTERNET permission?)", we added some code from a book.
//We needed to add "<uses-permission android:name="android.permission.INTERNET" />" to the code (Elenkov, 2016, pp. 33–34).

//References
//Elenkov, N. (2016). Android security internals: An in-depth guide to android’s security architecture (1st edition). No Starch Press.