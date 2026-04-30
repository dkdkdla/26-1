package com.example.a01_roomdbmemo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Memo {
    @PrimaryKey(autoGenerate = true) // ID 자동 증가
    public int id;
    public String title;  // 메모 제목
    public String content; // 메모 내용
}