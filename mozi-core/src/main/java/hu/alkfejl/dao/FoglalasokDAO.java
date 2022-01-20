package hu.alkfejl.dao;

import hu.alkfejl.model.Felhasznalok;
import hu.alkfejl.model.Filmek;
import hu.alkfejl.model.Foglalasok;

import java.util.List;

public interface FoglalasokDAO {
    List<Foglalasok> findAll();
    List<Foglalasok> findAll(Felhasznalok user);

    Foglalasok save(Foglalasok foglalas);

    void delete(Foglalasok foglalas);
}
