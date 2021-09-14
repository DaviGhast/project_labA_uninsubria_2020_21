package ui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class RicercaViewController {

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
            MainUIController.setRoot("RicercaCentro1");
        } else if (registraVaccinato.isSelected()) {
            MainUIController.setRoot("RicercaCentro2");
        }
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
}
