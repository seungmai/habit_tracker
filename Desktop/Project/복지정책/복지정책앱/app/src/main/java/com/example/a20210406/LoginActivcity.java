package com.example.a20210406;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivcity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.button_gotosignup).setOnClickListener(onClickListener);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    //클릭시 아이디에 따른  화면이동
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.loginButton:
                    Log.e("클릭", "클릭");
                    Login();
                    break;
                case R.id.button_gotosignup:
                    MovetoSignup();
                    break;

            }
        }
    };

    //로그인
    private void Login() {

        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditTExt)).getText().toString();

        //이메일이나 패스워드가 입력되었을시 작동 파이어베이스와 연동하여 그에 맞으면 그 아이디로 로그인
        if(email.length() >0 && password.length() >0 ){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                StartToast("로그인 성공");
                                MovetoMain();
                            } else {


                                if(task.getException() != null)
                                    // If sign in fails, display a message to the user.
                                    StartToast(task.getException().toString());
                            }
                        }
                    });




        }
        else {
            StartToast("이메일 또는 비밀번호를 입력해주세요");
        }
        }

    private void StartToast(String msg){
       Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void MovetoMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void MovetoSignup(){
        Intent intent = new Intent(this, SignUpActivcity.class);
        startActivity(intent);
    }


}

