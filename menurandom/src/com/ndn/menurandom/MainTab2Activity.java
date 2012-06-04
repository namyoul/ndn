package com.ndn.menurandom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class MainTab2Activity extends TopTabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout frameLayout = (LinearLayout) findViewById(R.id.tab2);

		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view1 = inflater.inflate(R.layout.tab_2, null);
		frameLayout.addView(view1);
		
	}

}
