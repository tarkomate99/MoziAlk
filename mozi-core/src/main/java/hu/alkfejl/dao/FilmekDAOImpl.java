package hu.alkfejl.dao;

import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Filmek;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FilmekDAOImpl implements FilmekDAO{

    private static final String SELECT_ALL_FILMEK = "SELECT * FROM FILMEK";
    private static final String INSERT_FILM = "INSERT INTO FILMEK (cim,hossz,korhatar,rendezo,szereplok,leiras,boritokep,webkep,trailer) values (?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_FILM = "UPDATE FILMEK SET cim=?, hossz=?, korhatar=?,rendezo=?,szereplok=?,leiras=?,boritokep=?,webkep=?,trailer=? where id=?";
    private static final String DELETE_FILM = "DELETE FROM FILMEK WHERE id=?";
    private Properties props = new Properties();
    private String CONNECTION_URL;
    public FilmekDAOImpl() {
        CONNECTION_URL = FilmConfig.getValue("db.url");
    }

    @Override
    public List<Filmek> findAll() {

        List<Filmek> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_FILMEK);

        ){
            while(rs.next()){
                Filmek film = new Filmek();
                film.setId(rs.getInt("id"));
                film.setCim(rs.getString("cim"));
                film.setHossz(rs.getInt("hossz"));
                film.setKorhatar(rs.getInt("korhatar"));
                film.setRendezo(rs.getString("rendezo"));
                film.setSzereplok(rs.getString("szereplok"));
                film.setLeiras(rs.getString("leiras"));
                film.setBoritokep(rs.getString("boritokep"));
                film.setWebkep(rs.getString("webkep"));
                film.setTrailer(rs.getString("trailer"));

                result.add(film);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Filmek save(Filmek film) {
        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            PreparedStatement stmt = film.getId()<=0 ? c.prepareStatement(INSERT_FILM, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_FILM);
        ){
            if(film.getId()>0) {
                stmt.setInt(10,film.getId());
            }
            stmt.setString(1, film.getCim());
            stmt.setInt(2, film.getHossz());
            stmt.setInt(3, film.getKorhatar());
            stmt.setString(4, film.getRendezo());
            stmt.setString(5, film.getSzereplok());
            stmt.setString(6, film.getLeiras());
            stmt.setString(7, film.getBoritokep());
            stmt.setString(8,film.getWebkep());
            stmt.setString(9,film.getTrailer());

            int affectedRows= stmt.executeUpdate();

            if(affectedRows == 0){
                return null;
            }
            if(film.getId()<=0){
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    film.setId(genKeys.getInt(1));
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return film;
    }

    @Override
    public void delete(Filmek film) {
        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            PreparedStatement stmt = c.prepareStatement(DELETE_FILM);
        ){
            stmt.setInt(1,film.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
