package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.VetitesekDAO;
import hu.alkfejl.dao.VetitesekDAOImpl;
import hu.alkfejl.model.Filmek;
import hu.alkfejl.model.Vetitesek;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.w3c.dom.Text;
import tornadofx.control.DateTimePicker;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AddVetitesController {
    private Vetitesek vetites;
    private VetitesekDAO vetitesekDAO = new VetitesekDAOImpl();

    @FXML
    private TextField film_id;
    @FXML
    private DateTimePicker datum;
    @FXML
    private TextField ido;
    @FXML
    private TextField terem_id;
    @FXML
    private TextField szabad_helyek;
    @FXML
    private TextField film_cim;

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/vetitesek_window.fxml");
    }
    @FXML
    public void onSave(){
        Vetitesek v = new Vetitesek();
        v.setFilm_id(Integer.parseInt(film_id.getText()));
        v.setDatum(datum.getDateTimeValue()); //todo convert to localdatetime
        v.setTerem_id(Integer.parseInt(terem_id.getText()));
        v.setSzabad_helyek(Integer.parseInt(szabad_helyek.getText()));
        v.setFilm_cim(film_cim.getText());
        vetitesekDAO.save(v);

        App.loadFXML("/fxml/vetitesek_window.fxml");
    }

}
