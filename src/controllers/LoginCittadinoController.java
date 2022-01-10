package controllers;

import centrivaccinali.GestioneCentriVaccinali;
import cittadini.GestioneCittadinoRegistrato;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class LoginCittadinoController implements Initializable {

    @FXML private TextField userid;
    @FXML private PasswordField password;
    @FXML private Label description, infoRegex;
    @FXML private Button confirm;
    @FXML private ImageView cross, checkmark, cross1, checkmark1, cross2, checkmark2;


    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException, URISyntaxException {
        if (GestioneCittadinoRegistrato.getInstance().rispostaCittadinoEsiste(userid.getText(), password.getText())){
            infoRegex.setText("Accesso Eseguito con Successo!");
            short idVaccinazione = GestioneCittadinoRegistrato.getInstance().getCittadinoRegistrato(userid.getText()).getIdVaccinazione();
            FixInput.getInstance().setDataBuffer(idVaccinazione);
            MainUIController.setRoot("InserisciEventoAvverso");
        }
        else {
            infoRegex.setText("Accesso non Eseguito, Userid o Password Errati!");
        }
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
