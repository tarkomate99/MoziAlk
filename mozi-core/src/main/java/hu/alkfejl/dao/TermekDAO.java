package hu.alkfejl.dao;

import hu.alkfejl.model.Termek;

import java.util.List;

public interface TermekDAO {
    List<Termek> findAll();

    Termek save(Termek terem);

    void delete(Termek terem);
}
