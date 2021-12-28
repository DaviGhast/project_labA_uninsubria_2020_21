package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class WelcomeController {

    private final String OPERATORE_DESC = "Pannello di controllo per gli operatori sanitari";

    private final String CITTADINO_DESC = "Pagina per i cittadini";


    @FXML private RadioButton operatore, cittadino;
    @FXML private Label description;
    @FXML private Button confirm;


    @FXML void view_selection() {
        if (operatore.isSelected()) {
            description.setText(OPERATORE_DESC);
            confirm.setDisable(false);
        } else if (cittadino.isSelected()) {
            description.setText(CITTADINO_DESC);
            confirm.setDisable(false);
        }
    }

    @FXML void confirm_selection(ActionEvent ev) throws IOException {
        if (operatore.isSelected()) {
            MainUIController.setRoot("OperatoreView");
        } else if (cittadino.isSelected()) {
            MainUIController.setRoot("CittadinoView");
        }
    }


    @FXML void exit_button() {
        Platform.exit();
        System.exit(0);
    }


}
