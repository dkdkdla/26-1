package com.example.ch_moviedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MovieActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText eTitle, eYear, eDirector, eRating, eCountry;
    long targetId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        eTitle = findViewById(R.id.title); eYear = findViewById(R.id.year);
        eDirector = findViewById(R.id.director); eRating = findViewById(R.id.rating);
        eCountry = findViewById(R.id.country);

        targetId = getIntent().getLongExtra("id", -1);

        if (targetId != -1) {
            Cursor cursor = db.rawQuery("SELECT * FROM movies WHERE _id=" + targetId, null);
            if (cursor.moveToFirst()) {
                eTitle.setText(cursor.getString(1));
                eYear.setText(cursor.getString(2));
                eDirector.setText(cursor.getString(3));
                eRating.setText(cursor.getString(4));
                eCountry.setText(cursor.getString(5));
            }
            cursor.close();
        }
    }

    public void insert(View v) {
        db.execSQL("INSERT INTO movies VALUES (null, '" + eTitle.getText() + "', '" +
                eYear.getText() + "', '" + eDirector.getText() + "', '" +
                eRating.getText() + "', '" + eCountry.getText() + "');");
        finish();
    }

    public void update(View v) {
        if (targetId != -1) {
            db.execSQL("UPDATE movies SET title='" + eTitle.getText() + "', year='" + eYear.getText() +
                    "', director='" + eDirector.getText() + "', rating='" + eRating.getText() +
                    "', country='" + eCountry.getText() + "' WHERE _id=" + targetId);
            finish();
        }
    }

    public void delete(View v) {
        if (targetId != -1) {
            db.execSQL("DELETE FROM movies WHERE _id=" + targetId);
            finish();
        }
    }
}