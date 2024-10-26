/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author ASUS
 */
public class UserMusic extends Model {

    // defind table
    private static final String createTable = ""
            + "CREATE TABLE IF NOT EXISTS User_Music ("
            + "user_music_id INT AUTO_INCREMENT PRIMARY KEY, "
            + "user_id INT, "
            + "song_id INT, "
            + "create_at DATETIME DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY (user_id) REFERENCES Users(user_id),"
            + "FOREIGN KEY (song_id) REFERENCES Songs(song_id))";
    private final String tableName = "User_Music";
    private final String idName = "user_music_id";

    protected static String getCreateTable() {
        return createTable;
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected String getIdName() {
        return idName;
    }

    // for model
    private int userMusicId;
    private int userId;
    private int songId;

    public UserMusic(int userId) {
        this.userId = userId;
    }

    public UserMusic(int userId, int songId) {
        this.userId = userId;
        this.songId = songId;
    }

    @Override
    protected String getInsertString() {
        return "INSERT INTO " + getTableName() + " (user_id, song_id) VALUES (?, ?)";
    }

    @Override
    protected int getId() {
        return userMusicId;
    }

    @Override
    protected void setValueInsert(PreparedStatement pstmt) {
        try {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, songId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean checkAccess() {
        return true;
    }

    @Override
    public boolean insert() {
        super.update("DELETE FROM " + getTableName() + " WHERE user_id = ? AND song_id = ?", (pstmt) -> {
            try {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, songId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        
        return super.insert();
    }

    public LinkedList<DAO.Song> findByUserId() {
        LinkedList<DAO.Song> result = new LinkedList<>();

        QueryResult qr = super.query(
                "SELECT * FROM " + getTableName() + " WHERE user_id = ? ORDER BY create_at DESC LIMIT 9",
                (pstmt) -> {
                    try {
                        pstmt.setInt(1, userId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

        ResultSet rs = qr.getResultSet();

        try {
            while (rs.next()) {
                int sId = rs.getInt("song_id");
                SongModel s = new SongModel(sId);
                s.findData();
                ArtistModel artist = s.getArtist();
                if (artist.getName() != null) {
                    s.setArtistName(artist.getName());
                } else {
                    s.setArtistName("System");
                }
                result.add(s.toSong());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
