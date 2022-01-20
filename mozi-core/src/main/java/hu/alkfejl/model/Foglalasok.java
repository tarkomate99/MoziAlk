package hu.alkfejl.model;

import javafx.beans.property.*;

import java.util.Date;

public class Foglalasok {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty nev = new SimpleStringProperty(this, "nev");
    private IntegerProperty vetites_id = new SimpleIntegerProperty(this, "vetites_id");
    private IntegerProperty helyek = new SimpleIntegerProperty(this, "helyek");
    private IntegerProperty ar = new SimpleIntegerProperty(this, "ar");
    private IntegerProperty felhasznalo_id = new SimpleIntegerProperty(this, "felhasznalo_id");
    private ObjectProperty<Felhasznalok> user = new SimpleObjectProperty<>(this, "user");
    private StringProperty felhasznalo_nev = new SimpleStringProperty(this, "felhasznalo_nev");
    private StringProperty datum = new SimpleStringProperty(this, "datum");
    private StringProperty rögzites_datuma = new SimpleStringProperty(this, "rögzites_datuma");
    private StringProperty lefoglalt_helyek = new SimpleStringProperty(this, "lefoglalt_helyek");

    public String getLefoglalt_helyek() {
        return lefoglalt_helyek.get();
    }

    public StringProperty lefoglalt_helyekProperty() {
        return lefoglalt_helyek;
    }

    public void setLefoglalt_helyek(String lefoglalt_helyek) {
        this.lefoglalt_helyek.set(lefoglalt_helyek);
    }

    public String getDatum() {
        return datum.get();
    }

    public StringProperty datumProperty() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum.set(datum);
    }

    public String getRögzites_datuma() {
        return rögzites_datuma.get();
    }

    public StringProperty rögzites_datumaProperty() {
        return rögzites_datuma;
    }

    public void setRögzites_datuma(String rögzites_datuma) {
        this.rögzites_datuma.set(rögzites_datuma);
    }

    public String getFelhasznalo_nev() {
        return felhasznalo_nev.get();
    }

    public StringProperty felhasznalo_nevProperty() {
        return felhasznalo_nev;
    }

    public void setFelhasznalo_nev(String felhasznalo_nev) {
        this.felhasznalo_nev.set(felhasznalo_nev);
    }

    public Felhasznalok getUser() {
        return user.get();
    }

    public ObjectProperty<Felhasznalok> userProperty() {
        return user;
    }

    public void setUser(Felhasznalok user) {
        this.user.set(user);
    }

    public int getFelhasznalo_id() {
        return felhasznalo_id.get();
    }

    public IntegerProperty felhasznalo_idProperty() {
        return felhasznalo_id;
    }

    public void setFelhasznalo_id(int felhasznalo_id) {
        this.felhasznalo_id.set(felhasznalo_id);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public int getVetites_id() {
        return vetites_id.get();
    }

    public IntegerProperty vetites_idProperty() {
        return vetites_id;
    }

    public void setVetites_id(int vetites_id) {
        this.vetites_id.set(vetites_id);
    }

    public int getHelyek() {
        return helyek.get();
    }

    public IntegerProperty helyekProperty() {
        return helyek;
    }

    public void setHelyek(int helyek) {
        this.helyek.set(helyek);
    }

    public int getAr() {
        return ar.get();
    }

    public IntegerProperty arProperty() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar.set(ar);
    }
}
