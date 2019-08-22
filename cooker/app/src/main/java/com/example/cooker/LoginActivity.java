package com.example.cooker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooker.model.Member;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText inputboxid,inputboxpw;
    Button btnlogin2;
    TextView searchid, searchpw;
    Member member;

    boolean login_ck=false;

    AsyncHttpClient client;
    HttpResponse response;
    String URL="http://192.168.0.71:8080//ezen_server/member/member_login.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputboxid = findViewById(R.id.inputSearchId);
        inputboxpw = findViewById(R.id.inputboxpw);
        btnlogin2 = findViewById(R.id.btnlogin2);
        searchid = findViewById(R.id.searchid);
        searchpw = findViewById(R.id.searchpw);

        client = new AsyncHttpClient();
        response = new HttpResponse(this);

        btnlogin2.setOnClickListener(this);
        searchid.setOnClickListener(this);
        searchpw.setOnClickListener(this);

        permissionCheck();

    }

    private void permissionCheck() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnlogin2:
                //로그인
                String member_id= inputboxid.getText().toString().trim();
                String member_pwd = inputboxpw.getText().toString().trim();
                if (member_id == null){
                    Toast.makeText(this,member_id,Toast.LENGTH_SHORT).show();
                }
                RequestParams params = new RequestParams();
                params.put("mem_id",member_id);
                params.put("mem_pwd",member_pwd);
                client.post(URL,params,response);

                break;

            case R.id.searchid:
                //아이디 찾기
                intent = new Intent(this,SearchIdActivity.class);
                startActivityForResult(intent,100);

                break;
            case R.id.searchpw:
                //비밀번호 찾기
                intent = new Intent(this,SearchPwActivity.class);
                startActivityForResult(intent,101);
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 100:
                if(resultCode ==RESULT_OK){
                    inputboxid.setText(data.getStringExtra("mem_id"));
                }
                break;
            case 101:
                if(resultCode ==RESULT_OK){
                    inputboxpw.setText(data.getStringExtra("mem_pwd"));
                }
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
            String strJson = new String(responseBody);

            try {
                JSONObject json = new JSONObject(strJson);
                String rt =json.getString("rt");

                if (rt.equals("login_ok")) {
                    JSONArray item = json.getJSONArray("item");
                    for (int i = 0; i < item.length(); i++) {
                        JSONObject jsonObject = item.getJSONObject(i);
                        member = new Member();
                        member.setMem_num(jsonObject.getInt("mem_num"));
                        member.setMem_name(jsonObject.getString("mem_name"));
                        member.setMem_id(jsonObject.getString("mem_id"));
                        member.setMem_date(jsonObject.getString("mem_date"));
                        member.setMem_deli(jsonObject.getString("mem_deli"));
                        member.setMem_regident(jsonObject.getString("mem_regident"));
                        member.setMem_phone(jsonObject.getString("mem_phone"));
                        member.setMem_authority(jsonObject.getString("mem_authority"));
                        member.setMem_pwd(jsonObject.getString("mem_pwd"));
                        member.setMem_address(jsonObject.getString("mem_address"));
                    }
                }
                if (rt.equals("login_ok")){
                    Toast.makeText(activity," 로그인 성공하였습니다.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("member",member);
                    startActivity(intent);
                }else if (rt.equals("login_fail")){
                    Toast.makeText(activity,"아이디와 비밀번호를 확인하세요.",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(getApplicationContext(),"통신 실패",Toast.LENGTH_SHORT).show();

        }
    }
}
