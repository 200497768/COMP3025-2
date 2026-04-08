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
 * RecyclerView adapter for city search results.
 * Shows city name, province, country, and country flag emoji.
 */
public class CreatedAdapter extends RecyclerView.Adapter<CreatedViewHolder> {

    private List<CityOption> cityOptions;
    private CityOptionChosenAction cityOptionChosenAction;

    public CreatedAdapter(List<CityOption> cityOptions, CityOptionChosenAction cityOptionChosenAction) {
        this.cityOptions = cityOptions;
        this.cityOptionChosenAction = cityOptionChosenAction;
    }

    @NonNull
    @Override
    public CreatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_layout, parent, false);
        CreatedAdapter createdAdapter = this;

        CreatedViewHolder createdViewHolder = new CreatedViewHolder(itemView) {
            @Override
            public void cityOptionChosen(int absoluteAdapterPosition) {
                CityOption cityOption = createdAdapter.cityOptions.get(absoluteAdapterPosition);
                Log.i("200497768", "City chosen: " + cityOption.getCity());
                createdAdapter.cityOptionChosenAction.cityOptionChosen(cityOption);
            }
        };

        return createdViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreatedViewHolder holder, int position) {
        CityOption cityOption = this.cityOptions.get(position);

        holder.getCityNameTextView().setText(cityOption.getCity());
        holder.getProvinceNameTextView().setText(cityOption.getProvince());
        holder.getCountryNameTextView().setText(cityOption.getCountry());

        // Show country flag emoji
        TextView flagView = holder.getCountryFlagTextView();
        if (flagView != null) {
            flagView.setText(getCountryFlag(cityOption.getCountry()));
        }
    }

    @Override
    public int getItemCount() {
        return this.cityOptions.size();
    }

    /** Returns a flag emoji for the given country name. */
    private String getCountryFlag(String countryName) {
        if (countryName == null) return "";
        String lower = countryName.toLowerCase();
        if (lower.contains("canada"))                                     return "\uD83C\uDDE8\uD83C\uDDE6";
        if (lower.contains("united states") || lower.contains("usa"))     return "\uD83C\uDDFA\uD83C\uDDF8";
        if (lower.contains("united kingdom") || lower.contains("uk"))     return "\uD83C\uDDEC\uD83C\uDDE7";
        if (lower.contains("australia"))                                  return "\uD83C\uDDE6\uD83C\uDDFA";
        if (lower.contains("germany"))                                    return "\uD83C\uDDE9\uD83C\uDDEA";
        if (lower.contains("france"))                                     return "\uD83C\uDDEB\uD83C\uDDF7";
        if (lower.contains("italy"))                                      return "\uD83C\uDDEE\uD83C\uDDF9";
        if (lower.contains("spain"))                                      return "\uD83C\uDDEA\uD83C\uDDF8";
        if (lower.contains("japan"))                                      return "\uD83C\uDDEF\uD83C\uDDF5";
        if (lower.contains("china"))                                      return "\uD83C\uDDE8\uD83C\uDDF3";
        if (lower.contains("india"))                                      return "\uD83C\uDDEE\uD83C\uDDF3";
        if (lower.contains("brazil"))                                     return "\uD83C\uDDE7\uD83C\uDDF7";
        if (lower.contains("mexico"))                                     return "\uD83C\uDDF2\uD83C\uDDFD";
        if (lower.contains("russia"))                                     return "\uD83C\uDDF7\uD83C\uDDFA";
        if (lower.contains("south korea") || lower.contains("korea"))     return "\uD83C\uDDF0\uD83C\uDDF7";
        if (lower.contains("netherlands"))                                return "\uD83C\uDDF3\uD83C\uDDF1";
        if (lower.contains("sweden"))                                     return "\uD83C\uDDF8\uD83C\uDDEA";
        if (lower.contains("norway"))                                     return "\uD83C\uDDF3\uD83C\uDDF4";
        if (lower.contains("denmark"))                                    return "\uD83C\uDDE9\uD83C\uDDF0";
        if (lower.contains("switzerland"))                                return "\uD83C\uDDE8\uD83C\uDDED";
        if (lower.contains("portugal"))                                   return "\uD83C\uDDF5\uD83C\uDDF9";
        if (lower.contains("pakistan"))                                   return "\uD83C\uDDF5\uD83C\uDDF0";
        if (lower.contains("turkey"))                                     return "\uD83C\uDDF9\uD83C\uDDF7";
        if (lower.contains("new zealand"))                                return "\uD83C\uDDF3\uD83C\uDDFF";
        if (lower.contains("ireland"))                                    return "\uD83C\uDDEE\uD83C\uDDEA";
        if (lower.contains("poland"))                                     return "\uD83C\uDDF5\uD83C\uDDF1";
        if (lower.contains("ukraine"))                                    return "\uD83C\uDDFA\uD83C\uDDE6";
        if (lower.contains("argentina"))                                  return "\uD83C\uDDE6\uD83C\uDDF7";
        if (lower.contains("south africa"))                               return "\uD83C\uDDFF\uD83C\uDDE6";
        if (lower.contains("egypt"))                                      return "\uD83C\uDDEA\uD83C\uDDEC";
        if (lower.contains("nigeria"))                                    return "\uD83C\uDDF3\uD83C\uDDEC";
        return "\uD83C\uDF0D"; // globe fallback
    }
}
