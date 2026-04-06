package comp3025.assignment2.views.choose;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3025.assignment2.R;
import comp3025.assignment2.models.CityOption;
import comp3025.assignment2.views.CityOptionChosenAction;

/**
 * This class is needed for RecyclerView.
 * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class CreatedAdapter extends RecyclerView.Adapter<CreatedViewHolder> {

    /**
     * This field is the city options model that the view is showing.
     */
    private List<CityOption> cityOptions;

    /**
     * This field is the action that needs to happen when a city option has been chosen.
     * This code receives the action from ChooseCityFragment.
     * ChooseCityFragment receives the action from the ViewModel for MainActivity.
     */
    private CityOptionChosenAction cityOptionChosenAction;

    public CreatedAdapter(List<CityOption> cityOptions, CityOptionChosenAction cityOptionChosenAction) {
        this.cityOptions = cityOptions;
        this.cityOptionChosenAction = cityOptionChosenAction;
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
                CityOption cityOption = createdAdapter.cityOptions.get(absoluteAdapterPosition);

                Log.i("200497768", "The city option model that was chosen is " + cityOption.getCity());

                //Provide the city option that was chosen to the city option chosen area.
                createdAdapter.cityOptionChosenAction.cityOptionChosen(cityOption);
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
        CityOption cityOption = this.cityOptions.get(position);

        //The remaining code for this method changes views to match the city option model.

        //Change the city name TextView item.
        TextView cityNameTextView = holder.getCityNameTextView();
        cityNameTextView.setText(cityOption.getCity());

        //Change the province name TextView item.
        TextView provinceNameTextView = holder.getProvinceNameTextView();
        provinceNameTextView.setText(cityOption.getProvince());

        //Change the country name TextView item.
        TextView countryNameTextView = holder.getCountryNameTextView();
        countryNameTextView.setText(cityOption.getCountry());
    }

    /**
     * This method returns the number of city options that exist.
     * This number depends on the city options models that have been retrieved.
     * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
     */
    @Override
    public int getItemCount() {
        return this.cityOptions.size();
    }
}
