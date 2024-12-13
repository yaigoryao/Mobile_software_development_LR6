package ru.msfd.lab6;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ImageListWidgetProvider extends AppWidgetProvider {
    @Override
    public void onEnabled(Context context)
    {
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetsIds)
    {
        super.onUpdate(context, appWidgetManager, appWidgetsIds);
        for (int appWidgetId : appWidgetsIds)
        {
            updateWidget(context, appWidgetManager, appWidgetId);

        }
    }

    private void updateWidget(Context context, AppWidgetManager appWidgetManager, Integer appWidgetId)
    {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.image_list_widget_layout);
        Intent adapter = new Intent(context, ImageListRemoteViewsService.class);
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        remoteViews.setRemoteAdapter(R.id.images_list_view, adapter);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetsIds)
    {
        super.onDeleted(context, appWidgetsIds);
    }

    @Override
    public void onDisabled(Context context)
    {
        super.onDisabled(context);
    }
}
