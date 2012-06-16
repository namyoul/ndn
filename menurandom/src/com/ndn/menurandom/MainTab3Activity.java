package com.ndn.menurandom;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainTab3Activity extends Activity implements Button.OnClickListener{
    
	private static String SEARCH_BTN="0";
	private EditText searchText;
	
//***************************************************
// 내용 : 백버튼 입력 변수
//***************************************************	
	private int backPressedCount = 0;
	private long backPressedStartTime = 0;
	private int doublePressedTimeThresHold = 300;
	private String currentState = STATE_FIRST;
	private static String STATE_FIRST = "0";
//*********************** 끝 *************************
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		LinearLayout frameLayout = (LinearLayout) findViewById(R.id.tab3);	
	
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view1 = inflater.inflate(R.layout.tab_3, null);
		frameLayout.addView(view1);
		
		searchText = (EditText) findViewById(R.id.searchText);
	
		Button button = (Button) findViewById(R.id.btn_Search_browser);
		button.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		Intent intent = new Intent(this, SearchMapActivity.class);
		intent.putExtra("search_menu", searchText.getText().toString());
		startActivity(intent); // 두번째 액티비티를 실행합니다.
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
	}
	
}
