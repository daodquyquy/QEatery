package com.example.qeatery.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qeatery.MainActivity;
import com.example.qeatery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Context context = LoginActivity.this;

    private EditText edt_email,edt_password;
    private Button btn_login;
    private TextView tv_resigter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        anh_xa();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap();
            }
        });
        tv_resigter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resigter = new Intent(LoginActivity.this , ResigterActivity.class);
                startActivity(resigter);
            }
        });

    }

    private void dangNhap(){
        mAuth.signInWithEmailAndPassword(edt_email.getText().toString(), edt_password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(LoginActivity.this , MainActivity.class);
                            startActivity(login);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, "Đăng nhập thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void anh_xa(){
        edt_email = findViewById(R.id.edt_email_login);
        edt_password = findViewById(R.id.edt_password_login);
        btn_login = findViewById(R.id.btn_login);
        tv_resigter = findViewById(R.id.tv_resigter);
    }

    private void validate(){
        if(edt_email.getText().length()==0 || edt_password.getText().length()==0){
            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
        }
    }
}