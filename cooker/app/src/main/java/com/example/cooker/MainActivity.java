package com.example.cooker;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.cooker.adapter.GridViewAdapter;
import com.example.cooker.model.Member;
import com.example.cooker.model.Product;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {
    List<Product> list;
    AsyncHttpClient client;
    HttpResponse response;
    GridViewAdapter adapter;
    GridView gridView;
    BottomNavigationView bottomNavigationView;
    ScrollView scrollView;
    String URL = "http://192.168.0.71:8080//ezen_server/item/item_list_newdate.jsp";
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list = new ArrayList<>();
        adapter = new GridViewAdapter(this, R.layout.list_item, list);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        gridView = findViewById(R.id.gridView_main);
        client = new AsyncHttpClient();
        scrollView=findViewById(R.id.scoroll);
        response = new HttpResponse(this);

        gridView.setAdapter(adapter);

        member= (Member) getIntent().getSerializableExtra("member");
        Log.d("[MEMBER]","="+member.getMem_name());
        if (member.getMem_name().equals("")){
            setTitle("로그인");
        }else {
            setTitle(member.getMem_name() + "님");
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        gridView.setOnItemClickListener(this);
        Log.d("[TEST]", "MAIN진입");
        scrollView.setOnTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        client.post(URL,response);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product item =adapter.getItem(position);

        Intent intent = new Intent(this,MenuDetailActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()){
            case  R.id.action_home :
                return true;
            case R.id.action_menu:
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_review:
                intent = new Intent(this, QListActivity.class);
                intent.putExtra("member",member);
                startActivity(intent);
                return true;
            case R.id.action_mypage:
                return true;
            case R.id.action_chat:
                return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.btn1:
                Toast.makeText(this, "장바구니" , Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                Toast.makeText(this, "로그아웃" , Toast.LENGTH_SHORT).show();
                break;

            case R.id.help:
                Toast.makeText(this, "도움말" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.qna:
                intent=new Intent(this, QListActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    class HttpResponse extends AsyncHttpResponseHandler {
        Activity activity;


        public HttpResponse(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            String strJson = new String(responseBody);
            try {
                JSONObject json = new JSONObject(strJson);
                String rt = json.getString("rt");
                int total = json.getInt("total");

                JSONArray item = json.getJSONArray("item");
                for (int i = 0; i < item.length(); i++) {
                    JSONObject temp = item.getJSONObject(i);

                    String item_image=temp.getString("filename");
                    Log.d("[DATAINFO]",item_image);
                    String item_date=temp.getString("item_date");
                    Log.d("[DATAINFO]",item_date);
                    int item_price=temp.getInt("item_price");
                    Log.d("[DATAINFO]",String.valueOf(item_price));
                    String item_time=temp.getString("item_time");
                    Log.d("[DATAINFO]",item_time);
                    String item_name=temp.getString("item_name");
                    Log.d("[DATAINFO]",item_name);
                    String item_total=temp.getString("item_total");
                    Log.d("[DATAINFO]",item_total);
                    String item_content=temp.getString("item_content");
                    Log.d("[DATAINFO]",item_content);
                    int item_num=temp.getInt("item_num");
                    Log.d("[DATAINFO]",String.valueOf(item_num));
                    String item_category= temp.getString("item_category");
                    Log.d("[DATAINFO]",item_category);
                    int item_quantity=temp.getInt("item_quantity");
                    Log.d("[DATAINFO]",String.valueOf(item_quantity));

                    Product item1= new Product(item_num,item_category,item_name,item_content,item_price,item_quantity,item_image,item_date,item_total,item_time);

                    if (item1== null){
                        Log.d("[INFO]","item1=널");
                    }
                    else {
                        Log.d("[INFO]","item1="+item1);
                    }
                    //list.add(item1);
                    adapter.add(item1);
                    Log.d("[DATAINFO]","adapter.getCount()="+adapter.getCount());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(activity, "통신실패", Toast.LENGTH_SHORT).show();
        }


    }
}
