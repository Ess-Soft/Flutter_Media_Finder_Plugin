import 'package:plugin_media_finder/model/Music.dart';
import 'package:plugin_media_finder/plugin_media_finder.dart';
import 'package:rxdart/rxdart.dart';

class MusicsListBloc {
  BehaviorSubject<List<Music>> _musicsListController;
  Stream<List<Music>> _musicsList;

  MusicsListBloc() {
    this._musicsListController = BehaviorSubject<List<Music>>();
    this._musicsList = _musicsListController.stream;

    fetch();
  }

  Future fetch() async {
    try {
      _musicsListController.add(await PluginMediaFinder.getAllSongs);
    } catch (e) {
      _musicsListController.addError(e);
      print(e);
    }

    return null;
  }

  void dispose() {
    //_musicsListController.close();
  }

  void clear() {
    _musicsListController.add(null);
  }


  Stream<List<Music>> get musicsList => _musicsList;
}
