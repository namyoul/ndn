package com.ndn.menurandom;

import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class SearchMapParser {
	private static final String NAVER_API_KEY = "a660ecea6bb6c3428aa623190f4b174d";
//	private static final String SEARCH_START_CURSOR = "1";			// 검색의 시작위치를 지정. 최대 1000까지 가능							// 참고 전용
//	private static final String SEARCH_DISPLAY_NUMBER = "5";		// 검색결과 출력건수를 지정. 최대 100까지 가능							// 참고 전용
//	private static final String SEARCH_SORT_OPTION = "random";		// 정렬 옵션. random: 유사도순 comment: 평가글 개수 vote: 평점순		// 참고 전용

	public int search(RestaurantData[] restaurantData, String query, int nWhenDidItStart, int nHowMany){
		boolean inItem=false, inTitle=false, inAddress=false, inTelephone=false, inMapx=false, inMapy=false;
		int count = 0;
		try {
			URL url = new URL("http://openapi.naver.com/search?"
					+ "key=" + NAVER_API_KEY
					+ "&query=" + URLEncoder.encode(query)
					+ "&target=local"
					+ "&start=" + Integer.toString(nWhenDidItStart)
					+ "&display=" + Integer.toString(nHowMany));
			
			XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			parser.setInput(url.openStream(), null);
			
			int parserEvent = parser.getEventType();
			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG: // parser가 시작 태그를 만나면 실행
		            if(parser.getName().equals("item")){
		                inItem = true;
		            }
					if (parser.getName().equals("title")) { // title 만나면
						inTitle = true;
					}
					if (parser.getName().equals("address")) { // address 만나면
						inAddress = true;
					}
					if (parser.getName().equals("telephone")) { // mapx 만나면
						inTelephone = true;
					}
					if (parser.getName().equals("mapx")) { // mapx 만나면
						inMapx = true;
					}
					if (parser.getName().equals("mapy")) { // mapy 만나면
						inMapy = true;
					}
					if (parser.getName().equals("message")) { // message 태그를 만나면 에러 출력
//						status1.setText(status1.getText()+"에러");
						Log.e("NHK", "error: " + parser.getText());
					}
					break;

				case XmlPullParser.TEXT:// parser가 내용에 접근했을때
					if (inTitle) { // isTitle이 true일 때 태그의 내용을 저장.
						restaurantData[count].sTitle = parser.getText();
						inTitle = false;
					}
					if (inAddress) { // isAddress이 true일 때 태그의 내용을 저장.
						restaurantData[count].sAddress = parser.getText();
						inAddress = false;
					}
					if (inTelephone) { // isAddress이 true일 때 태그의 내용을 저장.
						restaurantData[count].sTel = parser.getText();
						inAddress = false;
					}					
					if (inMapx) { // isMapx이 true일 때 태그의 내용을 저장.
						restaurantData[count].nMapX = Integer.parseInt(parser.getText());
						inMapx = false;
					}
					if (inMapy) { // isMapy이 true일 때 태그의 내용을 저장.
						restaurantData[count].nMapY = Integer.parseInt(parser.getText());
						inMapy = false;
					}
					break;
					
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("item")) {
						count++;
						inItem = false;
					}
					break;
				}
				parserEvent = parser.next();
			}
		} catch (Exception e) {
			Log.e("NHK", "SearchMapParser.search ERROR");
		}
		Log.i("NHK", " " + String.valueOf(count) + "개의 검색결과를 찾았습니다.");
		return count;
	}
}