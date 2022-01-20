package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.event.ActionEvent;;

public class MainWindowController{

    public void openFilmek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/filmek_window.fxml");
    }

    public void openTermek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/termek_window.fxml");
    }

    public void openVetitesek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/vetitesek_window.fxml");
    }

    public void openFoglalasok(ActionEvent actionEvent) {
        App.loadFXML("/fxml/foglalasok_window.fxml");
    }
}
