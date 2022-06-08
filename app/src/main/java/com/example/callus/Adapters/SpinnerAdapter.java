package com.example.callus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.callus.R;

public class SpinnerAdapter extends ArrayAdapter<String> {
    //vars
    private final Context context;
    private final String[] contentArray;
    private final Integer[] imageArray;

    public SpinnerAdapter(Context context, int resource, String[] objects,
                          Integer[] imageArray) {
        super(context,  R.layout.custom_spinner, R.id.tvSpn, objects);
        this.context = context;
        this.contentArray = objects;
        this.imageArray = imageArray;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_spinner, parent, false);

        TextView textView = row.findViewById(R.id.tvSpn);
        textView.setText(contentArray[position]);

        ImageView imageView = row.findViewById(R.id.ivIcon);
        imageView.setImageResource(imageArray[position]);

        return row;
    }
}
