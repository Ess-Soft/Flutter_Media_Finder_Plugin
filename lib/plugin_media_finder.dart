import 'dart:async';
import 'dart:convert';

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

    var musics = allMusic.map((m) => Music.fromJson(json.decode(m))).toList();
    completer.complete(musics);
    return completer.future;
  }
}

