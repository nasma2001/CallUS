package com.example.callus.Fragments.MainFragmentActivities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callus.R;

import java.util.ArrayList;

class savedplaceAdepter extends RecyclerView.Adapter<savedplaceAdepter.savedplaceViewHolder>{

    Activity fragment;
    ArrayList<savedplaceData> savedplaceData;

    public savedplaceAdepter(Activity activity, ArrayList<savedplaceData> savedplaceData) {
        this.fragment = activity;
        this.savedplaceData = savedplaceData;
    }


    @NonNull
    @Override
    public savedplaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_savedplace, parent, false);
        return new savedplaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull savedplaceViewHolder holder, int position) {
        final int newPosition = position;
        savedplaceData cat = savedplaceData.get(position);
        holder.textView1.setText(cat.getTxtview1());
        holder.textView2.setText(cat.getTxtview2());

    }

    @Override
    public int getItemCount() {
        return savedplaceData.size();
    }

    public class savedplaceViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2;
        public savedplaceViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);

        }
    }

}
