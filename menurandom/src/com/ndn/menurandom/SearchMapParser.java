package com.ndn.menurandom;

import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class SearchMapParser {
	private static final String NAVER_API_KEY = "a660ecea6bb6c3428aa623190f4b174d";
	private static final String DAUM_API_KEY = "80eff4071090b19ab6ec0fc09de77f39f5cefee6";
	
	private String[] convert(String x, String y) {

		String[] point = new String[2];
		
		try {
			URL text = new URL("http://apis.daum.net/local/geo/transcoord?" 
									+ "apikey=" + DAUM_API_KEY
									+ "&x=" + x
									+ "&y=" + y
									+ "&fromCoord=KTM"
									+ "&toCoord=WGS84"
									+ "&output=xml");
			
			XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			
			parser.setInput(text.openStream(), null);
			int parserEvent = parser.getEventType();
			
			while (parserEvent != XmlPullParser.END_DOCUMENT){
				switch(parserEvent) {
				case XmlPullParser.START_TAG:
					if (parser.getName().compareTo("result") == 0) {
						x = parser.getAttributeValue(null, "x");
						y = parser.getAttributeValue(null, "y");
						
						point[0] = x;
						point[1] = y;
						return point;
					}
					break;
				}
				parserEvent = parser.next();
			}
		} catch (Exception e) {
			Log.e("NHK", "Daum API Error");
		}
		return null;
	}
	
	// query 에 입력받은 검색어로 검색을 하여 nHowMany 만큼 검색 결과값을 restaurantData에 저장해서
	// 검색 결과 값이 몇개인지 int 로 return 해준다.
	public int search(RestaurantData[] restaurantData, String query, int nHowMany, int nStart) {
		boolean inTitle = false, inAddress = false, inMapx = false, inMapy = false;
		int indexCount = 0;

		try {
			URL url = new URL("http://openapi.naver.com/search?"
									+ "key=" + NAVER_API_KEY
									+ "&query=" + URLEncoder.encode(query)
									+ "&target=local"
									+ "&start=" + String.valueOf(nStart)
									+ "&display=" + String.valueOf(nHowMany)
									);

			XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			parser.setInput(url.openStream(), null);
			
			int parserEvent = parser.getEventType();
			
			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				if ( parserEvent == XmlPullParser.START_TAG ) {
					if ( parser.getName().equals( "item" ) )
						break;
				}
				parserEvent = parser.next();
			}

			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("title")) {	inTitle = true;	}
					if (parser.getName().equals("address")) { inAddress = true;	}
					if (parser.getName().equals("mapx")) { inMapx = true; }
					if (parser.getName().equals("mapy")) { inMapy = true; }
					if (parser.getName().equals("message")) { }
					break;

				case XmlPullParser.TEXT:
					if (inTitle) { 
						restaurantData[indexCount].sTitle = filter(parser.getText());
						inTitle = false;
					}
					if (inAddress) {
						restaurantData[indexCount].sAddress = parser.getText();
						inAddress = false;
					}
					if (inMapx) {
						restaurantData[indexCount].sMapX = parser.getText();
						inMapx = false;
					}
					if (inMapy) {
						restaurantData[indexCount].sMapY = parser.getText();
						inMapy = false;
					}
					break;
					
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("item")){
						String[] string = convert(restaurantData[indexCount].sMapX, restaurantData[indexCount].sMapY);
						
						restaurantData[indexCount].sMapX = string[0];
						restaurantData[indexCount].sMapY = string[1];
						
						indexCount++;
					}
					break;
				}
				parserEvent = parser.next();
			}
		} catch (Exception e) {
			Log.e("NHK", "search ERROR");
		}
		return indexCount;
	}
	
	// remove <b> or </b> from string
	private String filter(String str) {
		str = str.replace("<b>", "");
		str = str.replace("</b>", "");
		return str;
	}
}