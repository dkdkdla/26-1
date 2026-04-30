package com.example.a01_roomdbmemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDao {
    @Query("SELECT * FROM Memo ORDER BY id DESC")
    List<Memo> getAll();

    @Insert
    void insert(Memo memo);

    @Update
        // 수정 루틴 추가
    void update(Memo memo);

    @Delete // 삭제 루틴 추가
    void delete(Memo memo);
}