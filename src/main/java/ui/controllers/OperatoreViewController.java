package ui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OperatoreViewController {

    public void view_selection(ActionEvent actionEvent) {
    }

    public void confirm_selection(ActionEvent actionEvent) {
    }

    public void exit_button(ActionEvent actionEvent) {
    }

    public void back_button(ActionEvent actionEvent) throws IOException {
        MainUIController.setRoot("Welcome");
    }
}
