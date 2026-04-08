package comp3025.assignment2.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import comp3025.assignment2.databinding.ItemSavedCityBinding;
import comp3025.assignment2.models.SavedCityModel;
import com.google.firebase.Timestamp;

/**
 * Adapter for the Saved Cities RecyclerView.
 */
public class SavedCitiesAdapter extends RecyclerView.Adapter<SavedCitiesAdapter.ViewHolder> {

    public interface OnClickListener { void onClick(SavedCityModel city); }
    public interface OnDeleteListener { void onDelete(SavedCityModel city); }

    private List<SavedCityModel> data = new ArrayList<>();
    private final OnClickListener  clickListener;
    private final OnDeleteListener deleteListener;

    public SavedCitiesAdapter(OnClickListener click, OnDeleteListener delete) {
        this.clickListener  = click;
        this.deleteListener = delete;
    }

    @SuppressWarnings("NotifyDataSetChanged")
    public void updateData(List<SavedCityModel> newData) {
        this.data = newData != null ? new ArrayList<>(newData) : new ArrayList<>();
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (position >= 0 && position < data.size())
            deleteListener.onDelete(data.get(position));
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSavedCityBinding b = ItemSavedCityBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), clickListener, deleteListener);
    }

    @Override public int getItemCount() { return data.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSavedCityBinding b;
        ViewHolder(ItemSavedCityBinding b) { super(b.getRoot()); this.b = b; }

        void bind(SavedCityModel city, OnClickListener click, OnDeleteListener del) {
            b.savedCityNameTextView.setText(city.getName());
            String region = city.getRegion();
            if (region != null && !region.isEmpty())
                b.savedRegionCountryTextView.setText(region + ", " + city.getCountry());
            else
                b.savedRegionCountryTextView.setText(city.getCountry());

            Timestamp ts = city.getLastSearched();
            b.savedTimestampTextView.setText(ts != null ? formatTs(ts.toDate()) : "");
            b.getRoot().setOnClickListener(v -> click.onClick(city));
            b.savedDeleteButton.setOnClickListener(v -> del.onDelete(city));
        }

        private String formatTs(Date d) {
            Calendar now = Calendar.getInstance(), then = Calendar.getInstance();
            then.setTime(d);
            boolean sameDay = now.get(Calendar.YEAR) == then.get(Calendar.YEAR)
                    && now.get(Calendar.DAY_OF_YEAR) == then.get(Calendar.DAY_OF_YEAR);
            boolean yesterday = now.get(Calendar.YEAR) == then.get(Calendar.YEAR)
                    && now.get(Calendar.DAY_OF_YEAR) - then.get(Calendar.DAY_OF_YEAR) == 1;
            if (sameDay)   return "Today, " + new SimpleDateFormat("h:mm a", Locale.US).format(d);
            if (yesterday) return "Yesterday";
            return new SimpleDateFormat("MMM d", Locale.US).format(d);
        }
    }
}
