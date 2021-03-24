package com.example.cs478_project2;

import android.net.Uri;

// SubjectData Object containing Song Title, Artist/Band Name, Song Link, Thumbnail, Song Wiki, Artist/Band Wiki
public class SubjectData {
    String SubjectName;
    Uri Link;
    String ArtistName;
    Uri Wiki_song;
    Uri Wiki_artist;
    int Image;
    public SubjectData(String subjectName, String artistname, Uri link, int image, Uri song, Uri artist) {
        this.SubjectName = subjectName;
        this.Link = link;
        this.Image = image;
        this.ArtistName = artistname;
        this.Wiki_artist = artist;
        this.Wiki_song = song;
    }
}
