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

import java.util.List;

import comp3025.assignment2.databinding.FragmentSavedCitiesBinding;
import comp3025.assignment2.models.SavedCityModel;
import comp3025.assignment2.viewmodels.AuthViewModel;
import comp3025.assignment2.viewmodels.MainActivityViewModel;

/**
 * Shows the user's saved cities with real-time Firestore updates.
 * Swipe-to-delete removes a city. Tap a city to view weather.
 */
public class SavedCitiesFragment extends Fragment {

    private FragmentSavedCitiesBinding binding;
    private AuthViewModel              authViewModel;
    private MainActivityViewModel      weatherViewModel;
    private SavedCitiesAdapter         adapter;

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
        authViewModel    = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        weatherViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        setupRecyclerView();
        observeViewModel();
        authViewModel.startListening();
    }

    private void setupRecyclerView() {
        adapter = new SavedCitiesAdapter(
            city -> {
                // Tap → load weather for this city
                weatherViewModel.retrieveWeatherInformationByName(
                    city.getName(), city.getRegion(), city.getCountry());
                // Navigate to search/weather tab
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).showWeatherTabFromSaved();
                }
            },
            city -> authViewModel.deleteCity(city.getDocumentId())
        );
        binding.savedRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));
        binding.savedRecyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override public boolean onMove(@NonNull RecyclerView rv,
                    @NonNull RecyclerView.ViewHolder vh,
                    @NonNull RecyclerView.ViewHolder t) { return false; }
            @Override public void onSwiped(@NonNull RecyclerView.ViewHolder vh, int dir) {
                adapter.deleteItem(vh.getAdapterPosition());
            }
        }).attachToRecyclerView(binding.savedRecyclerView);
    }

    private void observeViewModel() {
        authViewModel.getSavedCities().observe(getViewLifecycleOwner(), cities -> {
            if (cities == null || cities.isEmpty()) {
                binding.savedRecyclerView.setVisibility(View.GONE);
                binding.emptyTextView.setVisibility(View.VISIBLE);
                binding.savedCountTextView.setText("No saved cities");
            } else {
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
        authViewModel.stopListening();
        binding = null;
        super.onDestroyView();
    }
}
