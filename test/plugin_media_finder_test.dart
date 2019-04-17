import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_media_finder/plugin_media_finder.dart';

void main() {
  const MethodChannel channel = MethodChannel('plugin_media_finder');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    //expect(await PluginMediaFinder.platformVersion, '42');
  });
}
