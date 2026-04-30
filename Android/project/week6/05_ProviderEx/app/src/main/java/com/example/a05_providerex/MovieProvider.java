package com.example.a05_providerex;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MovieProvider extends ContentProvider {
    public static final String TBL_NAME = DbHelper.TBL_NAME;
    public static final Uri CONTENT_URI =
            Uri.parse("content://kr.ac.doowon.MovieProvider/" + TBL_NAME);

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + TBL_NAME;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + TBL_NAME;

    public static final int MOVIE_COLLECTION = 1;
    public static final int SINGLE_MOVIE = 2;

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI("kr.ac.doowon.MovieProvider", TBL_NAME, MOVIE_COLLECTION);
        matcher.addURI("kr.ac.doowon.MovieProvider", TBL_NAME + "/#", SINGLE_MOVIE);
    }

    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        DbHelper helper = new DbHelper(getContext());
        db = helper.getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Override
    public String getType(Uri uri) {
        if (matcher.match(uri) == MOVIE_COLLECTION) {
            return CONTENT_TYPE;
        }
        if (matcher.match(uri) == SINGLE_MOVIE) {
            return CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TBL_NAME);

        if (matcher.match(uri) == SINGLE_MOVIE) {
            qb.appendWhere("_ID=" + uri.getLastPathSegment());
        }

        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sort);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        long rowId = db.insert(TBL_NAME, null, values);
        if (rowId > 0) {
            Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        if (matcher.match(uri) == MOVIE_COLLECTION) {
            count = db.delete(TBL_NAME, selection, selectionArgs);
        } else {
            String s = "_ID = " + uri.getPathSegments().get(1) + "";
            if (TextUtils.isEmpty(selection) == false) {
                s += " AND " + selection;
            }
            count = db.delete(TBL_NAME, s, selectionArgs);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rows = 0;
        if (matcher.match(uri) == MOVIE_COLLECTION) {
            rows = db.update(TBL_NAME, values, selection, selectionArgs);
        } else if (matcher.match(uri) == SINGLE_MOVIE) {
            String id = uri.getLastPathSegment();
            if (TextUtils.isEmpty(selection) == true) {
                rows = db.update(TBL_NAME, values, "_ID=" + id, null);
            } else {
                rows = db.update(TBL_NAME, values, selection + " AND " + "_ID=" + id, selectionArgs);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rows;
    }
}