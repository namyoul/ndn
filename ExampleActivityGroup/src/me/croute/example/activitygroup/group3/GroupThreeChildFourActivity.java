package me.croute.example.activitygroup.group3;

import me.croute.example.activitygroup.base.BaseActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;

public class GroupThreeChildFourActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String text = "그룹 1 / 차일드 4<br>" +
				"-> 그룹 1 / 차일드 1";
		
		Button button = new Button(this);
		button.setText(Html.fromHtml(text));
		
		setContentView(button);
	}
}
