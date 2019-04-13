package com.klickfie;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.klickfie.utilities.Constants;
import com.klickfie.utilities.SharedPref;

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
                if(!SharedPref.getInstance().getBoolean(Constants.Login_ID)) {
                    Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                }
                else{
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }
                finish();
            }
        }, Constants.SPLASH_TIME);
    }
}
