package hu.alkfejl.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Termek {
    private IntegerProperty t_id = new SimpleIntegerProperty(this, "t_id");
    private StringProperty nev = new SimpleStringProperty(this, "nev");
    private IntegerProperty sorszam = new SimpleIntegerProperty(this, "sorszam");
    private IntegerProperty oszlopszam = new SimpleIntegerProperty(this, "oszlopszam");

    public int getT_id() {
        return t_id.get();
    }

    public IntegerProperty t_idProperty() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id.set(t_id);
    }

    public String getNev() {
        return nev.get();
    }

    public StringProperty nevProperty() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev.set(nev);
    }

    public int getSorszam() {
        return sorszam.get();
    }

    public IntegerProperty sorszamProperty() {
        return sorszam;
    }

    public void setSorszam(int sorszam) {
        this.sorszam.set(sorszam);
    }

    public int getOszlopszam() {
        return oszlopszam.get();
    }

    public IntegerProperty oszlopszamProperty() {
        return oszlopszam;
    }

    public void setOszlopszam(int oszlopszam) {
        this.oszlopszam.set(oszlopszam);
    }
}
