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

public class MainTab3Activity extends Activity implements Button.OnClickListener{
    
	private static String SEARCH_BTN="0";
	private EditText searchText;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		LinearLayout frameLayout = (LinearLayout) findViewById(R.id.tab3);	
	
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view1 = inflater.inflate(R.layout.search_browser, null);
		frameLayout.addView(view1);
		
		searchText = (EditText) findViewById(R.id.searchText);
	
		Button button = (Button) findViewById(R.id.btn_Search_browser);
		button.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		Intent intent = new Intent(this, SearchMapActivity.class);
		intent.putExtra("search_menu", searchText.getText());
		startActivity(intent); // 두번째 액티비티를 실행합니다.
		}
}
