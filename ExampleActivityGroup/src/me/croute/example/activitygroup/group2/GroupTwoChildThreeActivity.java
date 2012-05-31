package me.croute.example.activitygroup.group2;

import me.croute.example.activitygroup.base.BaseActivity;
import me.croute.example.activitygroup.base.BaseActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GroupTwoChildThreeActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String text = "그룹 2 / 차일드 3<br>" +
				"-> 그룹 2 / 차일드 5 : 웹뷰";
		
		Button button = new Button(this);
		button.setText(Html.fromHtml(text));
		
		setContentView(button);
		
		button.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				GroupTwoParentActivity parent = (GroupTwoParentActivity) getParent();
				parent.setContentView(BaseActivityGroup.CHILD_FIVE);
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

	@Override
	public void setIntent(Intent newIntent)
	{
		super.setIntent(newIntent);
		Log.e(TAG, "setIntent");
		onNewIntent(newIntent);
	}

//	@Override
//	protected void onNewIntent(Intent intent)
//	{
//		super.onNewIntent(intent);
//		String text = intent.getStringExtra("text");
//		final String childId = intent.getStringExtra("childId");
//		final String callerId = intent.getStringExtra("callerId");
//		final String destId = intent.getStringExtra("destId");
//		
//		if(text != null)
//		{
//			Button button = new Button(this);
//			button.setText(Html.fromHtml(text));
//			
//			setContentView(button);
//			
//			button.setOnClickListener(new OnClickListener()
//			{
//				
//				@Override
//				public void onClick(View v)
//				{
//					String text = "그룹 2 / " + childId +"<br>" +
//							"-> 그룹 2 / " + destId;
//					
//					GroupTwoParentActivity parent = (GroupTwoParentActivity) getParent();
//					
//					Intent newIntent = parent.getIntentWithChildId(childId);
//					newIntent.putExtra("text", text);
//					newIntent.putExtra("childId", destId);
//					newIntent.putExtra("callerId", BaseActivityGroup.CHILD_THREE);
//					newIntent.putExtra("destId", callerId);
//					
//					parent.setContentView(childId, newIntent);
//				}
//			});
//		}
//	}
}
