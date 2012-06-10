package com.ndn.menurandom;

import com.ndn.menurandom.db.DBHandler;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainTabWidgetActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		DBHandler.initialize(this);//assets db 파일을 databases 폴더로 복사
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, MainTab1Activity.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("Random")
				.setIndicator("Random",res.getDrawable(R.drawable.ic_tab1_state))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, MainTab2Activity.class);
		spec = tabHost
				.newTabSpec("Recommended")
				.setIndicator("Recommended", res.getDrawable(R.drawable.ic_tab2_state))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, MainTab3Activity.class);
		spec = tabHost
				.newTabSpec("Map")
				.setIndicator("Map", res.getDrawable(R.drawable.ic_tab3_state))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

	}
}