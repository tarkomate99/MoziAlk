package hu.alkfejl.model;

import javafx.beans.property.*;

public class Filmek {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty cim = new SimpleStringProperty(this, "cim");
    private IntegerProperty hossz = new SimpleIntegerProperty(this, "hossz");
    private IntegerProperty korhatar = new SimpleIntegerProperty(this, "korhatar");
    private StringProperty rendezo = new SimpleStringProperty(this, "rendezo");
    private StringProperty szereplok = new SimpleStringProperty(this, "szereplok");
    private StringProperty leiras = new SimpleStringProperty(this, "leiras");
    private StringProperty boritokep = new SimpleStringProperty(this, "boritokep");
    private StringProperty webkep = new SimpleStringProperty(this,"webkep");
    private StringProperty trailer = new SimpleStringProperty(this,"trailer");

    public String getTrailer() {
        return trailer.get();
    }

    public StringProperty trailerProperty() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer.set(trailer);
    }

    public String getWebkep() {
        return webkep.get();
    }

    public StringProperty webkepProperty() {
        return webkep;
    }

    public void setWebkep(String webkep) {
        this.webkep.set(webkep);
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

    public String getCim() {
        return cim.get();
    }

    public StringProperty cimProperty() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim.set(cim);
    }

    public int getHossz() {
        return hossz.get();
    }

    public IntegerProperty hosszProperty() {
        return hossz;
    }

    public void setHossz(int hossz) {
        this.hossz.set(hossz);
    }

    public int getKorhatar() {
        return korhatar.get();
    }

    public IntegerProperty korhatarProperty() {
        return korhatar;
    }

    public void setKorhatar(int korhatar) {
        this.korhatar.set(korhatar);
    }

    public String getRendezo() {
        return rendezo.get();
    }

    public StringProperty rendezoProperty() {
        return rendezo;
    }

    public void setRendezo(String rendezo) {
        this.rendezo.set(rendezo);
    }

    public String getSzereplok() {
        return szereplok.get();
    }

    public StringProperty szereplokProperty() {
        return szereplok;
    }

    public void setSzereplok(String szereplok) {
        this.szereplok.set(szereplok);
    }

    public String getLeiras() {
        return leiras.get();
    }

    public StringProperty leirasProperty() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras.set(leiras);
    }

    public String getBoritokep() {
        return boritokep.get();
    }

    public StringProperty boritokepProperty() {
        return boritokep;
    }

    public void setBoritokep(String boritokep) {
        this.boritokep.set(boritokep);
    }
}
