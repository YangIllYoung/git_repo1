package com.example.cooker;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.cooker.helper.RegexHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class SearchIdActivity extends AppCompatActivity implements View.OnClickListener {
    EditText inputSearchId;//이메일입력
    Button btnSearchId;

    AsyncHttpClient client;
    HttpResponse response;
    String URL ="http://192.168.0.71:8080//ezen_server/member/member_searchId.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_id);
        inputSearchId = findViewById(R.id.inputSearchId);
        btnSearchId = findViewById(R.id.btnSearchId);

        client = new AsyncHttpClient();
        response = new HttpResponse(this);
        btnSearchId.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchId:
                String mem_email = inputSearchId.getText().toString().trim();
                if(mem_email ==null || !RegexHelper.getInstance().isValue(mem_email)){
                    Toast.makeText(this, "이메일이 정확하지 않습니다.",Toast.LENGTH_LONG).show();
                    return;
                }
                RequestParams params = new RequestParams();
                params.put("mem_email",mem_email);
                client.post(URL,params,response);
                break;
        }
    }

    private class HttpResponse extends AsyncHttpResponseHandler {
        Activity activity;

        public HttpResponse(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            String strString = new String(responseBody);
            try {
                JSONObject json = new JSONObject(strString);
                String rt = json.getString("rt");
                if(rt.equals("searchid_ok")){
                    JSONObject item = json.getJSONObject("item");
                    String mem_id = item.getString("mem_id");
                    Toast.makeText(getApplicationContext(), "아이디 :" + mem_id,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("mem_id",mem_id);
                    setResult(RESULT_OK, intent);
                    finish();

                }else if(rt.equals("searchid_fail")){
                    Toast.makeText(getApplicationContext(), "이메일을 찾을 수가 없습니다.",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

        }
    }
}