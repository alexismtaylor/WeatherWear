package edu.fsu.cs.mobile.weatherwear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OpenScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openscreen);
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
