package com.thirudetails.example;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thirudetails.example.utils.Constants;
import com.thirudetails.example.utils.SampleDB;

public class MainActivity extends AppCompatActivity {

    EditText et_username, et_password;
    public String email, password;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sth = "Not registered yet? ";

        String sth2 = sth+"Sign up";
        SpannableString ss = new SpannableString(sth2);
//        ss.setSpan(new StyleSpan(Typeface.BOLD), sth.length(), ss.length(),0);

        Log.e("lens", sth.length()+" $$ "+ss.length());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
//                Toast.makeText(MainActivity.this, "Sign up", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan,sth.length(),sth2.length(),  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.BOLD), sth.length(), ss.length(), 0);
        Log.e("lens kart", sth.length()+" $$ "+sth2.length());
        TextView textView = findViewById(R.id.tvsignup);
        textView.setText(ss);
//        textView.setTextColor(Color.WHITE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);


        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        String na = Constants.getPrefernce(this, Constants.NAME);
        String pa = Constants.getPrefernce(this, Constants.PASSWORD);
        if (!na.isEmpty() && !pa.isEmpty()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_username.getText().toString();
                password = et_password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    et_username.setError("Please Enter Email");
                    et_username.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    et_password.setError("Please Enter Password");
                    et_password.requestFocus();
                } else if (!email.matches(SignUpActivity.EMAIL_PATTERN)){
                    et_username.setError("Please Enter correct Email");
                    et_username.requestFocus();
                }

                else {

                    int count = new SampleDB(MainActivity.this).validateUser(email, password);
                    if (count > 0){
                        String user = new SampleDB(MainActivity.this).getUserName(email);

                        Constants.setPrefernce(MainActivity.this, Constants.EMAIL, email);
                        Constants.setPrefernce(MainActivity.this, Constants.NAME, user);
                        Constants.setPrefernce(MainActivity.this, Constants.PASSWORD, password);
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        finish();
                    } else
                        Toast.makeText(MainActivity.this, "Invalid details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        
        
        
        
    }
}
