package com.example.cooker;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooker.adapter.QAdapter;
import com.example.cooker.model.QnA;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class QListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button button1,button2,button4,button5,button6,button7;
    ListView listView;
    QAdapter adapter;
    List<QnA> list;
    LinearLayout layout, layout_list;
    TextView textView1, textView2,textView16;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    ImageView imageView;
    int currentPosition = 0;
    // 통신객체 선언
    AsyncHttpClient client;
    HttpResponse response;
    Activity activity;
    String url = "http://192.168.0.71:8080//ezen_server/qna/qna_list.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlist);

        setContentView(R.layout.activity_qlist);
        list = new ArrayList<>();
        adapter = new QAdapter(this,R.layout.qna_list,list);
        listView = findViewById(R.id.listView);
        layout = findViewById(R.id.layout);
        layout_list = findViewById(R.id.layout_list);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView16 = findViewById(R.id.textView16);
        layout.setVisibility(View.GONE);
        layout_list.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        imageView = findViewById(R.id.imageView);
        client = new AsyncHttpClient();
        response = new HttpResponse(this);

        listView.setAdapter(adapter);
        imageLoaderInit();

    }


    private void imageLoaderInit() {
        imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
            imageLoader.init(configuration);
        }
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.showImageOnLoading(R.drawable.ic_stub);
        builder.showImageForEmptyUri(R.drawable.ic_empty);
        builder.showImageOnFail(R.drawable.ic_error);
        options = builder.build();
    }

//    private void addData() {
//        adapter.add(new QnA("2019-08-13","배송관련문의","답변완료","왜 배송이 빨리 안되나요 얼른 보내주세요","네 오늘 배송해드립니다.","ilo2@naver.com","010-1111-1234","a14.jpeg"));
//    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();    // List 데이터 삭제
        client.post(url,response);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                Intent intent = new Intent(this, QWriteActivity.class);
                startActivity(intent);
                break;
            case R.id.button6:
                intent = new Intent(this, ReviewWriteActivity.class);
                startActivity(intent);
                break;
            case R.id.button7:
                intent = new Intent(this, ReviewListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        QnA item = adapter.getItem(position);
        layout.setVisibility(View.VISIBLE);
        layout_list.setVisibility(View.GONE);
        Log.d("[QLISTDATA]","subject-"+item.getQna_subject());
        Log.d("[QLISTDATA]","content-"+item.getQna_content());
        Log.d("[QLISTDATA]","qna_re-"+item.getQna_re());
        Log.d("[QLISTDATA]","qna_file"+item.getQna_file());
        textView1.setText(item.getQna_subject());
        textView2.setText(item.getQna_content());
        textView16.setText(item.getQna_re());
        imageLoader.displayImage(item.getQna_file(),imageView,options);
    }
    // 통신 응답 클래스
    class HttpResponse extends AsyncHttpResponseHandler {
        Activity activity;
        ProgressDialog dialog;

        public HttpResponse(Activity activity) {
            this.activity = activity;
        }

        // 통신 시작
        @Override
        public void onStart() {
            dialog = new ProgressDialog(activity);
            dialog.setMessage("잠시만 기다려주세요");
            dialog.setCancelable(false);
            dialog.show();
        }

        // 통신 종료
        @Override
        public void onFinish() {
            dialog.dismiss();
            dialog = null;
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            String str = new String(responseBody);
            try {
                JSONObject json = new JSONObject(str);
                String rt = json.getString("rt");
                int total = json.getInt("total");
                JSONArray item = json.getJSONArray("item");
                for (int i = 0; i < item.length(); i++) {
                    JSONObject jsonObject = item.getJSONObject(i);
                    QnA qnA = new QnA();

                    qnA.setQna_subject(jsonObject.getString("qna_subject"));
                    Log.d("[QNA_DATA]","qna_subject"+qnA.getQna_subject());

                    qnA.setQna_content(jsonObject.getString("qna_content"));
                    Log.d("[QNA_DATA]","qna_content"+qnA.getQna_content());

                    qnA.setQna_file(jsonObject.getString("qna_file"));
                    Log.d("[QNA_DATA]","qna_file"+qnA.getQna_file());

                    qnA.setMem_email(jsonObject.getString("mem_email"));
                    Log.d("[QNA_DATA]","mem_email"+qnA.getMem_email());

                    qnA.setMem_phone(jsonObject.getString("mem_phone"));
                    Log.d("[QNA_DATA]","mem_phone"+qnA.getMem_phone());
                    // List에 저장
                    adapter.add(qnA);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(activity, "통신실패 " + statusCode, Toast.LENGTH_SHORT).show();

        }
    }
}
