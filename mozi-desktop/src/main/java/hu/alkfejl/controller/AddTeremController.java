package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.TermekDAO;
import hu.alkfejl.dao.TermekDAOImpl;
import hu.alkfejl.model.Filmek;
import hu.alkfejl.model.Termek;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class AddTeremController {

    private Termek terem;
    private TermekDAO termekDAO = new TermekDAOImpl();

    @FXML
    private TextField nev;
    @FXML
    private TextField sorszam;
    @FXML
    private TextField oszlopszam;

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/termek_window.fxml");
    }

    @FXML
    public void onSave(){
        Termek t = new Termek();

        t.setNev(nev.getText());
        t.setSorszam(Integer.parseInt(sorszam.getText()));
        t.setOszlopszam(Integer.parseInt(oszlopszam.getText()));
        termekDAO.save(t);

        App.loadFXML("/fxml/termek_window.fxml");
    }

}
