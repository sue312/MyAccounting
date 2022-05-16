package com.android.myaccounting.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.myaccounting.Config;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Log常量
    private static final String TAG = "DatabasesHelper";

    /**
     * @param context 上下文
     *                name 数据库名
     *                factory 游标工厂
     *                version 版本
     */
    public DatabaseHelper(@Nullable Context context) {
        super(context, Config.DATABASE_NAME, null, Config.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建时回调
        Log.d(TAG, "创建数据库...");

        //创建表
        String sql = "create table " + Config.TABLE_NAME + "(expenditure integer,income integer,time long,reason varchar)";
        db.execSQL(sql);
        Log.d(TAG, "onCreate: "+sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级时回调
        Log.d(TAG, "更新数据库...");

        //sql: alter table table_name add phone integer
        //String sql;

        switch (oldVersion) {
            case 1:
//                sql = "alter table " +Config.TABLE_NAME+ " add phone integer";
//                db.execSQL(sql);
//                Log.d(TAG,sql);
                break;
            case 2:
//                sql = "alter table " +Config.TABLE_NAME+ " add address varchar";
//                db.execSQL(sql);
//                Log.d(TAG,sql);
                break;
            case 3:
                break;
        }
    }
}

