package com.fochmobile.plugin_media_finder.utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.fochmobile.plugin_media_finder.model.Music;

import java.util.ArrayList;
import java.util.List;

public class AudioUtils {

    /**
     * Note: this code has been edited from Flute Music Player
     * @param activity @description: Activity param is required to access Content Resolver
     * @return List<Music>
     */
    public static List<Music> getAllMusic(Activity activity){
        List<Music> allSongs = new ArrayList<>();

        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cur = activity.getContentResolver().query(uri, null,
                MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);

        if (cur == null || !cur.moveToFirst()) {
            Log.e("=>Cursor Init", "Cursor Null !!! ");
            return null;
        }

        do {
            allSongs.add(new Music(
                    cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media._ID)),
                    cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                    cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                    cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                    cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media.DURATION)),
                    cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)),
                    cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA)),
                    getAlbumArt(activity, cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media._ID)))
            ));
        } while (cur.moveToNext());

        // WE SHOULD NOT FORGET TO CLOSE THE CURSOR !!
        cur.close();

        return allSongs;
    }

    private static String getAlbumArt(Activity activity, long albumId) {
        String path = "";

        Cursor cursor = activity.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[] {MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                MediaStore.Audio.Albums._ID+ "=?",
                new String[] {String.valueOf(albumId)},
                null);

        if (cursor != null && cursor.moveToFirst()) {
            path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));

        }
        assert cursor != null;
        cursor.close();

        return path;
    }

}
