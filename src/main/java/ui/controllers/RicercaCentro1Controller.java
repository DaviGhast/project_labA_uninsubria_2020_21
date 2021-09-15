package ui.controllers;

import centrivaccinali.CentroVaccinale;
import centrivaccinali.GestioneCentriVaccinali;
import cittadini.CittadinoRegistrato;
import cittadini.GestioneCittadinoRegistrato;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RicercaCentro1Controller {

    private CentroVaccinale centroVaccinale;

    @FXML private TextField nomeCentro;
    @FXML private Label description;
    @FXML private Button confirm;
    @FXML private Button search;
    @FXML private TableView<CentroVaccinale> tabella;
    @FXML private TableColumn<CentroVaccinale, String> nome;
    @FXML private TableColumn<CentroVaccinale, String> qualificatoreIndirizzo;
    @FXML private TableColumn<CentroVaccinale, String> nomeIndirizzo;
    @FXML private TableColumn<CentroVaccinale, Integer> civico;
    @FXML private TableColumn<CentroVaccinale, String> comune;
    @FXML private TableColumn<CentroVaccinale, String> provincia;
    @FXML private TableColumn<CentroVaccinale, Integer> cap;
    @FXML private TableColumn<CentroVaccinale, String> tipologia;

    @FXML void search_button() {
        ObservableList<CentroVaccinale> list = FXCollections.observableArrayList(
                GestioneCentriVaccinali.getInstance().searchCentroByName(nomeCentro.getText())
        );

        nome.setCellValueFactory(new PropertyValueFactory<CentroVaccinale,String>("nomeCentroVaccinale"));
        qualificatoreIndirizzo.setCellValueFactory(new PropertyValueFactory<CentroVaccinale,String>("qualificatoreIndirizzo"));
        nomeIndirizzo.setCellValueFactory(new PropertyValueFactory<CentroVaccinale,String>("nomeIndirizzo"));
        civico.setCellValueFactory(new PropertyValueFactory<CentroVaccinale,Integer>("numeroCivico"));
        comune.setCellValueFactory(new PropertyValueFactory<CentroVaccinale,String>("comune"));
        provincia.setCellValueFactory(new PropertyValueFactory<CentroVaccinale,String>("siglaProvincia"));
        cap.setCellValueFactory(new PropertyValueFactory<CentroVaccinale,Integer>("cap"));
        tipologia.setCellValueFactory(new PropertyValueFactory<CentroVaccinale,String>("tipologia"));

        tabella.setItems(list);

    }

    public void selectRow(javafx.scene.input.MouseEvent mouseEvent) {
        if (tabella.getSelectionModel().getSelectedIndex() >= 0) {
            centroVaccinale = tabella.getSelectionModel().getSelectedItem();
            confirm.setDisable(false);
            description.setText("hai selezionato il centro:" + centroVaccinale.getNomeCentroVaccinale() + "nella riga " + tabella.getSelectionModel().getSelectedIndex()+1);
        }

    }

    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {

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
