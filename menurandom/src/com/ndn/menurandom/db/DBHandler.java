package com.ndn.menurandom.db;
//
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

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
    	//Log.v("", "################DBHelper(ctx) 시작");
        this.helper = new DBHelper(ctx);
        //Log.v("", "################DBHelper(ctx) 중간");
        this.db = helper.getWritableDatabase();
        //Log.v("", "################DBHelper(ctx) 끝");
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
    public Cursor randomRecommended(HashMap itemMap) throws SQLException {
    	
    	String code =  (String)itemMap.get("code");
    	String detailCode =  (String)itemMap.get("detailCode");
    	String snow =  (String)itemMap.get("snow");
    	String rain =  (String)itemMap.get("rain");
    	String hot =  (String)itemMap.get("hot");
    	String cold =  (String)itemMap.get("cold");
    	
    	Cursor cursor = null;
    	
    	StringBuffer sb = new StringBuffer();
    	
		sb.append(" select id,																											\n");																
		sb.append("        code,                                                    \n");
		sb.append("        detailCode,                                              \n");
		sb.append("        menuName,                                                \n");
		sb.append("        pictureName,                                             \n");
		sb.append("        snow,                                                    \n");
		sb.append("        rain,                                                    \n");
		sb.append("        hot,                                                     \n");
		sb.append("        cold,                                                    \n");
		sb.append("        rank                                                     \n");
		sb.append(" from                                                            \n");
		sb.append("     (                                                           \n");
		sb.append("      select                                                     \n");
		sb.append("     	    a.id,                                                 \n");
		sb.append("             a.code,                                             \n");
		sb.append("             a.detailCode,                                       \n");
		sb.append("             a.menuName,                                         \n");
		sb.append("             a.pictureName,                                      \n");
		sb.append("             a.snow,                                             \n");
		sb.append("             a.rain,                                             \n");
		sb.append("             a.hot,                                              \n");
		sb.append("             a.cold,                                             \n");
		sb.append(" 	    (select count(b.id)	                                      \n");
		sb.append("                from menu b                                      \n");
		sb.append("               where a.id <= b.id                                \n");
		if(code != null) 			
			sb.append("                 and b.code = '" + code + "'                \n");
		if(detailCode != null) 	
			sb.append("                 and b.detailCode = '" + detailCode + "'    \n");
		
		sb.append("                 and ( 1 = 0    \n"); // OR 조건을 넣기 위해서 넣어줌..
		
		if(snow != null) 			
			sb.append("                 or b.snow = '" + snow + "'                \n");
		if(rain != null) 			
			sb.append("                 or b.rain = '" + rain + "'                \n");
		if(hot != null) 			
			sb.append("                 or b.hot = '" + hot + "'                  \n");
		if(cold != null) 			
			sb.append("                 or b.cold = '" + cold + "'                 \n");
		sb.append("                     )    \n");
		sb.append("                 		) 'rank'   								\n"); //--로우번호 구하기
		sb.append("      from menu a                                                \n");
		sb.append("     where 1=1                                                   \n");
		if(code != null)
			sb.append("       and a.code = '" + code + "'                          \n");
		if(detailCode != null)
			sb.append("       and a.detailCode = '" + detailCode + "'              \n");
		sb.append("           and ( 1 = 0    \n"); // OR 조건을 넣기 위해서 넣어줌..
		if(snow != null)
			sb.append("       or a.snow = '" + snow + "'                          \n");
		if(rain != null)
			sb.append("       or a.rain = '" + rain + "'                          \n");
		if(hot != null)
			sb.append("       or a.hot = '" + hot + "'                            \n");
		if(cold != null)
			sb.append("       or a.cold = '" + cold + "'                          \n");
		sb.append("           )    \n");
		sb.append("     )c,                                                         \n");
		sb.append("     (select abs(random())% count(*) 'rdNumber'					\n");  //--random 값 구하기
		sb.append("        from menu                                                \n");
		sb.append("       where 1=1                                                	\n");
		if(code != null)
			sb.append("        and code = '" + code + "'                           \n");
		if(detailCode != null)	
			sb.append("        and detailCode = '" + detailCode + "'               \n");
		sb.append("            and ( 1 = 0    \n"); // OR 조건을 넣기 위해서 넣어줌..
		if(snow != null)	
			sb.append("        or snow = '" + snow + "'                           \n");
		if(rain != null)	
			sb.append("        or rain = '" + rain + "'                           \n");
		if(hot != null)	
			sb.append("        or hot = '" + hot + "'                             \n");
		if(cold != null)	
			sb.append("        or cold = '" + cold + "'                           \n");
		sb.append("                )    \n");
		sb.append("        								) r                        \n");			
		sb.append(" where c.rank = r.rdNumber      									\n");//-- random 값과 같은 로우 가져오기
    					
 		cursor=db.rawQuery(sb.toString() ,null);    	
    	
 		Log.v("", sb.toString());
        return cursor;
    }
    
    /* 설명 : 넘어온 파라메터 조건에 맞게 검색하여 그중 랜덤 1건의 데이터를 리턴해 준다.
     * 호출방법 : randomSelect(map); 
     */
    public Cursor randomSelect(HashMap itemMap) throws SQLException {
    	
    	String code =  (String)itemMap.get("code");
    	String detailCode =  (String)itemMap.get("detailCode");
    	
    	Cursor cursor = null;
    	
    	StringBuffer sb = new StringBuffer();
    	
		sb.append(" select id,																											\n");																
		sb.append("        code,                                                    \n");
		sb.append("        detailCode,                                              \n");
		sb.append("        menuName,                                                \n");
		sb.append("        pictureName,                                             \n");
		sb.append("        snow,                                                    \n");
		sb.append("        rain,                                                    \n");
		sb.append("        hot,                                                     \n");
		sb.append("        cold,                                                    \n");
		sb.append("        rank                                                     \n");
		sb.append(" from                                                            \n");
		sb.append("     (                                                           \n");
		sb.append("      select                                                     \n");
		sb.append("     	    a.id,                                                 \n");
		sb.append("             a.code,                                             \n");
		sb.append("             a.detailCode,                                       \n");
		sb.append("             a.menuName,                                         \n");
		sb.append("             a.pictureName,                                      \n");
		sb.append("             a.snow,                                             \n");
		sb.append("             a.rain,                                             \n");
		sb.append("             a.hot,                                              \n");
		sb.append("             a.cold,                                             \n");
		sb.append(" 	    (select count(b.id)	                                      \n");
		sb.append("                from menu b                                      \n");
		sb.append("               where a.id <= b.id                                \n");
		if(code != null) 			
			sb.append("                 and b.code = '" + code + "'                \n");
		if(detailCode != null) 	
			sb.append("                 and b.detailCode = '" + detailCode + "'    \n");
		
		sb.append("                 		) 'rank'   								\n"); //--로우번호 구하기
		sb.append("      from menu a                                                \n");
		sb.append("     where 1=1                                                   \n");
		if(code != null)
			sb.append("       and a.code = '" + code + "'                          \n");
		if(detailCode != null)
			sb.append("       and a.detailCode = '" + detailCode + "'              \n");
		sb.append("     )c,                                                         \n");
		sb.append("     (select abs(random())% count(*) 'rdNumber'					\n");  //--random 값 구하기
		sb.append("        from menu                                                \n");
		sb.append("       where 1=1                                                	\n");
		if(code != null)
			sb.append("        and code = '" + code + "'                           \n");
		if(detailCode != null)	
			sb.append("        and detailCode = '" + detailCode + "'               \n");
		sb.append("        								) r                        \n");			
		sb.append(" where c.rank = r.rdNumber      									\n");//-- random 값과 같은 로우 가져오기
    					
 		cursor=db.rawQuery(sb.toString() ,null);    	
    	
 		Log.v("", sb.toString());
        return cursor;
    }    

    /* 설명 : ListView에 넣을 리스트 반환
     * 호출방법 : getArrayList(code, detailCode); 
     */
    public Cursor getArrayList(String code, String detailCode ) throws SQLException {
    	
    	Cursor cursor = null;
    	
    	StringBuffer sb = new StringBuffer();
    	
		sb.append(" select id,                \n");                                              
		sb.append("    menuName               \n");
		sb.append(" from menu                 \n");                                            
		sb.append(" where 1=1                 \n");                                                 
	if(code != null)
		sb.append(" and code = '" + code + "' \n");                    
	if(detailCode != null)
		sb.append(" and detailCode = '" + detailCode + "'  \n");
    					
 		cursor=db.rawQuery(sb.toString() ,null);    	
    	
        return cursor;
    }
}
