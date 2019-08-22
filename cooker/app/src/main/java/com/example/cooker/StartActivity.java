package com.example.cooker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnlogin, btngoogle;
    TextView btnjoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //스테이터스바 숨기기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        //액션바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btngoogle = findViewById(R.id.btngoogle);
        btnlogin = findViewById(R.id.btnlogin);
        btnjoin = findViewById(R.id.btnjoin);

        btngoogle.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        btnjoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btngoogle://구글로그인
//                intent = new Intent();
//                startActivity(intent);

                break;
            case R.id.btnlogin://그냥 로그인
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

                break;
            case R.id.btnjoin://회원가입
                intent = new Intent(this,JoinActivity.class);
                startActivity(intent);
                break;
        }

    }
}
