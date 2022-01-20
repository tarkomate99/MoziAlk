package hu.alkfejl.dao;

import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Felhasznalok;
import hu.alkfejl.model.Filmek;
import hu.alkfejl.model.Foglalasok;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FoglalasokDAOImpl implements FoglalasokDAO{

    private static final String SELECT_ALL_FOGLALASOK = "SELECT * FROM FOGLALASOK";
    private static final String INSERT_FOGLALAS = "INSERT INTO FOGLALASOK (nev,vetites_id,helyek,ar,felhasznalo_nev,datum,lefoglalt_helyek) values (?,?,?,?,?,?,?)";
    private static final String UPDATE_FOGLALAS = "UPDATE FOGLALASOK SET nev=?,vetites_id=?,helyek=?,ar=?,felhasznalo_nev=?,datum=?,lefoglalt_helyek=? where id=?";
    private static final String DELETE_FOGLALAS = "DELETE FROM FOGLALASOK WHERE id=?";
    private static final String SELECT_ALL_FOGLALASOK_BY_USER = "SELECT * from FOGLALASOK where felhasznalo_id=?";
    private Properties props = new Properties();
    private String CONNECTION_URL;
    private FelhasznalokDAO userDAO = FelhasznalokDAOImpl.getInstance();
    private static FoglalasokDAOImpl instance;
    public FoglalasokDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CONNECTION_URL = FilmConfig.getValue("db.url");
    }

    public static FoglalasokDAO getInstance() {
        if(instance==null){
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1){
                e1.printStackTrace();
            }
            instance = new FoglalasokDAOImpl();
        }
        return instance;
    }


    @Override
    public Foglalasok save(Foglalasok foglalas) {
        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            PreparedStatement stmt = foglalas.getId()<=0 ? c.prepareStatement(INSERT_FOGLALAS, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_FOGLALAS);
        ){
            if(foglalas.getId()>0) {
                stmt.setInt(8,foglalas.getId());
            }
//            else {
//                if(foglalas.getUser() != null){
//                    stmt.setInt(8,foglalas.getUser().getId());
//                }
//            }
            stmt.setString(1, foglalas.getNev());
            stmt.setInt(2, foglalas.getVetites_id());
            stmt.setInt(3, foglalas.getHelyek());
            stmt.setInt(4, foglalas.getAr());
            stmt.setString(5, foglalas.getFelhasznalo_nev());
            stmt.setString(6, foglalas.getDatum());
            stmt.setString(7, foglalas.getLefoglalt_helyek());


            int affectedRows= stmt.executeUpdate();

            if(affectedRows == 0){
                return null;
            }
            if(foglalas.getId()<=0){
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    foglalas.setId(genKeys.getInt(1));
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return foglalas;
    }

    @Override
    public void delete(Foglalasok foglalas) {
        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            PreparedStatement stmt = c.prepareStatement(DELETE_FOGLALAS);
        ){
            stmt.setInt(1,foglalas.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Foglalasok> findAll() {
        List<Foglalasok> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_FOGLALASOK);

        ){
            while(rs.next()){
                Foglalasok foglalasok = new Foglalasok();
                foglalasok.setNev(rs.getString("nev"));
                foglalasok.setHelyek(rs.getInt("helyek"));
                foglalasok.setVetites_id(rs.getInt("vetites_id"));
                foglalasok.setAr(rs.getInt("ar"));
                foglalasok.setFelhasznalo_nev(rs.getString("felhasznalo_nev"));
                foglalasok.setDatum(rs.getString("datum"));
                foglalasok.setRögzites_datuma(rs.getString("rögzites_datuma"));
                foglalasok.setLefoglalt_helyek(rs.getString("lefoglalt_helyek"));

                result.add(foglalasok);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Foglalasok> findAll(Felhasznalok user) {
        List<Foglalasok> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONNECTION_URL);
        ){
            ResultSet rs;
            if(user==null){
                Statement stmt = c.createStatement();
                rs = stmt.executeQuery(SELECT_ALL_FOGLALASOK);
            } else{
                PreparedStatement stmt = c.prepareStatement(SELECT_ALL_FOGLALASOK_BY_USER);
                stmt.setInt(1, user.getId());
                rs = stmt.executeQuery();
            }
            while (rs.next()){
                Foglalasok foglalas = new Foglalasok();
                foglalas.setId(rs.getInt("id"));
                foglalas.setNev(rs.getString("nev"));
                foglalas.setVetites_id(rs.getInt("vetites_id"));
                foglalas.setHelyek(rs.getInt("helyek"));
                foglalas.setAr(rs.getInt("ar"));
                result.add(foglalas);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }
}
