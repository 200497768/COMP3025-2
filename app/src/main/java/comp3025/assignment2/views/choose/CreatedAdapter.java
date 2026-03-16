package comp3025.assignment2.views.choose;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3025.assignment2.R;
import comp3025.assignment2.models.City;

/**
 * This class is needed for RecyclerView.
 * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
 *
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Hao Tian
 */
public class CreatedAdapter extends RecyclerView.Adapter<CreatedViewHolder> {
    private List<City> cityOptions;

    public CreatedAdapter(List<City> cityOptions) {
        this.cityOptions = cityOptions;
    }

    /**
     * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
     */
    @NonNull
    @Override
    public CreatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_layout, parent, false);

        CreatedViewHolder createdViewHolder = new CreatedViewHolder(itemView);

        return createdViewHolder;
    }

    /**
     * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
     */
    @Override
    public void onBindViewHolder(@NonNull CreatedViewHolder holder, int position) {
        //When this code happens, it provides a number that can be used to determine the city option model that the code is showing.

        //Retrieve a single city option model using this number.
        City city = this.cityOptions.get(position);

        //Change information to match the city option model.
    }

    /**
     * APA
     */
    @Override
    public int getItemCount() {
        return this.cityOptions.size();
    }
}
