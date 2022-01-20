<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Bejelentkezés</title>
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
            <li><a href="list-foglalasok.jsp" target="_self" style="color: white; text-decoration: none">Foglalásaim</a></li>
            <li><a href="../pages/logout.jsp" style="color: white; text-decoration: none">Kijelentkezés</a></li>
            <%}%>
        </ul>
    </nav>
    <form action="../LoginController" method="post" id="form_login_reg">
            <label for="username">Felhasználó név</label>
            <input required name="username" type="text" class="form-control" id="username"
                   placeholder="Felhasználó név"/>
            <label for="password">Jelszó</label>
            <input required name="password" type="password" class="form-control" id="password"
                   placeholder="Jelszó"/><br>
        <button id="submit" type="submit" class="btn btn-primary">Bejelentkezés</button>
        <span><a href="register.jsp">Regisztráció</a></span>
    </form>
    <%
        String username = request.getParameter("username");
        session.setAttribute("username", username);
    %>
</div>

</body>
</html>
