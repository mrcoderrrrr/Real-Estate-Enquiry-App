package com.example.salesenquiry;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salesenquiry.Database.LoginDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity {
    ImageView logo;
    TextInputEditText email, password;
    View loginBut;
    TextView donthave, signupac, forgot;
    LoginDB loginDB;
    Dialog forgot_dialog;
    EditText emailenter;
    Button submit;
    String Username,Password;
    FirebaseAuth firebaseAuth;

    AuthCredential credential;
    String User_Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginBut = findViewById(R.id.login);
        signupac = findViewById(R.id.signupac);
        logo = findViewById(R.id.logoIcon);
        email = findViewById(R.id.emailtxt);
        password = findViewById(R.id.passwordtxt);
        donthave = findViewById(R.id.donthave);
        forgot = findViewById(R.id.forgot);
        loginDB = new LoginDB(this);
        forgot_dialog = new Dialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        //SignUpButton
        signupbut();
        //log in Button
        loginButton();
        //Dialog Box
        ForgotPassword();

    }
    //firebase forgot password
    private void  firebaseforgotdialog(){
            forgot_dialog.setContentView(R.layout.forgot_dialog);
            forgot_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            emailenter = forgot_dialog.findViewById(R.id.emailenter);
            submit = forgot_dialog.findViewById(R.id.emailsubmit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (emailenter.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter The Email Id", Toast.LENGTH_LONG).show();
                    } else {
                        firebaseAuth.sendPasswordResetEmail(emailenter.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Reset Password Link Sent On Your Mail Id",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
            forgot_dialog.show();
        }
//sqlite forget passwrod
//    private void sqliteforgotdialog() {
//        forgot_dialog.setContentView(R.layout.forgot_dialog);
//        forgot_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        emailenter = forgot_dialog.findViewById(R.id.emailenter);
//        submit = forgot_dialog.findViewById(R.id.emailsubmit);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (emailenter.getText().toString().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Enter The Email Id", Toast.LENGTH_LONG).show();
//                } else {
//                    Boolean checkName = loginDB.checkUsername(emailenter.getText().toString());
//                    if (checkName == true) {
//                        Intent intent = new Intent(getApplicationContext(), forgot_password.class);
//                        intent.putExtra("User",emailenter.getText().toString());
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.nav_default_pop_enter_anim,R.anim.nav_default_pop_exit_anim);
//                        finish();
//
//                        Toast.makeText(getApplicationContext(), "Correct Email id", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "InCorrect Email Id", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        });
//        forgot_dialog.show();
//    }


    //Forgot Password
    private void ForgotPassword() {
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sqliteforgotdialog();
                firebaseforgotdialog();
            }
        });
    }

    //Log in button
    private void loginButton() {
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = email.getText().toString();
                Password = password.getText().toString();
                // startActivity(new Intent(getApplicationContext(), welcome.class));
                //Firebase Login
                if (Username.isEmpty() || Password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill The Details", Toast.LENGTH_LONG).show();
                } else {
                    firebaselogin();
                }
            }
        });
    }
    //Firebase Login
    private void firebaselogin() {
        firebaseAuth.signInWithEmailAndPassword(Username,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Log In Sucessful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),welcome.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Log In Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


//    //Sqlite Login
//    private void sqliteLogin() {
//                if (Username.isEmpty() || Password.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Fill The Details", Toast.LENGTH_LONG).show();
//                } else {
//                    Boolean loginData = loginDB.checkusernamepassword(Username, Password);
//                    if (loginData == true) {
//                        Toast.makeText(getApplicationContext(), "Log In Sucessfully", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(getApplicationContext(), welcome.class));
//                        finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Log In Failed", Toast.LENGTH_LONG).show();
//                    }
//                }
//    }


    //Go to Login Activity
    private void signupbut() {
        signupac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

