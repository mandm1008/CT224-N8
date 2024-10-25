/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.SongModel;

/**
 *
 * @author ASUS
 */
public class Song {

    public int songId;
    public String title;
    public int albumId;
    public int artistId;
    public int userId = 0;
    public String artistName = "system";
    public String href;
    public String image;
    public int view;

    public Song(int songId, String title, int albumId, int artistId, int userId, String artistName, String href, String image, int view) {
        this.songId = songId;
        this.title = title;
        this.albumId = albumId;
        this.artistId = artistId;
        this.userId = userId;
        this.artistName = artistName;
        this.href = href;
        this.image = image;
        this.view = view;
    }

    public Song(SongModel s) {
        this.songId = s.getSongId();
        this.title = s.getTitle();
        this.albumId = s.getAlbumId();
        this.artistId = s.getArtistId();
        this.userId = s.getUserId();
        this.artistName = s.getArtistName();
        this.href = s.getHref();
        this.image = s.getImage();
        this.view = s.getView();
    }
}
