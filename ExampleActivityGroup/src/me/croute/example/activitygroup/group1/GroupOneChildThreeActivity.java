package me.croute.example.activitygroup.group1;

import me.croute.example.activitygroup.base.BaseActivity;
import me.croute.example.activitygroup.base.BaseActivityGroup;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GroupOneChildThreeActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String text = "그룹 1 / 차일드 3<br>" +
				"-> 그룹 1 / 차일드 4";
		
		Button button = new Button(this);
		button.setText(Html.fromHtml(text));
		
		setContentView(button);
		
		button.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				GroupOneParentActivity parent = (GroupOneParentActivity) getParent();
				parent.setContentView(BaseActivityGroup.CHILD_FOUR);
			}
		});
	}
}
