package com.example.a20210406;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        startLoading();
    }


    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(getApplicationContext(), LoginActivcity.class);
                //로그인화면을 띄운다.
                startActivity(intent);
                //현재 액티비티 종료
                finish();
            }
        }, 2000);
        // 화면에 Logo 2초간 보이기
    }// startLoading Method..
}
