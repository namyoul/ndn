package com.ndn.menurandom;

import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class SearchMapParser {
	private static final String NAVER_API_KEY = "a660ecea6bb6c3428aa623190f4b174d";
	
	public int search(RestaurantData[] restaurantData, String query, int nHowMany, int nStart) {
		boolean inTitle = false, inAddress = false, inMapx = false, inMapy = false;
		int indexCount = 0;

		try {
			URL url = new URL("http://openapi.naver.com/search?"
									+ "key=" + NAVER_API_KEY
									+ "&query=" + URLEncoder.encode(query)
									+ "&target=local"
									+ "&start=" + String.valueOf(nHowMany)
									+ "display=" + String.valueOf(nStart)
									);

			XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			parser.setInput(url.openStream(), null);
			
			int parserEvent = parser.getEventType();
			
			for(int i=0; i<nHowMany; i++){
				
			}
			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("title")) {	inTitle = true;	}
					if (parser.getName().equals("address")) { inAddress = true;	}
					if (parser.getName().equals("mapx")) { inMapx = true; }
					if (parser.getName().equals("mapy")) { inMapy = true; }
					if (parser.getName().equals("message")) {/* Error message*/}
					break;

				case XmlPullParser.TEXT:
					if (inTitle) { 
						restaurantData[indexCount].sTitle = parser.getText(); 
						inTitle = false;
					}
					if (inAddress) {
						restaurantData[indexCount].sAddress = parser.getText();
						inAddress = false;
					}
					if (inMapx) {
						restaurantData[indexCount].nMapX = Integer.parseInt(parser.getText());
						inMapx = false;
					}
					if (inMapy) {
						restaurantData[indexCount].nMapY = Integer.parseInt(parser.getText());
						inMapy = false;
					}
					break;
					
				case XmlPullParser.END_TAG:
						indexCount++;
					}
					break;
				}
				parserEvent = parser.next();
			} catch (Exception e) {
				Log.e("NHK", "search ERROR");
		}
		return indexCount;
	}
}