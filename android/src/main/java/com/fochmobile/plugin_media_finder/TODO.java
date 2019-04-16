package com.fochmobile.plugin_media_finder;

public class TODO {
    /// Methods to communicate with dart code

    //todo permissions
    /**
     * @Deperecated
     * bool checkStoragePermissions() => true == Granted | false == Denied
     *
     * Checking permission and waiting for response (bool) is not "possible" because "I think"
     * there wouldn't be a way to wait for onRequestPermissionsResult as a result here is the new
     * adapter approach:
     *
     * List<Music> getAllSongs(boolean loadAfterPermissionGranted)
     * this function will first check for permission :
     * Scenario 1: check for permission -> if already granted -> load Music
     * Scenario 2: check for permission -> if its not granted -> request it -> if granted -> load Music | if Denied -> return null
     * so Dart will wait for List<Music> if it was returned not null then all is OK | if it was returned null means user Denied
     * the permission hence we'll show an explanation Dialog with a Allow Button if Allow btn clicked then repeat this whole approach.
     *
     */

    // todo music
    /**
     * Data Models
     *  Music
     *
     * List<Media> fetchMusics()
     * Music getMusicByPath(String path)
     *
     */


    // todo fetch videos
    /**
     * Data Models
     *  Video
     *
     * List<Video> fetchVideos()
     * Video getVideoByPath(String path)
     *
     */

    // todo deep links
    /**
     * RouterClass to receive media file as deep link
     * RouterClass checkFile() => Media obj
     *
     */

    // todo file manager
    /**
     * bool renameFile(String path, String newName)
     * bool delete(String path)
     *
     *
     */
}
