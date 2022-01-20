package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.TermekDAO;
import hu.alkfejl.dao.TermekDAOImpl;
import hu.alkfejl.model.Termek;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class EditTeremController {
    private Termek terem;
    private TermekDAO termekDAO = new TermekDAOImpl();

    @FXML
    private TextField nev;
    @FXML
    private TextField sorszam;
    @FXML
    private TextField oszlopszam;

    public void setTerem(Termek t){
        this.terem = t;

        nev.textProperty().bindBidirectional(terem.nevProperty());
        sorszam.textProperty().bindBidirectional(terem.sorszamProperty(), new NumberStringConverter());
        oszlopszam.textProperty().bindBidirectional(terem.oszlopszamProperty(), new NumberStringConverter());
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/termek_window.fxml");
    }
    @FXML
    public void onSave(){
        termekDAO.save(terem);
        App.loadFXML("/fxml/termek_window.fxml");
    }
}
