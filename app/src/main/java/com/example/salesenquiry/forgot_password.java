package com.example.salesenquiry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesenquiry.Database.LoginDB;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class forgot_password extends AppCompatActivity {
TextView changetxt;
TextInputEditText password,changepassword;
Button confirm;
LoginDB loginDB;
FirebaseAuth firebaseAuth;
FirebaseUser user;
String User,Password,ChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        loginDB=new LoginDB(this);
        changetxt=findViewById(R.id.changetxt);
        password=findViewById(R.id.passwordtxt);
        changepassword=findViewById(R.id.confirmpass);
        confirm=findViewById(R.id.confirm);
       firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
    confirmbut();
    }

    private void confirmbut() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User=getIntent().getStringExtra("User");
                Password=password.getText().toString();
                ChangePassword=changepassword.getText().toString();
                //sqlite
                //sqliteupdatepassword();
                //forgot
                Firebaseupdatepassword();
            }
        });

    }

    private void Firebaseupdatepassword() {
        if (ChangePassword.equals(Password)) {
          user.updatePassword(ChangePassword).addOnSuccessListener(new OnSuccessListener<Void>() {
              @Override
              public void onSuccess(Void unused) {
                  Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_LONG).show();
              }
          }).addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull @NotNull Exception e) {
                  e.printStackTrace();
              }
          });
        }
    }
    private void sqliteupdatepassword() {
            if (ChangePassword.equals(Password)) {
                Boolean changePassword = loginDB.changepassword(User, Password);
                if (changePassword == true) {
                    Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Password Changed Failed", Toast.LENGTH_LONG).show();
                }
            }
        }
}