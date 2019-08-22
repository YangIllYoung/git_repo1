package com.example.cooker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooker.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MenuDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    TextView textView1, textView2, textView3, textView4, textView5;
    Button button1, button2;
    ImageLoader imageLoader;
    RatingBar ratingBar;
    String name;
    String content;
    String total;
    String time;
    float rate;
    int result;
    Product item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Intent intent = getIntent();
        item = (Product) intent.getSerializableExtra("item");
        setTitle(item.getItem_name());
        imageView = findViewById(R.id.imageView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        ratingBar = findViewById(R.id.ratingBar);

        imageLoader = ImageLoader.getInstance();

        name = item.getItem_name();
        content = item.getItem_content();
        total = item.getItem_total();
        time = item.getItem_time();
        result = 5;
        ratingBar.setRating(4);
        rate = ratingBar.getRating();

        textView1.setText(content);
        textView2.setText(name);
        textView3.setText(total + " | ");
        textView4.setText("조리시간 : " + time + " | ");
        textView5.setText("평점 : " + rate + "/" +result + " ");
        imageLoader.displayImage(item.getItem_image(), imageView);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Toast.makeText(this, "선물하기", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button2:
                Toast.makeText(this, "장바구니" , Toast.LENGTH_SHORT).show();
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
}
