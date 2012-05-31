package me.croute.example.activitygroup.group3;

import me.croute.example.activitygroup.base.BaseActivityGroup;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroupThreeParentActivity extends BaseActivityGroup
{
private LocalActivityManager lam;
	
	private View mVChildOne;
	private View mVChildTwo;
	private View mVChildThree;
	private View mVChildFour;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		lam = getLocalActivityManager();
		
		mVChildFour = lam.startActivity(CHILD_FOUR, new Intent(this, GroupThreeChildFourActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		mVChildThree = lam.startActivity(CHILD_THREE, new Intent(this, GroupThreeChildThreeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		mVChildTwo = lam.startActivity(CHILD_TWO, new Intent(this, GroupThreeChildTwoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		mVChildOne = lam.startActivity(CHILD_ONE, new Intent(this, GroupThreeChildOneActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	
		setContentView(mVChildOne);
	}
	
	public void setContentView(String childId)
	{	
		if(childId.equals(CHILD_ONE))
		{
			setContentView(mVChildOne);
		}
		else if(childId.equals(CHILD_TWO))
		{
			setContentView(mVChildTwo);
		}
		else if(childId.equals(CHILD_THREE))
		{
			setContentView(mVChildThree);
		}
		else if(childId.equals(CHILD_FOUR))
		{
			setContentView(mVChildFour);
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
}
