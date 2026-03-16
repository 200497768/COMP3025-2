package comp3025.assignment2.views.choose;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3025.assignment2.R;
import comp3025.assignment2.models.City;

/**
 * This class is needed for RecyclerView.
 * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
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

        CreatedAdapter createdAdapter = this;

        CreatedViewHolder createdViewHolder = new CreatedViewHolder(itemView) {

            /**
             * A city option has been chosen.
             * This method needs to determine the city model that corresponds with this number.
             * In addition, the fragment area needs to be changed to ShowWeatherFragment.
             */
            @Override
            public void cityOptionChosen(int absoluteAdapterPosition) {
                //Retrieve the city option that was chosen.
                City city = createdAdapter.cityOptions.get(absoluteAdapterPosition);

                //Change the fragment area.

            }
        };

        return createdViewHolder;
    }

    /**
     * This method changes views to match the fields from the city option model.
     * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
     */
    @Override
    public void onBindViewHolder(@NonNull CreatedViewHolder holder, int position) {
        //When this code happens, it provides a number that can be used to determine the city option model that the code is showing.

        //Retrieve a single city option model using this number.
        City city = this.cityOptions.get(position);

        //The remaining code for this method changes views to match the city option model.

        //Change the name of the city.
        TextView cityNameTextView = holder.getCityNameTextView();
        cityNameTextView.setText(city.getCity());
    }

    /**
     * APA
     */
    @Override
    public int getItemCount() {
        return this.cityOptions.size();
    }
}
