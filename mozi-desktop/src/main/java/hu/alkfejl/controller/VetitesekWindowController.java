package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.VetitesekDAO;
import hu.alkfejl.dao.VetitesekDAOImpl;
import hu.alkfejl.model.Filmek;
import hu.alkfejl.model.Vetitesek;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class VetitesekWindowController implements Initializable {
    VetitesekDAO dao = new VetitesekDAOImpl();

    private List<Vetitesek> all;

    @FXML
    private TableView<Vetitesek> vetitesekTable;

    @FXML
    private TableColumn<Vetitesek,Integer> filmidColumn;

    @FXML
    private TableColumn<Vetitesek,String> datumColumn;

    @FXML
    private TableColumn<Vetitesek,Integer> teremidColumn;
    @FXML
    private TableColumn<Vetitesek,Integer> szabadhelyekColumn;
    @FXML
    private TableColumn<Vetitesek,String> filmcimColumn;
    @FXML
    private TableColumn<Vetitesek,Void> actionsColumn;
    @FXML
    private TextField cimSearch;
    @FXML
    public void onSearch(){
        List<Vetitesek> filtered = all.stream().filter(vetitesek -> vetitesek.getFilm_cim().contains(cimSearch.getText())).collect(Collectors.toList());
        vetitesekTable.getItems().setAll(filtered);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();

        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        szabadhelyekColumn.setCellValueFactory(new PropertyValueFactory<>("szabad_helyek"));
        filmcimColumn.setCellValueFactory(new PropertyValueFactory<>("film_cim"));

        actionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Törlés");
            private final Button editBtn = new Button("Módosítás");

            {
                deleteBtn.setOnAction(event -> {
                    Vetitesek v = getTableRow().getItem();
                    deleteVetites(v);
                    refreshTable();
                });

                editBtn.setOnAction(event -> {
                    Vetitesek v = getTableRow().getItem();
                    editVetites(v);
                    refreshTable();
                });
            }
            private void editVetites(Vetitesek v) {
                FXMLLoader fxmlLoader = App.loadFXML("/fxml/edit_vetites.fxml");
                EditVetitesController controller = fxmlLoader.getController();
                controller.setVetites(v);
            }

            private void deleteVetites(Vetitesek v) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni akarod ezt a filmet:" + v.getFilm_cim() + "?", ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(buttonType -> {
                    if(buttonType.equals(ButtonType.YES)){
                        dao.delete(v);
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
        all = dao.findAll();
        vetitesekTable.getItems().setAll(all);
    }
    public void openAdd(ActionEvent actionEvent) {
        App.loadFXML("/fxml/add_vetites.fxml");
    }

    public void openMain(ActionEvent actionEvent) {
        App.loadFXML("/fxml/main_window.fxml");
    }

    public void openFilmek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/filmek_window.fxml");
    }


    public void openTermek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/termek_window.fxml");
    }

    public void openFoglalasok(ActionEvent actionEvent) {
        App.loadFXML("/fxml/foglalasok_window.fxml");
    }
}
