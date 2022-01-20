<%@ page import="hu.alkfejl.config.FilmConfig" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.xml.transform.Result" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.concurrent.ExecutionException" %><%--
  Created by IntelliJ IDEA.
  User: Mate
  Date: 2021. 04. 22.
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session==null || session.getAttribute("user")==null){
        response.sendRedirect("login.jsp");
    }
%>
<%
    String id = request.getParameter("id");
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String dbUrl=FilmConfig.getValue("db.url");
%>
<%
    try {
        connection=DriverManager.getConnection(dbUrl);
        statement=connection.prepareStatement("select * from foglalasok where id=?");
        statement.setString(1,id);
        resultSet=statement.executeQuery();
        while (resultSet.next()){
%>
<html>
<head>
    <title>Módosítás</title>
    <meta charset="utf-8"/>
    <link rel="icon" href="../img/cinema.png"/>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
        table tr td { width: 30px; height: 30px;}
        table tr td.over { background-color: yellow; }
        table tr td.active { background-color: red; }
        .controls { padding: 20px; }
        .disable { pointer-events: none; opacity: 0.4;}
    </style>
</head>
<body style="background-image: url('../img/chairwallpaper.jpg');">
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
            <li><a href="list-foglalasok.jsp" target="_self" style="color: white; text-decoration: none">Foglalásaim</a></li>
            <li><a href="../pages/logout.jsp" style="color: white; text-decoration: none">Kijelentkezés</a></li>
            <%}%>
        </ul>
    </nav>
    <h1>Foglalás módosítás</h1>
    <form action="update-process.jsp" method="post" id="form_foglalas">
        <input type="hidden" name="id" value="<%=resultSet.getString("id")%>">
        Név: <br>
        <input type="text" name="nev" value="<%=resultSet.getString("nev")%>">
        <br>
        Vetítés időpontja: <br>
        <select name="time" id="time" disabled="true">
            <%
                request.setCharacterEncoding("UTF-8");
                Integer terem_id=null;
                try{
                    PreparedStatement pst = connection.prepareStatement("SELECT * from Vetitesek where id=?");
                    pst.setInt(1,resultSet.getInt("vetites_id"));
                    ResultSet res = pst.executeQuery();
                    terem_id = res.getInt("terem_id");
                    while (res.next()){

            %>
            <option value="<%=res.getString("datum")%>"><%=res.getString("datum").substring(0,10)+" "+res.getString("datum").substring(11,19)%></option>
            <%
                }
                }catch (Exception e){
                    e.printStackTrace();
                }
            %>
        </select><br><br>
        <select name="jegy" id="jegy">
            <option value="VIP">VIP - (2000 Ft)</option>
            <option value="Normal">Normál - (1500 Ft)</option>
        </select><br>
        Helyek: <br>
        <input type="text" id="helyek" name="helyek" value="<%=resultSet.getString("helyek")%>" onclick="setvalue(this.value);" readonly>
        <br>
        Lefoglalt helyek: <br>
        <input type="text" id="lefoglalthelyek" name="lefoglalt_helyek" value="<%=resultSet.getString("lefoglalt_helyek")%>" readonly>
        <br>
        Ár: <br>
        <input type="text" id="ar" name="ar" placeholder="<%=resultSet.getString("ar")%>" readonly>
        <br><br>
        <input type="submit" value="Módosítás">
    </form>
    <div id="szekek">
        <table id="table">
            <%
                char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
                try{
                    connection=DriverManager.getConnection(dbUrl);
                    PreparedStatement stmt = connection.prepareStatement("SELECT sorszam, oszlopszam from termek where t_id=?");
                    stmt.setInt(1,terem_id);
                    ResultSet res = stmt.executeQuery();
                    for(int i=0;i<res.getInt("sorszam");i++){
            %>
            <tr>
                <td class="disable"><%=Character.toString(alphabet[i])%></td>
                <%
                for (int j=0;j<res.getInt("oszlopszam");j++){
                %>
                <td><img src="../img/chair.png" alt="szek" style="width: 30px; height: 30px;" id="szek"></td>
                <%
                }
                %>
            </tr>
            <%
                }
                }catch (Exception e){
                    e.printStackTrace();
                }
            %>
        </table>
        <br><br>
        <button id="veglegesit">Foglalás véglegesítés</button>
    </div>
<%
    }
        connection.close();
    }catch (Exception e){
        e.printStackTrace();
    }
%>
</div>
<script>
    function setvalue(value){
        var ar=0;
        if(document.getElementById("jegy").value==="VIP"){
            ar=value*2000;
        } else if(document.getElementById("jegy").value==="Normal"){
            ar=value*1500;
        }
        document.getElementById('ar').value=ar+" Ft";
    }
</script>
<script src="../js/add_foglalas_animation.js"></script>
</body>
</html>
