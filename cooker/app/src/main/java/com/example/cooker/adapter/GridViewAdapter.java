package com.example.cooker.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cooker.R;
import com.example.cooker.model.Product;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class GridViewAdapter extends ArrayAdapter<Product> {
    Activity activity;
    int resource;
    ImageLoader imageLoader;
    DisplayImageOptions options;

    public GridViewAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);

        this.activity = (Activity) context;
        this.resource = resource;

        imageLoaderInit();
    }

    private void imageLoaderInit() {
        imageLoader = imageLoader.getInstance();
        if(!imageLoader.isInited()) {
            ImageLoaderConfiguration configuration =
                    ImageLoaderConfiguration.createDefault(activity);
            imageLoader.init(configuration);
        }

        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.showImageOnLoading(R.drawable.ic_stub);
        builder.showImageForEmptyUri(R.drawable.ic_empty);
        builder.showImageOnFail(R.drawable.ic_error);
        builder.cacheInMemory(true);
        // 한번 다운받은 파일을 Disk에 보관할 지 여부
        builder.cacheOnDisk(true);
        // Exif 정보를 유지할 지 여부
        builder.considerExifParams(true);
        options = builder.build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = activity.getLayoutInflater().inflate(resource, null);
        }
        Product item = getItem(position);
        Log.d("[Adapter-DATA]",item.getItem_category());
        if(item != null) {
            //GridView gridView = convertView.findViewById(R.id.maingridView);
            ImageView imageView =convertView.findViewById(R.id.imageView1_list);
            TextView textView1 = convertView.findViewById(R.id.textView1_list);
            TextView textView2 = convertView.findViewById(R.id.textView2_list);


            textView1.setText(item.getItem_name());
            textView2.setText(String.valueOf(item.getItem_price()));
            imageLoader.displayImage(item.getItem_image(),imageView,options);
        }
        return convertView;
    }
}
