package com.ndn.menurandom;

import android.app.TabActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;


public class MenurandomActivity1 extends TabActivity implements OnClickListener, SensorEventListener {
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
	
	
	private String currentState = STATE_FIRST_1;
	
	private static String STATE_FIRST = "0";
	private static String STATE_FIRST_1 = "0_1";
	private static String STATE_SECOND = "1";
	private static String STATE_THIRD = "2";
	private static String STATE_FORTH = "3";
	 
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        MenurandomActivity1.BACK_TEMP = 0;  
        super.onCreate(savedInstanceState);
        
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
        
        
        ImageButton btn1_1 = (ImageButton)findViewById(R.id.ImgBtn1_1 );
        btn1_1.setTag(MenurandomActivity1.FIRST_BUTTON);
        btn1_1.setOnClickListener(this);
        
        ImageButton btn1_2 = (ImageButton) findViewById(R.id.ImgBtn1_2);
        btn1_2.setTag(SECOND_BUTTON);
      

        
        		
        
   //     SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
   // 	Sensor accelatorSensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
   //     sensorManager.registerListener(this, accelatorSensor,  SensorManager.SENSOR_DELAY_FASTEST);
        
        
        
        
       // btn1_1_1.setTag(korea);
        //ImageButton btn1_1_1 = (ImageButton) findViewById(R.id.imgBtn1_1_1);
        //btn1_1_1.setTag(korea);
        
//        button.setOnClickListener(new View.OnClickListener() {
			
//			@Override
//			public void onClick(View v) {
				
//				FrameLayout frameLayout = (FrameLayout)findViewById(R.id.tab1);
				
//				LinearLayout layout = (LinearLayout)findViewById(R.id.oldView);
//				layout.setVisibility(View.GONE);
				
//				LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//				inflater.inflate(R.layout.newlayout, frameLayout);
				
				//layout = (LinearLayout)findViewById(R.id.newView);
				//layout.setVisibility(View.VISIBLE);
			
//			}
//		});
        
        
        
    }
	
	public void onBackPressed(){  
		if(currentState == STATE_FIRST)
		{
			// 처리 없음
		}
		else if(currentState == STATE_SECOND)
		{
			// 첫번째 화면으로 변경
		}
		
		
		
		
		
		
		/*(//Toast.makeText(getApplicationContext(), "ㅁㄴ파ㅣㄴㅁㅇ퍼ㅏㅇ님ㅍ", Toast.LENGTH_LONG).show();
		if(MenurandomActivity1.BACK_TEMP== 0){
			super.finish(); 
		}else{		
			FrameLayout back_frameLayout = (FrameLayout)findViewById(R.id.tab1);
			
			LinearLayout layout_Back = (LinearLayout) findViewById(MenurandomActivity1.BACK_TEMP);
			layout_Back.setVisibility(View.GONE);
		
			layout_Back = (LinearLayout)findViewById(MenurandomActivity1.BACK_VIEW_TEMP);
			layout_Back.setVisibility(View.VISIBLE);
		}
		
//		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//		View back_view1_1 = inflater.inflate(MenurandomActivity.BACK_VIEW_TEMP, back_frameLayout);
		*/
		

	} 



	
	public void onClick(View v) {
		
		//Toast.makeText(getApplicationContext(), "안녕", Toast.LENGTH_SHORT).show();
		if(v.getTag() == FIRST_BUTTON)
		{
	        MenurandomActivity1.BACK_TEMP = R.id.view1_1;
		//	MenurandomActivity1.BACK_VIEW_TEMP = R.id.FirstView;
			
			FrameLayout frameLayout = (FrameLayout)findViewById(R.id.tab1);
			
		//	LinearLayout layout = (LinearLayout)findViewById(R.id.FirstView);
		//	layout.setVisibility(View.GONE);
			
			LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1 = inflater.inflate(R.layout.view1_1, frameLayout);
			
			ImageButton btn1_1_1 = (ImageButton) view1_1.findViewById(R.id.imgBtn1_1_1);
			btn1_1_1.setTag(KOREA);
	        btn1_1_1.setOnClickListener(this);
	        
			
	        ImageButton btn1_1_2 = (ImageButton) view1_1.findViewById(R.id.imgBtn1_1_2);
			btn1_1_2.setTag(CHINA);
	        btn1_1_2.setOnClickListener(this);
	        
	        
	        ImageButton btn1_1_3 = (ImageButton) view1_1.findViewById(R.id.imgBtn1_1_3);
			btn1_1_3.setTag(JAPAN);
	        btn1_1_3.setOnClickListener(this);
			
	        ImageButton btn1_1_4 = (ImageButton) view1_1.findViewById(R.id.imgBtn1_1_4);
			btn1_1_4.setTag(AMERICA);
	        btn1_1_4.setOnClickListener(this);
	        
	        ImageButton btn1_1_5 = (ImageButton) view1_1.findViewById(R.id.imgBtn1_1_4);
			btn1_1_5.setTag(OTHER);
	        btn1_1_5.setOnClickListener(this);
			//Intent intent = new Intent(getApplicationContext(), MainTabActivity.class);
			//intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			//startActivity(intent);
			
		

			
			
			
			//ImageButton button = (ImageButton)newLayout.findViewById(R.id.imageButton1);
			//button.setTag(SECOND_BUTTON);
			//button.setOnClickListener(this);
			
		}
		else if(v.getTag() == SECOND_BUTTON) {
			Toast toast = Toast.makeText(this, "ㅋㅋㅋㅋㅋ", 1000);
			toast.show();
		}
		
		if(v.getTag()==KOREA){ //btn1_1_1.setTag(korea);
			//MenurandomActivity1.BACK_TEMP = R.id.view1_1_1;
			MenurandomActivity1.BACK_VIEW_TEMP = R.id.view1_1;
			
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.GONE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_1 = inflater1.inflate(R.layout.view1_1_1, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			

			
		}
		
		if(v.getTag()==CHINA){ //btn1_1_1.setTag(korea);
			//MenurandomActivity1.BACK_TEMP = R.id.view1_1_1;
			MenurandomActivity1.BACK_VIEW_TEMP = R.id.view1_1;
			
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.GONE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_2 = inflater1.inflate(R.layout.view1_1_2, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			

		}
		if(v.getTag()==JAPAN){ //btn1_1_1.setTag(korea);
			//MenurandomActivity1.BACK_TEMP = R.id.view1_1_1;
			MenurandomActivity1.BACK_VIEW_TEMP = R.id.view1_1;
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.GONE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_3 = inflater1.inflate(R.layout.view1_1_3, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			

		}
		
		if(v.getTag()==AMERICA){ //btn1_1_1.setTag(korea);
			//MenurandomActivity1.BACK_TEMP = R.id.view1_1_1;
			MenurandomActivity1.BACK_VIEW_TEMP = R.id.view1_1;
			
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.GONE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_4 = inflater1.inflate(R.layout.view1_1_4, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			

		}
		if(v.getTag()==OTHER){ //btn1_1_1.setTag(korea);
			//MenurandomActivity1.BACK_TEMP = R.id.view1_1_1;
			MenurandomActivity1.BACK_VIEW_TEMP = R.id.view1_1;
			
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.GONE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_5 = inflater1.inflate(R.layout.view1_1_5, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			
			

		}
		
		

		
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
	}

	public void onSensorChanged(SensorEvent event) {
		Log.i("Test", String.valueOf(event.values[0]) + " " + String.valueOf(event.values[1]) + " " + String.valueOf(event.values[2]));
		float a = event.values[0];
		if(a<12){
			
			
		}
	}
}
