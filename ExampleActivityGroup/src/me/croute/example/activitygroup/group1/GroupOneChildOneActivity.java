package me.croute.example.activitygroup.group1;

import me.croute.example.activitygroup.base.BaseActivity;
import me.croute.example.activitygroup.base.BaseActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GroupOneChildOneActivity extends BaseActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String text = "그룹 1 / 차일드 1<br>" +
				"-> 그룹 1 / 차일드 2";
		
		Button button = new Button(this);
		button.setText(Html.fromHtml(text));
		
		setContentView(button);
		
		button.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				GroupOneParentActivity parent = (GroupOneParentActivity) getParent();
				parent.setContentView(BaseActivityGroup.CHILD_TWO);
			}
		});
		
	}

	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		
		String text = intent.getStringExtra("text");
		final String childId = intent.getStringExtra("childId");
		
		if(text != null)
		{
			Button button = new Button(this);
			button.setText(Html.fromHtml(text));
			
			setContentView(button);
			
			button.setOnClickListener(new OnClickListener()
			{
				
				public void onClick(View v)
				{
					GroupOneParentActivity parent = (GroupOneParentActivity) getParent();
					parent.setContentView(childId);
				}
			});
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		
		Log.e(TAG, "ONE");
	}

	
}
