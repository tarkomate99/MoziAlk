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
<html>
<head>
    <title>Foglalások</title>
    <meta charset="utf-8"/>
    <link rel="icon" href="../img/cinema.png"/>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<%--    <script src="../js/add_foglalas.js"></script>--%>
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
    <form action="add_foglalas.jsp" method="post" id="form_foglalas">
        <label for="name">Név: <br>
            <input type="text" id="name" name="name" placeholder="Név"/>
        </label><br>
        <label for="time">Vetítés időpontja: <br>
            <select name="time" id="time">
                <%
                    request.setCharacterEncoding("UTF-8");
                    String cim = request.getParameter("cim");
                    String dbUrl = FilmConfig.getValue("db.url");
                    String nev = request.getParameter("name");
                    String helyek = request.getParameter("helyek");
                    String ar = request.getParameter("ar");
                    String felhasznalo_nev = (String) session.getAttribute("user");
                    String datum = request.getParameter("time");
                    String lefoglalt_helyek = request.getParameter("lefoglalthelyek");
                    String vetites = request.getParameter("vetites_id");
                    Date date = new Date();
                    Integer vetites_id=null;
                    Connection connection=null;
                    Integer szabad_helyek=null;
                    Integer terem_id=null;
                    try {
                        connection=DriverManager.getConnection(dbUrl);
                        PreparedStatement pst = connection.prepareStatement("select * from vetitesek where datum>=CURRENT_DATE and film_cim=?");
                        pst.setString(1,cim);
                        ResultSet result = pst.executeQuery();
                        Statement st=connection.createStatement();
                        vetites_id=result.getInt("id");
                        szabad_helyek=result.getInt("szabad_helyek");
                        terem_id=result.getInt("terem_id");
                        while (result.next()){
                %>
                <option value="<%=result.getString("datum")%>"><%=result.getString("datum").substring(0,10)+" "+result.getString("datum").substring(11,19)%></option>
                <%
                        }
                    }catch (Exception e){
                    e.printStackTrace();
                    } finally {
                        try {
                            assert connection != null;
                            connection.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }

                %>
            </select>
        </label><br>
        <label for="filmcim">Film címe: <br>
            <input type="text" id="filmcim" value="<%=cim%>" readonly="readonly">
            <input type="hidden" name="vetites_id" value="<%=vetites_id%>">
        </label><br>
        <br>
        <label for="jegy">
            <select name="jegy" id="jegy">
                <option value="VIP">VIP jegy - (2000 Ft)</option>
                <option value="Normal">Normál jegy - (1500 Ft)</option>
            </select>
        </label>
        <label for="helyek">Lefoglalt helyek: (db)<br>
            <input type="number" id="helyek" name="helyek" onclick="setvalue(this.value);" readonly style="width: 40px">
        </label><br>
        <label for="ar">Ár:
            <input type="text" id="ar" name="ar" placeholder="0 Ft" style="color: red;" readonly>
            <input type="text" id="lefoglalthelyek" name="lefoglalthelyek" readonly>
        </label><br><br>
        <input type="submit" name="foglalas" value="Foglalás"><br>
    </form>
    <br><br><br><br>
    <%
        PreparedStatement pst = null;
        Connection conn=null;
        try{
            conn = DriverManager.getConnection(dbUrl);
            Statement st=conn.createStatement();

            if(request.getParameter("foglalas")!=null && request.getParameter("time")!=null){
                    System.out.println(nev);
                    st.executeUpdate("insert into foglalasok (nev,vetites_id,helyek,ar,felhasznalo_nev,datum,rögzites_datuma,lefoglalt_helyek) values ('"+nev+"','"+vetites+"','"+helyek+"','"+ar+"','"+felhasznalo_nev+"','"+datum+"','"+date.toString()+"','"+lefoglalt_helyek+"')");
                    response.sendRedirect("list-foglalasok.jsp");
            }
        }catch (Exception e){

    %>
    <script>
        alert("Erre a vetítésre már beteltek a szabad helyek!");
        window.close();
        window.location="list-film.jsp";
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
    <div id="szekek">
    <table id="table">
        <%
            char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            Connection connection1 = null;
            try {
                connection1 = DriverManager.getConnection(dbUrl);
                PreparedStatement stmt = connection1.prepareStatement("SELECT sorszam, oszlopszam from termek where t_id=?");
                stmt.setInt(1,terem_id);
                ResultSet res = stmt.executeQuery();
                for(int i=0; i<res.getInt("sorszam");i++){
                    %>
                        <tr>
                        <td class="disable"><%=Character.toString(alphabet[i])%></td>
                    <%
                    for(int j=0; j<res.getInt("oszlopszam"); j++){
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
            }finally {
                            try {
                                assert connection1 != null;
                                connection1.close();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
        %>
    </table>
        <br><br>
        <button id="veglegesit">Foglalás véglegesítése</button>
    </div>
</div>
<script src="../js/add_foglalas_animation.js"></script>
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
</body>
</html>
