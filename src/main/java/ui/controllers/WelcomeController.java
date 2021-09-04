package ui.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    /**
     * Description for the basic view.
     */
    private final String OPERATORE_DESC = "A view with only basic features";
    /**
     * Description for the debug view.
     */
    private final String CITTADINO_DESC = "A view with debugging features, showing logs, errors and other info";
    /**
     * Path to the choose window fxml file.
     */
    private final URL chooseWindowFxml = getClass().getResource("/fxml/ChooseWindow.fxml");


    @FXML
    private RadioButton operatore, cittadino;
    @FXML private Label description;
    @FXML private Button confirm;

    /**
     * Displays the correlated description of the view when one of the options is selected.
     */
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
        } else{

        }
    }

    /**
     * Closes the application.
     */
    @FXML void exit_button() {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
