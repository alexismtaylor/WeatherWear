package edu.fsu.cs.mobile.weatherwear;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Button;

import java.util.ArrayList;

public class AddClothingActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinner;
    Button bAdd, bUpload, bTake;
    String item; //clothing category
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int SELECT_IMAGE = 2;
    ImageView ivPictureTaken;
    Bitmap imageBitmap;

    ArrayList<Bitmap> shorts = new ArrayList<Bitmap>();
    ArrayList<Bitmap> pants = new ArrayList<Bitmap>();
    ArrayList<Bitmap> tshirts = new ArrayList<Bitmap>();
    ArrayList<Bitmap> longsleeve = new ArrayList<Bitmap>();
    ArrayList<Bitmap> dress = new ArrayList<Bitmap>();
    ArrayList<Bitmap> tanktop = new ArrayList<Bitmap>();
    ArrayList<Bitmap> skirts = new ArrayList<Bitmap>();
    ArrayList<Bitmap> sweaters = new ArrayList<Bitmap>();
    int location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothing);
        spinner = (Spinner) findViewById(R.id.spinner);
        bAdd = (Button) findViewById(R.id.bAdd);            //add clothing item button
        bUpload = (Button) findViewById(R.id.bUpload);      //upload picture button
        bTake = (Button) findViewById(R.id.bTake);          //take picture button
        ivPictureTaken = (ImageView) findViewById(R.id.ivPictureTaken);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        if(position != 0) {
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
            location = position;
        }
        else {
            //default position
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void clickAdd(View view) {
        //add pic to arraylist
        if(location != 0) {
            spinner.setSelection(0); //set back to default
            if(location == 1) { //tshirts
                tshirts.add(imageBitmap);
            }
            else if(location == 2) { //shorts
                shorts.add(imageBitmap);
            }
            else if(location == 3){ //pants
                pants.add(imageBitmap);
            }
            else if(location == 4){ //long sleeve
                longsleeve.add(imageBitmap);
            }
            else if(location == 5){ //dress
                dress.add(imageBitmap);
            }
            else if(location == 6){ //tanktop
                tanktop.add(imageBitmap);
            }
            else if(location == 7){ //skirts
                skirts.add(imageBitmap);
            }
            else if(location == 8){ //sweaters
                sweaters.add(imageBitmap);
            }
        }
        else{
            Toast.makeText(this, "Please select a valid category.", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickTake(View view){
        dispatchTakePictureIntent(); //call taking picture activity
    }

    public void clickUpload(View view) {
        openGalleryIntent();
    }

    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void openGalleryIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivPictureTaken.setImageBitmap(imageBitmap); //set imageview to image
        }
        else if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivPictureTaken.setImageBitmap(imageBitmap);
        }
    }


}
