package com.fochmobile.plugin_media_finder;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fochmobile.plugin_media_finder.utils.PermissionHandler;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** PluginMediaFinderPlugin */
public class PluginMediaFinderPlugin implements MethodCallHandler, PluginRegistry.RequestPermissionsResultListener {
    private static final int setMethodCallHandler = 3777;


    private boolean executeAfterPermissionGranted;
    private Result pendingResult;

    private Activity activity;
    private static PluginMediaFinderPlugin instance;

    private PluginMediaFinderPlugin(Activity activity, MethodChannel channel) {
        this.activity = activity;
        channel.setMethodCallHandler(this);
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "plugin_media_finder");
        instance = new PluginMediaFinderPlugin(registrar.activity(), channel);
        channel.setMethodCallHandler(instance);
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("getPermissionState")) {
            result.success(PermissionHandler.checkStoragePermissions(activity, PermissionHandler.STORAGE_REQUEST_CODE));
        } else {
            result.notImplemented();
        }
    }

    @Override
    public boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i : grantResults) {
            if (i == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
}
