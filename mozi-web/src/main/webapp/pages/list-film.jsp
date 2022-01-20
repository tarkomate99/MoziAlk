<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="hu.alkfejl.config.FilmConfig" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Filmek</title>
    <meta charset="utf-8"/>
    <link rel="icon" href="../img/cinema.png"/>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

</head>
<body style="background-color: gray">
<div id="filmekcontiner">
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
    <%
        String connectionUrl = FilmConfig.getValue("db.url");

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;
        String img = FilmConfig.getValue("img.url");
    %>
    <label for="filtercim">Keresés cím alapján: <br>
        <input type="text" id="filtercim" onkeyup="myFunction()">
    </label>
    <table align="center" cellpadding="5" cellspacing="5" border="1" id="myTable">
        <caption align="top">Filmek</caption>
        <tbody>
            <tr style="text-align: center">
                <th>Cím</th>
                <th>Borítókép</th>
                <th>Korhatár</th>
                <th>Szereplők</th>
                <th>Foglalás</th>
            </tr>
        <%
            try{
                conn = DriverManager.getConnection(connectionUrl);
                statement=conn.createStatement();
                String sql = "SELECT * FROM FILMEK";

                result = statement.executeQuery(sql);
                while (result.next()){
                    %>
                    <tr>
                        <td><%=result.getString("cim")%><input type="hidden" name="cim" readonly="readonly" value="<%=result.getString("cim")%>"></td>
                        <td><a href="<%=result.getString("trailer")%>" target="_blank"><img src="../img/<%=result.getString("webkep")%>" alt="boritokep" class="film_kep"></a></td>
                        <td style="text-align: center"><%=result.getString("korhatar")%></td>
                        <td><%=result.getString("szereplok")%></td>
                        <td><a href="../pages/add_foglalas.jsp?cim=<%=result.getString("cim")%>"><input type="submit" value="Foglalás"></a></td>
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
<script src="../js/list_film.js"></script>
<script>
    var modal = document.getElementById("myModal");
    var btn = document.getElementById("myBtn");
    var span = document.getElementsByClassName("close")[0];
    btn.onclick = function() {
        modal.style.display = "block";
    }
    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
