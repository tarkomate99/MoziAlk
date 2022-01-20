package hu.alkfejl.controller;

import com.sun.glass.ui.CommonDialogs;
import hu.alkfejl.App;
import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.dao.FilmekDAO;
import hu.alkfejl.dao.FilmekDAOImpl;
import hu.alkfejl.model.Filmek;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class EditFilmController {

    @FXML
    private ImageView imageview;
    @FXML
    private TextField boritokep;
    @FXML
    private TextField webkep;

    private String imageUrl;
    private String webUrl;

    public void fileChooser(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        String path= FilmConfig.getValue("img.url");
        if(file !=null){
            try{
                imageUrl = file.toURL().toExternalForm();
                Image img = new Image(imageUrl);
                imageview.setImage(img);
                boritokep.setText(imageUrl);
                webUrl = file.getName();
                webkep.setText(webUrl);
                Files.copy(file.toPath(),
                        (new File(path +"/"+ file.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println(path+"/"+file.getName());
            }catch (MalformedURLException ex){
                throw new IllegalStateException(ex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Filmek film;
    private FilmekDAO filmekDAO = new FilmekDAOImpl();

    @FXML
    private TextField cim;
    @FXML
    private TextField hossz;
    @FXML
    private TextField korhatar;
    @FXML
    private TextField rendezo;
    @FXML
    private TextField szereplok;
    @FXML
    private TextField leiras;
    @FXML
    private TextField trailer;




    public void setFilm(Filmek f) {
        this.film = f;

        cim.textProperty().bindBidirectional(film.cimProperty());
        hossz.textProperty().bindBidirectional(film.hosszProperty(), new NumberStringConverter());
        korhatar.textProperty().bindBidirectional(film.korhatarProperty(), new NumberStringConverter());
        rendezo.textProperty().bindBidirectional(film.rendezoProperty());
        szereplok.textProperty().bindBidirectional(film.szereplokProperty());
        leiras.textProperty().bindBidirectional(film.leirasProperty());
        boritokep.textProperty().bindBidirectional(film.boritokepProperty());
        webkep.textProperty().bindBidirectional(film.webkepProperty());
        trailer.textProperty().bindBidirectional(film.trailerProperty());

        imageUrl = boritokep.getText();
        Image img = new Image(imageUrl);
        imageview.setImage(img);
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/filmek_window.fxml");
    }
    @FXML
    public void onSave(){
        filmekDAO.save(film);
        App.loadFXML("/fxml/filmek_window.fxml");
    }
}
