package com.ndn.menurandom;

import com.ndn.menurandom.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

public class MenuSlideView extends HorizontalScrollView {
	
	private Context mContext;
	private int mScreenWidth;
	
	//Gesture;
	private GestureDetector mGesture;
	private GestureDetector.OnGestureListener mGesturesListener = new OnGestureListener(){

		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			Log.d("ROSA", "start onDown");
			return false;
		}

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.d("ROSA", "start onFling");
			// TODO Auto-generated method stub
			
			if(velocityX > 0){
				smoothScrollTo(0,0);
			}else{
				smoothScrollTo(mScreenWidth,0);
			}
			
			
			return false;
		}

		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			Log.d("ROSA", "start onLongPress");
			
		}

		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			Log.d("ROSA", "start onScroll");
			
			smoothScrollBy((int)distanceX, 0);
			
			return false;
		}

		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			Log.d("ROSA", "start onShowPress");
			
		}

		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			Log.d("ROSA", "start onSingleTapUp");
			
			return false;
		}
		
		
		
	};

	public MenuSlideView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		createSubView();
	}
	
	public MenuSlideView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		createSubView();
	}
	
	private void createSubView(){
		mGesture = new GestureDetector(mContext, mGesturesListener);
		
		//scroll view setting
		setHorizontalScrollBarEnabled(false);
		
		//get screen size
		Display display = ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		mScreenWidth = display.getWidth();
		int height = display.getHeight();
		
		//set sub layout
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.menu_layout, null);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, height);
		addView(v,params);
		
	}
	
	public boolean onTouchEvent(MotionEvent ev){
		mGesture.onTouchEvent(ev);
		
		int action = ev.getAction();
		
		switch(action){
		case MotionEvent.ACTION_UP:
			if(getScrollX() < mScreenWidth/2){
				smoothScrollTo(0,0);
			}else{
				smoothScrollTo(mScreenWidth,0);
			}
			break;
		
		}
		
		return true;
	}

}
