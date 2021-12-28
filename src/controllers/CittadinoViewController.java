package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class CittadinoViewController {

    private final String REGISTRA_CITTADINO_DESC = "Registrati al Portale, tieni sotto mano l'id che hai ricevuto al momento della vaccinazione";
    private final String SEGNA_EVENTI_AVVERSI_DESC = "Se hai già il tuo account puoi segnalare eventuali eventi avversi, dopo aver fatto il login";
    private final String INFO_CENTRI_DESC = "Sei alla ricerca di informazioni sui Centri Vaccinali, fai una ricerca";

    @FXML private RadioButton registraCittadino, segnaEventiAvversi, infoCentriVaccinali;
    @FXML private Label description;
    @FXML private Button confirm;

    @FXML void view_selection() {
        if (registraCittadino.isSelected()) {
            description.setText(REGISTRA_CITTADINO_DESC);
            confirm.setDisable(false);
        } else if (segnaEventiAvversi.isSelected()) {
            description.setText(SEGNA_EVENTI_AVVERSI_DESC);
            confirm.setDisable(false);
        } else if (infoCentriVaccinali.isSelected()) {
            description.setText(INFO_CENTRI_DESC);
            confirm.setDisable(false);
        }
    }

    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {
        if (registraCittadino.isSelected()) {
            MainUIController.setRoot("RegistrazioneCittadino");
        } else if (segnaEventiAvversi.isSelected()) {
            MainUIController.setRoot("LoginCittadino");
        } else if (infoCentriVaccinali.isSelected()) {
            MainUIController.setRoot("RicercaView");
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
        MainUIController.setRoot("Welcome");
    }
}
