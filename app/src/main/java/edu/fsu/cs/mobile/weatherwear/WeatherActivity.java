package edu.fsu.cs.mobile.weatherwear;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.johnhiott.darkskyandroidlib.ForecastApi;
import com.johnhiott.darkskyandroidlib.RequestBuilder;
import com.johnhiott.darkskyandroidlib.models.Request;
import com.johnhiott.darkskyandroidlib.models.WeatherResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WeatherActivity extends AppCompatActivity implements LocationListener {

    private LocationManager mLocationManager;
    private Double userlat, userlon;

    @Override
    public void onLocationChanged(Location location) {
        userlat = location.getLatitude();
        userlon = location.getLongitude();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // establish link to Dark Sky Weather API
        ForecastApi.create("4322ff0f22d1eb90e2f4fa5ce615296c");

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this);


        RequestBuilder weather = new RequestBuilder();

        Request request = new Request();
        request.setLat(String.valueOf(userlat));
        //Log.i("error", String.valueOf(userlon));

        request.setLng(String.valueOf(userlon));
        request.setUnits(Request.Units.US);
        request.setLanguage(Request.Language.ENGLISH);
        request.addExcludeBlock(Request.Block.CURRENTLY);


        weather.getWeather(request, new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                Toast.makeText(getApplicationContext(), weatherResponse.getDaily().toString(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d("error", "Error while calling: " + retrofitError.getUrl());
            }
        });


    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

}
