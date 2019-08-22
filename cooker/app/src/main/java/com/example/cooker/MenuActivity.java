package com.example.cooker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooker.adapter.ItemAdapter;
import com.example.cooker.model.Product;
import com.example.cooker.response.ItemResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    List<Product> list;
    ItemAdapter adapter;
    GridView gridView;
    AsyncHttpClient client;
    ItemResponse response;
    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    BottomNavigationView bottomNavigationView;

    String URL = "http://192.168.0.71:8080//ezen_server/item/item_list.jsp";

    String item_category;
    String URL_S = "http://192.168.0.71:8080//ezen_server/item/item_listS.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setTitle("COOKING 메뉴");
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        list = new ArrayList<>();
        adapter = new ItemAdapter(this, R.layout.list_menu, list);
        gridView = findViewById(R.id.gridView);
        client = new AsyncHttpClient();
        response = new ItemResponse(this, adapter);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        client.post(URL, response);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product item = adapter.getItem(position);
        Intent intent = new Intent(this, MenuDetailActivity.class);
        intent.putExtra("item", item);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        RequestParams params = new RequestParams();
        switch (v.getId()) {
            case R.id.textView1:    // 전체
                list = new ArrayList<>();
                adapter = new ItemAdapter(this, R.layout.list_menu, list);
                gridView = findViewById(R.id.gridView);
                client = new AsyncHttpClient();
                response = new ItemResponse(this, adapter);

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(this);

                client.post(URL, response);
                break;

            case R.id.textView2:    // 한식
                item_category = "한식";
                params.put("item_category", item_category);

                list = new ArrayList<>();
                adapter = new ItemAdapter(this, R.layout.list_menu, list);
                gridView = findViewById(R.id.gridView);
                response = new ItemResponse(this, adapter);

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(this);

                client.post(URL_S, params, response);
                break;

            case R.id.textView3:    // 일식
                item_category = "일식";
                params.put("item_category", item_category);

                list = new ArrayList<>();
                adapter = new ItemAdapter(this, R.layout.list_menu, list);
                gridView = findViewById(R.id.gridView);
                response = new ItemResponse(this, adapter);

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(this);

                client.post(URL_S, params, response);
                break;

            case R.id.textView4:    // 중식
                item_category = "중식";
                params.put("item_category", item_category);

                list = new ArrayList<>();
                adapter = new ItemAdapter(this, R.layout.list_menu, list);
                gridView = findViewById(R.id.gridView);
                response = new ItemResponse(this, adapter);

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(this);

                client.post(URL_S, params, response);
                break;

            case R.id.textView5:    // 파스타
                item_category = "파스타";
                params.put("item_category", item_category);

                list = new ArrayList<>();
                adapter = new ItemAdapter(this, R.layout.list_menu, list);
                gridView = findViewById(R.id.gridView);
                response = new ItemResponse(this, adapter);

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(this);

                client.post(URL_S, params, response);
                break;

            case R.id.textView6:    // 소스
                item_category = "소스";
                params.put("item_category", item_category);

                list = new ArrayList<>();
                adapter = new ItemAdapter(this, R.layout.list_menu, list);
                gridView = findViewById(R.id.gridView);
                response = new ItemResponse(this, adapter);

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(this);

                client.post(URL_S, params, response);
                break;
        }
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
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()){
            case  R.id.action_home :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_menu:
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_review:
                return true;
            case R.id.action_mypage:
                return true;
            case R.id.action_chat:
                return true;
        }
        return false;
    }

}
