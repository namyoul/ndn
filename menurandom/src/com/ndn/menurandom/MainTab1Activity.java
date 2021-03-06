package com.ndn.menurandom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import com.ndn.menurandom.db.DBHandler;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainTab1Activity extends Activity implements OnClickListener, SensorEventListener {
    /** Called when the activity is first created. */
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 변수선언부
//************************************************************************

//***************************************************
// 내용 : 버튼 선택시 처리 변수
//***************************************************
	private static Integer FIRST_BUTTON = 1;
	private static Integer SECOND_BUTTON = 2;
	private static Integer KOREA = 3;
	private static Integer CHINA = 4;
	private static Integer JAPAN = 5;
	private static Integer AMERICA = 6;
	private static Integer OTHER = 7;
//*********************** 끝 *************************
	

//***************************************************
// 내용 : 어떤 뷰를 선택했는지에 대한 상태 함수 관련 변수
//***************************************************
	private String currentState = STATE_FIRST;
	private static String STATE_FIRST = "0";
	private static String STATE_SECOND = "1";
	private static String STATE_THIRD = "2";
	private static String STATE_FOURTH = "3";
	private static String STATE_DRINK = "4";
	private static String STATE_DRINK_LIST = "5";
//*********************** 끝 *************************
	
	
//***************************************************
// 내용 : 최종메뉴 선택시 해당음식 출력 관련 변수
//***************************************************
	private String currentThird_View = T_View1;
	private String currentFourth_View = F_View1;
	private static String F_View0 = "0";
	private static String F_View1 = "1";
	private static String F_View2 = "2";
	private static String F_View3 = "3";
	private static String F_View4 = "4";
	private static String F_View5 = "5";
	private static String T_View1 = "1";
	private static String T_View2 = "2";
	private static String T_View3 = "3";
	private static String T_View4 = "4";
	private static String T_View5 = "5";
//*********************** 끝 *************************
	
	
//***************************************************
// 내용 : 백버튼 입력 변수
//***************************************************	
	private int backPressedCount = 0;
	private long backPressedStartTime = 0;
	private int doublePressedTimeThresHold = 300;
//*********************** 끝 *************************
	
	
//***************************************************
// 내용 : 센서부 변수
//***************************************************
	SensorManager sensorManager = null;

    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
   
    private float x, y, z;
    private static final int SHAKE_THRESHOLD = 900;     // 흔들기 모션 강도(얼만큼 세게 흔들었느냐)
   
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;
//*********************** 끝 *************************

    
//***************************************************
// 내용 : 뷰를 저장하기 위한 변수
//***************************************************
	private View view1;
	private View view1_1;
	private View view1_2;
	private View view1_1_1;
	private View view1_1_2;
	private View view1_1_3;
	private View view1_1_4;
	private View view1_1_5;
	private View view_pic;
//*********************** 끝 *************************

//******************************* 끝 *************************************
	
	
	
	
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : Activity OnCreate함수
//************************************************************************
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);

//***************************************************
// 내용 : 센서부 호출
//***************************************************
        sensor_Initialize();
//*********************** 끝 *************************
        //setSelectTab(0);

//***************************************************
// 내용 : 각 뷰 전체를 호출함(처음 보여줄 뷰를 제외한 나머지 뷰 숨김)
//***************************************************
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.tab1);	//탭선택부
		
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
//*********************** 끝 *************************
    }
    	
//******************************* 끝 *************************************

	
	
	
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 각 뷰에 버튼 및 리스너 등록
//************************************************************************

//***************************************************
// 내용 : 처음 화면[식사 / 안주]
//***************************************************
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
//*********************** 끝 *************************
    
    
//***************************************************
// 내용 : 처음화면->식사버튼 클릭[한식/중식/일식/양식/기타]
//***************************************************
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
//*********************** 끝 *************************
    
    
//***************************************************
// 내용 : 처음화면->안주버튼 클릭[안주 리스트]
//***************************************************    
    private View createView1_2()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_2, null);    
           
        return returnVal;
    }
//*********************** 끝 *************************
    
    
    
//***************************************************
// 내용 : 식사버튼->한식버튼 클릭[한식 리스트]
//***************************************************
    private View createView1_1_1()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_1, null);        
    
        return returnVal;
    }
//*********************** 끝 *************************
    
    
    
//***************************************************
// 내용 : 식사버튼->중식버튼 클릭[중식 리스트]
//***************************************************
    private View createView1_1_2()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_2, null);        
        return returnVal;
    }
//*********************** 끝 *************************
    


//***************************************************
// 내용 : 식사버튼->일식버튼 클릭[일식 리스트]
//***************************************************
    private View createView1_1_3()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_3, null);        
           
        return returnVal;
    }
//*********************** 끝 *************************
    
    
//***************************************************
// 내용 : 식사버튼->양식버튼 클릭[양식 리스트]
//***************************************************
    private View createView1_1_4()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_4, null);        
           
        return returnVal;
    }
//*********************** 끝 *************************
    
    
//***************************************************
// 내용 : 식사버튼->기타버튼 클릭[기타음식 리스트]
//***************************************************
    private View createView1_1_5()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view1_1_5, null);        
           
        return returnVal;
    }
//*********************** 끝 *************************


//***************************************************
// 내용 : 각 리스트-> 음식정보
//***************************************************
    private View createView_Pic()
    {
    	View returnVal;
    	
    	LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		returnVal = inflater.inflate(R.layout.view_pic, null);        

        return returnVal;
    }
//*********************** 끝 *************************
    
//******************************* 끝 *************************************
    
    
    
    
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 각 뷰에 버튼 및 리스너 등록
//************************************************************************
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
//******************************* 끝 *************************************
    
    
    
    
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 백버튼 클릭시 처리 함수
//************************************************************************
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
		else if(currentState == STATE_DRINK_LIST){
			currentState = STATE_FIRST;
			
			setViewAsVisible(view1);
		}
		else if(currentState == STATE_DRINK)
		{
			// 첫번째 화면으로 변경
			currentState = STATE_DRINK_LIST;
			setViewAsVisible(view1_2);
		}
		else if(currentState == STATE_THIRD)
		{
			// 첫번째 화면으로 변경
			currentState = STATE_SECOND;
			setViewAsVisible(view1_1);
		}
		else if(currentState == STATE_FOURTH)
		{
			if (currentFourth_View==F_View0){
				currentState = STATE_SECOND;
				setViewAsVisible(view1_1);
			}
			if (currentFourth_View==F_View1){
				currentState = STATE_THIRD;
				setViewAsVisible(view1_1_1);
				Array_Korea();
			}
			else if(currentFourth_View==F_View2){
				currentState = STATE_THIRD;
				setViewAsVisible(view1_1_2);
				Array_China();
			}
			else if(currentFourth_View==F_View3){
				currentState = STATE_THIRD;
				setViewAsVisible(view1_1_3);
				Array_Japan();
			}
			else if(currentFourth_View==F_View4){
				currentState = STATE_THIRD;
				setViewAsVisible(view1_1_4);
				Array_America();
			}
			else if(currentFourth_View==F_View5){
				currentState = STATE_THIRD;
				setViewAsVisible(view1_1_5);
				Array_Other();
			}
		}
	}
//******************************* 끝 *************************************

	
	
	
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 버튼 클릭시 처리 함수
//************************************************************************
	public void onClick(View v) {
		if(v.getTag()==FIRST_BUTTON){
	        currentState = STATE_SECOND;			
			
	        setViewAsVisible(view1_1);
	    }
		else if(v.getTag()==SECOND_BUTTON){
			
			currentState = STATE_DRINK_LIST;			
			
	        setViewAsVisible(view1_2);
			
			//Array1_2 = new ArrayList<String>();
			//Array1_2.add(0, "술마셔 베이베");
			//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_2);
	        
	        ArrayList arItem = getArrayList("2", "O");;
	        //어댑터를 만듬
	        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.mylist, arItem);
	       
			final ListView listview = (ListView) view1_2.findViewById(R.id.list1_2);
			listview.setAdapter(MyAdapter);
//			
//			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//					
//					//PIC_TEXT = (String) listview.getSelectedItem();
//					PIC_TEXT = (String) ((TextView)view).getText();
//					Toast.makeText(getApplicationContext(),PIC_TEXT, Toast.LENGTH_LONG).show();
//					
//					ImageView imageView = (ImageView) findViewById(R.id.img_View);
//					int resId = getResources().getIdentifier("img1", "drawable", "com.ndn.menurandom");
//					imageView.setImageResource(resId);
//					
//					
//					EditText editText = (EditText) findViewById(R.id.img_Txt);
//					editText.setText(PIC_TEXT);
//					setViewAsVisible(view_pic);
//				}
//			});
			
			
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
//******************************* 끝 *************************************

	
	
	
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 각 리스트 배열 입력
//************************************************************************
	public void Array_Korea(){
		
		ArrayList arItem = getArrayList("1", "K"); // 한식 데이터 가져오기
        //어댑터를 만듬
        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.mylist, arItem);
        
        
		ListView listview = (ListView) view1_1_1.findViewById(R.id.list1_1_1);
		listview.setAdapter(MyAdapter);
		currentThird_View=T_View1;
//		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
//				setViewAsVisible(view_pic);
//			}
//		});
		
	}
	public void Array_China(){
		
		ArrayList arItem = getArrayList("1", "C"); // 중식 데이터 가져오기
        //어댑터를 만듬
        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.mylist, arItem);
        
		ListView listview = (ListView) view1_1_2.findViewById(R.id.list1_1_2);
		listview.setAdapter(MyAdapter);
//		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
//				setViewAsVisible(view_pic);
//			}
//		});
		currentThird_View=T_View2;
	}
	public void Array_Japan(){

		ArrayList arItem = getArrayList("1", "J"); // 일식 데이터 가져오기
        //어댑터를 만듬
        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.mylist, arItem);
        
		ListView listview = (ListView) view1_1_3.findViewById(R.id.list1_1_3);
		listview.setAdapter(MyAdapter);
//		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
//				setViewAsVisible(view_pic);
//			}
//		});
		currentThird_View=T_View3;
	}
	public void Array_America(){

		ArrayList arItem = getArrayList("1", "A"); // 양식 데이터 가져오기
        //어댑터를 만듬
        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.mylist, arItem);
		
		ListView listview = (ListView) view1_1_4.findViewById(R.id.list1_1_4);
		listview.setAdapter(MyAdapter);
		
//		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
//				setViewAsVisible(view_pic);
//			}
//		});
		currentThird_View=T_View4;
	}
	public void Array_Other(){
		/*
		Array1_1_5 = new ArrayList<String>();
		Array1_1_5.add(0, "김밥");
		Array1_1_5.add(1, "햄버거");
		Array1_1_5.add(2, "떡볶이");
		Array1_1_5.add(3, "튀김");
		Array1_1_5.add(4, "어묵");
		Array1_1_5.add(5, "순대");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Array1_1_5);
		*/

		ArrayList arItem = getArrayList("1", "S"); // 분식 데이터 가져오기
        //어댑터를 만듬
        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.mylist, arItem);
		
		
		ListView listview = (ListView) view1_1_5.findViewById(R.id.list1_1_5);
		listview.setAdapter(MyAdapter);
/*		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), ((TextView)view).getText(),Toast.LENGTH_LONG).show();
				setViewAsVisible(view_pic);
			}
		});*/
		currentThird_View=T_View5;
	}
//******************************* 끝 *************************************
	
	
	
	
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 센서 선언부
//************************************************************************
	private void sensor_Initialize(){
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		/*SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor accelatorSensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        sensorManager.registerListener(this, accelatorSensor,  SensorManager.SENSOR_DELAY_UI);*/
	}
	@Override
    protected void onResume() {
        super.onResume();
        operateShaking();
    }

	private void operateShaking()
	{
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER)  ; //
        sensorManager.registerListener(this, sensors.get(0),        //
          SensorManager.SENSOR_ACCELEROMETER|
             SensorManager.SENSOR_DELAY_NORMAL
        );
	}
	
	private void stopShaking()
	{
		sensorManager.unregisterListener(this);
	}
	
    
   @Override
    protected void onStop() {
	   stopShaking();
	   super.onStop();
    }
//******************************* 끝 *************************************	
	
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

		
	}

    
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 센서 변경시 처리 구문
//************************************************************************
	public void onSensorChanged(SensorEvent event) {
		// Log.i("Test", String.valueOf(event.values[0]) + " " +
		// String.valueOf(event.values[1]) + " " +
		// String.valueOf(event.values[2]));

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			x = event.values[SensorManager.DATA_X];
			y = event.values[SensorManager.DATA_Y];
			z = event.values[SensorManager.DATA_Z];

			long gabOfTime = 150;
			
			speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

			if (speed > SHAKE_THRESHOLD) {
				
				//if(!isShaked)
				//{
					
					/*Random random = new Random(System.nanoTime());
					int r = random.nextInt();
					String temp_String2 = String.valueOf(r);
					String dtemp_String = temp_String2.substring(temp_String2
							.length() - 1);
					int abc = Integer.parseInt(dtemp_String);*/
					
	//				1 shake pass 
	//				2 shake 4th -b 2p
	//				3 shake 4th -b 3p
	//				4 shake pass
					if (currentState == STATE_FIRST || currentState == STATE_FOURTH) {
						
					}
					if (currentState == STATE_SECOND) {
						
						select_food("1", "", STATE_FOURTH, F_View0);
					} 
					else if (currentState == STATE_THIRD) 
					{
						if(currentThird_View==T_View1)
						{
							
							select_food("1", "K", STATE_FOURTH, F_View1);
							
						}
						else if(currentThird_View==T_View2)
						{
							
							select_food("1", "C", STATE_FOURTH, F_View2);
							
						}
						else if(currentThird_View==T_View3)
						{
							
							select_food("1", "J", STATE_FOURTH, F_View3);
							
						}
						else if(currentThird_View==T_View4)
						{
							
							select_food("1", "A", STATE_FOURTH, F_View4);
							
							
						}
						else if(currentThird_View==T_View5)
						{
							
							select_food("1", "S", STATE_FOURTH, F_View5);
							
						}
	
						Toast toast = Toast.makeText(this, "세번째 페이지", 2);
						toast.show();
	
					} 
					else if (currentState == STATE_DRINK_LIST) {
						Toast toast = Toast.makeText(this, "술먹기 페이지", 2);
						toast.show();
	
						select_food("2", "", STATE_DRINK, "");
	
					}
					
				//}
				
			}
			lastX = event.values[DATA_X];
			lastY = event.values[DATA_Y];
			lastZ = event.values[DATA_Z];

		}


	}
//******************************* 끝 *************************************	
	
	
	
	
//************************************************************************
// 개발자 : 김두현
// 개발버전 : VER 1.000
// 개발일시 : 12. 06. 14
// 개발내용 : 선택된 리스트 음식추천화면 넘김
//************************************************************************
	private void select_food(String code, String detailCode, String state, String f_view){
		DBHandler dbhandler = DBHandler.open(this);
		HashMap itemMap = new HashMap();
		itemMap.put("code", code);// 1 : 식사
		if(detailCode!=""){
			itemMap.put("detailCode", detailCode);// C : 중식
		}
		Cursor cursor = dbhandler.randomSelect(itemMap);
		startManagingCursor(cursor);
		cursor.moveToFirst(); // 커서 처음으로 이동 시킴
		String result = cursor.getString(cursor
				.getColumnIndex("menuName"));
		dbhandler.close();
		moveShowPage(result);

		currentState = state;
		if(f_view!=""){
			currentFourth_View = f_view;
		}
	}
//******************************* 끝 *************************************	
	
	
	
	
	private ArrayList getArrayList(String code, String detailCode){
		DBHandler dbhandler = DBHandler.open(this);
		Cursor cursor = dbhandler.getArrayList(code, detailCode);
        startManagingCursor(cursor);
        
		
	   //데이터를 만듬(ac220v)
	   ArrayList<MyItem> arItem = new ArrayList<MyItem>();
       MyItem mi;
        
        if (cursor.moveToFirst()) {
            do {
	            String id = cursor.getString(cursor.getColumnIndex("id"));
	            String menuName = cursor.getString(cursor.getColumnIndex("menuName"));
	            
	            mi = new MyItem(id, menuName, R.drawable.ic_launcher);
	            arItem.add(mi);    
            } while (cursor.moveToNext());
        }
        
		dbhandler.close();
		return arItem;
	}
	
	
	
	//어댑터를 커스터마이징 해야됨
	class MyListAdapter extends BaseAdapter{
	    Context maincon;
	    LayoutInflater Inflater;
	    ArrayList<MyItem> arSrc;
	    int layout;
	   
	    //생성자
	    public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc){
	          maincon = context;
	          //생성시 인플레이트 준비를 미리해둠
	          Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	          arSrc = aarSrc;
	          layout = alayout;//alayout은 메인에서보면 R.layout.icontext임 int타입이여서 이런식으로도 가능
	    }
	   
	    //추상메소드를 구현해야됨
	         public int getCount() {//getCount는 데이터의 사이즈를 리턴하면됨
	                // TODO Auto-generated method stub
	                return arSrc.size();
	         }

	         public Object getItem(int position) {
	                // TODO Auto-generated method stub
	                return arSrc.get(position).menuName;//getItem은 그 position의 값을 리턴하면됨
	         }

	         public long getItemId(int position) {//getItemId는 position을 리턴해주면됨
	                // TODO Auto-generated method stub
	                return position;
	         }

	        
	         //이 부분은 리스트 각항목 하나하나를 만드는 부분임
	          
	         public View getView(int position, View convertView, ViewGroup parent) {
	                // TODO Auto-generated method stub
	                final int pos = position;
	                //첫번째는 convertView가 null이여서 inflate를 통해서
	                //마지막인자는 Whether the inflated hierarchy should be attached to the root parameter?
	                //해석 못했음
	                if(convertView == null){//layout은 R.layout.icontext parent는 뷰그룹인 리스트뷰를 뜻함
	                       convertView = Inflater.inflate(layout, parent, false);
	                      
	                }

	                //이미지뷰를 세팅하고
	                ImageView img = (ImageView)convertView.findViewById(R.id.img);
	                img.setImageResource(arSrc.get(position).Icon);
	               
	                
	              //텍스브튜도 세팅
	                TextView id = (TextView)convertView.findViewById(R.id.id);
	                id.setText(arSrc.get(position).id);
	               
	              //텍스브튜도 세팅
	                TextView txt = (TextView)convertView.findViewById(R.id.text);
	                txt.setText(arSrc.get(position).menuName);
	               
	               
	                //버튼도 세팅함
	                Button btn = (Button)convertView.findViewById(R.id.btn);
	                btn.setOnClickListener(new OnClickListener() {
	                      
	                       public void onClick(View v) {
	                             // TODO Auto-generated method stub
	                             String str = arSrc.get(pos).menuName + "(id : " + arSrc.get(pos).id + ")";
	                             
	                             moveShowPage(str);//메뉴 소개 페이지로 이동!
	                       }
	                });
	               
	                return convertView;//위 과정이 리스트뷰에 getCount만큼 반복됨
	         }
	}
	
	/*
	 * 메뉴 소개 페이지로 이동!
	 */
	private void moveShowPage(String txt) {
		
		ImageView imageView = (ImageView) findViewById(R.id.img_View);
		int resId = getResources().getIdentifier("img1", "drawable", "com.ndn.menurandom");
		imageView.setImageResource(resId);
		
		
		EditText editText = (EditText) findViewById(R.id.img_Txt);
		editText.setText(txt);
		setViewAsVisible(view_pic);
	}
	
}