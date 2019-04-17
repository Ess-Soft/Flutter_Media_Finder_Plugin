import 'dart:async';

import 'package:flutter/services.dart';
import 'package:plugin_media_finder/model/Music.dart';

class PluginMediaFinder {
  static var completer = Completer();

  static const MethodChannel _channel = const MethodChannel('plugin_media_finder');

  /*
   * This should return bool wheter permission granted or denied
   */
  static Future<bool> get getStoragePermissionState async {
    final bool version = await _channel.invokeMethod('getStoragePermissionState');
    return version;
  }

  /*
   * this function needs some optimization as Excepetion handling etc...
   */
  static Future<dynamic> get getAllSongs async {
    List<dynamic> allMusic = await _channel.invokeMethod('getAllSongs');

    var musics = allMusic.map((m) => Music.fromMap(m)).toList();
    completer.complete(musics);
    return completer.future;
  }
}

class Music {
  int id;
  String artist;
  String title;
  String album;
  int albumId;
  int duration;
  String uri;
  String albumArt;

  Music(this.id, this.artist, this.title, this.album, this.albumId,
      this.duration, this.uri, this.albumArt);

  Music.fromMap(Map m) {
    id = m["id"];
    artist = m["artist"];
    title = m["title"];
    album = m["album"];
    albumId = m["albumId"];
    duration = m["duration"];
    uri = m["uri"];
    albumArt = m["albumArt"];
  }
}
