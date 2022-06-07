package com.hadia.leanplumandroidreal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.leanplum.Leanplum;
import com.leanplum.Var;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    //set variables
    public static Var<String> lpGameTitle = Var.define("gameTitle","Fun Game");
    public static Var<String> lpGameBgImg = Var.defineFile("gameBgImg","gamebgimg.jpeg");
    @Variable public static Map<String, Object> lpPowerup = new HashMap<String, Object>() {
        {
            put("lpName", "Turbo Boost");
            put("lpPrice", 150);
            put("lpSpeed", 1.5);
            put("lpTimeout", 15);
        }
    };

    //public static Var<String> drawableColor = Var.define("drawableColor", "#ffffff"); //#246697 or #ffffff

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button btnWin= findViewById(R.id.btnWin);
        Button btnLose= findViewById(R.id.btnLose);
        Button btnReset= findViewById(R.id.btnReset);

        TextView gameTitle = findViewById(R.id.titleTextView);
        //final String[] gameTitleString = {gameTitle.getText().toString()};
        //ImageView bgImg = findViewById(R.id.bgImgView);



        //onVariablesChanged
        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                // Insert code here.
                //gameTitleString[0] = lpGameTitle;
                //gameTitle.setText(); = lpGameTitle.value();
                gameTitle.setText(lpGameTitle.value());

                if (lpGameBgImg.fileValue()!= null) {
                    java.io.File imgFile = new java.io.File(lpGameBgImg.fileValue());
                    if(imgFile.exists()) {
                        ImageView bgImg = findViewById(R.id.bgImgView);
                        bgImg.setImageURI(Uri.fromFile(imgFile));
                    }
                }
            }
        });

        //btnWin function
        btnWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("gameStatus", "Win");
                Leanplum.track("btnClick", params);
                System.out.println("WIN CLICKED");
            }
        });

        //btnLose function
        btnLose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("gameStatus", "Lose");
                Leanplum.track("btnClick", params);
                System.out.println("LOSE CLICKED");
            }
        });

        //btnReset function
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Leanplum.track("Reset");
                System.out.println("RESET CLICKED");
                //maybe do a push notif if they press reset and then put the game on bg or dont play with it for a while
            }
        });



        //Leanplum.setApplicationContext(this);

    }
}