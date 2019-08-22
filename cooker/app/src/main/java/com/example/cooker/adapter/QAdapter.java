package com.example.cooker.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cooker.R;
import com.example.cooker.model.QnA;


import java.util.List;

public class QAdapter extends ArrayAdapter<QnA> {
    Activity activity;
    int resource;

    public QAdapter( Context context, int resource, List<QnA> objects) {
        super(context, resource, objects);
        activity = (Activity) context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(resource,null);
        }
        QnA item = getItem(position);
        if (item != null) {
            TextView textView = convertView.findViewById(R.id.textView);
            TextView textView1 = convertView.findViewById(R.id.textView1);
            TextView textView2 = convertView.findViewById(R.id.textView2);

            textView.setText(item.getQna_date());
            textView1.setText(item.getQna_subject());
            textView2.setText(item.getQna_re_status());
        }
        return convertView;
    }
}
