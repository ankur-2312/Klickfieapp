package com.klickfie;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHandler();
    }

    private void getHandler(){
        Handler hand=new Handler(getMainLooper());
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentLogin=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentLogin);
                finish();
            }
        },1000);
    }
}
