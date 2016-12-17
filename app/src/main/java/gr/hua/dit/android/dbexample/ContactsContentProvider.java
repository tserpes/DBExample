package gr.hua.dit.android.dbexample;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by tserpes on 16/12/16.
 */

public class ContactsContentProvider extends ContentProvider {
    private ContactsHelper helper;
    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    @Override
    public boolean onCreate() {
        helper = new ContactsHelper(getContext());
        uriMatcher.addURI("gr.dit.hua.android.dbexample","contacts/#", 1);
        //e.g. uri = "content://gr.dit.hua.android.DBExample/contacts/2" translates to: "select * from contacts where _ID=2"
        uriMatcher.addURI("gr.dit.hua.android.dbexample","contacts",2);
        //e.g. uri = "content://gr.dit.hua.android.DBExample/contacts" translates to: "select * from contacts"
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();

        switch (uriMatcher.match(uri)){
            case 1:
                selection = "_ID=?";
                selectionArgs[0] = uri.getLastPathSegment();
                break;
            case 2:
                selection = null;
                selectionArgs = null;
                sortOrder = null;
                break;
            default:
                break;
        }
        Cursor cursor = db.query(ContactsHelper.TABLE_NAME,projection, selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch(uriMatcher.match(uri)){
            case 1:
                try {
                    throw new Exception("Unsupported URI");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(ContactsHelper.TABLE_NAME,null,values);
        uri = Uri.parse(uri.getScheme()+"/"+uri.getAuthority()+"/"+uri.getPath()+"/"+id);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
