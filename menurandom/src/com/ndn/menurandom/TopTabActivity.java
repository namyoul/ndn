package com.ndn.menurandom;

import android.app.TabActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class TopTabActivity extends TabActivity {
    /** Called when the activity is first created. */
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initializeTab();
    }
    
    private void initializeTab()
    {
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
    }
    
    protected void setSelectTab(int index)
    {
    	TabHost tabHost = getTabHost();
    	tabHost.setCurrentTab(index);
    }
 	

    
}
