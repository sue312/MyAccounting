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
        Data.balance = 0;
        while (cursor.moveToNext()) {
            Data.expenditure = cursor.getInt(0);
            Data.income = cursor.getInt(1);
            Data.time = cursor.getLong(2);
            Data.reason = cursor.getString(3);
            Data.balance = Data.balance + Data.income - Data.expenditure;
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
