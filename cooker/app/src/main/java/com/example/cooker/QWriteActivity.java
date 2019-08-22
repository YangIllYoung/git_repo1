package com.example.cooker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooker.helper.FileUtils;
import com.example.cooker.helper.PhotoHelper;
import com.example.cooker.helper.RegexHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

public class QWriteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText1, editText2, editText3, editText4;     // 입력 값
    TextView textView12;                                     // 글자 수
    Button button15,button16;                                // 등록, 취소
    ImageView imageView1;                                    // 사진첨부
    // 통신객체 선언
    AsyncHttpClient client;
    HttpResponse response;
    String url = "http://192.168.0.71:8080//ezen_server/qna/qna_insert.jsp";
    Activity activity;
    // 업로드할 사진파일의 경로
    String filePath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qwrite);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        textView12 = findViewById(R.id.textView12);
        imageView1 = findViewById(R.id.imageView1);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);

        button15.setOnClickListener(this);
        button16.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        client = new AsyncHttpClient();
        response = new HttpResponse(this);

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = editText2.getText().toString();
                textView12.setText(input.length() + " / 1000자");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        switch (v.getId()) {
            case R.id.imageView1:       // 사진등록
                showListDialog();
                break;
            case R.id.button15:         // 문의등록
                String qna_subject = editText1.getText().toString().trim();
                String qna_content = editText2.getText().toString().trim();
                String mem_email = editText3.getText().toString().trim();
                String mem_phone = editText4.getText().toString().trim();
                // 입력값검사
                RegexHelper regexHelper = RegexHelper.getInstance();
                String msg = null;
                if (msg == null && !regexHelper.isValue(qna_subject)) {
                    msg = "제목을 입력하세요";
                } else if (msg == null && !regexHelper.isValue(qna_content)) {
                    msg = "내용을 입력하세요";
                } else if (msg == null && !regexHelper.isEmail(mem_email)) {
                    msg = "이메일이 형식에 맞지 않습니다.";
                } else if (msg == null && !regexHelper.isNum(mem_phone)) {
                    msg = "핸드폰 번호를 입력하세요";
                }
                if (msg != null) {
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    return;
                }
                RequestParams params = new RequestParams();
                params.put("qna_subject", qna_subject);
                params.put("qna_content", qna_content);
                params.put("mem_email", mem_email);
                params.put("mem_phone", mem_phone);
                params.put("photo", filePath);
                try {
                    params.put("photo", new File(filePath));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                client.post(url, params, response);
                break;
            case R.id.button16:     // 취소.돌아가기
                finish();
                break;
        }
    }
    private void showListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 리스트에 출력할 문자열 배열
        final String[] items = {"새로 촬영하기", "갤러리에서 가져오기"};        // 리스트 : 긍정버튼의 역할
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                switch (which) {
                    case 0:     // 새로 촬영하기
                        filePath = PhotoHelper.getInstance().getNewPhotoPath();
//                        filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                        Log.d("[INFO333]", "filePath = " + filePath);
                        /* 카메라 내장 앱 사용 */
                        // 액션 + Uri
                        File file = new File(filePath);
                        Uri uri = null;
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 안드로이드 버전 관리
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            uri = FileProvider.getUriForFile(activity, getApplicationContext().getPackageName() + ".fileprovider", file);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        } else {
                            uri = Uri.fromFile(file);
                        }
                        Log.d("[INFO2]", "uri = " + uri);
                        // 저장될 경로를 파라미터로 설정
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        intent.putExtra(AUDIO_SERVICE, false);
                        // 카메라 앱 호출
                        startActivityForResult(intent, 100);
                        break;
                    case 1:
                        Intent gIntent = null;
                        if (Build.VERSION.SDK_INT >= 19) {
                            gIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                            gIntent.addCategory(Intent.CATEGORY_OPENABLE);
                        } else {
                            gIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        }
                        // 이미지 파일만 필터링
                        gIntent.setType("image/*");
                        gIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                        startActivityForResult(gIntent, 101);
                        break;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 100:       // 카메라 앱
                // 촬영 결과를 갤러리에 등록
                Intent photoIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + filePath));
                sendBroadcast(photoIntent);
                break;
            case 101:       // 갤러리 앱
                if (resultCode == RESULT_OK) {
                    // 선택한 파일 경로 얻기
                    filePath = FileUtils.getPath(this,data.getData());
                    Log.d("[INFO3]","filePath = " +filePath);
                }

        }
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        imageView1.setImageBitmap(bitmap);

    }
    class HttpResponse extends AsyncHttpResponseHandler {
        Activity activity;

        public HttpResponse (Activity activity) {
            this.activity = activity;
        }
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            String str = new String(responseBody);
            try {
                JSONObject json = new JSONObject(str);
                String rt = json.getString("rt");
                if (rt.equals("OK")) {
                    Toast.makeText(activity,"저장성공",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "저장실패", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(activity,"통신실패 " + statusCode, Toast.LENGTH_SHORT).show();

        }
    }
}
