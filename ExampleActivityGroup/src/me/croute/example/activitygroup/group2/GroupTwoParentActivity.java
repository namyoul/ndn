package me.croute.example.activitygroup.group2;

import java.util.Stack;

import me.croute.example.activitygroup.base.BaseActivityGroup;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

public class GroupTwoParentActivity extends BaseActivityGroup
{
private LocalActivityManager lam;
	
	private View mVChildOne;
	private View mVChildTwo;
	private View mVChildThree;
	private View mVChildFour;
	private View mVChildFive;
	
	private Stack<View> mStack; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		lam = getLocalActivityManager();
		mStack = new Stack<View>();
		
		mVChildFour = lam.startActivity(CHILD_FOUR, new Intent(this, GroupTwoChildFourActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		mVChildThree = lam.startActivity(CHILD_THREE, new Intent(this, GroupTwoChildThreeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		mVChildTwo = lam.startActivity(CHILD_TWO, new Intent(this, GroupTwoChildTwoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		mVChildOne = lam.startActivity(CHILD_ONE, new Intent(this, GroupTwoChildOneActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		mVChildFive = lam.startActivity(CHILD_FIVE, new Intent(this, GroupTwoChildFiveActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		
		setContentView(CHILD_ONE);
	}
	
	public void setContentView(String childId)
	{	
		if(childId.equals(CHILD_ONE))
		{
			mStack.push(mVChildOne);
			setContentView(mStack.peek());
		}
		else if(childId.equals(CHILD_TWO))
		{
			mStack.push(mVChildTwo);
			setContentView(mStack.peek());
		}
		else if(childId.equals(CHILD_THREE))
		{
			mStack.push(mVChildThree);
			setContentView(mStack.peek());
		}
		else if(childId.equals(CHILD_FOUR))
		{
			mStack.push(mVChildFour);
			setContentView(mStack.peek());
		}
		else if(childId.equals(CHILD_FIVE))
		{
			mStack.push(mVChildFive);
			setContentView(mStack.peek());
		}
	}
	
	public void setContentView(String childId, Intent newIntent)
	{
		Activity activity = lam.getActivity(childId);
		activity.setIntent(newIntent);
		setContentView(childId);
	}
	
	public Intent getIntentWithChildId(String childId)
	{
		Activity activity = lam.getActivity(childId);
		return activity.getIntent();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
			if(mStack.peek() == mVChildFive)
			{
				GroupTwoChildFiveActivity webViewActivity = (GroupTwoChildFiveActivity) lam.getActivity(CHILD_FIVE);
				if(webViewActivity.onKeyDown(keyCode, event))
				{
					return true;
				}
			}
			
			mStack.pop();
			if(mStack.isEmpty())
			{
				return false;
			}
			else
			{
				setContentView(mStack.peek());
			}
		}
		
		return true;
	}
	
	
}
