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
public class them extends HttpServlet {
    public static final String UPLOAD_PATH = "D://Workspace/NetBeans/MusicProject/web/uploads";
    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    static final String db_url = "jdbc:mysql://localhost/musicproject";
    static final String db_user = "root";
    static final String db_pw = "";
    Connection conn = null;
    Statement stm = null;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
       try
            {
                Class.forName(jdbc_driver);
                conn = DriverManager.getConnection(db_url,db_user,db_pw);
                stm = conn.createStatement();
               
                
                String title = (String)request.getParameter("title");
                String artist_id = (String)request.getParameter("artist_id");
                String lof_them = (String)request.getParameter("lof_them");
                
                
                String href = null;
                String image = null;
                if(lof_them.equals("1"))
                {
                    href = (String)request.getParameter("link_them");
                }
                else if(lof_them.equals("2"))
                {
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
                    String filePath = uploadPath + "/" + fileName;

                  // Lưu tệp vào thư mục
                   try (InputStream fileContent = filePart.getInputStream()) 
          {
                       
                   Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
          }
                   href = request.getContextPath() + "/uploads/audio" + "/" + fileName;
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
                    String filePath2 = uploadPath + "/" + fileName;

                  // Lưu tệp vào thư mục
                   try (InputStream fileContent = filePart2.getInputStream()) 
          {
                       
                   Files.copy(fileContent, Paths.get(filePath2), StandardCopyOption.REPLACE_EXISTING);
          }
                   image = request.getContextPath() + "/uploads/images" + "/" + fileName;
                
                
                
                
                String sql = "insert into songs (title,artist_id,href,image) values "
                    + "('" + title + "','" + artist_id + "','" + href + "','" + image + "');";
                
                int kq_exsql = stm.executeUpdate(sql);
                
                stm.close();
                conn.close();
                response.sendRedirect("LoadData");
                
            }
            catch(Exception e)
            {
                response.getWriter().println("Error: " + e);
            }          
    }
}
