package ru.msfd.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViewsService;

public class ImageListRemoteViewsService  extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent)
    {
        Intent permissionIntent = new Intent(this, PermissionRequestActivity.class);
        permissionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(permissionIntent);
        return new ImageListRemoteViewsFactory(getApplicationContext(), intent);
    }
}
