package hu.alkfejl.dao;

import hu.alkfejl.model.Felhasznalok;

public interface FelhasznalokDAO {
    Felhasznalok getUserById(int id);
    void save(Felhasznalok user);
    Felhasznalok login(String username, String password);
}
