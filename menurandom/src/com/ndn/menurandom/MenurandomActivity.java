package com.ndn.menurandom;

import android.app.TabActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;


public class MenurandomActivity extends TabActivity implements OnClickListener {
    /** Called when the activity is first created. */
    
	private static Integer FIRST_BUTTON = 1;
	private static Integer SECOND_BUTTON = 2;
	private static Integer korea = 3;
	ImageButton btn1_1_1 ;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
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
        
        
        ImageButton btn1_1 = (ImageButton)findViewById(R.id.ImgBtn1_1 );
        btn1_1.setTag(FIRST_BUTTON);
        btn1_1.setOnClickListener(this);
        
        ImageButton btn1_2 = (ImageButton) findViewById(R.id.ImgBtn1_2);
        btn1_2.setTag(SECOND_BUTTON);
        
         btn1_1_1 = (ImageButton) findViewById(R.id.imgBtn1_1_1);
        btn1_1_1.setOnClickListener(this);
        
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

	@Override
	public void onClick(View v) {
		/*
		Toast.makeText(getApplicationContext(), "안녕", Toast.LENGTH_SHORT).show();
		if(v.getTag() == FIRST_BUTTON)
		{
			
			FrameLayout frameLayout = (FrameLayout)findViewById(R.id.tab1);
			
			LinearLayout layout = (LinearLayout)findViewById(R.id.FirstView);
			layout.setVisibility(View.GONE);
			
			LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1 = inflater.inflate(R.layout.view1_1, frameLayout);
			
			
			
			
			
			
			//ImageButton button = (ImageButton)newLayout.findViewById(R.id.imageButton1);
			//button.setTag(SECOND_BUTTON);
			//button.setOnClickListener(this);
			
		}
		else if(v.getTag() == SECOND_BUTTON) {
			Toast toast = Toast.makeText(this, "ㅋㅋㅋㅋㅋ", 1000);
			toast.show();
		}
		if(v == btn1_1_1){ //btn1_1_1.setTag(korea);
			
			
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);
			
			
			LinearLayout layout1_1 = (LinearLayout)findViewById(R.id.view1_1);
			layout1_1.setVisibility(View.GONE);
			
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View view1_1_1 = inflater1.inflate(R.layout.view1_1_1, frameLayout1);
			//layout1_1 = (LinearLayout)findViewById(R.id.view1_1_1);
			//layout1_1.setVisibility(View.VISIBLE);
			
		
		}
		*/
	}
}