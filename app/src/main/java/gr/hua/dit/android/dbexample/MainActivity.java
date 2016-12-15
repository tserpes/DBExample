package gr.hua.dit.android.dbexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ContactsHelper helper = new ContactsHelper(this);

        Button insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameET = (EditText) findViewById(R.id.nameInput);
                EditText phoneET = (EditText) findViewById(R.id.phoneInput);
                String name = nameET.getText().toString();
                String phone = phoneET.getText().toString();

                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(ContactsHelper.KEY_NAME,name);
                values.put(ContactsHelper.KEY_PHONE,phone);
                db.insert(ContactsHelper.TABLE_NAME,null,values);
            }
        });

        Button selectButton = (Button) findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] cols = {ContactsHelper.KEY_NAME};
                String selection = ContactsHelper.KEY_ID+"=?";
                String[] selargs ={"2"};
                Cursor c = db.query(ContactsHelper.TABLE_NAME,cols,selection,selargs,null,null,null);
                if (c.moveToFirst()){
                    do{
                        String name = c.getString(0);
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                    }while(c.moveToNext());
                }
            }
        });
    }
}





