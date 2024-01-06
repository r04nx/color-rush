package com.example.colorrush;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity{
    ConstraintLayout bg;
    Random random;
    TextView t1,t2,score,avgspeed;
    int flag =0;
    int flag2 =0;
    int sum;
    long startTime;
    long endTime;


    public void play() {
        flag = 0;
        flag2=0;
        t1.setText("Wait for green...");
        t2.setText("");
        bg.setBackgroundResource(R.drawable.restart);
        random = new Random();
        int randomNumber = random.nextInt(4001) + 1000;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
if(flag2==0) {
    bg.setBackgroundResource(R.drawable.my_bg);
    flag = 1;
    t1.setText("Click!");
    t2.setText("");
    startTime = System.currentTimeMillis();
}
            }
        };
        handler.postDelayed(runnable, randomNumber);
 
    }

    @SuppressLint({"MissingInflatedId", "ResourceType"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        bg = (ConstraintLayout) findViewById(R.id.bg);
        t1= (TextView) findViewById(R.id.prevtext);
        t2 = (TextView) findViewById(R.id.t2);
        score = (TextView)  findViewById(R.id.scoreboard);
        avgspeed = (TextView)  findViewById(R.id.avgspeed);
        play();

 

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                if(flag==1){
                    endTime= System.currentTimeMillis();
                    long a = endTime-startTime;
                    t1.setText(a+" milliseconds");
                    if ( score.getText().toString().split("\n").length - 1 == 5) {
                        score.setText("Scoreboard");
                    }else {
                        score.append("\n    " + a);
                    }


                    bg.setBackgroundResource(R.drawable.restart);
                    t2.setText("CLick to restart");
                    flag2=1;
                    flag=2;
                }

                else if(flag==2){
                        play();
                        flag=0;
                        flag2=0;
                }
                else{
                    t1.setText("Too Early");
                    t2.setText("CLick to restart");
                    bg.setBackgroundResource(R.drawable.fail);
                    flag2=1;
                    flag=2;
                }

            }
        });


    }

}
