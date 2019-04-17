package com.fochmobile.plugin_media_finder.utils;

import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.fochmobile.plugin_media_finder.model.Video;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class VideoUtils {


    public static List<Video> getAllVideos(Activity activity) {
        List<Video> allVideos = new ArrayList<>();
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME};
        Cursor cursor = activity.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()){
            try {
                cursor.moveToFirst();
                do{
                    allVideos.add(new Video(
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media._ID)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE)),
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION)),
                            VideoUtils.getVideoSize(
                                    new File(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA)))
                            ),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA))
                    ));
                }while(cursor.moveToNext());

                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Video v : allVideos){
            Log.e("==>VideoDATA", v.getThumbnail());
        }
        return allVideos;
    }

    /*
     * getVideoSize will return video size in (Gib - MiB - B)
     * @param file
     * @return
     */
    private static String getVideoSize(File file) {
        DecimalFormat format = new DecimalFormat("#.##");
        final long GiB = 1024 * 1024 * 1024;
        final long MiB = 1024 * 1024;
        long KiB = 1024;

        if (!file.isFile()) {
            throw new IllegalArgumentException("Expected a file");
        }
        final double length = file.length();

        if (length > GiB) {
            return format.format(length / GiB) + " GiB";
        }
        if (length > MiB) {
            return format.format(length / MiB) + " MiB";
        }
        if (length > KiB) {
            return format.format(length / KiB) + " KiB";
        }
        return format.format(length) + " B";
    }

}
