package ui.controllers;

import centrivaccinali.CentroVaccinale;
import centrivaccinali.CittadinoVaccinato;
import centrivaccinali.GestioneCentriVaccinali;
import centrivaccinali.GestioneVaccinati;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegistraVaccinatoController implements Initializable {

    private CittadinoVaccinato cittadinoVaccinato;
    private String vaccino;

    @FXML private RadioButton pfizer, moderna, astrazeneca, jandj;
    @FXML private TextField nomeCentro, nomeCittadino, cognomeCittadino, codiceFiscale;
    @FXML private DatePicker dataVaccinazione;
    @FXML private Label description, infoRegex;
    @FXML private Button confirm;
    @FXML private ImageView cross, checkmark, cross1, checkmark1, cross2, checkmark2, cross3, checkmark3, cross4, checkmark4;

    @FXML void check_info() {
        if (!nomeCentro.getText().equals("") & !nomeCittadino.getText().equals("") & !cognomeCittadino.getText().equals("")
                & !codiceFiscale.getText().equals("")){
            if (pfizer.isSelected()) {
                vaccino = pfizer.getText();
                confirm.setDisable(false);
            } else if (moderna.isSelected()) {
                vaccino = moderna.getText();
                confirm.setDisable(false);
            } else if (astrazeneca.isSelected()) {
                vaccino = astrazeneca.getText();
                confirm.setDisable(false);
            } else if (jandj.isSelected()) {
                vaccino = jandj.getText();
                confirm.setDisable(false);
            }
        }
    }

    public boolean validatorfield1(){
        if (Pattern.matches("^[a-zA-Z0-9 ,.'-]{2,30}",nomeCentro.getText()) & GestioneCentriVaccinali.getInstance().cercaCentroEsiste(nomeCentro.getText())){
            cross.setVisible(false);
            checkmark.setVisible(true);
            return true;
        } else {
            checkmark.setVisible(false);
            cross.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield2(){
        if (Pattern.matches("^[a-zA-Z]{2,30}",nomeCittadino.getText())){
            cross1.setVisible(false);
            checkmark1.setVisible(true);
            return true;
        } else {
            checkmark1.setVisible(false);
            cross1.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield3(){
        if (Pattern.matches("^[a-zA-Z]{2,30}",cognomeCittadino.getText())){
            cross2.setVisible(false);
            checkmark2.setVisible(true);
            return true;
        } else {
            checkmark2.setVisible(false);
            cross2.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield4(){
        if (Pattern.matches("^[A-Z0-9]{16}",codiceFiscale.getText())){
            cross3.setVisible(false);
            checkmark3.setVisible(true);
            return true;
        } else {
            checkmark3.setVisible(false);
            cross3.setVisible(true);
            return false;
        }
    }

    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {
        if (validatorfield1() & validatorfield2() & validatorfield3() & validatorfield4()){
            cittadinoVaccinato = new CittadinoVaccinato();
            GestioneVaccinati gestioneVaccinati = GestioneVaccinati.getInstance(nomeCentro.getText());
            gestioneVaccinati.verificaFile();
            cittadinoVaccinato.setId(gestioneVaccinati.nextId());
            cittadinoVaccinato.setNomeCentroVaccinale(FixInput.getInstance().fixString(nomeCentro.getText()));
            cittadinoVaccinato.setNomeCittadino(FixInput.getInstance().fixString(nomeCittadino.getText()));
            cittadinoVaccinato.setCognomeCittadino(FixInput.getInstance().fixString(cognomeCittadino.getText()));
            cittadinoVaccinato.setCodiceFiscaleCittadino(codiceFiscale.getText().toUpperCase());
            cittadinoVaccinato.setDataVaccinazione(dataVaccinazione.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            cittadinoVaccinato.setVaccinoSomministrato(vaccino);
            cittadinoVaccinato.setIdVaccinazione(gestioneVaccinati.nextIdUniv());
            gestioneVaccinati.registraVaccinato(cittadinoVaccinato);
            description.setText("Cittadino Vaccinato Registrato con Successo, mi raccomando comunica al cittadino il suo codice: "+cittadinoVaccinato.getIdVaccinazione());
            confirm.setDisable(true);
            cittadinoVaccinato = null;
        }
    }

    @FXML public void viewRegex1(){
        infoRegex.setText("Nome Centro: Inserire da 2 a 30 caratteri alfanumerici, il centro deve esistere");
    }
    @FXML public void viewRegex2(){
        infoRegex.setText("Nome Cittadino: Inserire da 2 a 30 caratteri alfabetici");
    }
    @FXML public void viewRegex3(){
        infoRegex.setText("Cognome Cittadino: Inserire da 2 a 30 caratteri alfabetici");
    }
    @FXML public void viewRegex4(){
        infoRegex.setText("Codice Fiscale: Inserire al massimo 16 caratteri alfanumerici");
    }


    /**
     * Closes the application.
     */
    @FXML void exit_button() {
        Platform.exit();
        System.exit(0);
    }

    @FXML public void back_button(ActionEvent actionEvent) throws IOException {
        MainUIController.setRoot("OperatoreView");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GestioneCentriVaccinali.getInstance().verificaFile();
    }
}
