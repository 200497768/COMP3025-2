package comp3025.assignment2.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import comp3025.assignment2.R;
import comp3025.assignment2.databinding.ActivityDispositionBinding;
import comp3025.assignment2.viewmodels.DispositionActivityViewModel;

/**
 * This activity allows choosing a disposition option during LOGOFF.
 *
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class DispositionActivity extends AppCompatActivity {

    /**
     * This is the view binding class for this activity.
     */
    private ActivityDispositionBinding binding;

    /**
     * This is the ViewModel for this activity.
     */
    private DispositionActivityViewModel viewModel;

    /**
     * The onCreate method adds views to show retrieved information.
     * This method might happen repeatedly.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Prepare the view binding class.
        this.binding = ActivityDispositionBinding.inflate(getLayoutInflater());

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
        this.viewModel = new ViewModelProvider(this).get(DispositionActivityViewModel.class);

        DispositionActivity dispositionActivity = this;

        //Determine the code that needs to happen when the completedText field from the ViewModel changes.
        this.viewModel.getCompletedText().observe(this, new Observer<String>() {

            /**
             * This method happens when the completedText field from the ViewModel has been changed.
             */
            @Override
            public void onChanged(String s) {
                //The completedText field from the ViewModel has changed.
                dispositionActivity.completedTextChanged();
            }
        });

        //Change completedTextView so that it's the same as the ViewModel.
        this.completedTextChanged();

        //Change the action that happens when choosing noRecordButton.
        this.binding.noRecordButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method happens when the no record option has been chosen.
             * The ViewModel includes a method that needs to happen when the no record option has been chosen.
             */
            @Override
            public void onClick(View v) {
                //Use the noRecordChosen method from the ViewModel.
                dispositionActivity.viewModel.noRecordChosen();
            }
        });
    }

    /**
     * This method needs to happen when the completedText field from the ViewModel has changed.
     * This method retrieves the completedText field from the ViewModel.
     * After it has been retrieved, the method needs to change completedTextView to show that text.
     */
    public void completedTextChanged() {
        //Retrieve the completedText field.
        String completedText = this.viewModel.getCompletedText().getValue();

        //Retrieve completedTextView.
        TextView completedTextView = this.binding.completedTextView;

        //Change text for completedTextView so that it's the same as the field.
        completedTextView.setText(completedText);
    }
}