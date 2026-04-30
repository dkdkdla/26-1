package com.example.usermanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;
public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_USERID = "UserID";
    String CurUserID = null;
    EditText etUserID, etName, etBirthYear, etAddr, etMobile1, etMobile2, etHeight, etDate;
    TableRow trDate;
    Button btnAction, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etUserID = (EditText) findViewById(R.id.userId);
        etName = (EditText) findViewById(R.id.userName);
        etBirthYear = (EditText) findViewById(R.id.birthYear);
        etAddr = (EditText) findViewById(R.id.addr);
        etMobile1 = (EditText) findViewById(R.id.mobile1);
        etMobile2 = (EditText) findViewById(R.id.mobile2);
        etHeight = (EditText) findViewById(R.id.height);
        etDate = (EditText) findViewById(R.id.date);

        trDate = (TableRow) findViewById(R.id.tr_date);

        btnAction = (Button) findViewById(R.id.action);
        btnDelete = (Button) findViewById(R.id.delete);
        btnCancel = (Button) findViewById(R.id.cancel);

        setResult(RESULT_CANCELED);

        Intent in = getIntent();
        CurUserID = in.getStringExtra(EXTRA_USERID);

        if(CurUserID != null){
            btnAction.setText("수정");
            btnDelete.setVisibility(View.VISIBLE);
            etUserID.setEnabled(false);
            DispalyData();
        }
        else{
            btnAction.setText("입력");
            btnDelete.setVisibility(View.GONE);
            trDate.setVisibility(View.GONE);
        }

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CurUserID == null)
                    InsertData();
                else
                    UpdateData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder adl = new AlertDialog.Builder(DetailActivity.this);

                    adl.setMessage("자료를 삭제하시겠습니까?");
                    adl.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DeleteData();
                                }
                            });

                    adl.setNegativeButton("아니오",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(DetailActivity.this,
                                            "삭제가 취소되었습니다.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    AlertDialog ad = adl.create();
                    ad.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.this.finish();
            }
        });
    }
    public void DispalyData(){
        UserTblData user = UserTblCtrl.Select(CurUserID);
        if(user != null){
            etUserID.setText(user.getUserID());
            etName.setText(user.getName());
            etBirthYear.setText(user.getBirthYear());
            etAddr.setText(user.getAddr());
            etMobile1.setText(user.getMobile1());
            etMobile2.setText(user.getMobile2());
            etHeight.setText(user.getHeight());
            etDate.setText(user.getDate());
        }
    }
    public void InsertData(){
        int cnt = UserTblCtrl.Insert(
                etUserID.getText().toString(),etName.getText().toString(),
                etBirthYear.getText().toString(),etAddr.getText().toString(),
                etMobile1.getText().toString(),etMobile2.getText().toString(),
                etHeight.getText().toString());
        if(cnt > 0){
            Toast.makeText(this, "입력 완료", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            DetailActivity.this.finish();
        }
        else
            Toast.makeText(this, "Insert Error", Toast.LENGTH_SHORT).show();
    }

    public void UpdateData(){
        int cnt = UserTblCtrl.Insert(
                etUserID.getText().toString(),etName.getText().toString(),
                etBirthYear.getText().toString(),etAddr.getText().toString(),
                etMobile1.getText().toString(),etMobile2.getText().toString(),
                etHeight.getText().toString());
        if(cnt > 0){
            Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            DetailActivity.this.finish();
        }
        else
            Toast.makeText(this, "Update Error", Toast.LENGTH_SHORT).show();
    }

    public void DeleteData(){
        int cnt = UserTblCtrl.Delete(CurUserID);
        if(cnt > 0){
            Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            DetailActivity.this.finish();
        }
        else
            Toast.makeText(this, "Delete Error", Toast.LENGTH_SHORT).show();
    }
}
