package com.example.project_team_2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    TextView btn;
    private EditText inputUsername, inputEmailregister, inputPassword, inputConfirmpassword;
    Button btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_registration);

        btn=findViewById(R.id.alreadyHaveAccount);
        inputUsername=findViewById(R.id.InputEmaillogin);
        inputEmailregister=findViewById(R.id.InputPasswordlogin);
        inputPassword=findViewById(R.id.inputPassword);
        inputConfirmpassword=findViewById(R.id.inputConfirmpassword);
        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(Register.this);

        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }

    private void checkCredentials() {
        String username=inputUsername.getText().toString();
        String email=inputEmailregister.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmpassword=inputConfirmpassword.getText().toString();

        if (username.isEmpty() || username.length()<7)
        {
            showError(inputUsername, "Your username is not Valid!");
        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmailregister, "Email is not Valid!");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            showError(inputPassword, "Password must be 7 characters");
        }
        else if (confirmpassword.isEmpty() || !confirmpassword.equals(password))
        {
            showError(inputConfirmpassword, "Password do not Match!");
        }
        else
        {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait while checking Credentials!");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password). addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(Register.this, "Register Successful!", Toast.LENGTH_SHORT).show();

                        mLoadingBar.dismiss();
                        Intent intent=new Intent(Register.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(Register.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}