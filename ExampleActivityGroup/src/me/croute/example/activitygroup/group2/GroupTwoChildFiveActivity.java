package me.croute.example.activitygroup.group2;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import me.croute.example.activitygroup.R;
import me.croute.example.activitygroup.base.BaseActivity;

public class GroupTwoChildFiveActivity extends BaseActivity
{
	private WebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_two_child_five_activity);
		
		mWebView = (WebView)findViewById(R.id.group_two_child_five_activity_wv_blog);
		final ProgressBar progressBar = (ProgressBar)findViewById(R.id.group_two_child_five_activity_pb_pregress);
		
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new WebViewClient());		
		mWebView.loadUrl("http://croute.me");
		mWebView.setWebChromeClient(new WebChromeClient() 
		{  
			   public void onProgressChanged(WebView view, int progress) 
			   {  
				   if (progress<100)
				   {
					   progressBar.setVisibility(ProgressBar.VISIBLE);
				   }
				   else if (progress==100)
				   {
					   progressBar.setVisibility(ProgressBar.GONE);
				   }
				   progressBar.setProgress(progress);  
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
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
			if(mWebView.canGoBack())
			{
				mWebView.goBack();
				return true;
			}
		}
		
		return false;
	}

	public WebView getmWebView()
	{
		return mWebView;
	}

	public void setmWebView(WebView mWebView)
	{
		this.mWebView = mWebView;
	}
	
}
