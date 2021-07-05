package com.example.immobiler.Activities;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.immobiler.Authentication.LoginActivity;
import com.example.immobiler.R;


public class ChooseActivity extends AppCompatActivity {

    Button chatButton,fileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        chatButton = (Button)findViewById(R.id.chat);
        fileButton = (Button)findViewById(R.id.file);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        fileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this,ListBienActivity.class);
                startActivity(intent);
            }
        });
    }
}
