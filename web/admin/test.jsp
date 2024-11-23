<%-- 
    Document   : test
    Created on : Nov 16, 2024, 11:55:45 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <form name="uploadForm" action="upload_test" method="post" enctype="multipart/form-data">
    <label for="fileUpload">Choose a file:</label>
    <input type="text" name="texta" id="texta" values = "abc"><br>
    <input type="text" name="text_url" id="text_url"><br>
    <input type="file" name="file" id="file" required /><br>
    <input type="submit" value="Submit" />
    </form>
    </body>
</html>
