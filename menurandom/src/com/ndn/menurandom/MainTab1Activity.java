package com.ndn.menurandom;

import java.util.ArrayList;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainTab1Activity extends TopTabActivity implements OnClickListener, SensorEventListener {
    /** Called when the activity is first created. */
	private static Integer FIRST_BUTTON = 1;
	private static Integer SECOND_BUTTON = 2;
	private static Integer KOREA = 3;
	private static Integer CHINA = 4;
	private static Integer JAPAN = 5;
	private static Integer AMERICA = 6;
	private static Integer OTHER = 7;
	
	private String currentState = STATE_FIRST;
	
	private static String STATE_FIRST = "0";
	private static String STATE_SECOND = "1";
	private static String STATE_THIRD = "2";
	private static String STATE_FORTH = "3";

	private View temp_First_view;
	private View temp_View1_1;
	
	private int backPressedCount = 0;
	private long backPressedStartTime = 0;
	private int doublePressedTimeThresHold = 300;
	
	private static ArrayList<String> Array1_1_1;
	private static ArrayList<String> Array1_1_2;
	private static ArrayList<String> Array1_1_3;
	private static ArrayList<String> Array1_1_4;
	private static ArrayList<String> Array1_1_5;

	private View view1;
	private View view1_1;
	private View view1_1_1;
	
	
	private ArrayList<View> viewList = new ArrayList<View>();
	
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        
        //setSelectTab(0);
        
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.tab1);	
		
        view1 = createView1();
        frameLayout.addView(view1);
		
        //viewList.add(view1);
        view1_1 = createView1_1();
		frameLayout.addView(view1_1);
		view1_1.setVisibility(View.GONE);
		
		view1_1_1 = createView1_1_1();
		frameLayout.addView(view1_1_1);
		view1_1_1.setVisibility(View.GONE);
		
    }
    
    
    private View createView1()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.tab_1, null);        
        
		ImageButton btn1_1 = (ImageButton)returnVal.findViewById(R.id.ImgBtn1_1 );
        btn1_1.setTag(FIRST_BUTTON);
        btn1_1.setOnClickListener(this);
        
        ImageButton btn1_2 = (ImageButton)returnVal.findViewById(R.id.ImgBtn1_2);
        btn1_2.setTag(SECOND_BUTTON);
        btn1_2.setOnClickListener(this);

        return returnVal;
    }
    
    private View createView1_1()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1, null);        
        
		ImageButton btn1_1_1 = (ImageButton) returnVal.findViewById(R.id.imgBtn1_1_1);
		btn1_1_1.setTag(KOREA);
        btn1_1_1.setOnClickListener(this);
        
        ImageButton btn1_1_2 = (ImageButton) returnVal.findViewById(R.id.imgBtn1_1_2);
		btn1_1_2.setTag(CHINA);
        btn1_1_2.setOnClickListener(this);
        
        ImageButton btn1_1_3 = (ImageButton) returnVal.findViewById(R.id.imgBtn1_1_3);
		btn1_1_3.setTag(JAPAN);
        btn1_1_3.setOnClickListener(this);
		
        ImageButton btn1_1_4 = (ImageButton) returnVal.findViewById(R.id.imgBtn1_1_4);
		btn1_1_4.setTag(AMERICA);
        btn1_1_4.setOnClickListener(this);
        
        ImageButton btn1_1_5 = (ImageButton) returnVal.findViewById(R.id.imgBtn1_1_4);
		btn1_1_5.setTag(OTHER);
        btn1_1_5.setOnClickListener(this);
        
        return returnVal;
    }
    
    private View createView1_1_1()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_1, null);        
           
        return returnVal;
    }
    
    
    private void setViewAsVisible(View view)
    {
    	FrameLayout frameLayout = (FrameLayout) findViewById(R.id.tab1);	
		for(int i =0; i < frameLayout.getChildCount(); i++)
		{
			View v = frameLayout.getChildAt(i);
			if(v == view)
    		{
    			v.setVisibility(View.VISIBLE);
    		}
    		else
    		{
    			v.setVisibility(View.GONE);
    		}
		}
    	
    	/*for(View v : viewList)
    	{
    		if(v == view)
    		{
    			v.setVisibility(View.VISIBLE);
    		}
    		else
    		{
    			v.setVisibility(View.GONE);
    		}
    	}*/
    }
    
	
	public void onBackPressed(){

		if(currentState == STATE_FIRST){
		
		// 첫번째 버튼을 클릭하면, 
		// 1. 시간을 측정한다.
		// 2. 뒤로 가기 버튼 클릭 횟수를 증가시킨다.
			long currentTime = System.currentTimeMillis();
			if(backPressedCount == 0)
			{
				Toast toast = Toast.makeText(this, "한번 더 누르면 종료됩니다", 200);
				toast.show();
				backPressedStartTime = currentTime;
				backPressedCount++;
				//Log.d("Test", "currentTime : " + currentTime);
			}
			else if(backPressedCount == 1 && (currentTime - backPressedStartTime) < doublePressedTimeThresHold)
			{
				//Log.d("Test", "double Clicked");
				// 두번째 클릭한 것 처리
				finish();
				backPressedCount = 0;
			}
			else
			{
				//Log.d("Test", "Over");
				// 시간을 초과했을 경우
				backPressedStartTime = currentTime;
			}
		}
		else if(currentState == STATE_SECOND)
		{
			// 첫번째 화면으로 변경
			currentState = STATE_FIRST;
			
			setViewAsVisible(view1);
		}
		else if(currentState == STATE_THIRD)
		{
			// 첫번째 화면으로 변경
			currentState = STATE_SECOND;
	        
		}
	}




	public void onClick(View v) {
		if(v.getTag()==FIRST_BUTTON){
	        currentState = STATE_SECOND;			
			
	        setViewAsVisible(view1_1);
	    }
		else if(v.getTag()==SECOND_BUTTON){
			
			
			
		}
		if(v.getTag()==KOREA){ //btn1_1_1.setTag(korea);
	        currentState = STATE_THIRD;

	        setViewAsVisible(view1_1_1);
	        
			Array1_1_1 = new ArrayList<String>();
			Array1_1_1.add(0, "된장국");
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_1_1);
			
			ListView listview = (ListView) view1_1_1.findViewById(R.id.list1_1_1);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
				}
			});
			
		}
		
		if(v.getTag()==CHINA){ //btn1_1_1.setTag(korea);

			
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.INVISIBLE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_2 = inflater1.inflate(R.layout.view1_1_2, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			
			
		}
		if(v.getTag()==JAPAN){ //btn1_1_1.setTag(korea);

			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.INVISIBLE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_3 = inflater1.inflate(R.layout.view1_1_3, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			

		}
		
		if(v.getTag()==AMERICA){ //btn1_1_1.setTag(korea);

			
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.INVISIBLE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_4 = inflater1.inflate(R.layout.view1_1_4, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			

		}
		if(v.getTag()==OTHER){ //btn1_1_1.setTag(korea);
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);

			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.INVISIBLE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_5 = inflater1.inflate(R.layout.view1_1_5, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			
			

		}

	}
	

	
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

		
	}

	public void onSensorChanged(SensorEvent event) {

		
	}
	

}
