package umn.ac.keadaanudara.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import umn.ac.keadaanudara.R;
import umn.ac.keadaanudara.Model.City;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> implements Filterable {
    private Context context;
    private final List<City> cityList;
    private final List<City> cityListFull;
    private OnItemClickListener onItemClickListener;

    public CityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
        cityListFull = new ArrayList<>(cityList);
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_item, parent, false);
        return new CityViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = cityList.get(position);

        holder.txtLocationName.setText(city.getKabko());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<City> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(cityListFull);
            } else {
                String filterPattern = constraint.toString().toUpperCase().trim();

                for (City itemCity : cityListFull) {
                    if (itemCity.getKabko().contains(filterPattern)) {
                        filteredList.add(itemCity);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            cityList.clear();
            cityList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtLocationName;
        OnItemClickListener onItemClickListener;

        public CityViewHolder(@NonNull View itemView, OnItemClickListener clickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            txtLocationName = itemView.findViewById(R.id.txtLocationName);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
