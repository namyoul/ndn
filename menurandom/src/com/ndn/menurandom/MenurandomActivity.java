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
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TabHost tabHost = getTabHost();
        
        final TabSpec tabSpec1 = tabHost.newTabSpec("Tab1").setIndicator("湛 腰属 吐");        
        tabSpec1.setContent(R.id.tab1);
        tabHost.addTab(tabSpec1);
        
        TabSpec tabSpec2 = tabHost.newTabSpec("Tab2").setIndicator("砧 腰属 吐");
        tabSpec2.setContent(R.id.tab2);
        tabHost.addTab(tabSpec2);
        
        Drawable img = getResources().getDrawable(R.drawable.star);
        TabSpec tabSpec3 = tabHost.newTabSpec("Tab3").setIndicator("室 腰属 吐", img);
        tabSpec3.setContent(R.id.tab3);
        tabHost.addTab(tabSpec3);
        
        tabHost.setCurrentTab(0);
        
        
        ImageButton button = (ImageButton)findViewById(R.id.First_ImgBtn1 );
        button.setTag(FIRST_BUTTON);
        button.setOnClickListener(this);
        
        ImageButton button2 = (ImageButton) findViewById(R.id.Secon_ImgBtn1);
        button2.setTag(SECOND_BUTTON);
        
        
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
		if(v.getTag() == FIRST_BUTTON)
		{
			
			FrameLayout frameLayout = (FrameLayout)findViewById(R.id.tab1);
			
			LinearLayout layout = (LinearLayout)findViewById(R.id.FirstView);
			layout.setVisibility(View.GONE);
			
			LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View newLayout = inflater.inflate(R.layout.view_1_1, frameLayout);
			
			
			
			
			
			
			//ImageButton button = (ImageButton)newLayout.findViewById(R.id.imageButton1);
			//button.setTag(SECOND_BUTTON);
			//button.setOnClickListener(this);
			
		}
		else if(v.getTag() == SECOND_BUTTON) {
			Toast toast = Toast.makeText(this, "せせせせせせ", 1000);
			toast.show();
		}
		if(v.getTag() == "korea"){
			FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.tab1);
			
			LinearLayout layout_1_1 = (LinearLayout)findViewById(R.id.View_1_1);
			layout_1_1.setVisibility(View.GONE);
			
			layout_1_1 = (LinearLayout)findViewById(R.id.view_1_1_1);
			layout_1_1.setVisibility(View.VISIBLE);
			
		}
	}
}