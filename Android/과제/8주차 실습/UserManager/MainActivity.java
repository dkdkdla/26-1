package com.example.usermanager;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    UserTblAdapter adapter = null;
    ListView listView;
    Button btnAddUser;

    ActivityResultLauncher<Intent>launcher
            = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode()== RESULT_OK)
                    {
                        DisplayListView();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.userList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                UserTblData user = (UserTblData) adapter.getItem(i);

                Intent in = new Intent(MainActivity.this, DetailActivity.class);
                in.putExtra(DetailActivity.EXTRA_USERID, user.getUserID());
                launcher.launch(in);
            }
        });

        btnAddUser = (Button) findViewById(R.id.add);
        btnAddUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent in = new Intent(MainActivity.this, DetailActivity.class);
                launcher.launch(in);
            }
        });

        DisplayListView();
    }
    public void DisplayListView(){
        adapter = UserTblCtrl.SelectAll();
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        launcher.unregister();
    }
}