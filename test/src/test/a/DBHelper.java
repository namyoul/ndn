package test.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "dbmenu.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { 
        String table = 
            "CREATE TABLE menu (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
            "code TEXT NOT NULL, " +
            "detailCode TEXT , " +
            "menuName TEXT NOT NULL, " +
            "pictureName TEXT NOT NULL, " +
            "snow TEXT NOT NULL, " +
            "rain TEXT NOT NULL, " +
            "hot TEXT NOT NULL, " +
	        "cold TEXT NOT NULL);";
        
        
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {        
        db.execSQL("DROP TABLE IF EXISTS menu");
        onCreate(db);
    }
}
