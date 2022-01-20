package hu.alkfejl.dao;

import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Filmek;
import hu.alkfejl.model.Vetitesek;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VetitesekDAOImpl implements VetitesekDAO {

    private static final String SELECT_VETITESEK = "SELECT * from VETITESEK";
    private static final String INSERT_VETITES = "insert into vetitesek (film_id,datum,terem_id,szabad_helyek,film_cim) values(?,?,?,?,?)";
    private static final String UPDATE_VETITES = "update vetitesek set film_id=?, datum=?, terem_id=?, szabad_helyek=?, film_cim=? where id=?";
    private static final String DELETE_VETITES = "delete from vetitesek where id=?";
    private Properties props = new Properties();
    private String db_URL;

    public VetitesekDAOImpl(){db_URL = FilmConfig.getValue("db.url");
    }


    @Override
    public List<Vetitesek> findAll() {

        List<Vetitesek> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(db_URL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_VETITESEK);

        ){
            while(rs.next()){
                Vetitesek vetites = new Vetitesek();
                vetites.setId(rs.getInt("id"));
                vetites.setFilm_id(rs.getInt("film_id"));
                LocalDateTime date = LocalDateTime.parse(rs.getString("datum"));
                vetites.setDatum(date== null ? LocalDateTime.now() : date);

                vetites.setTerem_id(rs.getInt("terem_id"));
                vetites.setSzabad_helyek(rs.getInt("szabad_helyek"));
                vetites.setFilm_cim(rs.getString("film_cim"));

                result.add(vetites);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Vetitesek save(Vetitesek vetites) {
        try(Connection c = DriverManager.getConnection(db_URL);
            PreparedStatement stmt = vetites.getId()<=0 ? c.prepareStatement(INSERT_VETITES, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_VETITES);
        ){
            if(vetites.getId()>0) {
                stmt.setInt(6,vetites.getId());
            }
            stmt.setInt(1,vetites.getFilm_id());
            stmt.setString(2, vetites.getDatum().toString());
            stmt.setInt(3, vetites.getTerem_id());
            stmt.setInt(4, vetites.getSzabad_helyek());
            stmt.setString(5, vetites.getFilm_cim());

            int affectedRows= stmt.executeUpdate();

            if(affectedRows == 0){
                return null;
            }
            if(vetites.getId()<=0){
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    vetites.setId(genKeys.getInt(1));
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return vetites;
    }

    @Override
    public void delete(Vetitesek vetites) {
        try(Connection c = DriverManager.getConnection(db_URL);
            PreparedStatement stmt = c.prepareStatement(DELETE_VETITES);
        ){
            stmt.setInt(1,vetites.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
