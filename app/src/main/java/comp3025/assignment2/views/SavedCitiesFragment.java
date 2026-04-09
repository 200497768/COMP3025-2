package comp3025.assignment2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3025.assignment2.databinding.FragmentSavedCitiesBinding;
import comp3025.assignment2.viewmodels.AuthViewModel;
import comp3025.assignment2.viewmodels.MainActivityViewModel;

/**
 * This fragment shows the list of cities the signed-in user has saved.
 * The list updates in real time using a Firestore snapshot listener via AuthViewModel.
 * Tapping a city card navigates to ShowWeatherFragment.
 * Swiping a card left, or tapping the delete button, removes the city from Firestore.
 */
public class SavedCitiesFragment extends Fragment {

    /**
     * This field is the view binding class for this fragment.
     */
    private FragmentSavedCitiesBinding binding;

    /**
     * This field is the AuthViewModel for this fragment.
     * It provides the saved cities LiveData and the delete method.
     */
    private AuthViewModel authViewModel;

    /**
     * This field is the MainActivityViewModel for this fragment.
     * It is used to load weather for a saved city when the card is tapped.
     */
    private MainActivityViewModel weatherViewModel;

    /**
     * This field is the RecyclerView adapter for the saved cities list.
     */
    private SavedCitiesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSavedCitiesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create the AuthViewModel using the activity scope so it shares state with ShowWeatherFragment.
        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        //Create the MainActivityViewModel using the activity scope so it can change the fragment area.
        weatherViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        //Set up the RecyclerView with tap and delete actions.
        setupRecyclerView();

        //Observe the savedCities LiveData so the list updates automatically.
        observeViewModel();

        //Start the Firestore real-time snapshot listener.
        //The listener fires immediately with the current list.
        authViewModel.startListening();
    }

    /**
     * This method sets up the RecyclerView with a LinearLayoutManager and a SavedCitiesAdapter.
     * An ItemTouchHelper is also attached to support swipe-to-delete.
     */
    private void setupRecyclerView() {
        //Create the adapter with tap and delete callbacks.
        adapter = new SavedCitiesAdapter(
                city -> {
                    //Tapping a city card loads the weather for that city.
                    weatherViewModel.retrieveWeatherInformationByName(
                            city.getName(), city.getRegion(), city.getCountry());

                    //Switch the bottom navigation to the Search tab so the weather screen is visible.
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).showWeatherTabFromSaved();
                    }
                },
                city -> {
                    //Tapping the delete button removes the city from Firestore.
                    authViewModel.deleteCity(city.getDocumentId());
                }
        );

        binding.savedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.savedRecyclerView.setAdapter(adapter);

        //Attach swipe-to-delete using ItemTouchHelper.
        //Swiping a card left also removes the city from Firestore.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView rv,
                                  @NonNull RecyclerView.ViewHolder vh,
                                  @NonNull RecyclerView.ViewHolder t) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder vh, int dir) {
                //Retrieve the position of the swiped item and delete it.
                adapter.deleteItem(vh.getAdapterPosition());
            }
        }).attachToRecyclerView(binding.savedRecyclerView);
    }

    /**
     * This method observes the savedCities LiveData from the AuthViewModel.
     * When the list is empty, the empty state view is shown.
     * When the list has items, the RecyclerView is shown with the updated data.
     */
    private void observeViewModel() {
        authViewModel.getSavedCities().observe(getViewLifecycleOwner(), cities -> {
            if (cities == null || cities.isEmpty()) {
                //No saved cities — show the empty state message.
                binding.savedRecyclerView.setVisibility(View.GONE);
                binding.emptyTextView.setVisibility(View.VISIBLE);
                binding.savedCountTextView.setText("No saved cities");
            } else {
                //Cities are available — show the list and update the count.
                binding.emptyTextView.setVisibility(View.GONE);
                binding.savedRecyclerView.setVisibility(View.VISIBLE);
                int count = cities.size();
                binding.savedCountTextView.setText(count + (count == 1 ? " city saved" : " cities saved"));
                adapter.updateData(cities);
            }
        });
    }

    @Override
    public void onDestroyView() {
        //Stop the Firestore snapshot listener when the fragment is destroyed.
        authViewModel.stopListening();
        binding = null;
        super.onDestroyView();
    }
}
