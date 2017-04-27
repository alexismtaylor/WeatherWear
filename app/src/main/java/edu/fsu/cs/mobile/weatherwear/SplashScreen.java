package edu.fsu.cs.mobile.weatherwear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.Random;


public class SplashScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random r = new Random();
        int rand = Math.abs(r.nextInt()) % 4;


        //loads one of 4 splash screens at random
        switch(rand)
        {
            case 0:
                setContentView(R.layout.activity_splash_screen);
                break;
            case 1:
                setContentView(R.layout.farm);
                break;
            case 2:
                setContentView(R.layout.snow);
                break;
            case 3:
                setContentView(R.layout.desert);
                break;
            default:;

        }

        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), OpenScreenActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
    }
}