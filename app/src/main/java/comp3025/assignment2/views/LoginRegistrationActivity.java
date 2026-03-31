package comp3025.assignment2.views;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.ActivityLoginRegistrationBinding;

/**
 * This activity shows LoginFragment and RegistrationFragment.
 *
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class LoginRegistrationActivity extends AppCompatActivity {

    /**
     * This is the view binding class for this activity.
     */
    private ActivityLoginRegistrationBinding binding;

    /**
     * This is the ViewModel for this activity.
     */
    private LoginRegistrationActivityViewModel viewModel;

    /**
     * The onCreate method shows the fragment area.
     * This method might happen repeatedly.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Prepare the view binding class.
        this.binding = ActivityLoginRegistrationBinding.inflate(getLayoutInflater());

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

        LoginRegistrationActivity loginRegistrationActivity = this;

        //Change the action that happens when choosing login.
        this.binding.loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method happens when choosing the login option.
             * The code for this method changes the fragment area to show LoginFragment.
             */
            @Override
            public void onClick(View v) {
                //Create RegistrationFragment.
                Fragment registrationFragment = new RegistrationFragment();

                //Show the created fragment.
                loginRegistrationActivity.changeFragmentArea(registrationFragment);
            }
        });

        //Change the action that happens when choosing registration.
        this.binding.registrationButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method happens when choosing the login option.
             * The code for this method changes the fragment area to show RegistrationFragment.
             */
            @Override
            public void onClick(View v) {
                //Create LoginFragment.
                Fragment loginFragment = new LoginFragment();

                //Show the created fragment.
                loginRegistrationActivity.changeFragmentArea(loginFragment);
            }
        });
    }

    /**
     * This method changes the fragment area for LoginRegistrationActivity to show the provided fragment.
     */
    private void changeFragmentArea(Fragment fragment) {
        //Change the fragment area to show the provided fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}