<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="hu.alkfejl.config.FilmConfig" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Mate
  Date: 2021. 05. 02.
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    String nev = request.getParameter("nev");
    String helyek = request.getParameter("helyek");
    String lefoglalt_helyek = request.getParameter("lefoglalt_helyek");
    String dbUrl = FilmConfig.getValue("db.url");
    if(id !=null){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con= DriverManager.getConnection(dbUrl);
            String sql = "update foglalasok set nev=?,helyek=?,lefoglalt_helyek=? where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,nev);
            ps.setString(2,helyek);
            ps.setString(3,lefoglalt_helyek);
            ps.setString(4,id);
            int i = ps.executeUpdate();
            if(i>0){
                out.print("Sikeres módosítás!");
                response.sendRedirect("list-foglalasok.jsp");
            } else {
                out.print("Sikertelen módosítás!");
            }
        }catch (SQLException e){
%>
<script>
    alert("Erre a vetítésre már beteltek a szabad helyek!");
    window.close();
    window.location="list-film.jsp";
</script>
<%
            e.printStackTrace();
        }
    }
%>