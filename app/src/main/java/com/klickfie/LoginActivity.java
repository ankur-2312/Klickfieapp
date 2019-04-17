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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.klickfie.utilities.*;
import com.klickfie.utilities.LoginPojo;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {


    private Button butLogin;
    private TextInputEditText etPhoneNo;
    private TextView tvNotRegistered;
    private ProgressBar progressBar;
    private ImageView ivSwipeArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initViews();
        //spannableString();
    }

    private void initViews() {
        ivSwipeArrow =findViewById(R.id.ivSwipeArrow);
        butLogin = findViewById(R.id.butLogin);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        tvNotRegistered = findViewById(R.id.tvNotRegistered);
        butLogin.setOnClickListener(this);
        etPhoneNo.addTextChangedListener(this);
        ivSwipeArrow.setOnClickListener(this);
        // progressBar=findViewById(R.id.progressBarLogin);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.butLogin:


            Call<LoginPojo> call = ApiInstance.getInstance().getApi().login(Constants.NORMAL_LOGIN, Constants.SOCIAL_TYPE, Constants.COUNTRY_CODE, Objects.requireNonNull(etPhoneNo.getText()).toString(), Constants.DEVICE_TYPE);
            call.enqueue(new Callback<LoginPojo>() {
                @Override
                public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {

                    assert response.body() != null;

                    if (response.body().getCODE() == Constants.LOGIN_SUCCESS) {
                        Log.i(Constants.Tag, "Login Success");
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.PHONE_NO_KEY, etPhoneNo.getText().toString());
                        OTPFragment otpFragment = new OTPFragment();
                        otpFragment.setArguments(bundle);
                        otpFragment.show(getSupportFragmentManager(), otpFragment.getTag());


                    }
                }

                @Override
                public void onFailure(Call<LoginPojo> call, Throwable t) {

                    Log.i(Constants.Tag, "" + t);
                }
            });
            break;

            case R.id.ivSwipeArrow:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String phoneNo = Objects.requireNonNull(etPhoneNo.getText()).toString();
        if (phoneNo.length() < 10) {
            butLogin.setEnabled(false);
            butLogin.setTextColor(getResources().getColor(R.color.buttonColor));
            butLogin.setBackgroundResource(R.drawable.rounded_button_disable);
        } else {
            butLogin.setEnabled(true);
            butLogin.setTextColor(getResources().getColor(R.color.white));
            butLogin.setBackgroundResource(R.drawable.rounded_button);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void spannableString() {
        String str = getString(R.string.notregistered);

        SpannableString spannableString = new SpannableString(str);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.buttonColor));
                ds.setUnderlineText(false);
                ds.baselineShift = 0;
            }
        };
        spannableString.setSpan(clickableSpan, 20, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvNotRegistered.setText(spannableString);
        tvNotRegistered.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
