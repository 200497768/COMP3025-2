package comp3025.assignment2.views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.ExampleCode;
import comp3025.assignment2.R;
import comp3025.assignment2.databinding.ActivityMainBinding;


/**
 * This is the code for assignment 2.
 * The MainActivity class can be repeatedly created in order to create views.
 * Any fields will not be maintained when the MainActivity class is created again.
 * We need to use the ViewModel in order to maintain fields, and avoid needing to retrieve information again.
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
     * The ViewModel allows us to access fields, even if the activity needs to be created again.
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

        //Use the example code to retrieve some information.
        //We'll ensure that we've brought the example code to this assignment correctly.
        //If it's able to retrieve, we'll know that we've added it correctly.
        ExampleCode exampleCode = new ExampleCode();
        exampleCode.retrieve();
        //Permission denied (missing INTERNET permission?)

        // code to load fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ShowWeatherFragment())
                    .commit();
        }
    }
}