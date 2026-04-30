package com.example.usermanager;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManager {

    private static Connection connection = null;

    // DB 연결 (매개변수 지정 방식)
    public static void Open(String ip, String port, String dbName, String userName, String password) {
        final String CLASS_NAME = "net.sourceforge.jtds.jdbc.Driver";
        final String JTDS_PREFIX = "jdbc:jtds:sqlserver://";
        String url;

        if (port != null)
            url = JTDS_PREFIX + ip + ":" + port + "/" + dbName;
        else
            url = JTDS_PREFIX + ip + "/" + dbName;

        // StrictMode는 네트워크 연결을 메인스레드에서 동작할 예정인데 이로 인해 속도가 느려지는 것을 감지하고
        // Android Not Response를 방지할 수 있도록 미리 탐지하는 역할을 함
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            // 연결 시도
            Class.forName(CLASS_NAME); // jdbc 드라이버 클래스 적용
            connection = DriverManager.getConnection(url, userName, password);
            Log.i("jdbc", "연결 성공");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.i("jdbc", "Class를 찾을 수 없음");
        } catch (SQLException e) {
            Log.i("jdbc", "연결 실패");
        }
    }

    // 고정 DB 서버 Open
    public static void Open() {
        Open("192.168.0.52", "1433", "sqlDB", "BrProgrammer", "pass");
    }

    // SELECT 구문 실행
    public static ResultSet ExecuteQuery(String qry) {
        ResultSet rs = null;

        if (connection == null)
            Open();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // INSERT, UPDATE, DELETE 구문 실행
    public static int ExecuteUpdate(String qry) {
        if (connection == null)
            Open();

        int actCnt = 0;

        try {
            PreparedStatement preStmt = connection.prepareStatement(qry);
            actCnt = preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actCnt;
    }

    // DB 연결 닫기
    public static void Close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}