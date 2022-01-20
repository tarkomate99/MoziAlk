package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.VetitesekDAO;
import hu.alkfejl.dao.VetitesekDAOImpl;
import hu.alkfejl.model.Vetitesek;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import tornadofx.control.DateTimePicker;

public class EditVetitesController {

    private Vetitesek vetites;
    private VetitesekDAO vetitesekDAO = new VetitesekDAOImpl();

    @FXML
    private TextField film_id;
    @FXML
    private DateTimePicker datum;
    @FXML
    private TextField ido;
    @FXML
    private TextField terem_id;
    @FXML
    private TextField szabad_helyek;
    @FXML
    private TextField film_cim;

    public void setVetites(Vetitesek v){
        this.vetites = v;

        film_id.textProperty().bindBidirectional(vetites.film_idProperty(), new NumberStringConverter());
        datum.dateTimeValueProperty().bindBidirectional(vetites.datumProperty());
        terem_id.textProperty().bindBidirectional(vetites.terem_idProperty(),new NumberStringConverter());
        szabad_helyek.textProperty().bindBidirectional(vetites.szabad_helyekProperty(),new NumberStringConverter());
        film_cim.textProperty().bindBidirectional(vetites.film_cimProperty());

    }
    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/vetitesek_window.fxml");
    }
    @FXML
    public void onSave(){
        vetitesekDAO.save(vetites);
        App.loadFXML("/fxml/vetitesek_window.fxml");
    }

}
