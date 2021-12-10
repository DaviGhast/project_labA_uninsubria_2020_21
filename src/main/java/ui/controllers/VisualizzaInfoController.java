package ui.controllers;

import centrivaccinali.CittadinoVaccinato;
import centrivaccinali.GestioneVaccinati;
import centrivaccinali.EventoAvverso;
import centrivaccinali.InfoEventoAvversoAnonimo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisualizzaInfoController implements Initializable {

    GestioneVaccinati gestioneVaccinati = GestioneVaccinati.getInstance((String) FixInput.getInstance().getDataBuffer());

    @FXML private Label nomeCentro;
    @FXML private TableView<InfoEventoAvversoAnonimo> tabella;
    @FXML private TableColumn<InfoEventoAvversoAnonimo, String> vaccino;
    @FXML private TableColumn<InfoEventoAvversoAnonimo, String> evento;
    @FXML private TableColumn<InfoEventoAvversoAnonimo, Byte> severita;
    @FXML private TableColumn<InfoEventoAvversoAnonimo, String> note;

    public VisualizzaInfoController() throws URISyntaxException {
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

        nomeCentro.setText(nomeCentro.getText() + FixInput.getInstance().getDataBuffer());

        ObservableList<InfoEventoAvversoAnonimo> list = FXCollections.observableArrayList(gestioneVaccinati.getAllEventiAvversi(gestioneVaccinati.getCittadiniVaccinati()));

        vaccino.setCellValueFactory(new PropertyValueFactory<InfoEventoAvversoAnonimo,String>("vaccinoSomministrato"));
        evento.setCellValueFactory(new PropertyValueFactory<InfoEventoAvversoAnonimo,String>("evento"));
        severita.setCellValueFactory(new PropertyValueFactory<InfoEventoAvversoAnonimo,Byte>("severita"));
        note.setCellValueFactory(new PropertyValueFactory<InfoEventoAvversoAnonimo,String>("note"));

        tabella.setItems(list);

    }
}
