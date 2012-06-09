package com.ndn.menurandom;

import java.util.ArrayList;
import java.util.Random;

import android.R.anim;
import android.R.integer;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
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
	private static String STATE_DRINK = "3";
	
	
	private int backPressedCount = 0;
	private long backPressedStartTime = 0;
	private int doublePressedTimeThresHold = 300;
	
	private static ArrayList<String> Array1_2;
	private static ArrayList<String> Array1_1_1;
	private static ArrayList<String> Array1_1_2;
	private static ArrayList<String> Array1_1_3;
	private static ArrayList<String> Array1_1_4;
	private static ArrayList<String> Array1_1_5;
	private static String PIC_TEXT;
	
	
    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
   
    private float x, y, z;
    private static final int SHAKE_THRESHOLD = 800;
   
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;
    
    

	private View view1;
	private View view1_1;
	private View view1_2;
	private View view1_1_1;
	private View view1_1_2;
	private View view1_1_3;
	private View view1_1_4;
	private View view1_1_5;
	private View view_pic;
		
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        
        //setSelectTab(0);
		sensor_Initialize();
		
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.tab1);	
		
        view1 = createView1();
        frameLayout.addView(view1);
		
        //viewList.add(view1);
        view1_1 = createView1_1();
		frameLayout.addView(view1_1);
		view1_1.setVisibility(View.GONE);
		
		view1_2 = createView1_2();
		frameLayout.addView(view1_2);
		view1_2.setVisibility(View.GONE);
		
		view1_1_1 = createView1_1_1();
		frameLayout.addView(view1_1_1);
		view1_1_1.setVisibility(View.GONE);
		
		view1_1_2 = createView1_1_2();
		frameLayout.addView(view1_1_2);
		view1_1_2.setVisibility(View.GONE);
		
		view1_1_3 = createView1_1_3();
		frameLayout.addView(view1_1_3);
		view1_1_3.setVisibility(View.GONE);
		
		view1_1_4 = createView1_1_4();
		frameLayout.addView(view1_1_4);
		view1_1_4.setVisibility(View.GONE);
		
		view1_1_5 = createView1_1_5();
		frameLayout.addView(view1_1_5);
		view1_1_5.setVisibility(View.GONE);
		
		view_pic = createView_Pic();
		frameLayout.addView(view_pic);
		view_pic.setVisibility(View.GONE);
    }
    
    private void sensor_Initialize(){
         SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
         Sensor accelatorSensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
         
         sensorManager.registerListener(this, accelatorSensor,  SensorManager.SENSOR_DELAY_UI);
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
        
        ImageButton btn1_1_5 = (ImageButton) returnVal.findViewById(R.id.imgBtn1_1_5);
		btn1_1_5.setTag(OTHER);
        btn1_1_5.setOnClickListener(this);
        
        return returnVal;
    }

    private View createView1_2()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_2, null);    
           
        return returnVal;
    }
    
    private View createView1_1_1()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_1, null);        
           
        return returnVal;
    }
    
    private View createView1_1_2()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_2, null);        
           
        return returnVal;
    }

    private View createView1_1_3()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_3, null);        
           
        return returnVal;
    }
    
    private View createView1_1_4()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_4, null);        
           
        return returnVal;
    }
    
    private View createView1_1_5()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_5, null);        
           
        return returnVal;
    }
    
    private View createView_Pic()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view_pic, null);        

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
				finish();   // 완전종료
				android.os.Process.killProcess(android.os.Process.myPid());
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
		else if(currentState == STATE_DRINK)
		{
			// 첫번째 화면으로 변경
			currentState = STATE_FIRST;
			
			setViewAsVisible(view1);
		}
		else if(currentState == STATE_THIRD)
		{
			// 첫번째 화면으로 변경
			currentState = STATE_SECOND;
			setViewAsVisible(view1_1);
		}
	}




	public void onClick(View v) {
		if(v.getTag()==FIRST_BUTTON){
	        currentState = STATE_SECOND;			
			
	        setViewAsVisible(view1_1);
	    }
		else if(v.getTag()==SECOND_BUTTON){
			
			currentState = STATE_DRINK;			
			
	        setViewAsVisible(view1_2);
			
			Array1_2 = new ArrayList<String>();
			Array1_2.add(0, "술마셔 베이베");
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_2);
			
			final ListView listview = (ListView) view1_2.findViewById(R.id.list1_2);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					
					//PIC_TEXT = (String) listview.getSelectedItem();
					PIC_TEXT = (String) ((TextView)view).getText();
					Toast.makeText(getApplicationContext(),PIC_TEXT, Toast.LENGTH_LONG).show();
					
					ImageView imageView = (ImageView) findViewById(R.id.img_View);
					int resId = getResources().getIdentifier("img1", "drawable", "com.ndn.menurandom");
					imageView.setImageResource(resId);
					
					
					EditText editText = (EditText) findViewById(R.id.img_Txt);
					editText.setText(PIC_TEXT);
					setViewAsVisible(view_pic);
				}
			});
			
			
		}
		if(v.getTag()==KOREA){ //btn1_1_1.setTag(korea);
	        currentState = STATE_THIRD;

	        setViewAsVisible(view1_1_1);
	        
	        Array_Korea();
			
		}
		
		if(v.getTag()==CHINA){ //btn1_1_1.setTag(korea);
			currentState = STATE_THIRD;
			
			setViewAsVisible(view1_1_2);
			
	        
			Array_China();
			

			
			
		}
		if(v.getTag()==JAPAN){ //btn1_1_1.setTag(korea);

			currentState = STATE_THIRD;
			
			setViewAsVisible(view1_1_3);
			
	        
			Array_Japan();
			

			
		}
		
		if(v.getTag()==AMERICA){ //btn1_1_1.setTag(korea);

			
			currentState = STATE_THIRD;
			
			setViewAsVisible(view1_1_4);
			
	        
			Array_America();
			

			
		}
		if(v.getTag()==OTHER){ //btn1_1_1.setTag(korea);

			currentState = STATE_THIRD;
			
			setViewAsVisible(view1_1_5);
			
	        
			Array_Other();
			

			

		}
	}


	public void Array_Korea(){
		Array1_1_1 = new ArrayList<String>();
		Array1_1_1.add(0, "미역국");
		Array1_1_1.add(1, "무우국");
		Array1_1_1.add(2, "순대국");
		Array1_1_1.add(3, "콩나물국");
		Array1_1_1.add(4, "김치국");
		Array1_1_1.add(5, "감자국");
		Array1_1_1.add(6, "옫뎅국");
		Array1_1_1.add(7, "만두국");
		Array1_1_1.add(8, "북어국");
		Array1_1_1.add(9, "해장국");
		Array1_1_1.add(10, "청국장");
		Array1_1_1.add(11, "김치찌개");
		Array1_1_1.add(12, "육개장");
		Array1_1_1.add(13, "된장찌개");
		Array1_1_1.add(14, "순두부찌개");
		Array1_1_1.add(15, "생태찌개");
		Array1_1_1.add(16, "부대찌개");
		Array1_1_1.add(17, "비지찌개");
		Array1_1_1.add(18, "갈비탕");
		Array1_1_1.add(19, "매운탕");
		Array1_1_1.add(20, "해물탕");
		Array1_1_1.add(21, "꽃게탕");
		Array1_1_1.add(22, "알탕");
		Array1_1_1.add(23, "비빔밥");
		Array1_1_1.add(24, "영양밥");
		Array1_1_1.add(25, "콩나물밥");
		Array1_1_1.add(26, "볶음밥");
		Array1_1_1.add(27, "국수장국");
		Array1_1_1.add(28, "칼국수");
		Array1_1_1.add(29, "비빔국수");
		Array1_1_1.add(30, "냉면");
		Array1_1_1.add(31, "비빔냉면");
		Array1_1_1.add(32, "닭찜");
		Array1_1_1.add(33, "돼지갈비찜");
		Array1_1_1.add(34, "북어찜");
		Array1_1_1.add(35, "알찜");
		Array1_1_1.add(36, "생선구이");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_1_1);
		
		ListView listview = (ListView) view1_1_1.findViewById(R.id.list1_1_1);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
				setViewAsVisible(view_pic);
			}
		});
		
	}
	public void Array_China(){
		Array1_1_2 = new ArrayList<String>();
		Array1_1_2.add(0, "짜장면");
		Array1_1_2.add(1, "간짜장");
		Array1_1_2.add(2, "쟁반짜장");
		Array1_1_2.add(3, "사천짜장");
		Array1_1_2.add(4, "삼선짜장");
		Array1_1_2.add(5, "짬뽕");
		Array1_1_2.add(6, "기스면");
		Array1_1_2.add(7, "짬뽕밥");
		Array1_1_2.add(8, "짜장밥");
		Array1_1_2.add(9, "유산슬밥");
		Array1_1_2.add(10, "새우덮밥");
		Array1_1_2.add(11, "마파두부밥");
		Array1_1_2.add(12, "탕수육");
		Array1_1_2.add(13, "깐풍기");
		Array1_1_2.add(14, "양장피");
		Array1_1_2.add(15, "깐쇼새우");
		Array1_1_2.add(16, "고추잡채");
		Array1_1_2.add(17, "팔보채");
		Array1_1_2.add(18, "양장피");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_1_2);
		
		ListView listview = (ListView) view1_1_2.findViewById(R.id.list1_1_2);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
				setViewAsVisible(view_pic);
			}
		});
	}
	public void Array_Japan(){
		Array1_1_3 = new ArrayList<String>();
		Array1_1_3.add(0, "미소된장국");
		Array1_1_3.add(1, "샤브샤브");
		Array1_1_3.add(2, "스기야키");
		Array1_1_3.add(3, "조개맑은국");
		Array1_1_3.add(4, "가쓰동");
		Array1_1_3.add(5, "볶음우동");
		Array1_1_3.add(6, "우동");
		Array1_1_3.add(7, "해물볶음우동");
		Array1_1_3.add(8, "초밥");
		Array1_1_3.add(9, "삼각김밥");
		Array1_1_3.add(10, "알밥");
		Array1_1_3.add(11, "오니기리");
		Array1_1_3.add(12, "오차즈케");
		Array1_1_3.add(13, "캘리포니아롤");
		Array1_1_3.add(14, "오코노미야키");
		Array1_1_3.add(15, "참치샐러드");
		Array1_1_3.add(16, "타코야키");
		Array1_1_3.add(17, "메로구이");
		Array1_1_3.add(18, "돈가스");
		Array1_1_3.add(19, "모듬튀김");
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_1_3);
		
		ListView listview = (ListView) view1_1_3.findViewById(R.id.list1_1_3);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
				setViewAsVisible(view_pic);
			}
		});
	}
	public void Array_America(){
		Array1_1_4 = new ArrayList<String>();
		Array1_1_4.add(0, "토스트");
		Array1_1_4.add(1, "식빵");
		Array1_1_4.add(2, "밤빵");
		Array1_1_4.add(3, "롤케잌");
		Array1_1_4.add(4, "케잌");
		Array1_1_4.add(5, "크로켓");
		Array1_1_4.add(6, "꽈배기");
		Array1_1_4.add(7, "도넛");
		Array1_1_4.add(8, "마늘빵");
		Array1_1_4.add(9, "핫도그");
		Array1_1_4.add(10, "브라우니");
		Array1_1_4.add(11, "스테이크");
		Array1_1_4.add(12, "바비큐폭챱");
		Array1_1_4.add(13, "베이컨");
		Array1_1_4.add(14, "치킨커틀릿");
		Array1_1_4.add(15, "감자수프");
		Array1_1_4.add(16, "브로콜리");
		Array1_1_4.add(17, "비프스프");
		Array1_1_4.add(18, "스튜");
		Array1_1_4.add(19, "오트밀");
		Array1_1_4.add(20, "옥수수스프");
		Array1_1_4.add(21, "토마토수프");
		Array1_1_4.add(22, "크림수프");
		Array1_1_4.add(23, "스파게티");
		Array1_1_4.add(24, "오믈렛");
		Array1_1_4.add(25, "파스타");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_1_4);
		
		ListView listview = (ListView) view1_1_4.findViewById(R.id.list1_1_4);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
				setViewAsVisible(view_pic);
			}
		});
	}
	public void Array_Other(){
		Array1_1_5 = new ArrayList<String>();
		Array1_1_5.add(0, "김밥");
		Array1_1_5.add(1, "햄버거");
		Array1_1_5.add(2, "떡볶이");
		Array1_1_5.add(3, "튀김");
		Array1_1_5.add(4, "어묵");
		Array1_1_5.add(5, "순대");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_1_5);
		
		ListView listview = (ListView) view1_1_5.findViewById(R.id.list1_1_5);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
				setViewAsVisible(view_pic);
			}
		});
	}
	
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

		
	}

	public void onSensorChanged(SensorEvent event) {
		//Log.i("Test", String.valueOf(event.values[0]) + " " + String.valueOf(event.values[1]) + " " + String.valueOf(event.values[2]));		
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
   
            if (gabOfTime > 100) {
                lastTime = currentTime;
   
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];
   
                speed = Math.abs(x + y + z - lastX - lastY - lastZ) /
                        gabOfTime * 10000;
   
                if (speed > SHAKE_THRESHOLD) {
        			Random random = new Random();
        			int r = random.nextInt();
        			String temp_String2 = String.valueOf(r);
        			String dtemp_String = temp_String2.substring(temp_String2.length()-1);
        			int abc = Integer.parseInt(dtemp_String);
        			if(currentState==STATE_FIRST){
        				Toast toast = Toast.makeText(this, "흔들기는 첫번째 페이지는 안합니다", 2);
        				toast.show();
        			}
        			else if(currentState==STATE_SECOND){
        				if(abc==0 || abc==1){
        					Toast toast = Toast.makeText(this, "한국음식", 2);
        					toast.show();		
        					setViewAsVisible(view1_1_1);
        					
        					Array_Korea();
        					currentState=STATE_THIRD;
        				}
        				if(abc==2 || abc==3){
        					Toast toast = Toast.makeText(this, "중국음식", 2);
        					toast.show();
        					setViewAsVisible(view1_1_2);
        					
        					Array_China();
        					currentState=STATE_THIRD;
        				}
        				if(abc==4 || abc==5){
        					Toast toast = Toast.makeText(this, "일본음식", 2);
        					toast.show();
        					setViewAsVisible(view1_1_3);
        					
        					Array_Japan();
        					currentState=STATE_THIRD;
        				}
        				if(abc==6 || abc==7){
        					Toast toast = Toast.makeText(this, "양식", 2);
        					toast.show();		
        					setViewAsVisible(view1_1_4);
        					
        					Array_America();
        					currentState=STATE_THIRD;
        				}
        				if(abc==8 || abc==9){
        					Toast toast = Toast.makeText(this, "기타등등", 2);
        					toast.show();
        					setViewAsVisible(view1_1_5);
        					
        					Array_Other();
        					currentState=STATE_THIRD;
        				}
        			}
        			else if(currentState==STATE_THIRD){
        				Toast toast = Toast.makeText(this, "세번째 페이지", 2);
        				toast.show();
        			}
        			else if(currentState==STATE_DRINK){
        				Toast toast = Toast.makeText(this, "술먹기 페이지", 2);
        				toast.show();
        			}
                }
                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
		
		
		
		
/*		if (lock_switch.equals("1")){
			SENSOR_X = Math.abs((int)event.values[0]);
			lock_switch="0";
		}else{
			SENSOR_Y = Math.abs((int)event.values[0]);
			lock_switch="1";
		}
		SENSOR_Z = SENSOR_X + SENSOR_Y;

		if (SENSOR_Z > 12){
			Random random = new Random();
			int r = random.nextInt();
			String temp_String2 = String.valueOf(r);
			String dtemp_String = temp_String2.substring(temp_String2.length()-1);
			int abc = Integer.parseInt(dtemp_String);
			if(currentState==STATE_FIRST){
				Toast toast = Toast.makeText(this, "흔들기는 첫번째 페이지는 안합니다", 2);
				toast.show();
			}
			else if(currentState==STATE_SECOND){
				if(abc==0 || abc==1){
					Toast toast = Toast.makeText(this, "한국음식", 2);
					toast.show();		
					setViewAsVisible(view1_1_1);
					
					Array_Korea();
					currentState=STATE_THIRD;
				}
				if(abc==2 || abc==3){
					Toast toast = Toast.makeText(this, "중국음식", 2);
					toast.show();
					setViewAsVisible(view1_1_2);
					
					Array_China();
					currentState=STATE_THIRD;
				}
				if(abc==4 || abc==5){
					Toast toast = Toast.makeText(this, "일본음식", 2);
					toast.show();
					setViewAsVisible(view1_1_3);
					
					Array_Japan();
					currentState=STATE_THIRD;
				}
				if(abc==6 || abc==7){
					Toast toast = Toast.makeText(this, "양식", 2);
					toast.show();		
					setViewAsVisible(view1_1_4);
					
					Array_America();
					currentState=STATE_THIRD;
				}
				if(abc==8 || abc==9){
					Toast toast = Toast.makeText(this, "기타등등", 2);
					toast.show();
					setViewAsVisible(view1_1_5);
					
					Array_Other();
					currentState=STATE_THIRD;
				}
			}
			else if(currentState==STATE_THIRD){
				Toast toast = Toast.makeText(this, "세번째 페이지", 2);
				toast.show();
			}
			else if(currentState==STATE_DRINK){
				Toast toast = Toast.makeText(this, "술먹기 페이지", 2);
				toast.show();
			}
			
			
			
			
			SENSOR_Z = 0;
			SENSOR_X = 0;
			SENSOR_Y = 0;
			
			
			
			
		}else{

		}*/
	}
}