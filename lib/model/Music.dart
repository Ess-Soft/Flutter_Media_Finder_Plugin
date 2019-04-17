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

  Music.fromJson(Map<String, dynamic> json) {
    id = json["id"];
    artist = json["artist"];
    title = json["title"];
    album = json["album"];
    albumId = json["albumId"];
    duration = json["duration"];
    uri = json["uri"];
    albumArt = json["albumArt"];
  }

}
