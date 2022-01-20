package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FoglalasokDAO;
import hu.alkfejl.dao.FoglalasokDAOImpl;
import hu.alkfejl.model.Foglalasok;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import org.w3c.dom.Text;

public class EditFoglalasController {
    private Foglalasok foglalasok;
    private FoglalasokDAO foglalasokDAO = new FoglalasokDAOImpl();

    @FXML
    private TextField nev;
    @FXML
    private TextField vetites_id;
    @FXML
    private TextField helyek;
    @FXML
    private TextField ar;
    @FXML
    private TextField felhasznalo_nev;
    @FXML
    private TextField datum;
    @FXML
    private TextField lefoglalt_helyek;

    public void setFoglalasok(Foglalasok f){
        this.foglalasok = f;

        nev.textProperty().bindBidirectional(foglalasok.nevProperty());
        vetites_id.textProperty().bindBidirectional(foglalasok.vetites_idProperty(), new NumberStringConverter());
        helyek.textProperty().bindBidirectional(foglalasok.helyekProperty(), new NumberStringConverter());
        ar.textProperty().bindBidirectional(foglalasok.arProperty(), new NumberStringConverter());
        felhasznalo_nev.textProperty().bindBidirectional(foglalasok.felhasznalo_nevProperty());
        datum.textProperty().bindBidirectional(foglalasok.datumProperty());
        lefoglalt_helyek.textProperty().bindBidirectional(foglalasok.lefoglalt_helyekProperty());
    }


    public void onSave(ActionEvent actionEvent) {
        foglalasokDAO.save(foglalasok);
        App.loadFXML("/fxml/foglalasok_window.fxml");
    }

    public void onCancel(ActionEvent actionEvent) {
        App.loadFXML("/fxml/foglalasok_window.fxml");
    }
}
