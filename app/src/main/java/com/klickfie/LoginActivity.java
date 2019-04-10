package com.klickfie;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private Button butLogin;
    private TextInputEditText etPhoneNo;
    private TextView tvNotRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
               WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initViews();
        spannableString();
    }

    private void initViews(){
        butLogin=findViewById(R.id.butLogin);
        etPhoneNo=findViewById(R.id.etPhoneNo);
        tvNotRegistered=findViewById(R.id.tvNotRegistered);
        butLogin.setOnClickListener(this);
        etPhoneNo.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {



    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String phoneNo= Objects.requireNonNull(etPhoneNo.getText()).toString();
        if(phoneNo.length()<10){
            butLogin.setEnabled(false);
            butLogin.setTextColor(getResources().getColor(R.color.buttonColor));
            butLogin.setBackgroundResource(R.drawable.rounded_button_disable);
        }
        else
        {
            butLogin.setEnabled(true);
            butLogin.setTextColor(getResources().getColor(R.color.white));
            butLogin.setBackgroundResource(R.drawable.rounded_button);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void spannableString(){
        String str="Not Registered Yet? SignUp First";

        SpannableString spannableString=new SpannableString(str);

        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick( View widget) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }

            @Override
            public void updateDrawState( TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.buttonColor));
                ds.setUnderlineText(false);
                //ds.bgColor=0;
                ds.baselineShift=0;
            }
        };
        spannableString.setSpan(clickableSpan,20,32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvNotRegistered.setText(spannableString);
        tvNotRegistered.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
