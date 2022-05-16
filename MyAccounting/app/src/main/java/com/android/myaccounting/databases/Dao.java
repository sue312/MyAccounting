package com.android.myaccounting.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.myaccounting.Config;
import com.android.myaccounting.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Dao {

    private static final String TAG = "Dao";
    public static DatabaseHelper helper;

    public Dao(Context context) {

        //创建数据库
        helper = new DatabaseHelper(context);

    }

    //data增
    public void dataInsert() {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("expenditure", Data.expenditure);
        values.put("income", Data.income);
        values.put("time",Data.time);
        values.put("reason",Data.reason);
        db.insert(Config.TABLE_NAME, null, values);
        db.close();
    }

    //data删
    public void dataDelete() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(Config.TABLE_NAME,null,null);
        db.close();
    }

    //data查
    public static Cursor dataQuery() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(Config.TABLE_NAME, null, null, null, null, null, null);
        return cursor;

    }

    //将游标转换成List，其中数据库每一列转换成一个Map
    public static ArrayList<Map<String, String>> converCusorTolist(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        //遍历Cursor结果集
        while (cursor.moveToNext()) {
            //将结果集中的数据存入ArrayList中
            Map<String, String> map = new HashMap<>();
            //取出查询记录中的值
            map.put("expenditure", "支出:" + cursor.getInt(0));
            map.put("income", "收入:" + cursor.getInt(1));
            map.put("time", timeShift(cursor.getLong(2)));
            map.put("reason", "缘由:" + cursor.getString(3));
            result.add(map);
        }
        cursor.close();
        return result;
    }

    public static String timeShift(Long times) {
        //SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒E", Locale.getDefault());
        SimpleDateFormat sdf =new SimpleDateFormat("MM月dd日HH时E", Locale.getDefault());
        String time = sdf.format(times);
        return time;
    }

}