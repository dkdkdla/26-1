package com.example.usermanager;

public class UserTblData {

    private String userID;
    private String name;
    private int birthYear;
    private String addr;
    private String mobile1;
    private String mobile2;
    private int height;
    private String date;

    public String getUserID(){return userID;}
    public void setUserID(String userID) {this.userID = userID;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getBirthYearInt() {return birthYear;}
    public String getBirthYear(){
        return Integer.toString(birthYear);
    }

    public void setBirthYear(int birthYear) {this.birthYear=birthYear;}
    public void setBirthYear(String birthYear){
        this.birthYear = Integer.parseInt(birthYear.trim());
    }

    public String getAddr() {return addr;}
    public void setAddr(String addr) {this.addr=addr;}

    public String getMobile1(){return mobile1;}
    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {return mobile2;}
    public void setMobile2(String mobile2){this.mobile2=mobile2;}

    public int getHeightInt() {return height;}
    public String getHeight(){
        return Integer.toString(height);
    }

    public void setHeight(int height){this.height=height;}
    public void setHeight(String height){
        if(height ==null)
            this.height = 0;
        else this.height=Integer.parseInt(height.trim());
    }

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
}
