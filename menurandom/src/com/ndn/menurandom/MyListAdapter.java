package com.ndn.menurandom;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//어댑터를 커스터마이징 해야됨
public class MyListAdapter extends BaseAdapter{
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<MyItem> arSrc;
    int layout;
   
    //생성자
    public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc){
          maincon = context;
          //생성시 인플레이트 준비를 미리해둠
          Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          arSrc = aarSrc;
          layout = alayout;//alayout은 메인에서보면 R.layout.icontext임 int타입이여서 이런식으로도 가능
    }
   
    //추상메소드를 구현해야됨
         public int getCount() {//getCount는 데이터의 사이즈를 리턴하면됨
                // TODO Auto-generated method stub
                return arSrc.size();
         }

         public Object getItem(int position) {
                // TODO Auto-generated method stub
                return arSrc.get(position).menuName;//getItem은 그 position의 값을 리턴하면됨
         }

         public long getItemId(int position) {//getItemId는 position을 리턴해주면됨
                // TODO Auto-generated method stub
                return position;
         }

        
         //이 부분은 리스트 각항목 하나하나를 만드는 부분임
          
         public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                final int pos = position;
                //첫번째는 convertView가 null이여서 inflate를 통해서
                //마지막인자는 Whether the inflated hierarchy should be attached to the root parameter?
                //해석 못했음
                if(convertView == null){//layout은 R.layout.icontext parent는 뷰그룹인 리스트뷰를 뜻함
                       convertView = Inflater.inflate(layout, parent, false);
                      
                }

                //이미지뷰를 세팅하고
                ImageView img = (ImageView)convertView.findViewById(R.id.img);
                img.setImageResource(arSrc.get(position).Icon);
               
                
              //텍스브튜도 세팅
                TextView id = (TextView)convertView.findViewById(R.id.id);
                id.setText(arSrc.get(position).id);
               
              //텍스브튜도 세팅
                TextView txt = (TextView)convertView.findViewById(R.id.text);
                txt.setText(arSrc.get(position).menuName);
               
               
                //버튼도 세팅함
                Button btn = (Button)convertView.findViewById(R.id.btn);
                btn.setOnClickListener(new OnClickListener() {
                      
                       public void onClick(View v) {
                             // TODO Auto-generated method stub
                             String str = "id : " + arSrc.get(pos).id + " 를 주문합니다.";
                             Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();
                             
                       }
                });
               
                return convertView;//위 과정이 리스트뷰에 getCount만큼 반복됨
         }
   
}