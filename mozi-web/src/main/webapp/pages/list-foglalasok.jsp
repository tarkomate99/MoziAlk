<%--
  Created by IntelliJ IDEA.
  User: Mate
  Date: 2021. 04. 22.
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hu.alkfejl.config.FilmConfig" %>
<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%!
    private static final String SELECT_FROM_USER = "SELECT * from Foglalasok where felhasznalo_nev=?";
%><%
    if(session==null || session.getAttribute("user")==null){
        response.sendRedirect("login.jsp");
    }
%>
<html>
<head>
    <title>Foglalások</title>
    <meta charset="utf-8"/>
    <link rel="icon" href="../img/cinema.png"/>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

</head>
<body style="background-color: gray">
<div id="continer">
    <header>
        <img src="../img/banner.jpg" alt="banner">
    </header>
    <nav>
        <ul id="menu">
            <li><a href="../pages/list-film.jsp" target="_self" style="color: white; text-decoration: none">Filmek</a></li>
            <% if(session==null || session.getAttribute("user")==null){%>
            <li><a href="../pages/login.jsp" target="_self" style="color: white; text-decoration: none">Bejelentkezés</a> </li>
            <li><a href="../pages/register.jsp" target="_self" style="color: white; text-decoration: none">Regisztráció</a></li>
            <% } else {%>
            <li><a href="../pages/list-foglalasok.jsp" target="_self" style="color: white; text-decoration: none">Foglalásaim</a></li>
            <li><a href="../pages/logout.jsp" style="color: white; text-decoration: none">Kijelentkezés</a></li>
            <%}%>
        </ul>
    </nav>
    <%
        String connectionUrl = FilmConfig.getValue("db.url");

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(connectionUrl);
    %>
    <label for="filter">Keresés név alapján: <br>
        <input type="text" id="filter" onkeyup="myFunction()">
    </label>
    <table align="center" cellpadding="5" cellspacing="5" border="1" id="myTable">
        <caption align="top">Foglalások</caption>
        <tbody>
        <tr style="text-align: center">
            <th>Név</th>
            <th>Vetítés időpontja</th>
            <th>Helyek</th>
            <th>Ár</th>
            <th colspan="2">Módosítás</th>
        </tr>
        <%
            try{
                PreparedStatement pst = connection.prepareStatement(SELECT_FROM_USER);
                pst.setString(1,session.getAttribute("user").toString());
                ResultSet result = pst.executeQuery();
                while (result.next()){
        %>
        <tr>
            <td><%=result.getString("nev")%></td>
            <td style="text-align: center"><%=result.getString("datum").substring(0,10)+" "+result.getString("datum").substring(11,19)%></td>
            <td style="text-align: center; width: 100px;"><%=result.getString("lefoglalt_helyek")%></td>
            <td><%=result.getString("ar")%></td>
            <td><a href="../pages/update.jsp?id=<%=result.getString("id")%>"><button type="button">Módosítás</button></a></td>
            <td><a href="../pages/delete.jsp?id=<%=result.getString("id")%>"><button type="button" class="delete">Törlés</button></a></td>
        </tr>
        <%
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
