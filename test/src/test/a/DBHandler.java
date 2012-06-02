package test.a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
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
    
    public static final String ROOT_DIR = "/data/data/test.a/";
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
    	initialize(ctx);//assets 폴더의 db 덮어쓰기 복사
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
        return db.insert("menu", null, values);
        */
    	
    	
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
