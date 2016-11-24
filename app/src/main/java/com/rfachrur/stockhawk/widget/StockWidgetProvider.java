package com.rfachrur.stockhawk.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.rfachrur.stockhawk.activities.DetailsActivity;
import com.rfachrur.stockhawk.activities.MainActivity;
import com.rfachrur.stockhawk.R;
import com.rfachrur.stockhawk.stockservices.StockTaskService;

/**
 * Created by rfachrur on 11/24/16.
 *
 */

public class StockWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (StockTaskService.ACTION_DATA_UPDATED.equals(intent.getAction())) {
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            int ids[] = manager.getAppWidgetIds(new ComponentName(context, getClass()));
            manager.notifyAppWidgetViewDataChanged(ids, R.id.list_widget);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_stocks);
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widget_header, pendingIntent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                setRemoteAdapter(context, views);
            } else {
                setRemoteAdapterV11(context, views);
            }

            views.setEmptyView(R.id.list_widget, R.id.empty_text);

            Intent clickIntentTemplate = new Intent(context, DetailsActivity.class);
            PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(clickIntentTemplate)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.list_widget, clickPendingIntentTemplate);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setRemoteAdapter(Context context, RemoteViews views) {
        views.setRemoteAdapter(R.id.list_widget,
                new Intent(context, StockWidgetRemoteService.class));
    }

    @SuppressWarnings("deprecation")
    private void setRemoteAdapterV11(Context context, RemoteViews views) {
        views.setRemoteAdapter(0, R.id.list_widget,
                new Intent(context, StockWidgetRemoteService.class));
    }
}
