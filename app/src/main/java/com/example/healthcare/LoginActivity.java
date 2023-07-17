package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private Button login;
    private TextView toRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.txt_username);
        password=findViewById(R.id.text_password);
        login=findViewById(R.id.btn_login);
        toRegister=findViewById(R.id.txt_To_register);

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=username.getText().toString();
                String pword=password.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(TextUtils.isEmpty(uname)){
                    Toast.makeText(LoginActivity.this, "Username is empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pword)) {
                    Toast.makeText(LoginActivity.this, "Password field is empty", Toast.LENGTH_SHORT).show();
                }else if(pword.length()<8){
                    Toast.makeText(LoginActivity.this, "Your password must be greater then 7 characters", Toast.LENGTH_SHORT).show();
                }else {
                    if(db.loginn(uname,pword)==1){
                        Toast.makeText(LoginActivity.this, "You have successifully loged in....", Toast.LENGTH_SHORT).show();
                        SharedPreferences pref=getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=pref.edit();
                        editor.putString("username",uname);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Wrong username and password...", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}