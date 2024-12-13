package ru.msfd.lab6;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import models.ImageInfo;

public class ImageListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    ArrayList<ImageInfo> data = null;
    Context context = null;
    int widgetId = -1;

    final static String NAME_PREFIX = "Название: ";
    final static String SIZE_PREFIX = "Размер, Мб: ";

    public ImageListRemoteViewsFactory(Context context, Intent intent)
    {
        this.context = context;
        widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate()
    {
        this.data = new ArrayList<>();
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public long getItemId(int position)
    {
        return data.get(position).getId();
    }

    @Override
    public RemoteViews getLoadingView()
    {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position)
    {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.image_list_item_layout);
        remoteViews.setTextViewText(R.id.image_name_text_view, NAME_PREFIX + data.get(position).getName());
        remoteViews.setTextViewText(R.id.image_size_text_view, SIZE_PREFIX + setPrecision(data.get(position).getSize(), 3));
        return remoteViews;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    private static Double toMB(long bytes)
    {
        return (Double) (bytes / (1024.d * 1024.d));
    }

    private static String setPrecision(double amt, int precision){
        return String.format("%." + precision + "f", amt);
    }

    @Override
    public boolean hasStableIds() { return true; }

    @Override
    public void onDataSetChanged()
    {
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID,
                               MediaStore.Images.Media.DISPLAY_NAME,
                               MediaStore.Images.Media.SIZE },
                null,
                null,
                null);
        if(cursor != null)
        {
            data.clear();
            ArrayList<ImageInfo> tempData = new ArrayList<>();
            while (cursor.moveToNext())
            {
                tempData.add(new ImageInfo( cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)),
                        toMB(cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.SIZE)))
                ));
            }
            data.addAll(tempData);
            cursor.close();
        }
    }

    @Override
    public void onDestroy() { }
}
