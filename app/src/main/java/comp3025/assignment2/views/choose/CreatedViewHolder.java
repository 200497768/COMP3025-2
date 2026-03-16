package comp3025.assignment2.views.choose;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import comp3025.assignment2.R;

/**
 * This class is needed for RecyclerView.
 * In order to write this code, we've started with the example code from the week 9 class (A. Perdikoulias, personal communication, March 13, 2026).
 *
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

        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("200497768", "CreatedViewHolder " + getAbsoluteAdapterPosition());
            }
        });
    }

    public TextView getCityNameTextView() {
        return cityNameTextView;
    }
}
