/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.LinkedList;

public class Playlist {

    private int playlistId;
    private String name;
    private int userId;
    private LinkedList<Song> songs;

    // Constructor
    public Playlist(int playlistId, String name, int userId) {
        this.playlistId = playlistId;
        this.name = name;
        this.userId = userId;
        this.songs = new LinkedList<>();
    }

    // Getters and Setters
    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LinkedList<Song> getSongs() {
        return songs;
    }

    public void setSongs(LinkedList<Song> songs) {
        this.songs = songs;
    }

    // Add a song to the playlist
    public void addSong(Song song) {
        this.songs.add(song);
    }

    // Remove a song from the playlist
    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    // Save playlist to database
    public void save() {
        // Logic to save playlist to database
        // Add code to insert playlist and songs into database tables
    }

    // Static method to fetch playlist by ID
    public static Playlist getPlaylistById(int playlistId) {
        // Logic to fetch playlist and songs from database
        Playlist playlist = new Playlist(playlistId, "Sample Playlist", 1); // Example
        // Add code to populate songs from database
        return playlist;
    }
}

