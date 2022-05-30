package com.android.myaccounting.databases;

import android.content.Context;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class DataProcessing {

    private Context mContext;
    public ArrayList<Map<String,String>> listItems;

    public DataProcessing(Context context) {
        mContext = context;
    }

    public void dataInsert() {
        Dao dao = new Dao(mContext);
        dao.dataInsert();
    }

    public void dataQuery() {
        Dao dao = new Dao(mContext);
        Cursor cursor = dao.dataQuery();
        Data.setBalance(0);
        while (cursor.moveToNext()) {
            Data.setExpenditure(cursor.getInt(0));
            Data.setIncome(cursor.getInt(1));
            Data.setTime(cursor.getLong(2));
            Data.setReason(cursor.getString(3));
            Data.setBalance(Data.getBalance() + Data.getIncome() - Data.getExpenditure());
        }
    }

    public ArrayList dataTolist() {
        Dao dao = new Dao(mContext);
        Cursor cursor = dao.dataQuery();
        //获取数据库的列表
        listItems = dao.converCusorTolist(cursor);
        return listItems;
    }



}
