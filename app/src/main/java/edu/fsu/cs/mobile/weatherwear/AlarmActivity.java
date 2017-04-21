package edu.fsu.cs.mobile.weatherwear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class AlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    boolean editTime = false;
    Button bEdit;
    int hour, minute;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        bEdit = (Button) findViewById(R.id.bEdit);
        switch1 = (Switch) findViewById(R.id.switch1);

        if (switch1 != null) {
            switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(AlarmActivity.this, "Alarm is now on", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AlarmActivity.this, "Alarm is now off", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        //starts off at 0 and disabled
        timePicker.setCurrentMinute(0);
        timePicker.setCurrentHour(0);
        timePicker.setEnabled(false);
    }

    public void clickEdit(View view) {
        if(editTime == true){
            bEdit.setText("Edit");
            editTime = false;
            timePicker.setEnabled(false);
        }
        else {
            editTime = true;
            timePicker.setEnabled(true);
            bEdit.setText("Save");
            hour = timePicker.getCurrentHour();                 //get hour
            minute = timePicker.getCurrentMinute();            //get minutes
        }
    }
}
