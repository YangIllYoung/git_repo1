package com.example.cooker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.cooker.adapter.ReviewAdapter;
import com.example.cooker.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewListActivity extends AppCompatActivity implements View.OnClickListener {
    List<Review> list;
    ReviewAdapter adapter;
    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        list = new ArrayList<>();
        adapter = new ReviewAdapter(this, R.layout.review_list, list);
        listView = findViewById(R.id.listView);
        View footerView = getLayoutInflater().inflate(R.layout.review_footer,null);
        button = footerView.findViewById(R.id.button);

        listView.addFooterView(footerView);
        listView.setAdapter(adapter);

        button.setOnClickListener(this);

        addData();
    }
    private void addData() {
        adapter.add(new Review("오리엔탈 유린기", "2019-03-13",3,"hong123","오리엔탈 유린기 오리엔탈 유린기 오리엔탈 유린기(현지시간) 민주당의 의원 4인방을 향해 \"너희 원래 나라로 돌아가라\"고 공격하면서 큰 파문이 일고 있다.\n" +
                "2020년 미국 대선을 앞두고 최근 공개 표출된 민주당 지도부와", R.drawable.job01));
    }

    @Override
    public void onClick(View v) {
        addData();
    }
}
