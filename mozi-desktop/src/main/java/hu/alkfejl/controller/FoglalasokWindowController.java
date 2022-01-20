package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FoglalasokDAO;
import hu.alkfejl.dao.FoglalasokDAOImpl;
import hu.alkfejl.model.Filmek;
import hu.alkfejl.model.Foglalasok;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FoglalasokWindowController implements Initializable {
    private FoglalasokDAO dao = new FoglalasokDAOImpl();

    private List<Foglalasok> all;

    @FXML
    private TableView<Foglalasok> foglalasokTable;
    @FXML
    private TableColumn<Foglalasok, String> nev;
    @FXML
    private TableColumn<Foglalasok, Integer> helyek;
    @FXML
    private TableColumn<Foglalasok,Integer> ar;
    @FXML
    private TableColumn<Foglalasok, String> felhasznalo_nev;
    @FXML
    private TableColumn<Foglalasok, String> datum;
    @FXML
    private TableColumn<Foglalasok, Date> rögzites_datuma;
    @FXML
    private TableColumn<Foglalasok, Void> actionsColumn;

    public void openMain(ActionEvent actionEvent) {
        App.loadFXML("/fxml/main_window.fxml");
    }

    public void openTermek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/termek_window.fxml");
    }

    public void openAdd(ActionEvent actionEvent) {
        System.out.println("edit");
    }

    public void onSearch(KeyEvent keyEvent) {
        System.out.println("search");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();

        nev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        helyek.setCellValueFactory(new PropertyValueFactory<>("helyek"));
        ar.setCellValueFactory(new PropertyValueFactory<>("ar"));
        felhasznalo_nev.setCellValueFactory(new PropertyValueFactory<>("felhasznalo_nev"));
        datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        rögzites_datuma.setCellValueFactory(new PropertyValueFactory<>("rögzites_datuma"));

        actionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Törlés");
            private final Button editBtn = new Button("Módosítás");

            {
                deleteBtn.setOnAction(event -> {
                    Foglalasok f = getTableRow().getItem();
                    deleteFoglalas(f);
                    refreshTable();
                });
                editBtn.setOnAction(event -> {
                    Foglalasok f = getTableRow().getItem();
                    editFoglalas(f);
                    refreshTable();
                });
            }
            private void editFoglalas(Foglalasok f){
                FXMLLoader fxmlLoader = App.loadFXML("/fxml/edit_foglalas.fxml");
                EditFoglalasController controller = fxmlLoader.getController();
                controller.setFoglalasok(f);
            }
            private void deleteFoglalas(Foglalasok f){
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni akarod ezt a foglalást:" + f.getNev() + "?", ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(buttonType -> {
                    if(buttonType.equals(ButtonType.YES)){
                        dao.delete(f);
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn, deleteBtn);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });
    }
    @FXML
    private void refreshTable(){
        foglalasokTable.getItems().setAll(dao.findAll());
    }

    public void openFilmek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/filmek_window.fxml");
    }

    public void openVetitesek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/vetitesek_window.fxml");
    }
}
