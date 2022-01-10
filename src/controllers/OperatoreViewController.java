package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;

/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class OperatoreViewController {

    private final String REGISTRA_CENTRO_DESC = "Registra un nuovo Centro Vaccinale";
    private final String REGISTRA_VACCINATO_DESC = "Pannello di controllo per gli operatori sanitari";

    @FXML private RadioButton registraCentro, registraVaccinato;
    @FXML private Label description;
    @FXML private Button confirm;

    @FXML void view_selection() {
        if (registraCentro.isSelected()) {
            description.setText(REGISTRA_CENTRO_DESC);
            confirm.setDisable(false);
        } else if (registraVaccinato.isSelected()) {
            description.setText(REGISTRA_VACCINATO_DESC);
            confirm.setDisable(false);
        }
    }

    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {
        if (registraCentro.isSelected()) {
            MainUIController.setRoot("RegistraCentroVaccinale");
        } else if (registraVaccinato.isSelected()) {
            MainUIController.setRoot("RegistraVaccinato");
        }
    }


    @FXML void exit_button() {
        Platform.exit();
        System.exit(0);
    }

    @FXML public void back_button(ActionEvent actionEvent) throws IOException {
        MainUIController.setRoot("Welcome");
    }
}
