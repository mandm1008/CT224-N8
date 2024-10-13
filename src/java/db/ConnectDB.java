package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectDB {
  // local mysql
  private String url = "jdbc:mysql://localhost:3306/"; // Database URL
  private String dbName = "musicproject"; // Database Name
  private String driver = "com.mysql.cj.jdbc.Driver"; // Driver Name
  private String userName = "root"; // Database Username
  private String password = ""; // Database Password

  private Connection conn = null;

  public ConnectDB() {
    connectDB();
  }

  public Connection getConnect() {
    return conn;
  }

  public void closeConnect() {
    try {
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean createTable() {
    try {
      conn.createStatement().execute(UserModel.getCreateTable());
      conn.createStatement().execute(ArtistModel.getCreateTable());
      conn.createStatement().execute(AlbumModel.getCreateTable());
      conn.createStatement().execute(PlaylistModel.getCreateTable());
      conn.createStatement().execute(SongModel.getCreateTable());
      conn.createStatement().execute(UserLikes.getCreateTable());
      conn.createStatement().execute(PlaylistSongModel.getCreateTable());

      return true;
    } catch (SQLException e) {
      System.out.println("Failed to create tables");
      e.printStackTrace();
      return false;
    }
  }

  private Connection connectDB() {
    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url + dbName, userName, password);

      // set Timeout
      ExecutorService executor = Executors.newSingleThreadExecutor();
      conn.setNetworkTimeout(executor, 10000);

      // createTable();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("Driver JBDC not found");
      e.printStackTrace();
    }

    return conn;
  }

  public static void initDataDB() {
    ConnectDB db = new ConnectDB();
    db.createTable();

    // init data
    // user 0
    UserModel user0 = new UserModel("system", "system", "system@system.system");
    user0.insert();
    user0.update("UPDATE Users SET user_id = 0 WHERE user_id = 1", (pstm) -> {

    });
    // user 1
    UserModel user1 = new UserModel("admin", "admin", "admin@admin.admin");
    user1.insert();

    System.out.println("Init data for Users");

    // default songs
    SongModel song1 = new SongModel(
        "Áng mây vô tình",
        "/uploads/audio/angmayvotinh.mp3",
        "/uploads/images/angmayvotinh.jpg");
    song1.insert("Lương Gia Hùng");

    SongModel song2 = new SongModel(
        "Cho Mình Em",
        "/uploads/audio/chominhem.mp3",
        "/uploads/images/chominhem.jpg");
    song2.insert("Binz");

    SongModel song3 = new SongModel(
        "Trốn tìm",
        "/uploads/audio/trontim.mp3",
        "/uploads/images/trontim.jpg");
    song3.insert("Đen Vâu");

    SongModel song4 = new SongModel(
        "Đường tôi chở em về",
        "/uploads/audio/duongtoichoemve.mp3",
        "/uploads/images/duongtoichoemve.jpg");
    song4.insert("Buitruonglinh");

    SongModel song5 = new SongModel(
        "Cho tôi lang thang",
        "/uploads/audio/chotoilangthang.mp3",
        "/uploads/images/chotoilangthang.jpg");
    song5.insert("Đen Vâu");

    SongModel song6 = new SongModel(
        "Người lạ ơi",
        "/uploads/audio/nguoilaoi.mp3",
        "/uploads/images/nguoilaoi.jpg");
    song6.insert("Karik");

    SongModel song7 = new SongModel(
        "Người thứ ba",
        "/uploads/audio/nguoithuba.mp3",
        "/uploads/images/nguoithu3a.jpg");
    song7.insert("H2K");

    System.out.println("Init data for Songs");

    db.closeConnect();
  }

  public static void main(String[] args) {
    initDataDB();
  }
}
