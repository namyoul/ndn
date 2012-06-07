package com.ndn.menurandom.db;
//
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHandler {
    private DBHelper helper;
    private SQLiteDatabase db;
    
    private DBHandler(Context ctx) {
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase();
    }
    
    public static final String ROOT_DIR = "/data/data/com.ndn.menurandom/";
    private static final String DATABASE_NAME = "dbmenu.db";
    
    public static void initialize(Context ctx) {
		// check 
		File folder = new File(ROOT_DIR + "databases");
		folder.mkdirs();
		File outfile = new File(ROOT_DIR + "databases/" + DATABASE_NAME);
		if (outfile.isFile()) {
			AssetManager assetManager = ctx.getResources().getAssets();
			try {
				InputStream is = assetManager.open(DATABASE_NAME, AssetManager.ACCESS_BUFFER);
				long filesize = is.available();
				byte [] tempdata = new byte[(int)filesize];
				is.read(tempdata); 
				is.close();
				
				outfile.createNewFile();
				FileOutputStream fo = new FileOutputStream(outfile);
				fo.write(tempdata);
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}    
    

    public static DBHandler open(Context ctx) throws SQLException {
    	Log.v("", "디비오픈시작");
        DBHandler handler = new DBHandler(ctx);        
        Log.v("", "디비오픈끝");
        return handler;    
    }
    
    public void close() {
        helper.close();
    }

    public long insert(String car_name) {
    	/*
        ContentValues values = new ContentValues();
        values.put("code", "1");             
        return db.insert("menu", null, values);
        */
    	
    	
    	return 1;
    } 

    /* 설명 : 넘어온 파라메터 조건에 맞게 검색하여 그중 랜덤 1건의 데이터를 리턴해 준다.
     * 호출방법 : randomSelect(code, detailCode, snow, rain, hot, cold); 
     */
    public Cursor randomSelect(String code, String detailCode, String snow, String rain, String hot, String cold ) throws SQLException {
    	
    	//Log.v("randomSelect","##################쿼리시작");
    	Cursor cursor = null;
    	
    	cursor=db.rawQuery(
    			
    			" select id,																											"																
    					+"        code,                                                    "
    					+"        detailCode,                                              "
    					+"        menuName,                                                "
    					+"        pictureName,                                             "
    					+"        snow,                                                    "
    					+"        rain,                                                    "
    					+"        hot,                                                     "
    					+"        cold,                                                    "
    					+"        rank                                                     "
    					+" from                                                            "
    					+"     (                                                           "
    					+"      select                                                     "
    					+"     	    a.id,                                                 "
    					+"             a.code,                                             "
    					+"             a.detailCode,                                       "
    					+"             a.menuName,                                         "
    					+"             a.pictureName,                                      "
    					+"             a.snow,                                             "
    					+"             a.rain,                                             "
    					+"             a.hot,                                              "
    					+"             a.cold,                                             "
    					+" 	    (select count(b.id)	                                      "
    					+"                from menu b                                      "
    					+"               where a.id <= b.id                                "
    					+"                 and b.code like '%" + code + "%'                            "
    					+"                 and b.detailCode like '%" + detailCode + "%'                      "
    					+"                 and b.snow like '%" + snow + "%'                            "
    					+"                 and b.rain like '%" + rain + "%'                            "
    					+"                 and b.hot like '%" + hot + "%'                             "
    					+"                 and b.cold like '%" + cold + "%') 'rank'   " //--로우번호 구하기
    					+"      from menu a                                                "
    					+"     where 1=1                                                   "
    					+"       and a.code like '%" + code + "%'                                      "
    					+"       and a.detailCode like '%" + detailCode + "%'                                "
    					+"       and a.snow like '%" + snow + "%'                                      "
    					+"       and a.rain like '%" + rain + "%'                                      "
    					+"       and a.hot like '%" + hot + "%'                                       "
    					+"       and a.cold like '%" + cold + "%'                                      "
    					+"     )c,                                                         "
    					+"     (select abs(random())% count(*) 'rdNumber'"  //--random 값 구하기
    					+"        from menu                                                "
    					+"       where 1=1                                                 "
    					+"        and code like '%" + code + "%'                                       "
    					+"        and detailCode like '%" + detailCode + "%'                                 "
    					+"        and snow like '%" + snow + "%'                                       "
    					+"        and rain like '%" + rain + "%'                                       "
    					+"        and hot like '%" + hot + "%'                                        "
    					+"        and cold like '%" + cold + "%') r                                    "
    					+" where c.rank = r.rdNumber      "//-- random 값과 같은 로우 가져오기
    			
                ,null);
    	//Log.v("randomSelect","##################쿼리끝");
        return cursor;
    }
}
