import 'dart:async';

import 'package:flutter/services.dart';
import 'package:plugin_media_finder/model/Music.dart';

class PluginMediaFinder {
  static var completer = new Completer();

  static const MethodChannel _channel = const MethodChannel('plugin_media_finder');

  /*
   * This should return bool wheter permission granted or denied
   */
  static Future<bool> get getStoragePermissionState async {
    final bool version = await _channel.invokeMethod('getStoragePermissionState');
    print(version);
    return version;
  }

  /*
   * this function needs some optimization as Excepetion handling etc...
   */
  static Future<dynamic> get getAllSongs async {
    List<dynamic> songs = await _channel.invokeMethod('getAllSongs');

    var mySongs = songs.map((m) => new Music.fromMap(m)).toList();
    completer.complete(mySongs);
    return completer.future;
  }
}
