package com.example.qeatery.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qeatery.MainActivity;
import com.example.qeatery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ResigterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btn_back;
    Context context = ResigterActivity.this;
    private EditText edt_email,edt_password,edt_repassword;
    private Button btn_resigter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resigter);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        anhxa();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(ResigterActivity.this , LoginActivity.class);
                startActivity(login);
            }
        });
        btn_resigter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKy();
            }
        });
    }

    private void anhxa(){
        btn_back = findViewById(R.id.btn_back);
        edt_email = findViewById(R.id.edt_email_resigter);
        edt_password = findViewById(R.id.edt_password_resigter);
        edt_repassword = findViewById(R.id.edt_repassword_resigter);
        btn_resigter = findViewById(R.id.btn_resigter);
    }
    private void dangKy(){
        mAuth.createUserWithEmailAndPassword(edt_email.getText().toString(), edt_password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(ResigterActivity.this , LoginActivity.class);
                            startActivity(login);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context, "Đăng ký thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}