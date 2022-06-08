package com.example.callus.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callus.Database.MyViewModel;
import com.example.callus.Database.SavedPlacesModel;
import com.example.callus.R;

import java.util.List;

public class SavedPlaceAdapter extends RecyclerView.Adapter<SavedPlaceAdapter.savedPlaceViewHolder>{

    //vars
    Activity activity;
    List<SavedPlacesModel> savedPlaceData;
    private final MyViewModel viewModel;

    public SavedPlaceAdapter(Activity activity,List<SavedPlacesModel> savedPlaceData, MyViewModel viewModel) {
        this.activity = activity;
        this.savedPlaceData = savedPlaceData;
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public savedPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_savedplace,
                parent, false);
        return new savedPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull savedPlaceViewHolder holder, int position) {
        SavedPlacesModel place = savedPlaceData.get(position);

        holder.tvCity.setText(place.getCity());
        holder.tvStreet.setText(place.getStreet());
        holder.tvHome.setText(place.getHome());
        holder.delete.setOnClickListener(view -> {
            viewModel.deletePlaceById(savedPlaceData.get(position).getId());
            savedPlaceData.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        if (savedPlaceData!=null)
            return savedPlaceData.size();
        return 0;
    }

    public static class savedPlaceViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity, tvStreet,tvHome;
        ImageView delete;
        public savedPlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tvItemCity);
            tvStreet = itemView.findViewById(R.id.tvItemStreet);
            tvHome = itemView.findViewById(R.id.tvItemHome);
            delete = itemView.findViewById(R.id.btnDelete);
        }
    }

}
