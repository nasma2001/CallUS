package com.example.callus.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callus.Models.SavedPlaceData;
import com.example.callus.R;

import java.util.ArrayList;

class SavedPlaceAdepter extends RecyclerView.Adapter<SavedPlaceAdepter.savedPlaceViewHolder>{

    Activity fragment;
    ArrayList<SavedPlaceData> savedPlaceData;

    public SavedPlaceAdepter(Activity activity, ArrayList<SavedPlaceData> savedPlaceData) {
        this.fragment = activity;
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
        final int newPosition = position;
        SavedPlaceData cat = savedPlaceData.get(position);
        holder.textView1.setText(cat.getTxtView1());
        holder.textView2.setText(cat.getTxtView2());

    }

    @Override
    public int getItemCount() {
        return savedPlaceData.size();
    }

    public class savedPlaceViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2;
        public savedPlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);

        }
    }

}
