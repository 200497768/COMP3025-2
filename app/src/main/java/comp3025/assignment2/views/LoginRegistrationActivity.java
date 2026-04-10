package comp3025.assignment2.views;

import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.ActivityLoginRegistrationBinding;
import comp3025.assignment2.sounds.ExampleSound;
import comp3025.assignment2.sounds.LoginSound;
import comp3025.assignment2.sounds.SoundCode;
import comp3025.assignment2.viewmodels.AuthViewModel;

/**
 * This activity shows LoginFragment and RegistrationFragment.
 *
 * Assignment 3 additions:
 * - Firebase Auth replaces the old student-number login.
 * - Sign In / Create Account toggle on the same screen (loginButton and registrationButton).
 * - Confirm password field shown only on the Create Account tab.
 * - Passwords are checked to match before calling Firebase createUserWithEmailAndPassword.
 * - If the user is already signed in on launch, this activity is skipped and MainActivity opens.
 * - Error messages are shown inline using errorTextView.
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
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
     * This is the AuthViewModel for this activity.
     * It handles Firebase Auth sign in and registration, and exposes results via LiveData.
     */
    private AuthViewModel authViewModel;

    /**
     * This field tracks whether the Sign In tab or the Create Account tab is currently active.
     * true means Sign In is active; false means Create Account is active.
     */
    private boolean isSignInMode = true;

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
        ViewCompat.setOnApplyWindowInsetsListener(this.binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Create the AuthViewModel for this activity.
        this.authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        //If the user is already signed in, skip the login screen and go straight to MainActivity.
        //This uses FirebaseAuth's addAuthStateListener under the hood (via AuthViewModel).
        if (authViewModel.isSignedIn()) {
            loginCompleted();
            return;
        }

        //Set up the Sign In / Create Account tab toggle buttons.
        setupToggleTabs();

        //Set up the action button that performs sign in or registration.
        setupActionButton();

        //Observe the AuthViewModel for loading state, errors, and success.
        observeViewModel();

        //Create the sound code.
        SoundCode soundCode = new SoundCode(this, this);

        //Start the example sound.
        ExampleSound exampleSound = new ExampleSound();
        soundCode.startSound(exampleSound);

        //Start the login sound.
        LoginSound loginSound = new LoginSound();
        soundCode.startSound(loginSound);
    }

    /**
     * This method sets up the tab toggle buttons.
     * loginButton switches to Sign In mode.
     * registrationButton switches to Create Account mode.
     */
    private void setupToggleTabs() {
        //Change the action that happens when choosing login.
        this.binding.loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method happens when choosing the login option.
             * The code for this method switches the screen to Sign In mode.
             */
            @Override
            public void onClick(View v) {
                switchMode(true);
            }
        });

        //Change the action that happens when choosing registration.
        this.binding.registrationButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method happens when choosing the registration option.
             * The code for this method switches the screen to Create Account mode.
             */
            @Override
            public void onClick(View v) {
                switchMode(false);
            }
        });
    }

    /**
     * This method switches between Sign In mode and Create Account mode.
     * In Sign In mode, the confirm password field is hidden.
     * In Create Account mode, the confirm password field is shown.
     * The action button text is updated to match the current mode.
     */
    private void switchMode(boolean signIn) {
        isSignInMode = signIn;

        //Hide any error message when switching modes.
        binding.errorTextView.setVisibility(View.GONE);

        if (signIn) {
            //Sign In tab is now active.
            binding.loginButton.setAlpha(1.0f);
            binding.registrationButton.setAlpha(0.5f);
            binding.actionButton.setText("Sign In");
            binding.confirmPasswordLayout.setVisibility(View.GONE);
            binding.loginRegistrationActivityInformationTextView.setText("Sign in to your account");
        } else {
            //Create Account tab is now active.
            binding.registrationButton.setAlpha(1.0f);
            binding.loginButton.setAlpha(0.5f);
            binding.actionButton.setText("Create Account");
            binding.confirmPasswordLayout.setVisibility(View.VISIBLE);
            binding.loginRegistrationActivityInformationTextView.setText("Create a new account");
        }
    }

    /**
     * This method sets up the action button click listener.
     * In Sign In mode, it calls FirebaseAuth.signInWithEmailAndPassword via the AuthViewModel.
     * In Create Account mode, it validates that passwords match before calling
     * FirebaseAuth.createUserWithEmailAndPassword via the AuthViewModel.
     */
    private void setupActionButton() {
        binding.actionButton.setOnClickListener(v -> {
            //Retrieve the email and password that were written.
            String email = binding.emailEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();

            //Validate that an email has been entered.
            if (email.isEmpty()) {
                showError("Email is required");
                return;
            }

            //Validate that a password has been entered.
            if (password.isEmpty()) {
                showError("Password is required");
                return;
            }

            if (!isSignInMode) {
                //Validate the confirm password field on the Create Account tab.
                //Passwords must match before calling Firebase.
                String confirm = binding.confirmPasswordEditText.getText().toString().trim();
                if (confirm.isEmpty()) {
                    showError("Please confirm your password");
                    return;
                }
                if (!password.equals(confirm)) {
                    showError("Passwords do not match");
                    return;
                }

                //Use the AuthViewModel to register the user with Firebase Auth.
                authViewModel.register(email, password);
            } else {
                //Use the AuthViewModel to sign the user in with Firebase Auth.
                authViewModel.signIn(email, password);
            }
        });
    }

    /**
     * This method observes the AuthViewModel for loading state, errors, and success.
     * It disables the action button while loading, shows errors inline, and navigates on success.
     */
    private void observeViewModel() {
        //Show a progress bar and disable the button while Firebase is working.
        authViewModel.getAuthLoading().observe(this, loading -> {
            if (Boolean.TRUE.equals(loading)) {
                binding.actionButton.setEnabled(false);
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.errorTextView.setVisibility(View.GONE);
            } else {
                binding.actionButton.setEnabled(true);
                binding.progressBar.setVisibility(View.GONE);
            }
        });

        //Show a friendly error message when Firebase Auth returns an error.
        authViewModel.getAuthError().observe(this, error -> {
            if (error != null) {
                showError(error);
                //Consume the error so it doesn't show again on re-observe.
                authViewModel.consumeAuthError();
            }
        });

        //Navigate to MainActivity when Firebase Auth returns success.
        authViewModel.getAuthSuccess().observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                //Consume the success so it doesn't trigger again on re-observe.
                authViewModel.consumeAuthSuccess();
                loginCompleted();
            }
        });
    }

    /**
     * This method shows an error message in the errorTextView.
     */
    private void showError(String msg) {
        binding.errorTextView.setText(msg);
        binding.errorTextView.setVisibility(View.VISIBLE);
    }

    /**
     * This method changes the fragment area for LoginRegistrationActivity to show the provided fragment.
     * Kept for compatibility with the original code structure.
     */
    private void changeFragmentArea(Fragment fragment) {
        //Change the fragment area to show the provided fragment.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    /**
     * This method changes the program to show MainActivity.
     * This method needs to happen after the login task has been completed.
     */
    public void loginCompleted() {
        //Create an explicit intent that refers to MainActivity.
        Intent intent = new Intent(LoginRegistrationActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
