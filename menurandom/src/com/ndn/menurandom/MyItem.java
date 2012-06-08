package com.ndn.menurandom;

//리스트 뷰에 출력할 항목
public class MyItem{
	
    int Icon;
    String id;
    String menuName;	
	
    MyItem(String id, String menuName, int aIcon){
          this.Icon = aIcon;//아이콘
          this.id = id;//id
          this.menuName = menuName;//이름
    }

}
