import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:plugin_media_finder/model/Music.dart';
import 'package:plugin_media_finder/plugin_media_finder.dart';
import 'package:plugin_media_finder_example/blocs/musics_list_bloc.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static String permissionStateMessage = "NULL" ;

  MusicsListBloc musicsListBloc;

  @override
  void initState() {
    super.initState();
    initPermissionState();
    musicsListBloc = MusicsListBloc();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPermissionState() async {
    bool permissionState;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      permissionState = await PluginMediaFinder.getStoragePermissionState;
        print(permissionState ? "Granted" : "Denied");

        if(permissionState) permissionStateMessage = "Permission Granted";
        else permissionStateMessage = "Permission Denied";

    } on PlatformException {
    //  platformVersion = 'Failed to get platform version.';
    print('Exception checking permission !');
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      permissionStateMessage = permissionState ? 'YES' : 'NO';
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: ListView(
          children: <Widget>[
            Text("Storage permission state : $permissionStateMessage"),
            StreamBuilder(
              stream: musicsListBloc.musicsList,
              initialData: <Music>[],
              builder: (BuildContext context, AsyncSnapshot<List<Music>> snapshot) {
                List<Widget> musicsList = List();
                if (snapshot.data != null) {
                  for(Music music in snapshot.data) {
                    musicsList.add(Text("Music: ${music.title}"));
                  }
                  return Column(children: musicsList,);
                } else {
                  return Center(child: Text("No music :/\nWhy ?! I don't know"),);
                }
              },
            ),
          ],
        ),
      ),
    );
  }
}
