package edu.fsu.cs.mobile.weatherwear;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
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

        //Going through the list of files and adding to the list of counters for each thing
        //so that the random function goes through only items that exist
        for (int i=0;i<files.length;i++)
        {
            if(files[i].getName().contains("tshirt"));
                clothesTopCount[0]+=1;
            if(files[i].getName().contains("tanktop"));
                clothesTopCount[1]+=1;
            if(files[i].getName().contains("sweater"));
                clothesTopCount[2]+=1;
            if(files[i].getName().contains("longsleeves"));
                clothesTopCount[3]+=1;
            if(files[i].getName().contains("dress"));
                clothesTopCount[4]+=1;
            if(files[i].getName().contains("shorts"));
                clothesBottomCount[0]+=1;
            if(files[i].getName().contains("pants"));
                clothesBottomCount[1]+=1;
            if(files[i].getName().contains("skirt"));
                clothesBottomCount[2]+=1;
        }


        //Just gets a random picture and sets it to the first one
        //File file = files[rand.nextInt(files.length)];
        File file=getRandomTop(clothesTopCount,files,rand,clothesTop);
        Uri uri = Uri.fromFile(file);
        ivPic1.setImageURI(uri);

        //file = files[rand.nextInt(files.length)];

        file=getRandomTop(clothesBottomCount,files,rand,clothesBottom);
        uri = Uri.fromFile(file);
        ivPic2.setImageURI(uri);

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


    File getRandomTop(int [] clothesTopCount,File files [], Random rand, String [] clothesTop)
    {
        ArrayList<File> temp=new ArrayList<>();
        ArrayList<Integer> temp1= new ArrayList<>();
        //Goes through the clothesCount to add all the postions that have clothes in them.
        for(int i=0; i<clothesTopCount.length;i++)
        {
            if(clothesTopCount[i]>0)
                temp1.add(i);
        }
        //gets a random position, which means it gets an article of clothing
        int pos= temp1.get(rand.nextInt(temp1.size()));

        //Adds all the files that have contain the name that it's looking for
        for (int i=0;i<files.length;i++)
        {
            if(files[i].getName().contains(clothesTop[pos]))
                temp.add(files[i]);
        }
        //return a random File which is an image.
        return temp.get(rand.nextInt(temp.size()));
    }



}
