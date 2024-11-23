<%-- 
    Document   : upfile
    Created on : Nov 4, 2024, 3:00:55 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Page</title>
    </head>
    <body>
        <h1>Test Upload file</h1>
        
        <form action="upload_test" method="post" enctype="multipart/form-data">
        <label>Chọn file để upload:</label><br>
        <input type="file" name="file" />
        <input type="submit" value="Upload" />
        </form>

        
    </body>
</html>
