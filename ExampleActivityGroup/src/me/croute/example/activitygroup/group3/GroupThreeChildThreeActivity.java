package me.croute.example.activitygroup.group3;

import me.croute.example.activitygroup.base.BaseActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;

public class GroupThreeChildThreeActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String text = "그룹 3 / 차일드 3<br>" +
				"-> 그룹 3 / 차일드 4";
		
		Button button = new Button(this);
		button.setText(Html.fromHtml(text));
		
		setContentView(button);
	}
}
