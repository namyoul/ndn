package test.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBHandler {
    private DBHelper helper;
    private SQLiteDatabase db;
    
    private DBHandler(Context ctx) {
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase();
    }

    public static DBHandler open(Context ctx) throws SQLException {
        DBHandler handler = new DBHandler(ctx);        

        return handler;    
    }
    
    public void close() {
        helper.close();
    }

    public long insert(String car_name) {
    	/*
        ContentValues values = new ContentValues();
        values.put("code", "1");        
        values.put("detailCode", "1");        
        values.put("menuName", "menuName");        
        values.put("pictureName", "pictureName");        
        values.put("snow", "0");        
        values.put("rain", "0");        
        values.put("hot", "0");        
        values.put("cold", "0");        
        return db.insert("menu", null, values);
        */
    	
    	



String strInsetItem = 

" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '���嶱����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '����Ĩ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '�����ġ�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '���������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '����̹�ħ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��â����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '����ħ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '���߶�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�踻��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '��ġ������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '��ġ�������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '��ġ�η�ġ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '��ġ�������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '��ġ��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '��ġ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��ǳ����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '������ħ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��ġ�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�ɰ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�ɰ�Ƣ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '����������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�߰�������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�߰���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�߲�ġ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�߶�������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�߸ſ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�߹��ͱ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '���ϱ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '���ռ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '�������Ҵ߰���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '���������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '���丮����ħ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '���±�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                    '��������ġ�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '������â����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '���������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�����Ұ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�κα�ġ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '�κεη�ġ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '�κν�����ũ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�κ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�κ�������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�κ�Ƣ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '����ä','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '����ġŲ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '�����Ը�Ÿ����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                      '�ſ���������ߺ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�ſ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��볿��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '���Ƣ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '��������ġ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '��ȸ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '��Ʈ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�ι��ſ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '������ä','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '����������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�δ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '������ä','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '��ħ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�ҳ�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '�Ҵ�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '����Ƣ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '�ҽ�����ä����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�۾�ȸ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                      '���Ⱓ���䱸��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '���⺺��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '������Ƣ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '���뺺��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��Ű��Ű','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '����Ұ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '��¡���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '��¡��Ұ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '��¡�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��¡����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��¡����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '���ڳ�̾�Ű','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�չ�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�췰�ſ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '���꽽','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '��ȸ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '¥�嶱����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '���񳿺�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '����ȸ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '����ȸ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '����ſ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '�ֲٹ̻��λ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '��ġ��','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '��ġ�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '��ġȸ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '��������ũ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'ġ��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'ġ��Ҵ߹�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'ġ�ƽ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'ġŲ������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ī����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'ī��������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '�����ػ�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�ᳪ����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '����ä','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'Ƣ��κ�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�Ⱥ�ä','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'ǥ�������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '�ع����׶���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '�ع�������','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�ع�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '�ع����λ���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '�ع���','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '�ع�����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ȫ�ձ�','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ȫ����','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ȫ����','test.png','0','0','0','0'); " ; 
 
    db.execSQL(strInsetItem);
    	
    	return 1;
    } 

    public Cursor select(int id) throws SQLException {        
        Cursor cursor = db.query(true, "menu", 
                                new String[] {"id", "code", "detailCode",
        		"menuName", "pictureName", "snow", "rain", "hot", "cold"},
                                "id" + "=" + id, 
                                null, null, null, null, null);        
        if (cursor != null) { cursor.moveToFirst(); }        

        return cursor;
    }
}
