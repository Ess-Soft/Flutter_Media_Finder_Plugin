package com.fochmobile.plugin_media_finder.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionHandler {
    public static final int STORAGE_REQUEST_CODE = 777;


    public static boolean getStoragePermissionState(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String requiredPermission = "android.permission.READ_EXTERNAL_STORAGE";
            int checkVal = activity.checkCallingOrSelfPermission(requiredPermission);
            return checkVal==PackageManager.PERMISSION_GRANTED;
        } else {
            //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    public static void requestStoragePermission(Activity activity, int requestCode){
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                requestCode);
    }
}
