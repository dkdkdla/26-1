package com.example.a01_roomdbmemo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MemoDao memoDao;
    private ListView listView;
    private EditText etTitle, etContent;
    private Button btnAddUpdate;

    private List<Memo> memoList;
    private ArrayAdapter<String> adapter;

    // 수정 모드 관리를 위한 변수
    private boolean isEditMode = false;
    private Memo selectedMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. View 연결
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnAddUpdate = findViewById(R.id.btnAddUpdate);
        listView = findViewById(R.id.listView);

        // 2. Room DB 초기화 (메인 스레드 쿼리 허용)
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "memo-db")
                .allowMainThreadQueries()
                .build();
        memoDao = db.memoDao();

        // 3. 저장/수정 버튼 클릭 이벤트
        btnAddUpdate.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "제목과 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isEditMode) {
                // [수정 루틴] 기존 메모 업데이트
                selectedMemo.title = title;
                selectedMemo.content = content;
                memoDao.update(selectedMemo);

                isEditMode = false;
                btnAddUpdate.setText("저장하기");
                Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                // [저장 루틴] 새 메모 삽입
                Memo newMemo = new Memo();
                newMemo.title = title;
                newMemo.content = content;
                memoDao.insert(newMemo);
                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }

            // 입력창 초기화 및 리스트 갱신
            etTitle.setText("");
            etContent.setText("");
            loadMemos();
        });

        // 4. 리스트 아이템 클릭 이벤트 (수정 모드 진입)
        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedMemo = memoList.get(position); // 선택된 메모 객체 저장
            etTitle.setText(selectedMemo.title);   // 화면에 데이터 로드
            etContent.setText(selectedMemo.content);

            isEditMode = true;
            btnAddUpdate.setText("수정완료"); // 사용자 알림용 버튼 텍스트 변경
        });

        // 5. 리스트 아이템 롱 클릭 이벤트 (삭제 확인 대화상자 포함)
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            // 삭제할 아이템 미리 확보
            Memo memoToDelete = memoList.get(position);

            // 대화상자 생성
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("삭제 확인")
                    .setMessage("정말 이 메모를 삭제하시겠습니까?")
                    .setPositiveButton("삭제", (dialog, which) -> {
                        // [확인] 버튼 클릭 시 삭제 수행
                        memoDao.delete(memoToDelete);
                        Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        loadMemos(); // 리스트 갱신
                    })
                    .setNegativeButton("취소", (dialog, which) -> {
                        // [취소] 버튼 클릭 시 아무것도 하지 않고 닫기
                        dialog.dismiss();
                    })
                    .show();

            return true; // 이벤트 종료 (짧은 클릭이 동시에 발생하는 것 방지)
        });

        // 초기 데이터 로드
        loadMemos();
    }

    // 데이터베이스의 내용을 ListView에 다시 뿌려주는 메서드
    private void loadMemos() {
        memoList = memoDao.getAll();
        List<String> displayItems = new ArrayList<>();

        for (Memo m : memoList) {
            // [제목] 내용 형식으로 리스트에 노출
            displayItems.add("[" + m.title + "] " + m.content);
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayItems);
        listView.setAdapter(adapter);
    }
}