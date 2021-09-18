package ui.controllers;

import centrivaccinali.GestioneCentriVaccinali;
import centrivaccinali.GestioneVaccinati;
import cittadini.CittadinoRegistrato;
import cittadini.EventoAvverso;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
        if (Pattern.matches("^[a-zA-Z ,.-]{0,256}",note.getText())){
            cross2.setVisible(false);
            checkmark2.setVisible(true);
            return true;
        } else {
            checkmark2.setVisible(false);
            cross2.setVisible(true);
            return false;
        }
    }


    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {
        if (validatorfield1() & validatorfield2() & validatorfield3()){
            eventoAvverso = new EventoAvverso();
            eventoAvverso.setEvento(FixInput.getInstance().fixString(evento.getText()));
            eventoAvverso.setSeverita(Byte.parseByte(severita.getText()));
            if (note.getText().equals("") | note.getText().equals(" ")) {
                eventoAvverso.setNote(note.getText());
            } else {
                eventoAvverso.setNote(FixInput.getInstance().fixString(note.getText()));
            }
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
        infoRegex.setText("id Vaccinazione : Inserire da 0 a 9 caratteri numerici");
    }
    @FXML public void viewRegex2(){
        infoRegex.setText("Nome Cittadino: Inserire da 2 a 30 caratteri alfabetici");
    }
    @FXML public void viewRegex3(){
        infoRegex.setText("Cognome Cittadino: Inserire da 2 a 30 caratteri alfabetici");
    }


    /**
     * Closes the application.
     */
    @FXML void exit_button() {
        Platform.exit();
        System.exit(0);
    }

    @FXML public void back_button(ActionEvent actionEvent) throws IOException {
        MainUIController.setRoot("CittadinoView");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GestioneCentriVaccinali.getInstance().verificaFile();
    }
}
