package test.a;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
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

import test.a.DBHandler;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TestActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	public void onClick(View v) {
		TextView tv = (TextView) findViewById(R.id.textView1);
		try {
			String html = loadKmaData();

			// DOM �Ľ�.
			ByteArrayInputStream bai = new ByteArrayInputStream(html.getBytes());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// dbf.setIgnoringElementContentWhitespace(true);//ȭ��Ʈ�����̽� ����
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document parse = builder.parse(bai);// DOM �ļ�
			// �±� �˻�
			NodeList datas = parse.getElementsByTagName("data");

			
			HashMap<String, String> map = new HashMap<String, String>();
			// 17���� data�±׸� ������ ����
			for (int idx = 0; idx < 1; idx++) { //ù��° �ο츸 ������
				// �ʿ��� �������� ���� ���� ����
				
				
				Node node = datas.item(idx);// data �±� ����
	
				int childLength = node.getChildNodes().getLength();
				// �ڽ��±� ��� ����
				NodeList childNodes = node.getChildNodes();
				for (int childIdx = 0; childIdx < childLength; childIdx++) {
					Node childNode = childNodes.item(childIdx);
					int count = 0;
					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
						count++;
						// �±��� ��츸 ó��
						// ����,����,�� ����(�ð����� ����)
						if (childNode.getNodeName().equals("day")) { // (0:����/1:����/2:��)
							map.put("day", childNode.getFirstChild().getNodeValue());
						} else if (childNode.getNodeName().equals("hour")) {//�ð�
							map.put("hour", childNode.getFirstChild().getNodeValue());
							// �ϴû����ڵ� �м�
						} else if (childNode.getNodeName().equals("sky")) {//1:����  2:��������  3:��������  4:�帲
							map.put("sky", childNode.getFirstChild().getNodeValue());
						} else if (childNode.getNodeName().equals("wfKor")) {//���� �ѱ���
							map.put("wfKor", childNode.getFirstChild().getNodeValue());
						} else if (childNode.getNodeName().equals("temp")) {//����ð��µ�
							map.put("temp", childNode.getFirstChild().getNodeValue());
						}else if (childNode.getNodeName().equals("pty")){ //0 : ����  1 : �� 2 : ��/��  3 : ��/��  4 : ��
							map.put("pty", childNode.getFirstChild().getNodeValue());
						}else if (childNode.getNodeName().equals("reh")){ // ����%
							map.put("reh", childNode.getFirstChild().getNodeValue());
						}
					}
				}// end ���� for��
				
			}// end �ٱ��� for��
			String resultMenu = menuSelection(map);
			
			tv.setText(dataSelect());
			
		} catch (Exception e) {
			tv.setText("����" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String dataSelect(){
		DBHandler dbhandler = DBHandler.open(this);
		
		long cnt = dbhandler.insert("");
		Cursor cursor = dbhandler.select(7);
        startManagingCursor(cursor);
        String result = cursor.getString(cursor.getColumnIndex("menuName"));
		dbhandler.close();
		
		return result;
	}
	
	private String dispName(String code){
		String dispName = "";
		
		char c = code.charAt(0);
		switch(c){
		case '0' : dispName = "����";
				   break;
		case '1' : dispName = "��";
		   break;
		case '2' : dispName = "��/��";
		   break;		   
		case '3' : dispName = "��/��";
		   break;	
		case '4' : dispName = "��";
		   break;
		}
		
		return dispName;
	}
	
	private String menuSelection(HashMap map){
		
		String result = "";
		
		result = "���� : " + map.get("wfKor") + "\n";
		result += "�µ� : " + map.get("temp") + "��\n"; 
		result += "���� : " + map.get("reh") + "%\n";
		result += dispName(map.get("pty").toString()) + "%\n";
		
		return result;
	
	}

	// ���û �������� ����
	private String loadKmaData() throws Exception {
		String page = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=63&gridy=123";
		URL url = new URL(page);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		if (urlConnection == null)
			return null;
		urlConnection.setConnectTimeout(10000);// �ִ� 10�� ���
		urlConnection.setUseCaches(false);// �Ź� �������� �о����
		StringBuilder sb = new StringBuilder();// ��� ���ڿ� ����ü
		if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream);

			// ���پ� �б�
			BufferedReader br = new BufferedReader(isr);
			while (true) {
				String line = br.readLine();// ���������� html �ڵ� �о����
				if (line == null)
					break;// ��Ʈ���� ������ null����
				sb.append(line + "\n");
			}// end while
			br.close();
		}// end if
		return sb.toString();
	}
}
