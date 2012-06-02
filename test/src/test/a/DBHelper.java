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
            "CREATE TABLE cars (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
            "car_name TEXT NOT NULL);";
        
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {        
        db.execSQL("DROP TABLE IF EXISTS cars");
        onCreate(db);
    }
}
