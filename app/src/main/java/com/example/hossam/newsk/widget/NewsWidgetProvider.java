package com.example.hossam.newsk.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.hossam.newsk.MainActivity;
import com.example.hossam.newsk.R;

/**
 * Created by Hossam on 5/2/2017.
 */

public class NewsWidgetProvider extends AppWidgetProvider {
    public static final String UPDATE_MEETING_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setRemoteAdapter(R.id.widget_list,
                new Intent(context, NewsWidgetRemoteViewsService.class));

        Intent appIntent = new Intent(context, MainActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, 0);

        views.setOnClickPendingIntent(R.id.appwidget_text, appPendingIntent);


        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        Toast.makeText(context, "hello", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(UPDATE_MEETING_ACTION)) {

            int appWidgetIds[] = mgr.getAppWidgetIds(new ComponentName(context, NewsWidgetProvider.class));


            mgr.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);


        }
        super.onReceive(context, intent);
    }
}
