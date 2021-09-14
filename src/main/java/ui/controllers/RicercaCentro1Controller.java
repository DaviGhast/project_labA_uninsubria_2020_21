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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RicercaCentro1Controller implements Initializable {

    private CittadinoRegistrato cittadinoRegistrato;

    @FXML private TextField nomeCentro;
    @FXML private Label description;
    @FXML private Button confirm;
    @FXML private TableView tabella;

    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {
        ArrayList<CentroVaccinale> listaCentriVaccinali = GestioneCentriVaccinali.getInstance().getCentroVaccinalePerNome(nomeCentro.getText());
        for (int i = 0; i < listaCentriVaccinali.size(); i++) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn nome = new TableColumn("Nome");
        TableColumn qualif = new TableColumn("Qualif");
        TableColumn nomInd = new TableColumn("Indirizzo");
        TableColumn nCivico = new TableColumn("Civico");
        TableColumn comune = new TableColumn("Comune");
        TableColumn provincia = new TableColumn("Provincia");
        TableColumn tipologia = new TableColumn("Tipologia");
        tabella.getColumns().addAll(nome, qualif, nomInd, nCivico, comune, provincia, tipologia);

        tabella.setEditable(false);



        final ObservableList<CentroVaccinale> data = FXCollections.observableArrayList();
    }
}
