package me.croute.example.activitygroup;

import me.croute.example.activitygroup.group1.GroupOneParentActivity;
import me.croute.example.activitygroup.group2.GroupTwoParentActivity;
import me.croute.example.activitygroup.group3.GroupThreeParentActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class ExampleActivityGroupActivity extends TabActivity
{
	private TabHost mHost;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mHost = getTabHost();
		
		// 3°³ÅÇÀ» Ãß°¡ÇÑ´Ù.
		addTab("ÅÇ1", R.drawable.button_zero, GroupOneParentActivity.class);
		addTab("ÅÇ2", R.drawable.button_one, GroupTwoParentActivity.class);
		addTab("ÅÇ3", R.drawable.button_two, GroupThreeParentActivity.class);
		
		mHost.setCurrentTab(0);
	}
	
	/**
	 * ÅÇÀ» Ãß°¡ÇÑ´Ù.
	 * 
	 * @param id ÅÇ¾ÆÀÌµð(String)
	 * @param resource ÅÇ¹öÆ° ¸®¼Ò½º
	 * @param activity ÅÇ ÄÁÅÙÆ® ¾×Æ¼ºñÆ¼
	 */
	private void addTab(String id, int resource, Class<?> activity)
	{
		View tabButton = getLayoutInflater().inflate(R.layout.tab_button, null);
		tabButton.setBackgroundResource(resource);
		
		Intent intent = new Intent(this, activity);
		
		TabSpec spec = mHost.newTabSpec(id);
		spec.setIndicator(tabButton);
		spec.setContent(intent);
		
		mHost.addTab(spec);
	}
}