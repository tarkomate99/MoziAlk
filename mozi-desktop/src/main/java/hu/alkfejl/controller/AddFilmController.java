package hu.alkfejl.controller;

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

public class AddFilmController {

   @FXML
   private ImageView imageview;

   @FXML
   private TextField boritokep;
   @FXML
   private TextField webkep;


   private String imageUrl;
   private String webUrl;

    public void fileChooser(ActionEvent actionEvent) {
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

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/filmek_window.fxml");
    }
    @FXML
    public void onSave(){
        Filmek f = new Filmek();

        f.setCim(cim.getText());
        f.setHossz(Integer.parseInt(hossz.getText()));
        f.setKorhatar(Integer.parseInt(korhatar.getText()));
        f.setRendezo(rendezo.getText());
        f.setSzereplok(szereplok.getText());
        f.setLeiras(leiras.getText());
        f.setBoritokep(boritokep.getText());
        f.setWebkep(webkep.getText());
        f.setTrailer(trailer.getText());
        filmekDAO.save(f);

        App.loadFXML("/fxml/filmek_window.fxml");
    }
}
