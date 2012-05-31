package me.croute.example.activitygroup.group1;

import me.croute.example.activitygroup.base.BaseActivity;
import me.croute.example.activitygroup.base.BaseActivityGroup;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GroupOneChildTwoActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String text = "�׷� 1 / ���ϵ� 2<br>" +
				"-> �׷� 1 / ���ϵ� 3";
		
		Button button = new Button(this);
		button.setText(Html.fromHtml(text));
		
		setContentView(button);
		
		button.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				GroupOneParentActivity parent = (GroupOneParentActivity) getParent();
				parent.setContentView(BaseActivityGroup.CHILD_THREE);
			}
		});
	}
}
