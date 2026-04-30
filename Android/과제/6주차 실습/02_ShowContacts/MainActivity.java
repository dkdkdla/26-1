package com.example.a02_showcontacts;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    public TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (TextView) findViewById(R.id.output);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS},
                1);
    }

    @SuppressLint("Range") // 경고 제거용 : getColumnIndex() 에서 -1 이 반환 가능
    public void onClick(View target) {
        ContentResolver cr = getContentResolver();
        Cursor cCur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        while (cCur.moveToNext()) {
            String id = cCur.getString(cCur.getColumnIndex(ContactsContract.Contacts._ID));
            if (Integer.parseInt(cCur.getString(
                    cCur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                message.append("\nNAME: " +
                        cCur.getString(
                                cCur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));

                Cursor dCur = cr.query(ContactsContract.Data.CONTENT_URI,
                        null,
                        ContactsContract.Data.CONTACT_ID + " =? " + " AND "
                                + ContactsContract.Data.MIMETYPE + " = '"
                                + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                                + "'", new String[]{id}, null);

                while (dCur.moveToNext()) {
                    message.append("\nPHONE NUMBER: " +
                            dCur.getString(
                                    dCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                }
                dCur.close();
            }
            message.append("\n\n");
        }
        cCur.close();
    }
}