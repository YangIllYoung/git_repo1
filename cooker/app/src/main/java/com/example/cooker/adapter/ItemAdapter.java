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

public class ItemAdapter extends ArrayAdapter<Product> {
    Activity activity;
    int resource;
    ImageLoader imageLoader;
    DisplayImageOptions options;

    public ItemAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        activity = (Activity) context;
        this.resource = resource;

        imageLoaderinit();
    }

    private void imageLoaderinit() {
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
        Log.d("[item2]",item.getItem_image());

        Log.d("[itemAdapter-DATA]",item.getItem_category());
        if(item != null) {
            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView textView1 = convertView.findViewById(R.id.textView1);
            TextView textView2 = convertView.findViewById(R.id.textView2);
            TextView textView3 = convertView.findViewById(R.id.textView3);

            imageLoader.displayImage(item.getItem_image(), imageView, options);
            textView1.setText("카테고리 >> " +item.getItem_category());
            textView2.setText(item.getItem_name());
            textView3.setText("단가 : " + item.getItem_price() + "원");
        }
        return convertView;
    }
}
