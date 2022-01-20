<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,java.util.*"%>
<%@ page import="hu.alkfejl.config.FilmConfig" %>
<%@ page import="javax.swing.*" %>
<%
    String id=request.getParameter("id");
    String connectionUrl = FilmConfig.getValue("db.url");
    Connection conn=null;
    try
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(connectionUrl);
        PreparedStatement st=conn.prepareStatement("DELETE FROM foglalasok WHERE id=?");
        st.setString(1,id);
        st.executeUpdate();
        response.sendRedirect("list-foglalasok.jsp");
    }
    catch(Exception e)
    {
%>
<script>
    alert("Csak 24 órával a vetítés előtt tudsz törölni!");
    window.close();
    window.location="list-foglalasok.jsp";
</script>
<%
    e.printStackTrace();
    }finally {
    assert conn != null;
    try {
    conn.close();
    } catch (SQLException throwables) {
    throwables.printStackTrace();
    }
    }
%>