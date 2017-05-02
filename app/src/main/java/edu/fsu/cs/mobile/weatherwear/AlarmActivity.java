package edu.fsu.cs.mobile.weatherwear;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.content.Context;

public class AlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    boolean editTime = false;
    Button bEdit;
    int hour, minute;
    Switch switch1;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

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
                        scheduleAlarm();
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
    //edit button, allows user to edit timepicker
    public void clickEdit(View view) {
        if(editTime == true){
            bEdit.setText("Edit");
            editTime = false;
            timePicker.setEnabled(false);
            if(switch1.isChecked())
                scheduleAlarm();
        }
        else {
            editTime = true;
            timePicker.setEnabled(true);
            bEdit.setText("Save");
            hour = timePicker.getCurrentHour();                 //get hour
            minute = timePicker.getCurrentMinute();            //get minutes
        }
    }
    //schedule alarm, this actually sets the alarm and calls the broadcast receiver
    public void scheduleAlarm()
    {
        hour = timePicker.getCurrentHour();                 //get hour
        minute = timePicker.getCurrentMinute();            //get minutes
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Alarm scheduled for " + hour + ":" + minute, Toast.LENGTH_SHORT).show();

    }
}
