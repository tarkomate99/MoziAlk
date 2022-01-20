package hu.alkfejl.controller;


import hu.alkfejl.dao.FelhasznalokDAO;
import hu.alkfejl.dao.FelhasznalokDAOImpl;
import hu.alkfejl.model.Felhasznalok;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {

    FelhasznalokDAO dao = FelhasznalokDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Felhasznalok user = new Felhasznalok();
        user.setUsername(req.getParameter("username"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));

        dao.save(user);

        resp.sendRedirect("pages/login.jsp");
    }
}
