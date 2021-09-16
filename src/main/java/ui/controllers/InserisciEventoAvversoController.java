package ui.controllers;

import centrivaccinali.GestioneCentriVaccinali;
import cittadini.CittadinoRegistrato;
import cittadini.GestioneCittadinoRegistrato;
import criptazione.AlgoritmoMD5;
import gestionefile.GestioneCsv;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class InserisciEventoAvversoController implements Initializable {

    private CittadinoRegistrato cittadinoRegistrato;

    @FXML private TextField idVaccinazione, evento, severita, note;
    @FXML private Label description, infoRegex;
    @FXML private Button confirm;
    @FXML private ImageView cross, checkmark, cross1, checkmark1, cross2, checkmark2, cross3, checkmark3, cross4, checkmark4, cross5, checkmark5, cross6, checkmark6;


    public boolean validatorfield1(){
        GestioneCsv vaccinati = new GestioneCsv("Vaccinati.dati",new String[]{"Id Univoco", "Centro Vaccinale", "Id Interno"});
        vaccinati.verificaFile();
        if (Pattern.matches("^[0-9]",idVaccinazione.getText()) & vaccinati.ricercaIdEsiste(idVaccinazione.getText())){
            cross.setVisible(false);
            checkmark.setVisible(true);
            return true;
        } else {
            checkmark.setVisible(false);
            cross.setVisible(true);
            return false;
        }
    }


    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {
        if (validatorfield1() ){

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
