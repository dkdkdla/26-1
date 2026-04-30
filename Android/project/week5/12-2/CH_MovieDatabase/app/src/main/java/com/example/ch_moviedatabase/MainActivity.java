package com.example.ch_moviedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        listView = findViewById(R.id.list);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, MovieActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movies", null);

        String[] from = {"title", "year", "director", "rating", "country"};
        int[] to = {R.id.t_title, R.id.t_year, R.id.t_director, R.id.t_rating, R.id.t_country};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.movie_item, cursor, from, to, 0);
        listView.setAdapter(adapter);
    }

    public void goMovieActivity(android.view.View v) {
        startActivity(new Intent(this, MovieActivity.class));
    }
}