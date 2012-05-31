package com.ndn.menurandom;

import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchMapParser extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// TextView status = (TextView)findViewById(R.id.status);
		// TextView status1 = (TextView)findViewById(R.id.status1);
		// TextView status2 = (TextView)findViewById(R.id.status2);

		boolean inItem = false, inTitle = false, inAddress = false, inMapx = false, inMapy = false;

		String title = null, address = null, mapx = null, mapy = null;
		String query = URLEncoder.encode("갈비탕");

		try {
			URL url = new URL("http://openapi.naver.com/search?"
									+ "key=a660ecea6bb6c3428aa623190f4b174d" + "&query="
									+ query // 여기는 쿼리를 넣으세요(검색어)
									+ "&target=local&start=1&display=4");

			XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();

			parser.setInput(url.openStream(), null);

			// status.setText("파싱 중이에요..");

			int parserEvent = parser.getEventType();

			while (parserEvent != XmlPullParser.END_DOCUMENT) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG: // parser가 시작 태그를 만나면 실행
					if (parser.getName().equals("item")) {
						inItem = true;
					}
					if (parser.getName().equals("title")) { // title 만나면 내용을 받을수
															// 있게 하자
						inTitle = true;
					}
					if (parser.getName().equals("address")) { // address 만나면 내용을
																// 받을수 있게 하자
						inAddress = true;
					}
					if (parser.getName().equals("mapx")) { // mapx 만나면 내용을 받을수
															// 있게 하자
						inMapx = true;
					}
					if (parser.getName().equals("mapy")) { // mapy 만나면 내용을 받을수
															// 있게 하자
						inMapy = true;
					}
					if (parser.getName().equals("message")) { // message 태그를 만나면
																// 에러 출력
					// status1.setText(status1.getText()+"에러");
						// 여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
					}
					break;

				case XmlPullParser.TEXT:// parser가 내용에 접근했을때
					if (inTitle) { // isTitle이 true일 때 태그의 내용을 저장.
						title = parser.getText();
						inTitle = false;
					}
					if (inAddress) { // isAddress이 true일 때 태그의 내용을 저장.
						address = parser.getText();
						inAddress = false;
					}
					if (inMapx) { // isMapx이 true일 때 태그의 내용을 저장.
						mapx = parser.getText();
						inMapx = false;
					}
					if (inMapy) { // isMapy이 true일 때 태그의 내용을 저장.
						mapy = parser.getText();
						inMapy = false;
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("item")) {
						// status1.setText(status1.getText()+"상호 : "+ title
						// +"\n주소 : "+ address +"\n좌표 : " + mapx + ", " +
						// mapy+"\n\n");
						inItem = false;
					}
					break;
				}
				parserEvent = parser.next();
			}
			// status2.setText("파싱 끝!");
		} catch (Exception e) {
			// status1.setText("에러가..났습니다...");
		}
	}
}