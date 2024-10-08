/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */

public class PlaylistModel extends Model {

  // defind table
  private static final String createTable = "" +
      "CREATE TABLE IF NOT EXISTS Playlists (" +
      "playlist_id INT AUTO_INCREMENT PRIMARY KEY, " +
      "name VARCHAR(255) NOT NULL, " +
      "user_id INT, " +
      "FOREIGN KEY (user_id) REFERENCES Users(user_id))";
  private final String tableName = "Playlists";
  private final String idName = "playlist_id";

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
  private int playlistId;
  private String name;
  private int userId;

  public PlaylistModel() {
    this.playlistId = -1;
    this.name = "";
    this.userId = -1;
  }

  public PlaylistModel(int playlistId) {
    this.playlistId = playlistId;
  }

  public PlaylistModel(String name, int userId) {
    this.name = new String(name);
    this.userId = userId;
  }

  public PlaylistModel(PlaylistModel playlist) {
    this.name = new String(playlist.name);
    this.userId = playlist.userId;
  }

  @Override
  protected String getInsertString() {
    return "INSERT INTO " + getTableName() + " (name, user_id) VALUES (?, ?)";
  }

  @Override
  protected int getId() {
    return playlistId;
  }

  @Override
  protected void setValueInsert(PreparedStatement pstmt) {
    try {
      pstmt.setString(1, name);
      pstmt.setInt(2, userId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected boolean checkAccess() {
    // check with title
    try {
      if (super.query("SELECT * FROM " + getTableName() + " WHERE (user_id, name) = (?, ?)", (pstmt) -> {
        try {
          pstmt.setInt(1, userId);
          pstmt.setString(2, name);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }).next() == false)
        return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return false;
  }

  public boolean findData() {
    if (playlistId == -1) {
      return false;
    }

    try {
      ResultSet rs = super.findById();
      if (rs.next()) {
        this.name = rs.getString("name");
        this.userId = rs.getInt("user_id");
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  public String getName() {
    return name;
  }

  public int getUserId() {
    return userId;
  }

  public int getPlaylistId() {
    return playlistId;
  }

  public UserModel getUser() {
    UserModel user = new UserModel(userId);
    user.findData();

    return user;
  }

}
