package comp3025.assignment2.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import comp3025.assignment2.databinding.ItemSavedCityBinding;
import comp3025.assignment2.models.SavedCityModel;

/**
 * This class is the RecyclerView adapter for the Saved Cities screen.
 * It uses ViewBinding to bind each saved city to the item_saved_city layout.
 * Each card shows the city name, region, country, and a formatted timestamp.
 * A visible delete button and swipe-to-delete are both supported.
 */
public class SavedCitiesAdapter extends RecyclerView.Adapter<SavedCitiesAdapter.ViewHolder> {

    /**
     * This interface is called when a saved city card is tapped.
     * The caller is responsible for loading weather for the chosen city.
     */
    public interface OnClickListener {
        void onClick(SavedCityModel city);
    }

    /**
     * This interface is called when the delete button is tapped or when the card is swiped.
     * The caller is responsible for removing the city from Firestore.
     */
    public interface OnDeleteListener {
        void onDelete(SavedCityModel city);
    }

    /**
     * This field holds the current list of saved cities.
     */
    private List<SavedCityModel> data = new ArrayList<>();

    private final OnClickListener clickListener;
    private final OnDeleteListener deleteListener;

    public SavedCitiesAdapter(OnClickListener click, OnDeleteListener delete) {
        this.clickListener = click;
        this.deleteListener = delete;
    }

    /**
     * This method replaces the current list with a new list and refreshes the RecyclerView.
     */
    @SuppressWarnings("NotifyDataSetChanged")
    public void updateData(List<SavedCityModel> newData) {
        this.data = newData != null ? new ArrayList<>(newData) : new ArrayList<>();
        notifyDataSetChanged();
    }

    /**
     * This method is called by ItemTouchHelper when a card is swiped.
     * It retrieves the city at the given position and calls the delete listener.
     */
    public void deleteItem(int position) {
        if (position >= 0 && position < data.size())
            deleteListener.onDelete(data.get(position));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSavedCityBinding b = ItemSavedCityBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), clickListener, deleteListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * This class is the ViewHolder for each saved city card.
     * It uses ViewBinding to access the views in item_saved_city.xml.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemSavedCityBinding b;

        ViewHolder(ItemSavedCityBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        /**
         * This method binds a SavedCityModel to the card views.
         * City name, region/country, and a formatted timestamp are shown.
         * The card tap and delete button are wired to the provided listeners.
         */
        void bind(SavedCityModel city, OnClickListener click, OnDeleteListener del) {
            //Show the city name.
            b.savedCityNameTextView.setText(city.getName());

            //Show region and country together, or just country if region is empty.
            String region = city.getRegion();
            if (region != null && !region.isEmpty())
                b.savedRegionCountryTextView.setText(region + ", " + city.getCountry());
            else
                b.savedRegionCountryTextView.setText(city.getCountry());

            //Format the timestamp as "Today, 9:38", "Yesterday", or "Mar 18".
            Timestamp ts = city.getLastSearched();
            b.savedTimestampTextView.setText(ts != null ? formatTimestamp(ts.toDate()) : "");

            //Tap the card to load weather for this city.
            b.getRoot().setOnClickListener(v -> click.onClick(city));

            //Tap the delete button to remove this city from Firestore.
            b.savedDeleteButton.setOnClickListener(v -> del.onDelete(city));
        }

        /**
         * This method formats a Date into a human-readable string.
         * Today: "Today, 9:38 AM"
         * Yesterday: "Yesterday"
         * Older: "Mar 18"
         */
        private String formatTimestamp(Date d) {
            Calendar now = Calendar.getInstance();
            Calendar then = Calendar.getInstance();
            then.setTime(d);

            boolean sameDay = now.get(Calendar.YEAR) == then.get(Calendar.YEAR)
                    && now.get(Calendar.DAY_OF_YEAR) == then.get(Calendar.DAY_OF_YEAR);
            boolean yesterday = now.get(Calendar.YEAR) == then.get(Calendar.YEAR)
                    && now.get(Calendar.DAY_OF_YEAR) - then.get(Calendar.DAY_OF_YEAR) == 1;

            if (sameDay) return "Today, " + new SimpleDateFormat("h:mm a", Locale.US).format(d);
            if (yesterday) return "Yesterday";
            return new SimpleDateFormat("MMM d", Locale.US).format(d);
        }
    }
}
