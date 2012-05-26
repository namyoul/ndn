package com.ndn.menurandom;

import android.app.Activity;
import android.app.TabActivity;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainTab1Activity extends TabActivity implements OnClickListener, SensorEventListener {
    /** Called when the activity is first created. */
    
	private static Integer FIRST_BUTTON = 1;
	private static Integer SECOND_BUTTON = 2;
	private static Integer KOREA = 3;
	private static Integer CHINA = 4;
	private static Integer JAPAN = 5;
	private static Integer AMERICA = 6;
	private static Integer OTHER = 7;
	public static Integer BACK_BUTTON_CNT = 0;
	public static int BACK_TEMP;
	public static int BACK_VIEW_TEMP;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
