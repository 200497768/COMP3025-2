package comp3025.assignment2.views;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.text.Editable;
import android.text.TextWatcher;
=======
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
<<<<<<< HEAD
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.databinding.ActivityLoginRegistrationBinding;
import comp3025.assignment2.viewmodels.AuthViewModel;

/**
 * Entry point for the app.
 * Shows Sign In / Create Account toggle with Firebase Auth.
 * If already signed in, goes straight to MainActivity.
 */
public class LoginRegistrationActivity extends AppCompatActivity {

    private ActivityLoginRegistrationBinding binding;
    private AuthViewModel authViewModel;
    private boolean isSignInMode = true;

=======
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.ActivityLoginRegistrationBinding;

/**
 * This activity shows LoginFragment and RegistrationFragment.
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
     * This is the ViewModel for this activity.
     */
    private LoginRegistrationActivityViewModel viewModel;

    /**
     * The onCreate method shows the fragment area.
     * This method might happen repeatedly.
     */
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

<<<<<<< HEAD
        this.binding = ActivityLoginRegistrationBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(this.binding.getRoot(), (v, insets) -> {
=======
        //Prepare the view binding class.
        this.binding = ActivityLoginRegistrationBinding.inflate(getLayoutInflater());

        //Change the view for this activity.
        //We'll access the view through the view binding class, instead of the R.layout.activity_main.
        setContentView(this.binding.getRoot());

        //Order is important, and the setOnApplyWindowInsetsListener method must be after the setContentView method.
        //If the setContentView method hasn't completed, the view will be null.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

<<<<<<< HEAD
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // If already signed in, skip straight to MainActivity
        if (authViewModel.isSignedIn()) {
            goToMain();
            return;
        }

        setupToggleTabs();
        setupTextWatchers();
        setupActionButton();
        observeViewModel();
        updateActionButtonState();
    }

    private void setupToggleTabs() {
        binding.loginButton.setOnClickListener(v -> switchMode(true));
        binding.registrationButton.setOnClickListener(v -> switchMode(false));
    }

    private void switchMode(boolean signIn) {
        isSignInMode = signIn;
        clearFieldErrorState();
        binding.errorTextView.setVisibility(View.GONE);

        if (signIn) {
            // Sign In tab active
            binding.loginButton.setAlpha(1.0f);
            binding.registrationButton.setAlpha(0.5f);
            binding.actionButton.setText("Sign In");
            binding.confirmPasswordLayout.setVisibility(View.GONE);
            binding.loginRegistrationActivityInformationTextView.setText("Sign in to your account");
        } else {
            // Create Account tab active
            binding.registrationButton.setAlpha(1.0f);
            binding.loginButton.setAlpha(0.5f);
            binding.actionButton.setText("Create Account");
            binding.confirmPasswordLayout.setVisibility(View.VISIBLE);
            binding.loginRegistrationActivityInformationTextView.setText("Create a new account");
        }

        updateActionButtonState();
    }

    private void setupTextWatchers() {
        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                clearFieldErrorState();
                binding.errorTextView.setVisibility(View.GONE);
                updateActionButtonState();
            }
        };

        binding.emailEditText.addTextChangedListener(watcher);
        binding.passwordEditText.addTextChangedListener(watcher);
        binding.confirmPasswordEditText.addTextChangedListener(watcher);
    }

    private void setupActionButton() {
        binding.actionButton.setOnClickListener(v -> {
            String email    = binding.emailEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();

            if (email.isEmpty()) {
                showError("Email is required");
                binding.emailEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_error);
                return;
            }
            if (password.isEmpty()) {
                showError("Password is required");
                binding.passwordEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_error);
                return;
            }

            if (!isSignInMode) {
                String confirm = binding.confirmPasswordEditText.getText().toString().trim();
                if (confirm.isEmpty()) {
                    showError("Please confirm your password");
                    binding.confirmPasswordEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_error);
                    return;
                }
                if (!password.equals(confirm)) {
                    showError("Passwords do not match");
                    binding.passwordEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_error);
                    binding.confirmPasswordEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_error);
                    return;
                }
                authViewModel.register(email, password);
            } else {
                authViewModel.signIn(email, password);
=======
        LoginRegistrationActivity loginRegistrationActivity = this;

        //Change the action that happens when choosing login.
        this.binding.loginButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method happens when choosing the login option.
             * The code for this method changes the fragment area to show LoginFragment.
             */
            @Override
            public void onClick(View v) {
                //Create a completed action.
                CompletedAction completedAction = new CompletedAction() {

                    /**
                     * This method happens when LoginFragment has completed.
                     * This method is responsible for showing MainActivity.
                     * This method allows LoginRegistrationActivity to cause some code to happen when LoginFragment has been complete.d
                     * LoginRegistrationActivity uses this class by creating a completed action, and providing it to LoginFragment.
                     * LoginFragment maintains the completed action as a field for the ViewModel.
                     * When the LoginFragment task has been completed, the ViewModel causes the completed method to happen.
                     * This causes LoginRegistrationActivity to show MainActivity.
                     */
                    @Override
                    public void completed() {
                        loginRegistrationActivity.loginCompleted();
                    }
                };

                //Create LoginFragment.
                Fragment loginFragment = new LoginFragment(completedAction);

                //Show the created fragment.
                loginRegistrationActivity.changeFragmentArea(loginFragment);
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
                //Create RegistrationFragment.
                Fragment registrationFragment = new RegistrationFragment();

                //Show the created fragment.
                loginRegistrationActivity.changeFragmentArea(registrationFragment);
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
            }
        });
    }

<<<<<<< HEAD
    private void observeViewModel() {
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

        authViewModel.getAuthError().observe(this, error -> {
            if (error != null) {
                showError(error);
                authViewModel.consumeAuthError();
            }
        });

        authViewModel.getAuthSuccess().observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                authViewModel.consumeAuthSuccess();
                goToMain();
            }
        });
    }

    private void showError(String msg) {
        binding.errorTextView.setText(msg);
        binding.errorTextView.setVisibility(View.VISIBLE);

        if (msg != null && msg.toLowerCase().contains("email")) {
            binding.emailEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_error);
        }

        if (msg != null && (msg.toLowerCase().contains("password") || msg.toLowerCase().contains("authentication") || msg.toLowerCase().contains("incorrect"))) {
            binding.passwordEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_error);
        }
    }

    private void clearFieldErrorState() {
        binding.emailEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_normal);
        binding.passwordEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_normal);
        binding.confirmPasswordEditText.setBackgroundResource(comp3025.assignment2.R.drawable.bg_input_normal);
    }

    private void updateActionButtonState() {
        String email = binding.emailEditText.getText().toString().trim();
        String password = binding.passwordEditText.getText().toString().trim();

        boolean enabled = !email.isEmpty() && !password.isEmpty();
        if (!isSignInMode) {
            String confirm = binding.confirmPasswordEditText.getText().toString().trim();
            enabled = enabled && !confirm.isEmpty();
        }

        binding.actionButton.setEnabled(enabled);
        binding.actionButton.setAlpha(enabled ? 1.0f : 0.55f);
    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    // Legacy methods kept for compatibility
    private void changeFragmentArea(androidx.fragment.app.Fragment fragment) {}
    public void loginCompleted() { goToMain(); }
}
=======
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

    /**
     * This method changes the program to show MainActivity.
     * This method needs to happen after the login task has been completed.
     */
    public void loginCompleted() {
        //Create an explicit intent that refers to MainActivity.
        Intent intent = new Intent(LoginRegistrationActivity.this, MainActivity.class);

        startActivity(intent);
    }
}
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
