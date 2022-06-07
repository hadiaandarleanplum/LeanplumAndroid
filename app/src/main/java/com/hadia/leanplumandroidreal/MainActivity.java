package com.hadia.leanplumandroidreal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leanplum.Leanplum;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    Map<String, Object> attributes = new HashMap<String, Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnterName = findViewById(R.id.btnEnterName);
        //read name input
        btnEnterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                EditText textName = findViewById(R.id.textName);
                attributes.put("playerName", textName.getText().toString());
                Leanplum.setUserAttributes(attributes);
                System.out.println(textName.getText().toString());
            }
        });

        //read username input
        Button btnLoginUser = findViewById(R.id.btnLoginUser);
        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                EditText textUsername = findViewById(R.id.textUsername);
                Leanplum.setUserId(textUsername.getText().toString());
                System.out.println(textUsername.getText().toString());
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        //go to GameActivity
        Button btnPlayAsGuest = findViewById(R.id.btnPlayAsGuest);
        btnPlayAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                System.out.println("Login as guest");
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }
}