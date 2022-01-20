package hu.alkfejl.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vetitesek {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private IntegerProperty film_id = new SimpleIntegerProperty(this, "film_id");
    private ObjectProperty<LocalDateTime> datum = new SimpleObjectProperty<>(this, "datum");
    private IntegerProperty terem_id = new SimpleIntegerProperty(this, "terem_id");
    private IntegerProperty szabad_helyek = new SimpleIntegerProperty(this, "szabad_helyek");
    private StringProperty film_cim = new SimpleStringProperty(this, "film_cim");

    public int getSzabad_helyek() {
        return szabad_helyek.get();
    }

    public IntegerProperty szabad_helyekProperty() {
        return szabad_helyek;
    }

    public void setSzabad_helyek(int szabad_helyek) {
        this.szabad_helyek.set(szabad_helyek);
    }

    public String getFilm_cim() {
        return film_cim.get();
    }

    public StringProperty film_cimProperty() {
        return film_cim;
    }

    public void setFilm_cim(String film_cim) {
        this.film_cim.set(film_cim);
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

    public int getFilm_id() {
        return film_id.get();
    }

    public IntegerProperty film_idProperty() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id.set(film_id);
    }

    public LocalDateTime getDatum() {
        return datum.get();
    }

    public ObjectProperty<LocalDateTime> datumProperty() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum.set(datum);
    }

    public int getTerem_id() {
        return terem_id.get();
    }

    public IntegerProperty terem_idProperty() {
        return terem_id;
    }

    public void setTerem_id(int terem_id) {
        this.terem_id.set(terem_id);
    }
}
