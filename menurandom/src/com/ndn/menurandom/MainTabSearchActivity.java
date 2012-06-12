package com.ndn.menurandom;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainTabSearchActivity extends Activity implements OnClickListener{
    
	private static String SEARCH_BTN="0";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		LinearLayout frameLayout = (LinearLayout) findViewById(R.id.tab3);	
	
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view1 = inflater.inflate(R.layout.search_browser, null);
		frameLayout.addView(view1);
	
		Button button = (Button) findViewById(R.id.btn_Search_browser);
	
    
    
	}
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
	
}
