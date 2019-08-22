package com.example.cooker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

public class SearchPwActivity extends AppCompatActivity implements View.OnClickListener {
    EditText inputSearchId,inputSearchPhone;
    Button btnSearchPw;
    HttpResponse response;
    AsyncHttpClient client;
    String URL = "http://192.168.0.71:8080//ezen_server/member/member_searchpw.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pw);
        inputSearchId = findViewById(R.id.inputSearchId);
        inputSearchPhone = findViewById(R.id.inputSearchPhone);
        btnSearchPw = findViewById(R.id.btnSearchPw);

        client = new AsyncHttpClient();
        response = new HttpResponse();

        btnSearchPw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchPw:
                String mem_id =inputSearchId.getText().toString().trim();
                String mem_phone =inputSearchPhone.getText().toString().trim();

                RequestParams params = new RequestParams();
                params.put("mem_id",mem_id);
                params.put("mem_phone",mem_phone);
                client.post(URL,params,response);
                break;
        }
    }

    private class HttpResponse extends AsyncHttpResponseHandler{

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            String strjson = new String(responseBody);
            try {
                JSONObject json = new JSONObject(strjson);
                String rt = json.getString("rt");
                if(rt.equals("searchpw_ok")) {
                    JSONObject item = json.getJSONObject("item");
                    String mem_pwd = item.getString("mem_pwd");
                    Toast.makeText(getApplicationContext(), "비밀번호 :" + mem_pwd,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.putExtra("mem_pwd",mem_pwd);
                    setResult(RESULT_OK, intent);
                    finish();


                }else if(rt.equals("searchpw_fail")){
                    Toast.makeText(getApplicationContext(), "비밀번호를 찾을 수가 없습니다.",Toast.LENGTH_SHORT).show();
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