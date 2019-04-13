import 'dart:async';

import 'package:flutter/services.dart';

class PluginMediaFinder {
  static const MethodChannel _channel =
      const MethodChannel('plugin_media_finder');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
