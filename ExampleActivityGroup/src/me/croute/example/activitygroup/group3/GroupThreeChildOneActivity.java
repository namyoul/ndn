package me.croute.example.activitygroup.group3;

import me.croute.example.activitygroup.base.BaseActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;

public class GroupThreeChildOneActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String text = "�׷� 3 / ���ϵ� 1<br>" +
				"-> �׷� 3 / ���ϵ� 2";
		
		Button button = new Button(this);
		button.setText(Html.fromHtml(text));
		
		setContentView(button);
	}
}
