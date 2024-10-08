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

public class UserLikes extends Model {

  // defind table
  private static final String createTable = "" +
      "CREATE TABLE IF NOT EXISTS User_Likes (" +
      "user_like_id INT AUTO_INCREMENT PRIMARY KEY, " +
      "user_id INT, " +
      "song_id INT, " +
      "FOREIGN KEY (user_id) REFERENCES Users(user_id)," +
      "FOREIGN KEY (song_id) REFERENCES Songs(song_id))";
  private final String tableName = "User_Likes";
  private final String idName = "user_like_id";

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
  private int userLikesId;
  private int userId;
  private int songId;

  public UserLikes(int userId) {
    this.userId = userId;
  }

  public UserLikes(int userId, int songId) {
    this.userId = userId;
    this.songId = songId;
  }

  public UserLikes(UserLikes userLikes) {
    this.userId = userLikes.userId;
    this.songId = userLikes.songId;
  }

  @Override
  protected String getInsertString() {
    return "INSERT INTO " + getTableName() + " (user_id, song_id) VALUES (?, ?)";
  }

  @Override
  protected int getId() {
    return userLikesId;
  }

  @Override
  protected void setValueInsert(PreparedStatement pstmt) {
    try {
      pstmt.setInt(1, userId);
      pstmt.setInt(2, songId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected boolean checkAccess() {
    // check with userId and songId
    try {
      if (super.query("SELECT * FROM " + getTableName() + " WHERE user_id = ? AND song_id = ?", (pstmt) -> {
        try {
          pstmt.setInt(1, userId);
          pstmt.setInt(2, songId);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }).next()) {
        return false;
      }

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public void findData() {
    try {
      ResultSet rs = super.query("SELECT * FROM " + getTableName() + " WHERE user_id = ? AND song_id = ?", (pstmt) -> {
        try {
          pstmt.setInt(1, userId);
          pstmt.setInt(2, songId);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });

      if (rs.next()) {
        userLikesId = rs.getInt("user_like_id");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ResultSet findByUserId() {
    return super.query("SELECT * FROM " + getTableName() + " WHERE user_id = ?", (pstmt) -> {
      try {
        pstmt.setInt(1, userId);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }

  public boolean checkLikeUser() {
    try {
      return super.query("SELECT * FROM " + getTableName() + " WHERE user_id = ? AND song_id = ?", (pstmt) -> {
        try {
          pstmt.setInt(1, userId);
          pstmt.setInt(2, songId);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }).next();
    } catch (SQLException e) {
      return false;
    }
  }
}

