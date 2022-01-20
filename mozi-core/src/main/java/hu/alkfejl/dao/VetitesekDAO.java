package hu.alkfejl.dao;

import hu.alkfejl.model.Vetitesek;

import java.util.List;

public interface VetitesekDAO {

    Vetitesek save(Vetitesek vetites);

    void delete(Vetitesek vetites);

    List<Vetitesek> findAll();
}
