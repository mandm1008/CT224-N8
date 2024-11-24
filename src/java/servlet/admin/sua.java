package servlet.admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@MultipartConfig
public class sua extends HttpServlet {
    public static final String UPLOAD_PATH = "D://Workspace/NetBeans/MusicProject/web/uploads";
    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
 //   Statement stm = null;
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
            try
            {
              Class.forName(jdbc_driver);
              conn = DriverManager.getConnection(db_url,db_user,db_pw);
        //      stm = conn.createStatement();
              
              String img_change = (String)request.getParameter("img_change");
              String link_change = (String)request.getParameter("link_change");
              String file_change = (String)request.getParameter("file_change");
              String old_href = (String)request.getParameter("old_href");
              String old_img = (String)request.getParameter("old_img");
              String song_id = (String)request.getParameter("song_id");
              String title = (String)request.getParameter("title");
              String artist_id = (String)request.getParameter("artist_id");
              String lof_them = (String)request.getParameter("lof_them");
              
              String href = null;
              String image = null;
              
              if(link_change.equals("1") || file_change.equals("1"))
              {
                  if(lof_them.equals("1"))
                {
                    href = (String)request.getParameter("link_them");
                }
                else if(lof_them.equals("2"))
                {
                    //Delete Old File     
            File oldFile = new File(old_href);
            if (oldFile.exists() && oldFile.isFile()) {
                boolean deleted_old_file = oldFile.delete();                
            }
   
                    // Lấy phần tệp từ request
                      // "file" là tên input trong form HTML
                    Part filePart = request.getPart("file_them");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file
                    
                    // Đường dẫn thư mục lưu trữ tệp
                    String rootPath = UPLOAD_PATH;
                    String uploadPath = rootPath + "/audio";
                    File uploadDir = new File(uploadPath);
                    
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();  // Tạo thư mục nếu chưa tồn tại
                    }
                    
                    // Đường dẫn đầy đủ của tệp tải lên
                    String filePath = uploadPath + File.separator + fileName;

                  // Lưu tệp vào thư mục
                   try (InputStream fileContent = filePart.getInputStream()) 
          {
                       
                   Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
          }
                   href = request.getContextPath() + "/uploads/audio" + "/" + fileName;
                }
              }
              else
              {
                  href = old_href;
              }
                  
              // Img
              
              if(img_change.equals("1"))
              {
                  //Delete Old File
                  File oldImg = new File(old_img);
            if (oldImg.exists() && oldImg.isFile()) {
                boolean deleted_old_img = oldImg.delete();                
            }
                  
                  // Lấy phần tệp từ request
                    Part filePart2 = request.getPart("image");  // "file" là tên input trong form HTML
                    String fileName = Paths.get(filePart2.getSubmittedFileName()).getFileName().toString(); // Lấy tên file
                    
                    // Đường dẫn thư mục lưu trữ tệp
                    String rootPath = UPLOAD_PATH;
                    String uploadPath = rootPath + "/images";
                    File uploadDir = new File(uploadPath);
                    
                    if (!uploadDir.exists()) {
                    uploadDir.mkdir();  // Tạo thư mục nếu chưa tồn tại
                        }
                    
                    // Đường dẫn đầy đủ của tệp tải lên
                    String filePath2 = uploadPath + File.separator + fileName;

                  // Lưu tệp vào thư mục
                   try (InputStream fileContent = filePart2.getInputStream()) 
          {
                       
                   Files.copy(fileContent, Paths.get(filePath2), StandardCopyOption.REPLACE_EXISTING);
          }
                   image = request.getContextPath() + "/uploads/images" + "/" + fileName;
              }
              else
              {
                  image = old_img;
              }
              
      /*        String sql = "update songs "
                    + "set " + "title = " + title + "artist_id = " + artist_id + "href = " + href + "image = " + image
                    + "where song_id = " + song_id + ";"; */
              
              String sql = "UPDATE songs SET title = ?, artist_id = ?, href = ?, image = ? WHERE song_id = ?";

                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, title); // Nếu 'title' là chuỗi
                    pstmt.setString(2, artist_id); // Nếu 'artist_id' là số
                    pstmt.setString(3, href); // Nếu 'href' là chuỗi
                    pstmt.setString(4, image); // Nếu 'image' là chuỗi
                    pstmt.setString(5, song_id); // Nếu 'song_id' là số

                    pstmt.executeUpdate();
              
              
              pstmt.close();
              conn.close();
              response.sendRedirect("LoadData");
            }
            catch(Exception e)
            {
                response.getWriter().println("Error: " + e);
            }
    }

}
