package com.example.cooker.response;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.cooker.adapter.ItemAdapter;
import com.example.cooker.model.Product;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ItemResponse extends AsyncHttpResponseHandler {
    Activity activity;
    ItemAdapter adapter;
    ProgressDialog dialog;

    public ItemResponse(Activity activity, ItemAdapter adapter) {
        this.activity = activity;
        this.adapter = adapter;
    }

    @Override
    public void onStart() {
        dialog = new ProgressDialog(activity);
        dialog.setMessage("잠시만 기다려주세요.");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onFinish() {
        dialog.dismiss();
        dialog = null;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String strJson = new String(responseBody);
        try {
            JSONObject json = new JSONObject(strJson);
            String rt=json.getString("rt");
            int total = json.getInt("total");

            JSONArray item = json.getJSONArray("item");
            for(int i=0; i<item.length(); i++) {
                JSONObject temp = item.getJSONObject(i);


                String item_image=temp.getString("filename");
                Log.d("[DATAINFO2]",item_image);
                String item_date=temp.getString("item_date");
                Log.d("[DATAINFO2]",item_date);
                int item_price=temp.getInt("item_price");
                Log.d("[DATAINFO2]",String.valueOf(item_price));
                String item_time=temp.getString("item_time");
                Log.d("[DATAINFO2]",item_time);
                String item_name=temp.getString("item_name");
                Log.d("[DATAINFO2]",item_name);
                String item_total=temp.getString("item_total");
                Log.d("[DATAINFO2]",item_total);
                String item_content=temp.getString("item_content");
                Log.d("[DATAINFO2]",item_content);
                int item_num=temp.getInt("item_num");
                Log.d("[DATAINFO2]",String.valueOf(item_num));
                String item_category= temp.getString("item_category");
                Log.d("[DATAINFO2]",item_category);
                int item_quantity=temp.getInt("item_quantity");
                Log.d("[DATAINFO2]",String.valueOf(item_quantity));

                Product item1= new Product(item_num,item_category,item_name,item_content,item_price,item_quantity,item_image,item_date,item_total,item_time);

                if (item1== null){
                    Log.d("[INFO2]","item1=널");
                }
                else {
                    Log.d("[INFO2]","item1="+item1);
                }

                adapter.add(item1);
                Log.d("[DATAINFO2]","adapter.getCount()="+adapter.getCount());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(activity, "통신 실패"+statusCode, Toast.LENGTH_SHORT).show();
    }
}
