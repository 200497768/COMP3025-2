package comp3025.assignment2.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        this.binding = ActivityLoginRegistrationBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(this.binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
            }
        });
    }

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
