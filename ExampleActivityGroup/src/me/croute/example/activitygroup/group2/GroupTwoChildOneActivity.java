package me.croute.example.activitygroup.group2;

import me.croute.example.activitygroup.base.BaseActivity;
import me.croute.example.activitygroup.base.BaseActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GroupTwoChildOneActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String text = "그룹 2 / 차일드 1<br>" +
				"-> 그룹 2 / 차일드 2";
		
		Button button = new Button(this);
		button.setText(Html.fromHtml(text));
		
		setContentView(button);
		
		button.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				String text = "그룹 2 / 차일드 2<br>" +
						"-> 그룹 2 / 차일드 4";
				
				GroupTwoParentActivity parent = (GroupTwoParentActivity) getParent();
				
				Intent intent = parent.getIntentWithChildId(BaseActivityGroup.CHILD_TWO);
				intent.putExtra("text", text);
				intent.putExtra("childId", BaseActivityGroup.CHILD_FOUR);
				intent.putExtra("callerId", BaseActivityGroup.CHILD_ONE);
				intent.putExtra("destId", BaseActivityGroup.CHILD_THREE);
				
				parent.setContentView(BaseActivityGroup.CHILD_TWO, intent);
			}
		});
		
	}

	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onNewIntent(Intent intent)
	{
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart()
	{
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onUserInteraction()
	{
		// TODO Auto-generated method stub
		super.onUserInteraction();
	}

	@Override
	protected void onUserLeaveHint()
	{
		// TODO Auto-generated method stub
		super.onUserLeaveHint();
	}

	@Override
	protected String getClassName(Class<?> cls)
	{
		// TODO Auto-generated method stub
		return super.getClassName(cls);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
	
}
