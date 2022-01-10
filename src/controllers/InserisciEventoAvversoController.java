package controllers;

import centrivaccinali.EventoAvverso;
import centrivaccinali.GestioneCentriVaccinali;
import centrivaccinali.GestioneVaccinati;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class InserisciEventoAvversoController implements Initializable {

    private EventoAvverso eventoAvverso;

    @FXML private TextField evento, severita, note;
    @FXML private Label description, infoRegex;
    @FXML private Button confirm;
    @FXML private ImageView cross, checkmark, cross1, checkmark1, cross2, checkmark2;


    public boolean validatorfield1(){
        if (Pattern.matches("^[a-zA-Z ]{2,30}",evento.getText())){
            cross.setVisible(false);
            checkmark.setVisible(true);
            return true;
        } else {
            checkmark.setVisible(false);
            cross.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield2(){
        if (Pattern.matches("^[0-5]",severita.getText())){
            cross1.setVisible(false);
            checkmark1.setVisible(true);
            return true;
        } else {
            checkmark1.setVisible(false);
            cross1.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield3(){
        if (Pattern.matches("^[a-zA-Z0-9 ,.-]{0,256}",note.getText())){
            cross2.setVisible(false);
            checkmark2.setVisible(true);
            return true;
        } else {
            checkmark2.setVisible(false);
            cross2.setVisible(true);
            return false;
        }
    }


    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException, URISyntaxException {
        if (validatorfield1() & validatorfield2() & validatorfield3()){
            eventoAvverso = new EventoAvverso();
            eventoAvverso.setEvento(FixInput.getInstance().fixString(evento.getText()));
            eventoAvverso.setSeverita(Byte.parseByte(severita.getText()));
            if (note.getText().equals("") | note.getText().equals(" ")) {
                eventoAvverso.setNote(" ");
            } else {
                eventoAvverso.setNote(FixInput.getInstance().fixString(note.getText()));
            }
            System.out.println(eventoAvverso.getEvento());
            GestioneVaccinati.getInstance(GestioneCentriVaccinali.getInstance().getNomeCentroByIdVaccinato((short)
                    FixInput.getInstance().getDataBuffer())).inserisciEventiAvversi((short)
                    FixInput.getInstance().getDataBuffer(),eventoAvverso);
            description.setText("Evento Avverso Registrato con Successo");
            confirm.setDisable(true);
            eventoAvverso = null;
            MainUIController.setRoot("InserisciEventoAvverso");
            FixInput.getInstance().setDataBuffer(FixInput.getInstance().getDataBuffer());
        }
    }

    @FXML public void viewRegex1(){
        infoRegex.setText("Evento Avverso : Inserire da 2 a 30 caratteri alfabetici");
    }
    @FXML public void viewRegex2(){
        infoRegex.setText("Severita: Scala da 1 a 5");
    }
    @FXML public void viewRegex3(){
        infoRegex.setText("Nota: Inserire al massimo 256 caratteri alfanumerici (,.-)");
    }



    @FXML void exit_button() throws IOException {
        MainUIController.setRoot("Welcome");
    }

    @FXML public void back_button(ActionEvent actionEvent) throws IOException {
        MainUIController.setRoot("CittadinoView");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            GestioneCentriVaccinali.getInstance().verificaFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
