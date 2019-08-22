package com.example.cooker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class JoinActivity extends AppCompatActivity implements View.OnClickListener{

    EditText inputboxid, inputboxname,inputboxpw,inputboxpw2,inputboxphone,inputboxaddr,inputboxemail,inputboxbirth;
    Button btnjoin2, id_check;

    AsyncHttpClient client;
    HttpResponse response;
    String URL="http://192.168.0.71:8080//ezen_server/member/member_insert.jsp";
    String URL2="http://192.168.0.71:8080//ezen_server/member/member_checkid.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        inputboxid = findViewById(R.id.inputSearchId);
        inputboxname =findViewById(R.id.inputboxname);
        inputboxpw = findViewById(R.id.inputboxpw);
        inputboxpw2 = findViewById(R.id.inputboxpw2);
        inputboxphone = findViewById(R.id.inputboxphone);
        inputboxaddr = findViewById(R.id.inputboxaddr);
        inputboxemail = findViewById(R.id.inputboxemail);
        inputboxbirth = findViewById(R.id.inputboxbirth);

        client=new AsyncHttpClient();
        response=new HttpResponse(this);

        id_check= findViewById(R.id.id_check);
        btnjoin2 = findViewById(R.id.btnjoin2);

        btnjoin2.setOnClickListener(this);
        id_check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //인풋박스 내용 읽어와서 정규표현식 적용
        String mem_id = inputboxid.getText().toString().trim();
        String mem_name = inputboxname.getText().toString().trim();
        String mem_pwd = inputboxpw.getText().toString().trim();
        String mem_pwdck = inputboxpw2.getText().toString().trim();
        String mem_phone = inputboxphone.getText().toString().trim();
        String mem_address = inputboxaddr.getText().toString().trim();
        String mem_email =inputboxemail.getText().toString().trim();
        String mem_regident =inputboxbirth.getText().toString().trim();

        switch (v.getId()){
            case R.id.btnjoin2:
                //다이알로그창
//                FragmentManager fm = getSupportFragmentManager();
//                JoinConfirm joinConfirm = new JoinConfirm();
//                joinConfirm.show(fm, "dialog_회원가입확인");
                String err_msg= null;
                if(err_msg == null && !RegexHelper.getInstance().isValue(mem_id)){
                    err_msg = "아이디를 입력하세요.";
                }
                if(err_msg == null && !RegexHelper.getInstance().isValue(mem_name)){
                    err_msg = "이름을 입력하세요.";
                }
                if(err_msg == null && !RegexHelper.getInstance().isValue(mem_pwd)){
                    err_msg = "비밀번호를 입력하세요.";
                }else if(err_msg == null && !RegexHelper.getInstance().isValue(mem_pwdck)){
                    err_msg = "비밀번호를 다시 한번 더 입력하세요.";
                }
                if(err_msg == null && !mem_pwd.equals(mem_pwdck)){
                    err_msg = "비밀번호가 일치하지 않습니다.";
                }
                if(err_msg == null && !RegexHelper.getInstance().isValue(mem_email)){
                    err_msg ="이메일 주소를 입력해 주세요";
                }
                if(err_msg == null && !RegexHelper.getInstance().isEmail(mem_email)){
                    err_msg ="이메일 형식에 맞지 않습니다.";
                }
                if(err_msg == null && !RegexHelper.getInstance().isValue(mem_address)){
                    err_msg ="주소를 입력해 주세요";
                }
                if (err_msg == null && !RegexHelper.getInstance().isValue(mem_phone)) {
                    err_msg = "핸드폰번호를 입력하세요.";
                }
                if(err_msg == null && !RegexHelper.getInstance().isCellPhone(mem_phone)){
                    err_msg ="핸드폰번호가 형식에 맞지 않습니다.";
                }
                if(err_msg == null && !RegexHelper.getInstance().isNum(mem_regident)){
                    err_msg ="-를 제외한 숫자만 입력해 주세요";
                }
                if(err_msg !=null) {
                    Toast.makeText(this, err_msg, Toast.LENGTH_SHORT).show();
                    return; //실행 종료
                }

                RequestParams params =new RequestParams();
                params.put("mem_id",mem_id);
                params.put("mem_name",mem_name);
                params.put("mem_pwd",mem_pwd);
                params.put("mem_phone",mem_phone);
                params.put("mem_address",mem_address);
                params.put("mem_email",mem_email);
                params.put("mem_regident",mem_regident);
                params.put("mem_deli",mem_address);
                Log.d("mem_id","mem_id : "+mem_id);
                client.post(URL,params,response);
                break;

            case R.id.id_check:
                // 아이디 중복 체크
                if(!RegexHelper.getInstance().isValue(mem_id)){
                    Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                }else {
                    RequestParams params1 = new RequestParams();
                    params1.put("mem_id", mem_id);
                    client.post(URL2, params1, response);
                }
                break;
        }
    }

    class HttpResponse extends AsyncHttpResponseHandler {
        Activity activity;

        public HttpResponse(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            //회원가입성공여부
            String strJson = new String(responseBody);
            try {
                JSONObject json = new JSONObject(strJson);
                String rt = json.getString("rt");
                if (rt.equals("ok")) {
                    Toast.makeText(activity, " 회원가입 성공하였습니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                } else if (rt.equals("login_fail")) {
                    Toast.makeText(activity, "회원가입 실패하였습니다.", Toast.LENGTH_LONG).show();
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //아이디 중복여부
            String strJson2 = new String(responseBody);
            try {
                JSONObject json2 = new JSONObject(strJson2);
                String rt = json2.getString("rt");
                if(rt.equals("id_fail")){
                    Toast.makeText(getApplicationContext(), "사용중인 아이디 입니다.", Toast.LENGTH_SHORT).show();
                    inputboxid.setText("");

                }else if(rt.equals("id_ok")){
                    Toast.makeText(getApplicationContext(), "사용할 수 있는 아이디 입니다.", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(getApplicationContext(), "통신 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
