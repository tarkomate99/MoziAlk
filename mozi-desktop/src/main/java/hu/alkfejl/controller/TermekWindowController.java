package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FilmekDAO;
import hu.alkfejl.dao.FilmekDAOImpl;
import hu.alkfejl.dao.TermekDAO;
import hu.alkfejl.dao.TermekDAOImpl;
import hu.alkfejl.model.Filmek;
import hu.alkfejl.model.Termek;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;

public class TermekWindowController implements Initializable {
    TermekDAO dao = new TermekDAOImpl();

    @FXML
    private TableView<Termek> termekTable;

    @FXML
    private TableColumn<Termek, String> nevColumn;
    @FXML
    private TableColumn<Termek, Integer> sorColumn;
    @FXML
    private TableColumn<Termek, Integer> oszlopColumn;
    @FXML
    private TableColumn<Termek, Void> actionsColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();

        nevColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        sorColumn.setCellValueFactory(new PropertyValueFactory<>("sorszam"));
        oszlopColumn.setCellValueFactory(new PropertyValueFactory<>("oszlopszam"));


        actionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Törlés");
            private final Button editBtn = new Button("Módosítás");

            {
                deleteBtn.setOnAction(event -> {
                    Termek t = getTableRow().getItem();
                    deleteTerem(t);
                    refreshTable();
                });

                editBtn.setOnAction(event -> {
                    Termek t = getTableRow().getItem();
                    editTerem(t);
                    refreshTable();
                });
            }

            private void editTerem(Termek t) {
                FXMLLoader fxmlLoader = App.loadFXML("/fxml/edit_terem.fxml");
                EditTeremController controller = fxmlLoader.getController();
                controller.setTerem(t);
            }

            private void deleteTerem(Termek t) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni akarod ezt a termet:" + t.getNev() + "?", ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(buttonType -> {
                    if(buttonType.equals(ButtonType.YES)){
                        dao.delete(t);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }else {
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn,deleteBtn);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });
    }

    private void refreshTable() {
        termekTable.getItems().setAll(dao.findAll());
    }

    public void openAdd(ActionEvent actionEvent) {
        App.loadFXML("/fxml/add_terem.fxml");
    }

    public void openMain(ActionEvent actionEvent) {
        App.loadFXML("/fxml/main_window.fxml");
    }

    public void openFilmek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/filmek_window.fxml");
    }

    public void openFoglalasok(ActionEvent actionEvent) {
        App.loadFXML("/fxml/foglalasok_window.fxml");
    }

    public void openVetitesek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/vetitesek_window.fxml");
    }
}
