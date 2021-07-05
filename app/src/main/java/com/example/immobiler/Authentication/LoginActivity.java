package com.example.immobiler.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.immobiler.Activities.MainActivity;
import com.example.immobiler.DBHelper;
import com.example.immobiler.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;
    private static DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

//        db = new DBHelper(LoginActivity.this);
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isExist = db.checkUserExist(edtUsername.getText().toString(), edtPassword.getText().toString());
//
//                if(isExist){
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("username", edtUsername.getText().toString());
//                    startActivity(intent);
//                } else {
//                    edtPassword.setText(null);
//                    Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }
}
