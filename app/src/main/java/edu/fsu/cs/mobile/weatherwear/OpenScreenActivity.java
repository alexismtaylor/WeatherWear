package edu.fsu.cs.mobile.weatherwear;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class OpenScreenActivity extends AppCompatActivity {

    Button weatherB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openscreen);

        weatherB = (Button) findViewById(R.id.bWeather);

        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File files[] = path.listFiles();

        if(files.length==0)
        {
            weatherB.setEnabled(false);
        }
        else
        {
            weatherB.setEnabled(true);
        }

    }

    protected void onResume() {

        super.onResume();

        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File files[] = path.listFiles();

        if(files.length==0)
        {
            weatherB.setEnabled(false);
        }
        else
        {
            weatherB.setEnabled(true);
        }

    }

    //go to alarm activity
    public void clickAlarm(View view)
    {
        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
        startActivity(intent);
    }
    //go to weather activity
    public void clickWeather(View view)
    {
        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
        startActivity(intent);
    }
    //go to adding pictures activity
    public void clickAdd(View view)
    {
        Intent intent = new Intent(getApplicationContext(), AddClothingActivity.class);
        startActivity(intent);
    }

}
