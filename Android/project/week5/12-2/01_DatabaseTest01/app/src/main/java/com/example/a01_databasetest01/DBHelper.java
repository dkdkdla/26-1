package com.example.a01_databasetest01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // contacts 테이블 생성: _id(기본키), name(이름), tel(전화번호)
        db.execSQL("CREATE TABLE contacts ( _id INTEGER PRIMARY KEY " +
                " AUTOINCREMENT, name TEXT, tel TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 기존 테이블을 삭제하고 새로 생성 (버전업 시 호출)
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}