package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FilmekDAO;
import hu.alkfejl.dao.FilmekDAOImpl;
import hu.alkfejl.model.Filmek;
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

public class FilmekWindowController implements Initializable {
    private FilmekDAO dao = new FilmekDAOImpl();

    private List<Filmek> all;

    @FXML
    private TableView<Filmek> filmekTable;

    @FXML
    private TableColumn<Filmek, String> cimColumn;
    @FXML
    private TableColumn<Filmek, Integer> hosszColumn;
    @FXML
    private TableColumn<Filmek, Integer> korhatarColumn;
    @FXML
    private TableColumn<Filmek, String> rendezoColumn;
    @FXML
    private TableColumn<Filmek, String> szereplokColumn;
    @FXML
    private TableColumn<Filmek, String> leirasColumn;
    @FXML
    private TableColumn<Filmek, Void> actionsColumn;
    @FXML
    private TextField cimSearch;
    @FXML
    private TextField hosszSearch;
    @FXML
    private TextField korhatarSearch;
    @FXML
    private TextField rendezoSearch;
    @FXML
    private TextField szereploSearch;

    @FXML
    public void onSearch(){
        List<Filmek> filtered = all.stream().filter(filmek -> filmek.getCim().contains(cimSearch.getText()) &&
                String.valueOf(filmek.getHossz()).contains(hosszSearch.getText()) &&
                String.valueOf(filmek.getKorhatar()).contains(korhatarSearch.getText()) &&
                filmek.getRendezo().contains(rendezoSearch.getText()) &&
                filmek.getSzereplok().contains(szereploSearch.getText()))
                .collect(Collectors.toList());
        filmekTable.getItems().setAll(filtered);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();

        cimColumn.setCellValueFactory(new PropertyValueFactory<>("cim"));
        hosszColumn.setCellValueFactory(new PropertyValueFactory<>("hossz"));
        korhatarColumn.setCellValueFactory(new PropertyValueFactory<>("korhatar"));
        rendezoColumn.setCellValueFactory(new PropertyValueFactory<>("rendezo"));
        szereplokColumn.setCellValueFactory(new PropertyValueFactory<>("szereplok"));
        //leirasColumn.setCellValueFactory(new PropertyValueFactory<>("leiras"));

        actionsColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Törlés");
            private final Button editBtn = new Button("Módosítás");

            {
                deleteBtn.setOnAction(event -> {
                    Filmek f = getTableRow().getItem();
                    deleteFilm(f);
                    refreshTable();
                });

                editBtn.setOnAction(event -> {
                    Filmek f = getTableRow().getItem();
                    editFilm(f);
                    refreshTable();
                });
            }

            private void editFilm(Filmek f) {
                FXMLLoader fxmlLoader = App.loadFXML("/fxml/edit_film.fxml");
                EditFilmController controller = fxmlLoader.getController();
                controller.setFilm(f);
            }

            private void deleteFilm(Filmek f) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni akarod ezt a filmet:" + f.getCim() + "?", ButtonType.YES, ButtonType.NO);
                confirm.showAndWait().ifPresent(buttonType -> {
                    if(buttonType.equals(ButtonType.YES)){
                        dao.delete(f);
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
        filmekTable.getItems().setAll(all);
    }

    public void openAdd(ActionEvent actionEvent) {
        App.loadFXML("/fxml/add_film.fxml");
    }

    public void openMain(ActionEvent actionEvent) {
        App.loadFXML("/fxml/main_window.fxml");
    }

    public void openTermek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/termek_window.fxml");
    }

    public void openFoglalasok(ActionEvent actionEvent) {
        App.loadFXML("/fxml/foglalasok_window.fxml");
    }

    public void openVetitesek(ActionEvent actionEvent) {
        App.loadFXML("/fxml/vetitesek_window.fxml");
    }
}
