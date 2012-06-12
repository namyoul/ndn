package com.ndn.menurandom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

public class SearchMapResourceProvider extends NMapResourceProvider {
	private static final String LOG_TAG = "NMapViewerResourceProvider";
	private static final boolean DEBUG = false;

	private static final Bitmap.Config BITMAP_CONFIG_DEFAULT = Bitmap.Config.ARGB_8888;

	private static final float POI_FONT_OFFSET_ALPHABET = 6.0F;
	private static final Typeface POI_FONT_TYPEFACE = null;//Typeface.DEFAULT_BOLD;

	private final Rect mTempRect = new Rect();
	private final Paint mTextPaint = new Paint();

	public SearchMapResourceProvider(Context context) {
		super(context);

		mTextPaint.setAntiAlias(true);
	}

	/**
	 * Get drawable for markerId at focused state
	 * 
	 * @param markerId unique id for POI or Number icons.
	 * @param focused true for focused state, false otherwise.
	 * @return
	 */
	public Drawable getDrawable(int markerId, boolean focused, NMapOverlayItem item) {
		Drawable marker = null;

		int resourceId = findResourceIdForMarker(markerId, focused);
		if (resourceId > 0) {
			marker = mContext.getResources().getDrawable(resourceId);
		} else {
			resourceId = 4 * markerId;
			if (focused) {
				resourceId += 1;
			}

			marker = getDrawableForMarker(markerId, focused);
		}

		// set bounds
		if (marker != null) {
			setBounds(marker, markerId, item);
		}

		return marker;
	}

	public Bitmap getBitmap(int markerId, boolean focused) {
		Bitmap bitmap = null;

		Drawable marker = getDrawable(markerId, focused, null);
		if (marker != null) {
			bitmap = getBitmap(marker);
		}

		return bitmap;
	}

	public Bitmap getBitmap(Drawable marker) {
		Bitmap bitmap = null;

		if (marker != null) {
			int width = marker.getIntrinsicWidth();
			int height = marker.getIntrinsicHeight();
			bitmap = Bitmap.createBitmap(width, height, BITMAP_CONFIG_DEFAULT);

			marker.setBounds(0, 0, width, height);

			Canvas canvas = new Canvas(bitmap);
			canvas.drawColor(0x00000000);

			marker.draw(canvas);
		}

		return bitmap;
	}

	public Bitmap getBitmap(int resourceId) {
		Bitmap bitmap = null;

		Drawable marker = null;
		if (resourceId > 0) {
			marker = mContext.getResources().getDrawable(resourceId);
		}

		if (marker != null) {
			bitmap = getBitmap(marker);
		}

		return bitmap;
	}

	public Bitmap getBitmapWithNumber(int resourceId, String strNumber, float offsetY, int fontColor, float fontSize) {
		Bitmap bitmap = null;

		Drawable marker = getDrawableWithNumber(resourceId, strNumber, offsetY, fontColor, fontSize);

		if (marker != null) {
			bitmap = getBitmap(marker);
		}

		return bitmap;
	}

	/**
	 * Class to find resource Ids on map view
	 */
	private class ResourceIdsOnMap {

		int markerId;
		int resourceId;
		int resourceIdFocused;

		ResourceIdsOnMap(int markerId, int resourceId, int resourceIdFocused) {
			this.markerId = markerId;
			this.resourceId = resourceId;
			this.resourceIdFocused = resourceIdFocused;
		}
	}

	// Resource Ids for single icons
	private final ResourceIdsOnMap mResourceIdsForMarkerOnMap[] = {
		// Spot, Pin icons
		new ResourceIdsOnMap(SearchMapPOIflagType.PIN, R.drawable.ic_pin_01, R.drawable.ic_pin_02),
		new ResourceIdsOnMap(SearchMapPOIflagType.SPOT, R.drawable.ic_pin_01, R.drawable.ic_pin_02),
	};

	/**
	 * Find resource id corresponding to the markerId.
	 * 
	 * @param markerId marker id for a NMapPOIitem.
	 * @param focused flag to indicated focused or normal state of this marker.
	 * 	
	 * @return resource id for the given markerId.
	 * 
	 * @see SearchMapPOIflagType
	 */
	@Override
	protected int findResourceIdForMarker(int markerId, boolean focused) {
		int resourceId = 0;

		if (DEBUG) {
			Log.i(LOG_TAG, "getResourceIdForMarker: markerId=" + markerId + ", focused=" + focused);
		}

		if (markerId < SearchMapPOIflagType.SINGLE_MARKER_END) {
			resourceId = getResourceIdOnMapView(markerId, focused, mResourceIdsForMarkerOnMap);
			if (resourceId > 0) {
				return resourceId;
			}
		}

		if (markerId >= SearchMapPOIflagType.NUMBER_BASE && markerId < SearchMapPOIflagType.NUMBER_END) { // Direction Number icons

		} else if (markerId >= SearchMapPOIflagType.CUSTOM_BASE && markerId < SearchMapPOIflagType.CUSTOM_END) { // Custom POI icons

		}

		return resourceId;
	}

	/**
	 * 	Set bounds for this marker depending on its shape.  
	 * 
	 */
	@Override
	protected void setBounds(Drawable marker, int markerId, NMapOverlayItem item) {

		// check shape of the marker to set bounds correctly.
		if (SearchMapPOIflagType.isBoundsCentered(markerId)) {
			if (marker.getBounds().isEmpty()) {
				NMapOverlayItem.boundCenter(marker);
			}

			if (null != item) {
				item.setAnchorRatio(0.5f, 0.5f);
			}

		} else {
			if (marker.getBounds().isEmpty()) {
				NMapOverlayItem.boundCenterBottom(marker);
			}

			if (null != item) {
				item.setAnchorRatio(0.5f, 1.0f);
			}
		}
	}


	public Drawable[] getLocationDot() {
		Drawable[] drawable = new Drawable[2];

		drawable[0] = mContext.getResources().getDrawable(R.drawable.ic_mylocation_off);
		drawable[1] = mContext.getResources().getDrawable(R.drawable.ic_mylocation_on);

		for (int i = 0; i < drawable.length; i++) {
			int w = drawable[i].getIntrinsicWidth() / 2;
			int h = drawable[i].getIntrinsicHeight() / 2;

			drawable[i].setBounds(-w, -h, w, h);
		}

		return drawable;
	}

	public Drawable getDrawableWithNumber(int resourceId, String strNumber, float offsetY, int fontColor, float fontSize) {

		Bitmap textBitmap = getBitmapWithText(resourceId, strNumber, fontColor, fontSize, offsetY);

		//Log.i(LOG_TAG, "getDrawableWithNumber: width=" + textBitmap.getWidth() + ", height=" + textBitmap.getHeight() + ", density=" + textBitmap.getDensity());

		// set bounds
		Drawable marker = new BitmapDrawable(mContext.getResources(), textBitmap);
		if (marker != null) {
			NMapOverlayItem.boundCenter(marker);
		}

		//Log.i(LOG_TAG, "getDrawableWithNumber: width=" + marker.getIntrinsicWidth() + ", height=" + marker.getIntrinsicHeight());

		return marker;
	}

	public Drawable getDrawableWithAlphabet(int resourceId, String strAlphabet, int fontColor, float fontSize) {

		Bitmap textBitmap = getBitmapWithText(resourceId, strAlphabet, fontColor, fontSize, POI_FONT_OFFSET_ALPHABET);

		// set bounds
		Drawable marker = new BitmapDrawable(mContext.getResources(), textBitmap);
		if (marker != null) {
			NMapOverlayItem.boundCenterBottom(marker);
		}

		return marker;
	}

	protected Drawable getDrawableForMarker(int markerId, boolean focused) {
		return null;
	}

	private Bitmap getBitmapWithText(int resourceId, String strNumber, int fontColor, float fontSize, float offsetY) {
		Bitmap bitmapBackground = BitmapFactory.decodeResource(mContext.getResources(), resourceId);

		int width = bitmapBackground.getWidth();
		int height = bitmapBackground.getHeight();
		//Log.i(LOG_TAG, "getBitmapWithText: width=" + width + ", height=" + height + ", density=" + bitmapBackground.getDensity());

		Bitmap textBitmap = Bitmap.createBitmap(width, height, BITMAP_CONFIG_DEFAULT);

		Canvas canvas = new Canvas(textBitmap);

		canvas.drawBitmap(bitmapBackground, 0, 0, null);

		// set font style
		mTextPaint.setColor(fontColor);
		// set font size
		mTextPaint.setTextSize(fontSize * mScaleFactor);
		// set font type
		if (POI_FONT_TYPEFACE != null) {
			mTextPaint.setTypeface(POI_FONT_TYPEFACE);
		}

		// get text offset		
		mTextPaint.getTextBounds(strNumber, 0, strNumber.length(), mTempRect);
		float offsetX = (width - mTempRect.width()) / 2 - mTempRect.left;
		if (offsetY == 0.0F) {
			offsetY = (height - mTempRect.height()) / 2 + mTempRect.height();
		} else {
			offsetY = offsetY * mScaleFactor + mTempRect.height();
		}

		//Log.i(LOG_TAG, "getBitmapWithText: number=" + number + ", focused=" + focused);
		//Log.i(LOG_TAG, "getBitmapWithText: offsetX=" + offsetX + ", offsetY=" + offsetY + ", boundsWidth=" + mTempRect.width() + ", boundsHeight=" + mTempRect.height());

		// draw text
		canvas.drawText(strNumber, offsetX, offsetY, mTextPaint);

		return textBitmap;
	}

	public Drawable getCalloutBackground() {

		Drawable drawable = mContext.getResources().getDrawable(R.drawable.bg_speech);

		return drawable;
	}

	public String getCalloutRightButtonText(NMapOverlayItem item) {
		return null;
	}

	public Drawable[] getCalloutRightButton(NMapOverlayItem item) {
		return null;
	}

	public Drawable[] getCalloutRightAccessory(NMapOverlayItem item) {
		return null;
	}

	public int getParentLayoutIdForOverlappedListView() {
		// not supported
		return 0;
	}

	public int getOverlappedListViewId() {
		// not supported
		return 0;
	}

	public int getLayoutIdForOverlappedListView() {
		// not supported
		return 0;
	}

	public int getListItemLayoutIdForOverlappedListView() {
		// not supported
		return 0;
	}

	public int getListItemTextViewId() {
		// not supported
		return 0;
	}

	public int getListItemImageViewId() {
		// not supported
		return 0;
	}

	public int getListItemDividerId() {
		// not supported
		return 0;
	}

	public void setOverlappedListViewLayout(ListView listView, int itemCount, int width, int height) {
		// not supported
	}

	public void setOverlappedItemResource(NMapPOIitem poiItem, ImageView imageView) {
		// not supported
	}

	private int getResourceIdOnMapView(int markerId, boolean focused, ResourceIdsOnMap resourceIdsArray[]) {
		int resourceId = 0;

		for (ResourceIdsOnMap resourceIds : resourceIdsArray) {
			if (resourceIds.markerId == markerId) {
				resourceId = (focused) ? resourceIds.resourceIdFocused : resourceIds.resourceId;
				break;
			}
		}

		return resourceId;
	}

	public Drawable getDirectionArrow() {
		return null;
	}
}
