package com.example.callus.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callus.Database.SavedPlacesModel;
import com.example.callus.R;

import java.util.ArrayList;

public class SavedPlaceAdapter extends RecyclerView.Adapter<SavedPlaceAdapter.savedPlaceViewHolder>{

    //vars
    Activity activity;
    ArrayList<SavedPlacesModel> savedPlaceData;

    public SavedPlaceAdapter(Activity activity, ArrayList<SavedPlacesModel> savedPlaceData) {
        this.activity = activity;
        this.savedPlaceData = savedPlaceData;
    }


    @NonNull
    @Override
    public savedPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_savedplace, parent, false);
        return new savedPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull savedPlaceViewHolder holder, int position) {
        SavedPlacesModel cat = savedPlaceData.get(position);
        holder.tvCity.setText(cat.getCity());
        holder.tvStreet.setText(cat.getStreet());
        holder.tvHome.setText(cat.getHome());

    }

    @Override
    public int getItemCount() {
        if (savedPlaceData!=null)
            return savedPlaceData.size();
        return 0;
    }

    public static class savedPlaceViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity, tvStreet,tvHome;
        public savedPlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tvItemCity);
            tvStreet = itemView.findViewById(R.id.tvItemStreet);
            tvHome = itemView.findViewById(R.id.tvItemHome);

        }
    }

}
