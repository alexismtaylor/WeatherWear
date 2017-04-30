package edu.fsu.cs.mobile.weatherwear;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Random;

public class WeatherActivity extends AppCompatActivity {
    int [] clothesTopCount={0,0,0,0,0};
    int [] clothesBottomCount={0,0,0};
    String [] clothesTop={"tshirt","tanktop","sweater","longsleeves","dress"};
    String [] clothesBottom={"shorts","pants","skirt"};
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


        //Getting a picture: Assuming the person has already taken pictures

        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File files []=path.listFiles();
        Random rand = new Random();

        //Just gets a random picture and sets it to the first one
        File file = files[rand.nextInt(files.length)];
        Uri uri = Uri.fromFile(file);
        ivPic1.setImageURI(uri);

        file = files[rand.nextInt(files.length)];
        uri = Uri.fromFile(file);
        ivPic2.setImageURI(uri);


        for (int i=0;i<files.length;i++)
        {
            if(files[i].getName().contains("tshirt"));
                clothesTopCount[0]+=1;
            if(files[i].getName().contains("tanktop"));
                clothesTopCount[1]+=1;
        }


        //ivPic1.

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
