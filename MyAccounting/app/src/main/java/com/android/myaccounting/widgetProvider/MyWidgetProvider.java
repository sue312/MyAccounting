package com.android.myaccounting.widgetProvider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.android.myaccounting.R;
import com.android.myaccounting.databases.Data;
import com.android.myaccounting.databases.DataProcessing;
import com.android.myaccounting.databases.GetDate;
import com.android.myaccounting.view.MainActivity;

public class MyWidgetProvider extends AppWidgetProvider {

    private String TAG = "wangxin666";
    private RemoteViews views;
    private String et2;

    private void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        DataProcessing dp = new DataProcessing(context);
        dp.dataQuery();
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views = new RemoteViews(context.getPackageName(), R.layout.my_appwidget);
        views.setTextViewText(R.id.tv_home,"结余");
        views.setTextViewText(R.id.tv_temperature, Data.getBalance()+"");
        views.setOnClickPendingIntent(R.id.ll_1, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG,"onReceive"+intent.getAction());
        if ("android.appwidget.action.APPWIDGET_UPDATE".equals(intent.getAction())) {
            // “更新”广播
            Bundle bundle = intent.getExtras();
            et2 = bundle.getString("et2");
            Log.d(TAG, "onReceive: et2= "+et2);
            if (et2 != null) {
                if (et2.isEmpty()) {
                    Log.d(TAG, "onReceive: ");
                }else {
                    GetDate getDate = new GetDate(context,et2);
                    getDate.execute();
                }
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG,"onUpdate");
        for (int appWidgetId : appWidgetIds) {
            updateWidget(context,appWidgetManager,appWidgetId);
        }
    }

}
