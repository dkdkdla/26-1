package com.example.usermanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class UserTblAdapter extends BaseAdapter {
    TextView tvUserID, tvName;
    ArrayList<UserTblData> users = new ArrayList<UserTblData>();

    @Override
    public int getCount(){
        return users.size();
    }

    @Override
    public Object getItem(int position){
        return users.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        final Context context = viewGroup.getContext();

        LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item, viewGroup,false);

        tvUserID = (TextView) view.findViewById(R.id.userId);
        tvName = (TextView) view.findViewById(R.id.userName);

        UserTblData user = users.get(position);
        tvUserID.setText(user.getUserID());
        tvName.setText(user.getName());

        return view;
    }

    public void addItem(String userID, String name, int birthYear, String addr,
                        String mobile1, String mobile2, int height, String date){
        UserTblData user = new UserTblData();

        user.setUserID(userID);
        user.setName(name);
        user.setBirthYear(birthYear);
        user.setAddr(addr);
        user.setMobile1(mobile1);
        user.setMobile2(mobile2);
        user.setHeight(height);
        user.setDate(date);

        users.add(user);
    }

    public void addItem(UserTblData user){
        users.add(user);
    }

    public void clear(){
        users.clear();
    }
}
