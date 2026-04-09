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
 * Assignment 3 addition:
 * - Country flag emoji is now shown on each city card using getCountryFlag().
 * If this code includes in-text citations, the corresponding references can be accessed through MainActivity.
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

        //Change the country flag TextView item.
        //The flag is an emoji that corresponds to the country name.
        TextView countryFlagTextView = holder.getCountryFlagTextView();
        if (countryFlagTextView != null) {
            countryFlagTextView.setText(getCountryFlag(cityOption.getCountry()));
        }
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

    /**
     * This method returns a flag emoji for the given country name.
     * It checks the country name string against a list of known country names.
     * If no match is found, a globe emoji is returned as a fallback.
     */
    private String getCountryFlag(String countryName) {
        if (countryName == null) return "";
        String lower = countryName.toLowerCase();
        if (lower.contains("canada")) return "\uD83C\uDDE8\uD83C\uDDE6";
        if (lower.contains("united states") || lower.contains("usa"))
            return "\uD83C\uDDFA\uD83C\uDDF8";
        if (lower.contains("united kingdom") || lower.contains("uk"))
            return "\uD83C\uDDEC\uD83C\uDDE7";
        if (lower.contains("australia")) return "\uD83C\uDDE6\uD83C\uDDFA";
        if (lower.contains("germany")) return "\uD83C\uDDE9\uD83C\uDDEA";
        if (lower.contains("france")) return "\uD83C\uDDEB\uD83C\uDDF7";
        if (lower.contains("italy")) return "\uD83C\uDDEE\uD83C\uDDF9";
        if (lower.contains("spain")) return "\uD83C\uDDEA\uD83C\uDDF8";
        if (lower.contains("japan")) return "\uD83C\uDDEF\uD83C\uDDF5";
        if (lower.contains("china")) return "\uD83C\uDDE8\uD83C\uDDF3";
        if (lower.contains("india")) return "\uD83C\uDDEE\uD83C\uDDF3";
        if (lower.contains("brazil")) return "\uD83C\uDDE7\uD83C\uDDF7";
        if (lower.contains("mexico")) return "\uD83C\uDDF2\uD83C\uDDFD";
        if (lower.contains("russia")) return "\uD83C\uDDF7\uD83C\uDDFA";
        if (lower.contains("south korea") || lower.contains("korea"))
            return "\uD83C\uDDF0\uD83C\uDDF7";
        if (lower.contains("netherlands")) return "\uD83C\uDDF3\uD83C\uDDF1";
        if (lower.contains("sweden")) return "\uD83C\uDDF8\uD83C\uDDEA";
        if (lower.contains("norway")) return "\uD83C\uDDF3\uD83C\uDDF4";
        if (lower.contains("denmark")) return "\uD83C\uDDE9\uD83C\uDDF0";
        if (lower.contains("switzerland")) return "\uD83C\uDDE8\uD83C\uDDED";
        if (lower.contains("portugal")) return "\uD83C\uDDF5\uD83C\uDDF9";
        if (lower.contains("pakistan")) return "\uD83C\uDDF5\uD83C\uDDF0";
        if (lower.contains("turkey")) return "\uD83C\uDDF9\uD83C\uDDF7";
        if (lower.contains("new zealand")) return "\uD83C\uDDF3\uD83C\uDDFF";
        if (lower.contains("ireland")) return "\uD83C\uDDEE\uD83C\uDDEA";
        if (lower.contains("poland")) return "\uD83C\uDDF5\uD83C\uDDF1";
        if (lower.contains("ukraine")) return "\uD83C\uDDFA\uD83C\uDDE6";
        if (lower.contains("argentina")) return "\uD83C\uDDE6\uD83C\uDDF7";
        if (lower.contains("south africa")) return "\uD83C\uDDFF\uD83C\uDDE6";
        if (lower.contains("egypt")) return "\uD83C\uDDEA\uD83C\uDDEC";
        if (lower.contains("nigeria")) return "\uD83C\uDDF3\uD83C\uDDEC";
        return "\uD83C\uDF0D"; // globe emoji fallback
    }
}
