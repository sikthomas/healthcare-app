package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText Username,Email, Password, Cpassword;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username=findViewById(R.id.txt_username);
        Password=findViewById(R.id.text_password);
        Email=findViewById(R.id.txt_email);
        Cpassword=findViewById(R.id.text_cpassword);
        Register=findViewById(R.id.btn_register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=Username.getText().toString();
                String email=Email.getText().toString();
                String password=Password.getText().toString();
                String cnpassword=Cpassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(username.length()==0 && email.length()==0 && password.length()==0 && cnpassword.length()==0){
                    Toast.makeText(RegisterActivity.this, "Fill all fields with data", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(cnpassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }else {
                    db.register(username,email,password);
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}