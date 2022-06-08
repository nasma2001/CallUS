package com.example.callus.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callus.Database.MyTrips;
import com.example.callus.Database.MyViewModel;
import com.example.callus.R;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.myTripsViewHolder> {
    Activity activity;
    List<MyTrips> trips;

    public TripsAdapter(Activity activity, List<MyTrips> trips, MyViewModel viewModel) {
        this.activity = activity;
        this.trips = trips;
    }

    @NonNull
    @Override
    public myTripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_trips,
                parent, false);
        return new TripsAdapter.myTripsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myTripsViewHolder holder, int position) {
        MyTrips trip = trips.get(position);

        holder.tvFrom.setText(trip.getPlaceFrom());
        holder.tvTo.setText(trip.getPlaceTo());
        holder.tvDate.setText(trip.getDate());
        holder.tvPrice.setText(String.valueOf(trip.getPrice()));
    }

    @Override
    public int getItemCount() {
        if (trips != null)
            return trips.size();
        return 0;
    }

    public static class myTripsViewHolder extends RecyclerView.ViewHolder {
        TextView tvFrom, tvTo, tvDate, tvPrice;

        public myTripsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFrom = itemView.findViewById(R.id.tvFrom);
            tvTo = itemView.findViewById(R.id.tvItemTo);
            tvDate = itemView.findViewById(R.id.tvItemDate);
            tvPrice = itemView.findViewById(R.id.tvItemPrice);
        }
    }
}
