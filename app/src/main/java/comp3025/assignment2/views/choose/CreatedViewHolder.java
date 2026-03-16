package comp3025.assignment2.views.choose;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import comp3025.assignment2.R;

/**
 * This class is needed for RecyclerView.
 * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
 * @author Yatri Devangbhai Padhiyar
 * @author Anastasios Perdikoulias
 * @author Hao Tian
 */
public class CreatedViewHolder extends RecyclerView.ViewHolder {

    private TextView cityNameTextView;

    public CreatedViewHolder(@NonNull View itemView) {
        super(itemView);

        //Retrieve each view that will be needed from itemView, so that it becomes a field.

        //Retrieve the TextView item that will be used to show the name of the city.
        this.cityNameTextView = itemView.findViewById(R.id.cityNameTextView);

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

    public TextView getCityNameTextView() {
        return cityNameTextView;
    }

}
