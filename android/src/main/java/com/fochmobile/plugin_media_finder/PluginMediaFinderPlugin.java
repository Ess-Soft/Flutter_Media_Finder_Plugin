package com.fochmobile.plugin_media_finder;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.fochmobile.plugin_media_finder.model.Music;
import com.fochmobile.plugin_media_finder.utils.AudioUtils;
import com.fochmobile.plugin_media_finder.utils.PermissionHandler;
import com.fochmobile.plugin_media_finder.utils.VideoUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** PluginMediaFinderPlugin */
public class PluginMediaFinderPlugin implements MethodCallHandler, PluginRegistry.RequestPermissionsResultListener {
    private String TAG = PluginMediaFinderPlugin.class.getSimpleName();

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
        switch (call.method) {
            case "getAllSongs":
                if (PermissionHandler.getStoragePermissionState(activity)) {
                    // permission is granted... just load all songs !
                    Log.e(TAG, "Permission is granted !");

                    ArrayList<String> musicsList = new ArrayList<>();
                    List<Music> musics = AudioUtils.getAllMusic(activity);

                    // Here we use Gson to convert Music object to json (type String)
                    // Cuz we can't send custom objects with result.success method
                    Gson gson = new GsonBuilder().create();

                    if (musics != null) {
                        for(Music m : musics) {
                            musicsList.add(gson.toJson(m));
                        }
                    }

                    result.success(musicsList);
                } else {
                    // permission isn't granted we should first request it and then load songs
                    Log.e(TAG, "Permission is denied !");
                    PermissionHandler.requestStoragePermission(activity, PermissionHandler.STORAGE_REQUEST_CODE);
                }
                break;
            case "getAllVideos":

                break;
            case "getStoragePermissionState":
               // VideoUtils.getAllVideos(activity);
                result.success(PermissionHandler.getStoragePermissionState(activity));
                break;
            default:
                result.notImplemented();
                break;
        }
    }



    @Override
    public boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.e("==>RequestPermissions", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //Permission granted... resuming tasks needing this permission
            AudioUtils.getAllMusic(activity);
            return true;
        }
        return false;
    }
}
