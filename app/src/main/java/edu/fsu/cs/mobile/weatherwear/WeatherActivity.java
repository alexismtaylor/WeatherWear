package edu.fsu.cs.mobile.weatherwear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    TextView tvRain, tvTemp, tvCondition;
    ImageView ivWeatherIcon, ivPic1, ivPic2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvRain = (TextView) findViewById(R.id.tvRain);
        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        ivWeatherIcon = (ImageView) findViewById(R.id.ivWeatherIcon);
        ivPic1 = (ImageView) findViewById(R.id.ivPic1);
        ivPic2 = (ImageView) findViewById(R.id.ivPic2);

        /*
        Get random clothes and update pic1 and pic2

        Get the climate to update the icon (replace climate with variable from weather API that represents climate)

        if(climate == "sunny")
            ivWeatherIcon.setImageResource(R.drawable.sunny);
        else if(climate == "mostly cloudy" || climate == "cloudy")
            ivWeatherIcon.setImageResource(R.drawable.mostlycloudy);
        else if(climate == "partly cloudy")
            ivWeatherIcon.setImageResource(R.drawable.partlycloudy);
        else if(climate == "rainy")
            ivWeatherIcon.setImageResource(R.drawable.rainy);
        else if(climate == "thunderstorms")
            ivWeatherIcon.setImageResource(R.drawable.thunderstorm);
        else
            ivWeatherIcon.setImageResource(R.drawable.unknown);

        tvRain.setText(); //add rain percentage from API
        tvTemp.setText(); //add temperature from API
        tvCondition.setText(); //add climate condition from API


        */
    }
}
