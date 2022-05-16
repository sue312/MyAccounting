package com.android.myaccounting.databases;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import com.android.myaccounting.R;
import com.android.myaccounting.widgetProvider.MyWidgetProvider;

public class GetDate extends AsyncTask {

    private String TAG = "wangxin666";
    AppWidgetManager awm = null;
    ComponentName componentName = null;
    Context mContext;
    private String et2;

    public GetDate(Context context) {
        mContext = context;
    }

    public GetDate(Context context, String s2) {
        mContext = context;
        et2 = s2;
    }

    @Override
    protected void onPreExecute() {
        if(awm==null){
            awm = AppWidgetManager.getInstance(mContext);
        }
        if(componentName==null){
            componentName = new ComponentName(mContext, MyWidgetProvider.class);
        }

        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        //更新界面
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.my_appwidget);
        remoteViews.setTextViewText(R.id.tv_temperature,et2);
        awm.updateAppWidget(componentName, remoteViews);
        super.onPostExecute(o);
    }
}
