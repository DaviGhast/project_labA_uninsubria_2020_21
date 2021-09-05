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

public class LoginCittadinoController implements Initializable {

    private CittadinoRegistrato cittadinoRegistrato;

    @FXML private TextField userid;
    @FXML private PasswordField password;
    @FXML private Label description, infoRegex;
    @FXML private Button confirm;
    @FXML private ImageView cross, checkmark, cross1, checkmark1, cross2, checkmark2;

    public boolean validatorfield1(){
        if (Pattern.matches("^[0-9]",userid.getText())){
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
        if (Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",password.getText())){
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
        if (validatorfield1() & validatorfield2() & GestioneCittadinoRegistrato.getInstance().rispostaCittadinoEsiste(userid.getText(), password.getText())){
            MainUIController.setRoot("EventiAvversi");
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
