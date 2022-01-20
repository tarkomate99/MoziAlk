package hu.alkfejl.controller;

import hu.alkfejl.dao.FelhasznalokDAO;
import hu.alkfejl.dao.FelhasznalokDAOImpl;
import hu.alkfejl.model.Felhasznalok;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        FelhasznalokDAO userDAO = FelhasznalokDAOImpl.getInstance();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Felhasznalok user = userDAO.login(username, password);

        if( user == null){
            response.sendRedirect("pages/login.jsp");
            return;
        }

        request.getSession().setAttribute("currentUser", user);
        request.getSession().setAttribute("user", username);
        response.sendRedirect("pages/list-film.jsp");
    }

}
