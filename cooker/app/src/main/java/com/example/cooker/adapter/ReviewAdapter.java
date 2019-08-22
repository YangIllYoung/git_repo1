package com.example.cooker.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cooker.R;
import com.example.cooker.model.Review;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<Review> {
    Activity activity;
    int resource;

    public ReviewAdapter(Context context, int resource, List<Review> objects) {
        super(context, resource, objects);
        activity = (Activity) context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(resource, null);
        }
        Review item = getItem(position);
        if (item != null) {
            TextView textView1 = convertView.findViewById(R.id.textView1);
            TextView textView2 = convertView.findViewById(R.id.textView2);
            TextView textView3 = convertView.findViewById(R.id.textView3);
            RatingBar ratingBar = convertView.findViewById(R.id.ratingBar);
            ImageView imageView = convertView.findViewById(R.id.imageView1);
            TextView textView4 = convertView.findViewById(R.id.textView4);

            imageView.setImageResource(item.getImage());
            textView1.setText(item.getFoodname());
            textView2.setText(item.getReview_date());
            textView3.setText(item.getMem_id());
            textView4.setText(item.getReview_content());
            ratingBar.setRating(item.getRating());
        }
        return convertView;
    }
}
