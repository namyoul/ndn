package me.croute.example.activitygroup.base;

import android.app.Activity;
import android.app.ActivityGroup;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class BaseActivityGroup extends ActivityGroup
{
	protected String TAG = "BaseActivityGroup";
	public static final String CHILD_ONE 	= "차일드 1";
	public static final String CHILD_TWO 	= "차일드 2";
	public static final String CHILD_THREE = "차일드 3";
	public static final String CHILD_FOUR 	= "차일드 4";
	public static final String CHILD_FIVE 	= "차일드 5";
	
	@Override
	public Activity getCurrentActivity()
	{
		Log.e(TAG, "getCurrentActivity");
		return super.getCurrentActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TAG = getClassName(getClass());
		
		Log.e(TAG, "onCreate");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.e(TAG, "onDestroy");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Log.e(TAG, "onPause");
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Log.e(TAG, "onResume");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.e(TAG, "onSaveInstanceState");
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Log.e(TAG, "onStop");
	}

	protected String getClassName(Class<?> cls)
	{
		String FQClassName = cls.getName();
		int firstChar = FQClassName.lastIndexOf('.') + 1;
			
		if(firstChar > 0)
		{
			FQClassName = FQClassName.substring(firstChar);
		}
			
		return FQClassName;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		Log.e(TAG, "onKeyDown");
		return super.onKeyDown(keyCode, event);
	}
}
