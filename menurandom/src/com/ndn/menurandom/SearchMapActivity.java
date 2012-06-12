package com.ndn.menurandom;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import com.ndn.menurandom.SearchMapPOIflagType;

public class SearchMapActivity extends NMapActivity {
	// set my API key which is registered for com.ndn.menurandom package
	private static final String NAVER_MAP_KEY = "749a7f89c8934b5d50a24f3a9ca8af01";
	private static String SEARCH_MENU = "갈비탕";
	private static final int SEARCH_INDEX = 3;
	
	private MapContainerView mMapContainerView;
	private NMapController mMapController;
	private NMapView mMapView;
	
	private NMapOverlayManager mOverlayManager;
	private SearchMapResourceProvider mMapViewerResourceProvider;
	
	private NMapLocationManager mMapLocationManager;
	
	
	
	////////////////////////////////////////////////////////////////////////////
	// for restoreInstance & saveInstance 
	private static NGeoPoint NMAP_LOCATION_DEFAULT = new NGeoPoint(126.978371, 37.5666091);
	private static final int NMAP_ZOOMLEVEL_DEFAULT = 10;
	private static final String KEY_ZOOM_LEVEL = "MainTab3Activity.zoomLevel";
	private static final String KEY_CENTER_LONGITUDE = "MainTab3Activity.centerLongitudeE6";
	private static final String KEY_CENTER_LATITUDE = "MainTab3Activity.centerLatitudeE6";

	private SharedPreferences mPreferences;
	
	
	
	////////////////////////////////////////////////////////////////////////////
	// for POIitem
	private NMapPOIdataOverlay mPOIdataOverlay;
	private NMapPOIitem mPOIitem;
	private SlidingDrawer mSlidingDrawer;
	
	
	
	////////////////////////////////////////////////////////////////////////////
	// for searching restaurant
	private SearchMapParser mSearchMapParser;
	private int searchedRestaurantIndex;
	private RestaurantData[] restaurantData = new RestaurantData[SEARCH_INDEX];
	private NGeoPoint mMyGeoPoint;
	private String currentAddress;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.e("NHK", "========================================================================================");
		Log.e("NHK", "onCreate!");
		
		// create map view
		mMapView = new NMapView(this);
		mMapView.setApiKey(NAVER_MAP_KEY);
		mMapContainerView = new MapContainerView(this);
		
		initializeView();
		initializeNMap();
		initializeSearch();
		
		setContentView(mMapContainerView);
	}
	
	protected void onStart() {
		super.onStart();
		Log.e("NHK", "onStart!");
	
		startMyLocation();
		searchRestaurant();
	}

	protected void onResume() {
		super.onResume();
		Log.e("NHK", "onResume!");
		restoreInstanceState();
	}
	
	@Override
	protected void onDestroy() {
		Log.e("NHK", "onDestroy");
		// save map view state such as map center position and zoom level.
		saveInstanceState();

		super.onDestroy();
	}

	private void initializeView() {
		// add NMapView
		mMapContainerView.addView(mMapView);
		
//		mSlidingDrawer = new SlidingDrawer(this, new AttributeSet()
//		mMapContainerView.addView(mSlidingDrawer);
	}
	
	private void initializeNMap() {
		mMapView.setClickable(true);
		mMapView.setEnabled(true);
		mMapView.setFocusable(true);
		mMapView.setFocusableInTouchMode(true);
		mMapView.requestFocus();

		// register listener for map state changes
//		mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
//		mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);
//		mMapView.setOnMapViewDelegate(onMapViewTouchDelegate);
	
		// use map controller to zoom in/out, pan and set map center, zoom level etc.
		mMapController = mMapView.getMapController();
	
		// use built in zoom controls
		NMapView.LayoutParams lp = new NMapView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, NMapView.LayoutParams.BOTTOM_RIGHT);
		mMapView.setBuiltInZoomControls(true, lp);
	
		// create resource provider
		mMapViewerResourceProvider = new SearchMapResourceProvider(this);
	
		// create overlay manager
		mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);
		// register callout overlay listener to customize it.
		mOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener);

		// set data provider listener
		super.setMapDataProviderListener(onDataProviderListener);
		
		// location manager
		mMapLocationManager = new NMapLocationManager(this);
	}
	
	private void initializeSearch() {
		mSearchMapParser = new SearchMapParser();
		for (int i=0; i<SEARCH_INDEX; i++)
			restaurantData[i] = new RestaurantData();
	}
	
	private void startMyLocation() {
		boolean is = mMapLocationManager.isMyLocationEnabled();
		if (is) {
			
		} else {
			boolean isMyLocationEnabled = mMapLocationManager.enableMyLocation(false);
			
			if ( !isMyLocationEnabled ) {
				Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(goToSettings);
				
				return ;
			}
		}

		mMapLocationManager.setOnLocationChangeListener(new NMapLocationManager.OnLocationChangeListener() {
			public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {
				mMyGeoPoint = myLocation;
//				mMapController.setMapCenter(myLocation);
				findPlacemarkAtLocation(mMyGeoPoint.getLongitude(), mMyGeoPoint.getLatitude());

				stopMyLocation();
				return true;
			}

			public void onLocationUnavailableArea(NMapLocationManager arg0,	NGeoPoint arg1) {
				Toast.makeText(SearchMapActivity.this, "Your current location is temporarily unavailable.", Toast.LENGTH_LONG).show();
				stopMyLocation();
			}

			public void onLocationUpdateTimeout(NMapLocationManager arg0) {
				Toast.makeText(SearchMapActivity.this, "Your current location is unavailable area.", Toast.LENGTH_LONG).show();
				stopMyLocation();
			}
		});
	}

	private void stopMyLocation() {
		mMapLocationManager.disableMyLocation();
	}

	private void displayRestaurantItem() {
		int markerId = SearchMapPOIflagType.PIN;

		NMapPOIdata poiData = new NMapPOIdata(searchedRestaurantIndex, mMapViewerResourceProvider);
		poiData.beginPOIdata(searchedRestaurantIndex);
		for(int i=0; i<searchedRestaurantIndex; i++)
			poiData.addPOIitem(Double.parseDouble(restaurantData[i].sMapX), Double.parseDouble(restaurantData[i].sMapY), restaurantData[i].sTitle, markerId, 0);
		poiData.endPOIdata();
		
		NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
		poiDataOverlay.selectPOIitem(0, true);
	}
	
	private void searchRestaurant() {
		new Thread(new Runnable(){
			public void run(){
				while( currentAddress == null ) {
					try{
						Thread.sleep(3000);
					} catch (Exception e) {
					}
				}
				searchedRestaurantIndex = mSearchMapParser.search(restaurantData, currentAddress+"+"+SEARCH_MENU, SEARCH_INDEX, 1);
				displayRestaurantItem();
			}
		}).start();
	}
	
	private final NMapOverlayManager.OnCalloutOverlayListener onCalloutOverlayListener = new NMapOverlayManager.OnCalloutOverlayListener() {
		public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay itemOverlay, NMapOverlayItem overlayItem, Rect itemBounds) {
			// handle overlapped items
			if (itemOverlay instanceof NMapPOIdataOverlay) {
				NMapPOIdataOverlay poiDataOverlay = (NMapPOIdataOverlay)itemOverlay;

				// check if it is selected by touch event
				if (!poiDataOverlay.isFocusedBySelectItem()) {
					int countOfOverlappedItems = 1;

					NMapPOIdata poiData = poiDataOverlay.getPOIdata();
					for (int i = 0; i < poiData.count(); i++) {
						NMapPOIitem poiItem = poiData.getPOIitem(i);

						// skip selected item
						if (poiItem == overlayItem) {
							
							
							
							continue;
						}

						// check if overlapped or not
						if (Rect.intersects(poiItem.getBoundsInScreen(), overlayItem.getBoundsInScreen())) {
							countOfOverlappedItems++;
						}
					}

					if (countOfOverlappedItems > 1) {
						String text = countOfOverlappedItems + " overlapped items for " + overlayItem.getTitle();
						Toast.makeText(SearchMapActivity.this, text, Toast.LENGTH_LONG).show();
						return null;
					}
				}
			}

			return new NMapCalloutCustomOverlay(itemOverlay, overlayItem, itemBounds, mMapViewerResourceProvider);			
		}

	};
	
	/* NMapDataProvider Listener */
	private final NMapActivity.OnDataProviderListener onDataProviderListener = new NMapActivity.OnDataProviderListener() {
		
		public void onReverseGeocoderResponse(NMapPlacemark placeMark, NMapError errInfo) {
			currentAddress = placeMark.doName + "+" + placeMark.siName + "+" + placeMark.dongName;

			if (errInfo != null) {
				Log.e("NHK", "Failed to findPlacemarkAtLocation: error=" + errInfo.toString());

				Toast.makeText(SearchMapActivity.this, errInfo.toString(), Toast.LENGTH_LONG).show();
				return;
			}
		}
	};

	private void restoreInstanceState() {
		mPreferences = getPreferences(MODE_PRIVATE);

		int longitudeE6 = mPreferences.getInt(KEY_CENTER_LONGITUDE,	NMAP_LOCATION_DEFAULT.getLongitudeE6());
		int	latitudeE6 = mPreferences.getInt(KEY_CENTER_LATITUDE, NMAP_LOCATION_DEFAULT.getLatitudeE6());
		int level = mPreferences.getInt(KEY_ZOOM_LEVEL, NMAP_ZOOMLEVEL_DEFAULT);

		mMapController.setMapCenter(new NGeoPoint(longitudeE6, latitudeE6),	level);
	}

	private void saveInstanceState() {
		if (mPreferences == null)
			return;

		NGeoPoint center = mMapController.getMapCenter();
		int level = mMapController.getZoomLevel();

		SharedPreferences.Editor edit = mPreferences.edit();

		edit.putInt(KEY_CENTER_LONGITUDE, center.getLongitudeE6());
		edit.putInt(KEY_CENTER_LATITUDE, center.getLatitudeE6());
		edit.putInt(KEY_ZOOM_LEVEL, level);

		edit.commit();
	}
	
	private class MapContainerView extends ViewGroup {

		public MapContainerView(Context context) {
			super(context);
		}

		@Override
		protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
			final int width = getWidth();
			final int height = getHeight();
			final int count = getChildCount();
			for (int i = 0; i < count; i++) {
				final View view = getChildAt(i);
				final int childWidth = view.getMeasuredWidth();
				final int childHeight = view.getMeasuredHeight();
				final int childLeft = (width - childWidth) / 2;
				final int childTop = (height - childHeight) / 2;

				view.layout(childLeft, childTop, childLeft+childWidth, childTop+childHeight);
			}

			if (changed) {
				mOverlayManager.onSizeChanged(width, height);
			}
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			int w = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
			int h = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
			int sizeSpecWidth = widthMeasureSpec;
			int sizeSpecHeight = heightMeasureSpec;

			final int count = getChildCount();
			for (int i = 0; i < count; i++) {
				final View view = getChildAt(i);

				if (view instanceof NMapView) {
					if (mMapView.isAutoRotateEnabled()) {
						int diag = (((int) (Math.sqrt(w * w + h * h)) + 1) / 2 * 2);
						sizeSpecWidth = MeasureSpec.makeMeasureSpec(diag, MeasureSpec.EXACTLY);
						sizeSpecHeight = sizeSpecWidth;
					}
				}

				view.measure(sizeSpecWidth, sizeSpecHeight);
			}
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
}



	
	

	

	


//	/////////////////////////////////////////////////////////////////////////////////////////////
//	// Global variable with Searching restaurant
//	
//
//	@Override
//	protected void onStop() {
//		Log.e(LOG_TAG, "onStop!");
//		stopMyLocation();
//
//		super.onStop();
//	}
//
//	/* MapView State Change Listener 
//	private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {
//
//		public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
//
//			if (errorInfo == null) { // success
//				// restore map view state such as map center position and zoom
//				// level.
////				restoreInstanceState();
//
//			} else { // fail
//				Log.e(LOG_TAG, "onFailedToInitializeWithError: " + errorInfo.toString());
//
//				Toast.makeText(MainTab3Activity.this, errorInfo.toString(),	Toast.LENGTH_LONG).show();
//			}
//		}
//
//		public void onAnimationStateChange(NMapView mapView, int animType, int animState) {
//			if (DEBUG) {
////				Log.i(LOG_TAG, "onAnimationStateChange: animType=" + animType + ", animState=" + animState);
//			}
//		}
//
//		public void onMapCenterChange(NMapView mapView, NGeoPoint center) {
//			if (DEBUG) {
////				Log.i(LOG_TAG, "onMapCenterChange: center=" + center.toString());
//			}
//		}
//
//		public void onZoomLevelChange(NMapView mapView, int level) {
//			if (DEBUG) {
//				Log.i(LOG_TAG, "onZoomLevelChange: level=" + level);
//			}
//		}
//
//		public void onMapCenterChangeFine(NMapView mapView) {
//
//		}
//	};
//
//	private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {
//
//		public void onLongPress(NMapView mapView, MotionEvent ev) {
//
//		}
//
//		public void onLongPressCanceled(NMapView mapView) {
//
//		}
//
//		public void onSingleTapUp(NMapView mapView, MotionEvent ev) {
//
//		}
//
//		public void onTouchDown(NMapView mapView, MotionEvent ev) {
//
//		}
//
//		public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {
//		}
//
//	};
//
//	private final NMapView.OnMapViewDelegate onMapViewTouchDelegate = new NMapView.OnMapViewDelegate() {
//
//		public boolean isLocationTracking() {
//			if (mMapLocationManager != null) {
//				if (mMapLocationManager.isMyLocationEnabled()) {
//					return mMapLocationManager.isMyLocationFixed();
//				}
//			}
//			return false;
//		}
//
//	};
//
//	/* POI data State Change Listener 
//	private final NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {
//
//		public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
//			if (DEBUG) {
//				Log.i(LOG_TAG, "onCalloutClick: title=" + item.getTitle());
//			}
//
//			// [[TEMP]] handle a click event of the callout
//			Toast.makeText(MainTab3Activity.this, "onCalloutClick: " + item.getTitle(), Toast.LENGTH_LONG).show();
//		}
//
//		public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
//			if (DEBUG) {
//				if (item != null) {
//					// item selected
//					// Log.e(LOG_TAG, "onFocusChanged: " + item.toString());
//				} else {
//					// no item selected
//					// Log.e(LOG_TAG, "onFocusChanged: ");
//				}
//			}
//		}
//	};
//
//	private final NMapPOIdataOverlay.OnFloatingItemChangeListener onPOIdataFloatingItemChangeListener = new NMapPOIdataOverlay.OnFloatingItemChangeListener() {
//
//		public void onPointChanged(NMapPOIdataOverlay poiDataOverlay, NMapPOIitem item) {
//			NGeoPoint point = item.getPoint();
//
//			if (DEBUG) {
//				Log.i(LOG_TAG, "onPointChanged: point=" + point.toString());
//			}
//
//			findPlacemarkAtLocation(point.longitude, point.latitude);
//
//			item.setTitle(null);
//
//		}
//	};
//
//	/* Local Functions 
//
//
//	/* Menus 
//	private static final int MENU_ITEM_CLEAR_MAP = 10;
//	private static final int MENU_ITEM_MAP_MODE = 20;
//	private static final int MENU_ITEM_MAP_MODE_SUB_VECTOR = MENU_ITEM_MAP_MODE + 1;
//	private static final int MENU_ITEM_MAP_MODE_SUB_SATELLITE = MENU_ITEM_MAP_MODE + 2;
//	private static final int MENU_ITEM_MAP_MODE_SUB_HYBRID = MENU_ITEM_MAP_MODE + 3;
//	private static final int MENU_ITEM_MAP_MODE_SUB_TRAFFIC = MENU_ITEM_MAP_MODE + 4;
//	private static final int MENU_ITEM_MAP_MODE_SUB_BICYCLE = MENU_ITEM_MAP_MODE + 5;
//	private static final int MENU_ITEM_ZOOM_CONTROLS = 30;
//	private static final int MENU_ITEM_MY_LOCATION = 40;
//
//	private static final int MENU_ITEM_TEST_MODE = 50;
//	private static final int MENU_ITEM_TEST_PATH_DATA = MENU_ITEM_TEST_MODE + 2;
//	private static final int MENU_ITEM_TEST_FLOATING_DATA = MENU_ITEM_TEST_MODE + 3;
//	private static final int MENU_ITEM_TEST_AUTO_ROTATE = MENU_ITEM_TEST_MODE + 4;
//
//	private static final int MENU_ITEM_RETURN = 60;
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		super.onCreateOptionsMenu(menu);
//
//		MenuItem menuItem = null;
//		SubMenu subMenu = null;
//
//		menuItem = menu.add(Menu.NONE, MENU_ITEM_CLEAR_MAP, Menu.CATEGORY_SECONDARY, "Clear Map");
//		menuItem.setAlphabeticShortcut('c');
//		menuItem.setIcon(android.R.drawable.ic_menu_revert);
//
//		subMenu = menu.addSubMenu(Menu.NONE, MENU_ITEM_MAP_MODE, Menu.CATEGORY_SECONDARY, "Map mode");
//		subMenu.setIcon(android.R.drawable.ic_menu_mapmode);
//
//		menuItem = subMenu.add(0, MENU_ITEM_MAP_MODE_SUB_VECTOR, Menu.NONE, "Standard");
//		menuItem.setAlphabeticShortcut('m');
//		menuItem.setCheckable(true);
//		menuItem.setChecked(false);
//
//		menuItem = subMenu.add(0, MENU_ITEM_MAP_MODE_SUB_SATELLITE, Menu.NONE, "Satellite");
//		menuItem.setAlphabeticShortcut('s');
//		menuItem.setCheckable(true);
//		menuItem.setChecked(false);
//
//		menuItem = subMenu.add(0, MENU_ITEM_MAP_MODE_SUB_HYBRID, Menu.NONE, "Hybrid");
//		menuItem.setAlphabeticShortcut('h');
//		menuItem.setCheckable(true);
//		menuItem.setChecked(false);
//
//		menuItem = subMenu.add(0, MENU_ITEM_MAP_MODE_SUB_TRAFFIC, Menu.NONE, "Traffic");
//		menuItem.setAlphabeticShortcut('t');
//		menuItem.setCheckable(true);
//		menuItem.setChecked(false);
//
//		menuItem = subMenu.add(0, MENU_ITEM_MAP_MODE_SUB_BICYCLE, Menu.NONE, "Bicycle");
//		menuItem.setAlphabeticShortcut('b');
//		menuItem.setCheckable(true);
//		menuItem.setChecked(false);
//
//		menuItem = menu.add(0, MENU_ITEM_ZOOM_CONTROLS,	Menu.CATEGORY_SECONDARY, "Zoom Controls");
//		menuItem.setAlphabeticShortcut('z');
//		menuItem.setIcon(android.R.drawable.ic_menu_zoom);
//
//		menuItem = menu.add(0, MENU_ITEM_MY_LOCATION, Menu.CATEGORY_SECONDARY, "My Location");
//		menuItem.setAlphabeticShortcut('l');
//		menuItem.setIcon(android.R.drawable.ic_menu_mylocation);
//
//		subMenu = menu.addSubMenu(Menu.NONE, MENU_ITEM_TEST_MODE, Menu.CATEGORY_SECONDARY, "Test mode");
//		subMenu.setIcon(android.R.drawable.ic_menu_more);
//
//		menuItem = subMenu.add(0, MENU_ITEM_TEST_PATH_DATA, Menu.NONE, "Test Path data");
//		menuItem.setAlphabeticShortcut('t');
//
//		menuItem = subMenu.add(0, MENU_ITEM_TEST_FLOATING_DATA, Menu.NONE, "Test Floating data");
//		menuItem.setAlphabeticShortcut('f');
//
//		menuItem = subMenu.add(0, MENU_ITEM_TEST_AUTO_ROTATE, Menu.NONE, "Test Auto Rotate");
//		menuItem.setAlphabeticShortcut('a');
//
//		menuItem = menu.add(0, MENU_ITEM_RETURN, Menu.CATEGORY_SECONDARY, "RETURN APP");
//		menuItem.setAlphabeticShortcut('r');
//
//		return true;
//	}
//
//	@Override
//	public boolean onPrepareOptionsMenu(Menu pMenu) {
//		super.onPrepareOptionsMenu(pMenu);
//
//		int viewMode = mMapController.getMapViewMode();
//		boolean isTraffic = mMapController.getMapViewTrafficMode();
//		boolean isBicycle = mMapController.getMapViewBicycleMode();
//
//		pMenu.findItem(MENU_ITEM_CLEAR_MAP).setEnabled((viewMode != NMapView.VIEW_MODE_VECTOR) || isTraffic || mOverlayManager.sizeofOverlays() > 0);
//		pMenu.findItem(MENU_ITEM_MAP_MODE_SUB_VECTOR).setChecked(viewMode == NMapView.VIEW_MODE_VECTOR);
//		pMenu.findItem(MENU_ITEM_MAP_MODE_SUB_SATELLITE).setChecked(viewMode == NMapView.VIEW_MODE_SATELLITE);
//		pMenu.findItem(MENU_ITEM_MAP_MODE_SUB_HYBRID).setChecked(viewMode == NMapView.VIEW_MODE_HYBRID);
//		pMenu.findItem(MENU_ITEM_MAP_MODE_SUB_TRAFFIC).setChecked(isTraffic);
//		pMenu.findItem(MENU_ITEM_MAP_MODE_SUB_BICYCLE).setChecked(isBicycle);
//
//		return true;
//	}
//
//	/**
//	 * Invoked when the user selects an item from the Menu.
//	 * 
//	 * @param item
//	 *            the Menu entry which was selected
//	 * @return true if the Menu item was legit (and we consumed it), false
//	 *         otherwise
//	 
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//
//		switch (item.getItemId()) {
//		case MENU_ITEM_CLEAR_MAP:
//			mMapController.setMapViewMode(NMapView.VIEW_MODE_VECTOR);
//			mMapController.setMapViewTrafficMode(false);
//			mMapController.setMapViewBicycleMode(false);
//
//			mOverlayManager.clearOverlays();
//
//			return true;
//
//		case MENU_ITEM_MAP_MODE_SUB_VECTOR:
//			mMapController.setMapViewMode(NMapView.VIEW_MODE_VECTOR);
//			return true;
//
//		case MENU_ITEM_MAP_MODE_SUB_SATELLITE:
//			mMapController.setMapViewMode(NMapView.VIEW_MODE_SATELLITE);
//			return true;
//
//		case MENU_ITEM_MAP_MODE_SUB_HYBRID:
//			mMapController.setMapViewMode(NMapView.VIEW_MODE_HYBRID);
//			return true;
//
//		case MENU_ITEM_MAP_MODE_SUB_TRAFFIC:
//			mMapController.setMapViewTrafficMode(!mMapController.getMapViewTrafficMode());
//			return true;
//
//		case MENU_ITEM_MAP_MODE_SUB_BICYCLE:
//			mMapController.setMapViewBicycleMode(!mMapController.getMapViewBicycleMode());
//			return true;
//
//		case MENU_ITEM_ZOOM_CONTROLS:
//			mMapView.displayZoomControls(true);
//			return true;
//
//		case MENU_ITEM_TEST_AUTO_ROTATE:
//			if (mMapView.isAutoRotateEnabled()) {
//				mMapView.setAutoRotateEnabled(false, false);
//
//				mMapContainerView.requestLayout();
//
//				mHnadler.removeCallbacks(mTestAutoRotation);
//			} else {
//
//				mMapView.setAutoRotateEnabled(true, false);
//
//				mMapView.setRotateAngle(30);
//				mHnadler.postDelayed(mTestAutoRotation, AUTO_ROTATE_INTERVAL);
//
//				mMapContainerView.requestLayout();
//			}
//			return true;
//
//		case MENU_ITEM_RETURN:
//			this.finish();
//			return true;
//		}
//
//		return super.onOptionsItemSelected(item);
//	}
//
//	private static final long AUTO_ROTATE_INTERVAL = 2000;
//	private final Handler mHnadler = new Handler();
//	private final Runnable mTestAutoRotation = new Runnable() {
//		public void run() {
//			if (mMapView.isAutoRotateEnabled()) {
//				float degree = (float) Math.random() * 360;
//
//				degree = mMapView.getRoateAngle() + 30;
//
//				mMapView.setRotateAngle(degree);
//
//				mHnadler.postDelayed(mTestAutoRotation, AUTO_ROTATE_INTERVAL);
//			}
//		}
//	};
//
//	/**
//	 * Container view class to rotate map view.
//	 
//
//}


