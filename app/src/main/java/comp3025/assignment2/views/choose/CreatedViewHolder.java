package comp3025.assignment2.views.choose;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import comp3025.assignment2.R;

/**
 * This class is needed for RecyclerView.
 * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class CreatedViewHolder extends RecyclerView.ViewHolder {

    /**
     * This field allows the methods to access the individual TextView items that might need to be retrieved.
     */
    private View itemView;

    public CreatedViewHolder(@NonNull View itemView) {
        super(itemView);

        //Change the field to this itemView, so that the code will be able to access the individual TextView items in the future.
        this.itemView = itemView;

        //Change what happens when this item has been chosen.
        //This code causes the cityOptionChosen method to happen.
        //Every item corresponds with a city option model, and the cityOptionChosen method will receive that number.
        //This number can be used to retrieve the city option model that was chosen.
        CreatedViewHolder createdViewHolder = this;
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Retrieve the number that corresponds with the city option that was chosen.
                //Provide this number to the cityOptionChosen method.
                createdViewHolder.cityOptionChosen(getAbsoluteAdapterPosition());
            }
        });
    }

    /**
     * This method is the code that needs to happen when a city option has been chosen.
     * The code for this method is provided with the number that corresponds with the city option that has been chosen.
     * The code for this method is responsible for determining the city model that corresponds with this number.
     * The code for this method is responsible for changing the fragment area to ShowWeatherFragment.
     */
    public void cityOptionChosen(int absoluteAdapterPosition) {

    }

    /**
     * This method retrieves the TextView item for city name.
     * The TextView item can be changed, including by changing text to match the city option model.
     */
    public TextView getCityNameTextView() {
        TextView cityNameTextView = this.itemView.findViewById(R.id.cityNameTextView);
        return cityNameTextView;
    }

    /**
     * This method retrieves the TextView item for province name.
     * The TextView item can be changed, including by changing text to match the city option model.
     */
    public TextView getProvinceNameTextView() {
        TextView provinceNameTextView = this.itemView.findViewById(R.id.provinceNameTextView);
        return provinceNameTextView;
    }

    /**
     * This method retrieves the TextView item for country name.
     * The TextView item can be changed, including by changing text to match the city option model.
     */
    public TextView getCountryNameTextView() {
        TextView countryNameTextView = this.itemView.findViewById(R.id.countryNameTextView);
        return countryNameTextView;
    }

}
