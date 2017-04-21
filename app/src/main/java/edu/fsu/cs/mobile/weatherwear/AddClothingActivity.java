package edu.fsu.cs.mobile.weatherwear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Button;

public class AddClothingActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinner;
    Button bAdd, bUpload, bTake;
    String item; //clothing category
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothing);
        spinner = (Spinner) findViewById(R.id.spinner);
        bAdd = (Button) findViewById(R.id.bAdd);            //add clothing item button
        bUpload = (Button) findViewById(R.id.bUpload);      //upload picture button
        bTake = (Button) findViewById(R.id.bTake);          //take picture button
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        if(position != 0)
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
        else
        {
            //default position
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void clickAdd(View view) {
        //add pic to array
        spinner.setSelection(0); //set back to default
    }

    public void clickTake(View view){
        //launch camera app
    }

    public void clickUpload(View view)
    {
        //launch gallery app
    }
}
