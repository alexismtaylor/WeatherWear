package edu.fsu.cs.mobile.weatherwear;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import az.openweatherapi.OWService;
import az.openweatherapi.listener.OWRequestListener;
import az.openweatherapi.model.OWResponse;
import az.openweatherapi.model.gson.common.Coord;
import az.openweatherapi.model.gson.current_day.CurrentWeather;

public class WeatherActivity extends AppCompatActivity implements LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION2 = 100;

    int[] clothesTopCount = {0, 0, 0, 0, 0};
    int[] clothesBottomCount = {0, 0, 0};
    ArrayList<File> shorts = new ArrayList<>();
    ArrayList<File> pants = new ArrayList<>();
    ArrayList<File> tshirts = new ArrayList<>();
    ArrayList<File> longsleeve = new ArrayList<>();
    ArrayList<File> dress = new ArrayList<>();
    ArrayList<File> tanktop = new ArrayList<>();
    ArrayList<File> skirts = new ArrayList<>();
    ArrayList<File> sweaters = new ArrayList<>();
    String[] clothesTop = {"tshirt", "tanktop", "sweater", "longsleeves", "dress"};
    String[] clothesBottom = {"shorts", "pants", "skirt"};
    TextView tvRain, tvTemp, tvCondition;
    ImageView ivWeatherIcon, ivPic1, ivPic2;

    //private OWService mOWService = new OWService("f2fa88c980099cd1a16b755da543b93d");
    LocationManager locationManager;
    Coord coordinate = new Coord();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (checkLocationPermission()) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                //Request location updates:
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                //Request location updates:
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }

        tvRain = (TextView) findViewById(R.id.tvRain);
        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        ivWeatherIcon = (ImageView) findViewById(R.id.ivWeatherIcon);
        ivPic1 = (ImageView) findViewById(R.id.ivPic1);
        ivPic2 = (ImageView) findViewById(R.id.ivPic2);


        //Getting a picture: Assuming the person has already taken pictures

        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File files[] = path.listFiles();
        Random rand = new Random();


        //Going through the list of files and adding to each individual arrayList
        //so that we have every file categorized in it's own ArrayList
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains("tshirt")) {
                tshirts.add(files[i]);
            }
            if (files[i].getName().contains("tanktop")) {
                tanktop.add(files[i]);
            }
            if (files[i].getName().contains("sweater")) {
                clothesTopCount[2] += 1;
                sweaters.add(files[i]);
            }
            if (files[i].getName().contains("longsleeves")) {
                clothesTopCount[3] += 1;
                longsleeve.add(files[i]);
            }
            if (files[i].getName().contains("dress")) {
                clothesTopCount[4] += 1;
                dress.add(files[i]);
            }
            if (files[i].getName().contains("shorts")) {
                clothesBottomCount[0] += 1;
                shorts.add(files[i]);
            }
            if (files[i].getName().contains("pants")) {
                clothesBottomCount[1] += 1;
                pants.add(files[i]);
            }
            if (files[i].getName().contains("skirt")) {
                clothesBottomCount[2] += 1;
                skirts.add(files[i]);
            }
        }


        //This sets the first imageview to a random tshirt
        //setRandomClothing(tshirts,ivPic1,rand);

        //This sets the second imageview to a random pair of shorts
        //setRandomClothing(shorts,ivPic2,rand);


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
        */


    }

    @Override
    public void onLocationChanged(Location location) {
        coordinate.setLat(location.getLatitude());
        coordinate.setLon(location.getLongitude());

        //Toast.makeText(getApplicationContext(), "it worked?", Toast.LENGTH_LONG).show();

        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_rain) {

                tvCondition.setText(weather_description);
                tvTemp.setText(weather_temperature);
                tvRain.setText("Rain: "+ weather_rain);
                if(weather_description.equals("Thunderstorm"))
                {
                    ivWeatherIcon.setImageResource(R.drawable.thunderstorm);
                }
                else if(weather_description.equals("Drizzle"))
                {
                    ivWeatherIcon.setImageResource(R.drawable.rainy);
                }
                else if(weather_description.equals("Rain"))
                {
                    ivWeatherIcon.setImageResource(R.drawable.rainy);

                }
                else if(weather_description.equals("Clear"))
                {
                    ivWeatherIcon.setImageResource(R.drawable.sunny);
                }
                else if(weather_description.equals("Clouds"))
                {
                    ivWeatherIcon.setImageResource(R.drawable.mostlycloudy);
                }
                else
                {//default weather icon
                    ivWeatherIcon.setImageResource(R.drawable.logo);
                }
            }
        });
        asyncTask.execute(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())); //  asyncTask.execute("Latitude", "Longitude")
/*
        mOWService.getCurrentDayForecast(coordinate, new OWRequestListener<CurrentWeather>() {
            @Override
            public void onResponse(OWResponse<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                tvTemp.setText(currentWeather.getMain().getTemp().toString());
                tvRain.setText(currentWeather.getRain().toString());
                tvCondition.setText(currentWeather.getCod()); // may need to match against conditions in strings.xml
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", "Current Day Forecast request failed: " + t.getMessage());
            }
        });*/
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (checkLocationPermission()) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }

    }


    public boolean checkLocationPermission()
    {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission. ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission. ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION2);
            }
            return true;
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        }

        return false;
    }

    void setRandomClothing(ArrayList<File> files, ImageView iv, Random rand) {
        File f = files.get(rand.nextInt(files.size()));
        Uri uri = Uri.fromFile(f);
        iv.setImageURI(uri);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    } //end if
                } //end if
                else {

                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_LOCATION2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        //Request location updates:
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    }
                } else {
                }

                return;
            }
        }
    }

}


