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

" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '°£Àå¶±ººÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '°¨ÀÚÄ¨','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '°¨ÀÚÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '°è¶õÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '°íµî¾î±¸ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '°íµî¾î±èÄ¡Âî°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '°íÃßÀå»ï°ã»ì','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '°íÃßÀåÂî°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '°ñ¹ðÀÌ¹«Ä§','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '°öÃ¢Àü°ñ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '±¼¹«Ä§','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '±ÃÁß¶±ººÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '±è¸»ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '±èÄ¡°¨ÀÚÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '±èÄ¡°è¶õ¸»ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '±èÄ¡µÎ·çÄ¡±â','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '±èÄ¡ºñºö±¹¼ö','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '±èÄ¡Á¦À°ººÀ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '±èÄ¡Âò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '±ñ¼î»õ¿ì','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '±ñÇ³»õ¿ì','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '²¿¸·¹«Ä§','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '²ÇÄ¡Âî°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '²É°ÔÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '²É°ÔÆ¢±è','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '³«ÁöººÀ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '³«Áö¿¬Æ÷ÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '³«ÁöÀü°ñ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '³«ÁöÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '´ß°£ÀåÁ¶¸²','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '´ß°­Á¤','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '´ß²¿Ä¡','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '´ß¶ËÁýººÀ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '´ß¸Å¿îÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '´ß¹öÅÍ±¸ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '´ßÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '´ëÇÏ±¸ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '´ëÇÕ¼úÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '´õ´ö±¸ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '´õ´ö½¡ºÒ´ß°¥ºñ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'µµ¸®¹ð¹ðÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'µµÅä¸®¹¬¹«Ä§','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'µ¿·¡ÆÄÀü','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'µ¿ÅÂ±¹','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'µ¿ÅÂÀü','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'ÅëÅÂÂî°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'µ¿ÅÂÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ÅëÆÄÀ°','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                    'µÅÁö°í±â±èÄ¡Âî°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'µÅÁö°öÃ¢±¸ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'µÅÁöµî»ÀÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'µÅÁöºÒ°í±â','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'µÎºÎ±èÄ¡','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'µÎºÎµÎ·çÄ¡±â','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'µÎºÎ½ºÅ×ÀÌÅ©','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'µÎºÎÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'µÎºÎÅÁ¼öÀ°','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'µÎºÎÆ¢±è','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'µî°¥ºñÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '¶±ÀâÃ¤','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '¶óÁ¶±â','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¸¶´ÃÄ¡Å²','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '¸¶¸£°Ô¸®Å¸ÇÇÀÚ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                      '¸Å¿î¹ÙÁö¶ô°íÃßººÀ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '¸Å¿îÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¸ðµë³¿ºñ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¸ðµëÆ¢±è','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '¹¬ÀºÁö±èÄ¡Âò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '¹°È¸','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '¹ÌÆ®º¼','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '¹Î¹°¸Å¿îÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¹ö¼¸ÀâÃ¤','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'º£ÀÌÄÁ¸»ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'ºÎ´ëÂî°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'ºÎÃßÀâÃ¤','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ºÎÃßÀü','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ºÎÄ§°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'ºÒ³«Àü°ñ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        'ºÒ´ß','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '»ï°ã»ìÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '»õ¿ìÆ¢±è','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '»ýÅÂÂî°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '¼Ò½ÃÁö¾ßÃ¤ººÀ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '¼Û¾îÈ¸','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                      '¼è°í±â°£Àå¾ç³ä±¸ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '¼è°í±âººÀ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  '¼è°í±â¾ç³äÆ¢±è','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '¼ø´ë','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¼ø´ëººÀ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '½ºÅ°¾ßÅ°','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '¾ËÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        '¾à°ú','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          '¾î¹¬±¹','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¾î¹¬Àü°ñ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '¿À»ïºÒ°í±â','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '¿ÀÂ¡¾îº¸½Ó','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '¿ÀÂ¡¾îºÒ°í±â','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '¿ÀÂ¡¾î¼ø´ë','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¿ÀÂ¡¾îÀü','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¿ÀÂ¡¾îÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                '¿ÀÄÚ³ë¹Ì¾ßÅ°','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¿ÀÇâÀåÀ°','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¿Õ¹®¾îÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              '¿ì·°¸Å¿îÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            '¿þÁö°¨ÀÚ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'À¯»ê½½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        'À°È¸','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'Â¥Àå¶±ººÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'Àå¾î±¸ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'Àü°ñ³¿ºñ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'Àüº¹È¸','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'Àü¾î±¸ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'Àü¾îÈ¸','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'Á¦À°ººÀ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'Á¶°³Âò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'Á¶°³ÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'Á¶±â¸Å¿îÅÁ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                  'ÁÖ²Ù¹Ì»þºÎ»þºÎ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ÂüÄ¡Àü','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'ÂüÄ¡Âî°³','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ÂüÄ¡È¸','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'Âý½ºÅ×ÀÌÅ©','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'Ä¡Áî°è¶õ¸»ÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'Ä¡ÁîºÒ´ß¹ß','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'Ä¡Áî½ºÆ½','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'Ä¡Å²»ø·¯µå','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'Ä«³ªÆä','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'Ä«·¹¶±ººÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'ÄÉÀÌÁØ»ø·¯µå','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'Äá³ª¹°Âò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ÅÁÆòÃ¤','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'Æ¢±èµÎºÎ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        'ÆÄÀü','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ÆÈº¸Ã¤','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',        'ÆËÄÜ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'Ç¥°í¹ö¼¸Àü','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'ÇØ¹°µ¿±×¶û¶¯','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',              'ÇØ¹°¶±ººÀÌ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'ÇØ¹°¶±Âò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',                'ÇØ¹°»þºÎ»þºÎ','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'ÇØ¹°Âò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',            'ÇØ¹°ÆÄÀü','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'È«ÇÕ±¹','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'È«ÇÕÂò','test.png','0','0','0','0'); " +
" insert into menu ('code', 'detailCode', 'menuName', 'pictureName', 'snow', 'rain', 'hot', 'cold') values('1','',          'È«ÇÕÅÁ','test.png','0','0','0','0'); " ; 
 
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
