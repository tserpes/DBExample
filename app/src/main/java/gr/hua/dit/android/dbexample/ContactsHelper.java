package gr.hua.dit.android.dbexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tserpes on 02/12/16.
 */

public class ContactsHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CONTACTS_DB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "CONTACTS";
    public static final String KEY_ID = "_ID";
    public static final String KEY_NAME = "_NAME";
    public static final String KEY_PHONE = "PHONE";
    public static final String SQL_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" ("+
            KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_NAME+" TEXT, "+
            KEY_PHONE+" TEXT "+
            ");";

    public ContactsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
