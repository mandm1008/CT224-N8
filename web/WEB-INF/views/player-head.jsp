<%-- 
    Document   : player-head
    Created on : Oct 12, 2024, 6:45:13 PM
    Author     : ASUS
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList"%>
<%@page import="db.SongModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    LinkedList<SongModel> list = SongModel.getNewSongs(9);
    HashMap<String, Object> data = new HashMap<>();
    
    data.put("songs", list);
    data.put("name", "Danh sách phát");
    data.put("userId", -1);
    
    String json = new Gson().toJson(data);
%>

<link rel="stylesheet" href="css/player.css">
<script src="js/player.js" defer></script>
<script>
    const metadata = {
        contextPath: '<%=request.getContextPath()%>',
        jsonSongs: '<%=json%>'
    }
</script>