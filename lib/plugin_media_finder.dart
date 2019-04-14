import 'dart:async';

import 'package:flutter/services.dart';

class PluginMediaFinder {
  static const MethodChannel _channel = const MethodChannel('plugin_media_finder');

  /*
   * This should return bool wheter permission granted or denied
   * but i dont know why it throws an Exception !!
   */
  static Future<bool> get platPermissionState async {
    final bool version = await _channel.invokeMethod('getPermissionState');
    print(version);
    return version;
  }
}
