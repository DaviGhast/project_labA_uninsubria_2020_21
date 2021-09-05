package ui.controllers;

import centrivaccinali.CittadinoVaccinato;
import centrivaccinali.GestioneCentriVaccinali;
import centrivaccinali.GestioneVaccinati;
import cittadini.CittadinoRegistrato;
import cittadini.GestioneCittadinoRegistrato;
import criptazione.AlgoritmoMD5;
import gestionefile.GestioneCsv;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegistrazioneCittadinoController implements Initializable {

    private CittadinoRegistrato cittadinoRegistrato;

    @FXML private TextField idVaccinazione, nomeCittadino, cognomeCittadino, codiceFiscale, userid, email;
    @FXML private PasswordField password;
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
    public boolean validatorfield2(){
        if (Pattern.matches("^[a-zA-Z]{2,30}",nomeCittadino.getText())){
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
        if (Pattern.matches("^[a-zA-Z]{2,30}",cognomeCittadino.getText())){
            cross2.setVisible(false);
            checkmark2.setVisible(true);
            return true;
        } else {
            checkmark2.setVisible(false);
            cross2.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield4(){
        if (Pattern.matches("^[A-Z0-9]{16}",codiceFiscale.getText())){
            cross3.setVisible(false);
            checkmark3.setVisible(true);
            return true;
        } else {
            checkmark3.setVisible(false);
            cross3.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield5(){
        if (Pattern.matches("^[0-9]",userid.getText())){
            cross4.setVisible(false);
            checkmark4.setVisible(true);
            return true;
        } else {
            checkmark4.setVisible(false);
            cross4.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield6(){
        if (Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",email.getText())){
            cross5.setVisible(false);
            checkmark5.setVisible(true);
            return true;
        } else {
            checkmark5.setVisible(false);
            cross5.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield7(){
        if (Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",password.getText())){
            cross6.setVisible(false);
            checkmark6.setVisible(true);
            return true;
        } else {
            checkmark6.setVisible(false);
            cross6.setVisible(true);
            return false;
        }
    }

    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {
        if (validatorfield1() & validatorfield2() & validatorfield3() & validatorfield4() & validatorfield5() & validatorfield6()){
            cittadinoRegistrato = new CittadinoRegistrato();
            cittadinoRegistrato.setIdVaccinazione(Short.parseShort(idVaccinazione.getText()));
            cittadinoRegistrato.setNome(nomeCittadino.getText());
            cittadinoRegistrato.setCognome(cognomeCittadino.getText());
            cittadinoRegistrato.setUserid(userid.getText());
            cittadinoRegistrato.setEmail(email.getText());
            cittadinoRegistrato.setPassword(AlgoritmoMD5.converti(password.getText()));
            GestioneCittadinoRegistrato.getInstance().registraCittadino(cittadinoRegistrato);
            description.setText("Cittadino Registrato con Successo");
            confirm.setDisable(true);
            cittadinoRegistrato = null;
        }
    }

    @FXML public void viewRegex1(){
        infoRegex.setText("Nome Centro: Inserire da 1 a 30 caratteri, il centro deve esistere");
    }
    @FXML public void viewRegex2(){
        infoRegex.setText("Qualificatore Indirizzo: Inserire da 1 a 10 caratteri alfabetici");
    }
    @FXML public void viewRegex3(){
        infoRegex.setText("Indirizzo: Inserireda 2 a 30 caratteri");
    }
    @FXML public void viewRegex4(){
        infoRegex.setText("Numero Civico: Inserire da 1 a 4 caratteri numerici");
    }
    @FXML public void viewRegex5(){
        infoRegex.setText("Comune: Inserire da 1 a 50 caratteri alfabetici");
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
