package com.ndn.menurandom;

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
	
    public void onCreate(Bundle savedInstanceState) {
        MenurandomActivity1.BACK_TEMP = 0;  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TabHost tabHost = getTabHost();
        
        final TabSpec tabSpec1 = tabHost.newTabSpec("Tab1").setIndicator("첫 번째 탭");        
        tabSpec1.setContent(R.id.tab1);
        tabHost.addTab(tabSpec1);
        
        TabSpec tabSpec2 = tabHost.newTabSpec("Tab2").setIndicator("두 번째 탭");
        tabSpec2.setContent(R.id.tab2);
        tabHost.addTab(tabSpec2);
        
        Drawable img = getResources().getDrawable(R.drawable.star);
        TabSpec tabSpec3 = tabHost.newTabSpec("Tab3").setIndicator("세 번째 탭", img);
        tabSpec3.setContent(R.id.tab3);
        tabHost.addTab(tabSpec3);
        
        tabHost.setCurrentTab(0);
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
