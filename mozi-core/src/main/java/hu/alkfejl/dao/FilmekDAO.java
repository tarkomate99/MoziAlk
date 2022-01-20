package hu.alkfejl.dao;

import hu.alkfejl.model.Filmek;

import java.util.List;

public interface FilmekDAO {

    List<Filmek> findAll();

    Filmek save(Filmek film);

    void delete(Filmek film);

}
