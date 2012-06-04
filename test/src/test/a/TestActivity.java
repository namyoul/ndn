package test.a;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestActivity extends Activity implements OnClickListener {
	LinearLayout layout = null;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.button1).setOnClickListener(this);
		layout = (LinearLayout)findViewById(R.id.tLayout);
	}

	public void onClick(View v) {
		TextView tv = (TextView) findViewById(R.id.textView1);
		try {
			String html = loadKmaData();

			// DOM 파싱.
			ByteArrayInputStream bai = new ByteArrayInputStream(html.getBytes());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// dbf.setIgnoringElementContentWhitespace(true);//화이트스패이스 생략
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document parse = builder.parse(bai);// DOM 파서
			// 태그 검색
			NodeList datas = parse.getElementsByTagName("data");

			
			HashMap<String, String> map = new HashMap<String, String>();
			// 17개의 data태그를 순차로 접근
			for (int idx = 0; idx < 1; idx++) { //첫번째 로우만 가져옴
				// 필요한 정보들을 담을 변수 생성
				
				
				Node node = datas.item(idx);// data 태그 추출
	
				int childLength = node.getChildNodes().getLength();
				// 자식태그 목록 수정
				NodeList childNodes = node.getChildNodes();
				for (int childIdx = 0; childIdx < childLength; childIdx++) {
					Node childNode = childNodes.item(childIdx);
					int count = 0;
					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
						count++;
						// 태그인 경우만 처리
						// 금일,내일,모레 구분(시간정보 포함)
						if (childNode.getNodeName().equals("day")) { // (0:오늘/1:내일/2:모레)
							map.put("day", childNode.getFirstChild().getNodeValue());
						} else if (childNode.getNodeName().equals("hour")) {//시간
							map.put("hour", childNode.getFirstChild().getNodeValue());
							// 하늘상태코드 분석
						} else if (childNode.getNodeName().equals("sky")) {//1:맑음  2:구름조금  3:구름많음  4:흐림
							map.put("sky", childNode.getFirstChild().getNodeValue());
						} else if (childNode.getNodeName().equals("wfKor")) {//날씨 한국어
							map.put("wfKor", childNode.getFirstChild().getNodeValue());
						} else if (childNode.getNodeName().equals("temp")) {//현재시간온도
							map.put("temp", childNode.getFirstChild().getNodeValue());
						}else if (childNode.getNodeName().equals("pty")){ //0 : 없음  1 : 비 2 : 비/눈  3 : 눈/비  4 : 눈
							map.put("pty", childNode.getFirstChild().getNodeValue());
						}else if (childNode.getNodeName().equals("reh")){ // 습도%
							map.put("reh", childNode.getFirstChild().getNodeValue());
						}
					}
				}// end 안쪽 for문
				
			}// end 바깥쪽 for문
			String resultMenu = menuSelection(map);
			resultMenu += "\n 추천 메뉴는 : " + dataSelect();
			tv.setText(resultMenu);
			
			
			int resId = getResources().getIdentifier("img1", "drawable", "test.a");
			
			ImageView image = new ImageView(this);
			image.setImageResource(resId);
			layout.removeAllViews();
			layout.addView(image);
			
			
			
		} catch (Exception e) {
			tv.setText("오류" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String dataSelect(){
		DBHandler dbhandler = DBHandler.open(this);
		
		Cursor cursor = dbhandler.select(8);
        startManagingCursor(cursor);
        String result = cursor.getString(cursor.getColumnIndex("menuName"));
		dbhandler.close();
		
		return result;
	}
	
	private String dispName(String code){
		String dispName = "";
		
		char c = code.charAt(0);
		switch(c){
		case '0' : dispName = "없음";
				   break;
		case '1' : dispName = "비";
		   break;
		case '2' : dispName = "비/눈";
		   break;		   
		case '3' : dispName = "눈/비";
		   break;	
		case '4' : dispName = "눈";
		   break;
		}
		
		return dispName;
	}
	
	private String menuSelection(HashMap map){
		
		String result = "";
		
		result = "날씨 : " + map.get("wfKor") + "\n";
		result += "온도 : " + map.get("temp") + "도\n"; 
		result += "습도 : " + map.get("reh") + "%\n";
		result += dispName(map.get("pty").toString()) + "%\n";
		
		return result;
	
	}

	// 기상청 날씨정보 추출
	private String loadKmaData() throws Exception {
		String page = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=63&gridy=123";
		URL url = new URL(page);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		if (urlConnection == null)
			return null;
		urlConnection.setConnectTimeout(10000);// 최대 10초 대기
		urlConnection.setUseCaches(false);// 매번 서버에서 읽어오기
		StringBuilder sb = new StringBuilder();// 고속 문자열 결합체
		if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream);

			// 한줄씩 읽기
			BufferedReader br = new BufferedReader(isr);
			while (true) {
				String line = br.readLine();// 웹페이지의 html 코드 읽어오기
				if (line == null)
					break;// 스트림이 끝나면 null리턴
				sb.append(line + "\n");
			}// end while
			br.close();
		}// end if
		return sb.toString();
	}
}
