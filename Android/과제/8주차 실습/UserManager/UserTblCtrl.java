package com.example.usermanager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTblCtrl {
    public static UserTblData parseUserTblData(ResultSet rs) {
        UserTblData row = new UserTblData();

        try {
            row.setUserID(rs.getString("userID"));
            row.setName(rs.getString("name"));
            row.setBirthYear(rs.getString("birthYear"));
            row.setAddr(rs.getString("addr"));
            row.setMobile1(rs.getString("mobile1"));
            row.setMobile2(rs.getString("mobile2"));
            row.setHeight(rs.getString("height"));
            row.setDate(rs.getString("mDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public static UserTblAdapter SelectAll() {
        UserTblAdapter adapter = new UserTblAdapter();
        String qry = "SELECT * FROM userTbl";
        UserTblData row = null;

        try {
            DbManager.Open();
            ResultSet rs = DbManager.ExecuteQuery(qry);
            while (rs.next()) {
                row = parseUserTblData(rs);
                adapter.addItem(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbManager.Close();
        }
        return adapter;
    }

    public static UserTblData Select(String userID) {
        String qry = "SELECT * FROM userTbl WHERE userID = '" + userID + "'";
        UserTblData row = null;
        try {
            DbManager.Open();
            ResultSet rs = DbManager.ExecuteQuery(qry);
            if (rs.next())
                row = parseUserTblData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbManager.Close();
        }
        return row;
    }

    public static int Insert(UserTblData row) {
        String qry = String.format("INSERT INTO userTbl(userID, name, "
                        + " birthYear, addr, mobile1, mobile2, height, mDate) "
                        + "VALUES ('%s','%s',%s,'%s','%s','%s',%s,GETDATE())",
                row.getUserID(), row.getName(), row.getBirthYear(),
                row.getAddr(), row.getMobile1(), row.getMobile2(),
                row.getHeight());
        int cnt = 0;
        try {
            DbManager.Open();
            cnt = DbManager.ExecuteUpdate(qry);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbManager.Close();
        }
        return cnt;
    }

    public static int Insert(String userID, String name, String birthYear,
                             String addr, String mobile1, String mobile2, String height) {
        UserTblData row = new UserTblData();

        row.setUserID(userID);
        row.setName(name);
        row.setBirthYear(birthYear);
        row.setAddr(addr);
        row.setMobile1(mobile1);
        row.setMobile2(mobile2);
        row.setHeight(height);

        return Insert(row);
    }

    public static int Update(String orgUserID, String userID,
                             String name, String birthYear, String addr,
                             String mobile1, String mobile2, String height) {
        int cnt = 0;
        String qry = String.format("UPDATE userTbl SET userID='%s', "
                        + "name='%s', birthYear=%s, addr='%s', mobile1='%s', "
                        + "mobile2='%s', height=%s WHERE userID='%s'",
                userID, name, birthYear, addr, mobile1, mobile2, height, orgUserID);
        try {
            DbManager.Open();
            cnt = DbManager.ExecuteUpdate(qry);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbManager.Close();
        }
        return cnt;
    }

    public static int Update(String orgUserID, UserTblData row) {
        return Update(orgUserID, row.getUserID(), row.getName(), row.getBirthYear(),
                row.getAddr(), row.getMobile1(), row.getMobile2(), row.getHeight());
    }

    public static int Delete(String userID) {
        String qry = "DELETE FROM userTbl WHERE userID = '" + userID + "'";
        int cnt = 0;
        try {
            DbManager.Open();
            cnt = DbManager.ExecuteUpdate(qry);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbManager.Close();
        }
        return cnt;
    }
}