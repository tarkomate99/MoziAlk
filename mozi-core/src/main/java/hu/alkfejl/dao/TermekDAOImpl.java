package hu.alkfejl.dao;

import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Termek;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TermekDAOImpl implements TermekDAO{

    private static final String SELECT_ALL_TERMEK = "SELECT * FROM TERMEK";
    private static final String INSERT_TEREM = "INSERT INTO TERMEK (nev,sorszam,oszlopszam) values (?,?,?)";
    private static final String UPDATE_TEREM = "UPDATE TERMEK set nev=?, sorszam=?, oszlopszam=? where t_id=?";
    private static final String DELETE_TEREM = "DELETE FROM TERMEK WHERE t_id=?";
    private String CONNECTION_URL;

    public TermekDAOImpl(String CONNECTION_URL) {
        this.CONNECTION_URL = FilmConfig.getValue("db.url");
    }

    public TermekDAOImpl() {
        CONNECTION_URL = FilmConfig.getValue("db.url");
    }

    @Override
    public List<Termek> findAll() {
        List<Termek> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_TERMEK);
        ){
            while (rs.next()){
                Termek terem = new Termek();
                terem.setT_id(rs.getInt("t_id"));
                terem.setNev(rs.getString("nev"));
                terem.setSorszam(rs.getInt("sorszam"));
                terem.setOszlopszam(rs.getInt("oszlopszam"));

                result.add(terem);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Termek save(Termek terem) {
        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            PreparedStatement stmt = terem.getT_id()<=0 ? c.prepareStatement(INSERT_TEREM, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_TEREM);
        ){
            if(terem.getT_id()>0) {
                stmt.setInt(4,terem.getT_id());
            }
            stmt.setString(1, terem.getNev());
            stmt.setInt(2, terem.getSorszam());
            stmt.setInt(3, terem.getOszlopszam());


            int affectedRows= stmt.executeUpdate();

            if(affectedRows == 0){
                return null;
            }
            if(terem.getT_id()<=0){
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    terem.setT_id(genKeys.getInt(1));
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return terem;
    }

    @Override
    public void delete(Termek terem) {
        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            PreparedStatement stmt = c.prepareStatement(DELETE_TEREM);
        ){
            stmt.setInt(1,terem.getT_id());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
