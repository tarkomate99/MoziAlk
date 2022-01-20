package hu.alkfejl.dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Felhasznalok;

import java.sql.*;

public class FelhasznalokDAOImpl  implements FelhasznalokDAO{

    private static FelhasznalokDAOImpl instance;
    private static final String DB_CONN_STR = FilmConfig.getValue("db.url");

    public static FelhasznalokDAO getInstance(){
        if(instance==null){
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1){
                e1.printStackTrace();
            }
            instance = new FelhasznalokDAOImpl();
        }
        return instance;
    }
    private FelhasznalokDAOImpl(){

    }

    @Override
    public Felhasznalok getUserById(int id) {
        try(Connection conn = DriverManager.getConnection(DB_CONN_STR);
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM FELHASZNALOK where id=?")
        ){
            pst.setInt(1,id);

            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                Felhasznalok u = new Felhasznalok();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setPassword(rs.getString(4));
                return u;

            }
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Felhasznalok user) {
        try (Connection conn = DriverManager.getConnection(DB_CONN_STR);
            PreparedStatement pst = conn.prepareStatement("INSERT INTO FELHASZNALOK (username, password, email) VALUES (?,?,?)")
        ){
            String newPwd = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(newPwd);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());

            pst.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Felhasznalok login(String username, String password) {
        try(Connection conn = DriverManager.getConnection(DB_CONN_STR);
            PreparedStatement pst = conn.prepareStatement("SELECT * from FELHASZNALOK where username=?")
        ){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){

                String dbPass = rs.getString("password");
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), dbPass);
                if(result.verified){
                    Felhasznalok user = new Felhasznalok();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setId(rs.getInt("id"));
                    return user;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
