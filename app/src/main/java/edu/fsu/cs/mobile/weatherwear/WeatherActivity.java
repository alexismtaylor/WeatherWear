package edu.fsu.cs.mobile.weatherwear;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;


public class WeatherActivity extends AppCompatActivity implements LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION2 = 100;
    public static final int REQUEST_TIME=1000*60*30;        //requests the time every 30 minutes

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
    TextView tvHumidity, tvTemp, tvCondition, tvCity;
    ImageView ivWeatherIcon, ivPic1, ivPic2;
    RelativeLayout layout;

    LocationManager locationManager;
    Random rand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (checkLocationPermission()) { //check if permissions have already ben granted
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                //Request location updates:
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, REQUEST_TIME, 0, this);
            }

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                //Request location updates:
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, REQUEST_TIME, 0, this);
            }
        }

        tvHumidity = (TextView) findViewById(R.id.tvHumidity);
        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvCondition = (TextView) findViewById(R.id.tvCondition);
        tvCity = (TextView) findViewById(R.id.tvCity);
        ivWeatherIcon = (ImageView) findViewById(R.id.ivWeatherIcon);
        ivPic1 = (ImageView) findViewById(R.id.ivPic1);
        ivPic2 = (ImageView) findViewById(R.id.ivPic2);
        layout = (RelativeLayout) findViewById(R.id.layout);

        //Getting a picture: Assuming the person has already taken pictures

        File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File files[] = path.listFiles();
        rand = new Random();


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

                sweaters.add(files[i]);
            }
            if (files[i].getName().contains("longsleeves")) {

                longsleeve.add(files[i]);
            }
            if (files[i].getName().contains("dress")) {

                dress.add(files[i]);
            }
            if (files[i].getName().contains("shorts")) {

                shorts.add(files[i]);
            }
            if (files[i].getName().contains("pants")) {

                pants.add(files[i]);
            }
            if (files[i].getName().contains("skirt")) {

                skirts.add(files[i]);
            }
        }




    }
    //for when location is changed, this sets all of the UI changes, updates textviews and pictures from the API
    @Override
    public void onLocationChanged(Location location) {
        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity) {



                tvCity.setText(weather_city);
                tvCondition.setText(weather_description);
                tvTemp.setText(weather_temperature);
                tvHumidity.setText("Humidity: "+ weather_humidity);



                tvCity.setTextColor(getResources().getColor(R.color.white));
                tvCondition.setTextColor(getResources().getColor(R.color.white));
                tvHumidity.setTextColor(getResources().getColor(R.color.white));
                tvTemp.setTextColor(getResources().getColor(R.color.white));
                tvTemp.setGravity(Gravity.CENTER);

               tvCity.setTextAppearance(getApplicationContext(), R.style.shadowText);
               tvCondition.setTextAppearance(getApplicationContext(), R.style.shadowText);
               tvHumidity.setTextAppearance(getApplicationContext(), R.style.shadowText);
               tvTemp.setTextAppearance(getApplicationContext(), R.style.shadowText);



                if(weather_description.equals("Thunderstorm"))
                {
                 //   ivWeatherIcon.setImageResource(R.drawable.thunderstorm);
                    layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.thndr));

                }
                else if(weather_description.equals("Drizzle"))
                {
                  //  ivWeatherIcon.setImageResource(R.drawable.thunderstorm);
                    layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.rain));

                }
                else if(weather_description.equals("Rain"))
                {
                //    ivWeatherIcon.setImageResource(R.drawable.thunderstorm);
                    layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.rain));
                }
                else if(weather_description.equals("Clear"))
                {
                //    ivWeatherIcon.setImageResource(R.drawable.thunderstorm);
                    layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.clear));

                }
                else if(weather_description.equals("Clouds"))
                {
              //      ivWeatherIcon.setImageResource(R.drawable.thunderstorm);
                    layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.clouds));

                }
                else
                {//default weather icon
                    ivWeatherIcon.setImageResource(R.drawable.logo2);
                }

                if(Double.parseDouble(weather_temperature.replace("°","")) >= 70 && Double.parseDouble(weather_temperature.replace("°","")) < 85) //if temp greater than 70 and less than 85
                {
                    if(weather_description.equals("Clear") || weather_description.equals("Clouds"))
                    { //sunny and 70+
                        if(tshirts.size() != 0)
                        {
                            setRandomClothing(tshirts,ivPic1,rand);
                        }
                        else if(tanktop.size() != 0)//no tshirts, try tank tops
                        {
                            setRandomClothing(tanktop,ivPic1,rand);
                        }
                        else//no applicable shirts added
                        {
                            ivPic1.setImageResource(R.drawable.tshirt);
                        }

                        if(shorts.size() != 0)
                        {
                            setRandomClothing(shorts,ivPic2,rand);
                        }
                        else//no shorts add default
                        {
                            ivPic2.setImageResource(R.drawable.shorts);
                        }
                    }
                    else
                    {//70+ and rainy/stormy
                        if(tshirts.size() != 0)
                        {//set tshirt
                            setRandomClothing(tshirts,ivPic1,rand);
                        }
                        else if(longsleeve.size() != 0)
                        {
                            setRandomClothing(longsleeve,ivPic1,rand);
                        }
                        else //no applicable shirts added
                        {//use default
                            ivPic1.setImageResource(R.drawable.tshirt);
                        }

                        if(shorts.size() != 0)
                        {
                            setRandomClothing(shorts,ivPic2,rand);
                        }
                        else//no shorts add default
                        {
                            ivPic2.setImageResource(R.drawable.shorts);
                        }
                    }
                }
                else if(Double.parseDouble(weather_temperature.replace("°","")) >= 85)
                {
                    if(tanktop.size() != 0)//tank tops
                    {
                        if(shorts.size() != 0)
                        {
                            setRandomClothing(shorts,ivPic2,rand);
                        }
                        else
                        {//no shorts, default
                            ivPic2.setImageResource(R.drawable.shorts);
                        }
                    }
                    else
                    {//default tank top
                        ivPic1.setImageResource(R.drawable.tanktop);
                    }
                }
                else //cold, wear pants and long sleeves
                {
                    if(pants.size() != 0)
                    {
                        setRandomClothing(pants,ivPic2,rand);

                        if(longsleeve.size() != 0 && Double.parseDouble(weather_temperature.replace("°","")) > 60)
                        {
                            setRandomClothing(longsleeve,ivPic1,rand);
                        }
                        else if(sweaters.size() != 0 && Double.parseDouble(weather_temperature.replace("°","")) <= 60)
                        {//either no long sleeves or weather was less than 60
                            setRandomClothing(sweaters,ivPic1,rand);
                        }
                        else //no sweaters or long sleeves
                        {
                            ivPic1.setImageResource(R.drawable.longsleeve);
                        }

                    }
                    else
                    {//default pants
                        ivPic2.setImageResource(R.drawable.pants);

                        if(longsleeve.size() != 0 && Double.parseDouble(weather_temperature.replace("°","")) > 60)
                        {
                            setRandomClothing(longsleeve,ivPic1,rand);
                        }
                        else if(sweaters.size() != 0 && Double.parseDouble(weather_temperature.replace("°","")) <= 60)
                        {//either no long sleeves or weather was less than 60
                            setRandomClothing(sweaters,ivPic1,rand);
                        }
                        else //no sweaters or long sleeves
                        {
                            ivPic1.setImageResource(R.drawable.longsleeve);
                        }
                    }
                }
            }
        });
        asyncTask.execute(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())); //  asyncTask.execute("Latitude", "Longitude")
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
    //on resume, also checks permissions
    @Override
    protected void onResume() {
        super.onResume();

        if (checkLocationPermission()) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission. ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, REQUEST_TIME, 0, this);
            }
        }

    }

    //function called in other functions to check if location permissions have been accepted
    //returns true if permissions exist, false otherwise
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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, REQUEST_TIME, 0, this);

        }

        return false;
    }
    //This actually sets the clothing items to the respective imageview
    void setRandomClothing(ArrayList<File> files, ImageView iv, Random rand) {
        File f = files.get(rand.nextInt(files.size()));
        Uri uri = Uri.fromFile(f);
        iv.setImageURI(uri);
    }
    //for when the user is requesting location permissions, this receives this request
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, REQUEST_TIME, 0, this);
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
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, REQUEST_TIME, 0, this);
                    }
                } else {
                }

                return;
            }
        }
    }

}


