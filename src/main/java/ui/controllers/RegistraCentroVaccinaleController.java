package ui.controllers;

import centrivaccinali.CentroVaccinale;
import centrivaccinali.GestioneCentriVaccinali;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegistraCentroVaccinaleController implements Initializable {

    private CentroVaccinale centroVaccinale;
    private String tipologia;

    @FXML private RadioButton ospedaliero, aziendale, hub;
    @FXML private TextField nomeCentro, qualificatore, nomeIndirizzo, nCivico, comune, provincia, cap;
    @FXML private Label description, infoRegex;
    @FXML private Button confirm;
    @FXML private ImageView cross, checkmark, cross1, checkmark1, cross2, checkmark2, cross3, checkmark3;

    @FXML void check_info() {
        if (!nomeCentro.getText().equals("") & !qualificatore.getText().equals("") & !nomeIndirizzo.getText().equals("") & !nCivico.getText().equals("") ){
            if (ospedaliero.isSelected()) {
                tipologia = ospedaliero.getText();
                confirm.setDisable(false);
            } else if (aziendale.isSelected()) {
                tipologia = aziendale.getText();
                confirm.setDisable(false);
            }
            else if (hub.isSelected()) {
                tipologia = hub.getText();
                confirm.setDisable(false);
            }
        }
    }

    public boolean validatorfield1(){
        if (Pattern.matches("^[a-zA-Z0-9 ,.'-]{2,30}",nomeCentro.getText())){
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
        if (Pattern.matches("^[a-zA-Z.]{2,10}",qualificatore.getText())){
            cross1.setVisible(false);
            checkmark1.setVisible(true);
            return true;
        } else {
            checkmark1.setVisible(false);
            cross1.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield3and4(){
        if (Pattern.matches("^[a-zA-Z0-9 ,.'-]{2,30}",nomeIndirizzo.getText()) & Pattern.matches("^[0-9]{1,3}",nCivico.getText())){
            cross2.setVisible(false);
            checkmark2.setVisible(true);
            return true;
        } else {
            checkmark2.setVisible(false);
            cross2.setVisible(true);
            return false;
        }
    }
    public boolean validatorfield5and6and7(){
        if (Pattern.matches("^[a-zA-Z ']{2,50}",comune.getText()) & Pattern.matches("^[a-zA-Z]{2}",provincia.getText()) &Pattern.matches("^[0-9]{5}",cap.getText())){
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
        if (validatorfield1() & validatorfield2() & validatorfield3and4() & validatorfield5and6and7()){
            centroVaccinale = new CentroVaccinale();
            centroVaccinale.setId(GestioneCentriVaccinali.getInstance().nextId());
            centroVaccinale.setNomeCentroVaccinale(FixInput.getInstance().fixString(nomeCentro.getText()));
            centroVaccinale.setQualificatoreIndirizzo(FixInput.getInstance().fixString(qualificatore.getText()));
            centroVaccinale.setNomeIndirizzo(FixInput.getInstance().fixString(nomeIndirizzo.getText()));
            centroVaccinale.setNumeroCivico(Integer.parseInt(nCivico.getText()));
            centroVaccinale.setComune(FixInput.getInstance().fixString(comune.getText()));
            centroVaccinale.setSiglaProvincia(provincia.getText().toUpperCase());
            centroVaccinale.setCap(Integer.parseInt(cap.getText()));
            centroVaccinale.setTipologia(tipologia);
            GestioneCentriVaccinali.getInstance().registraCentroVaccinale(centroVaccinale);
            infoRegex.setText("Centro Vaccinale Registrato con Successo, che vuoi fare ora?");
            centroVaccinale = null;
        }
    }

    @FXML public void viewRegex1(){
        infoRegex.setText("Nome Centro: Inserire da 1 a 30 caratteri");
    }
    @FXML public void viewRegex2(){
        infoRegex.setText("Qualificatore Indirizzo: Inserire da 1 a 10 caratteri alfabetici");
    }
    @FXML public void viewRegex3(){
        infoRegex.setText("Indirizzo: Inserireda 2 a 30 caratteri");
    }
    @FXML public void viewRegex4(){
        infoRegex.setText("Numero Civico: Inserire da 1 a 4 caratteri numerici");
    }
    @FXML public void viewRegex5(){
        infoRegex.setText("Comune: Inserire da 1 a 50 caratteri alfabetici");
    }
    @FXML public void viewRegex6(){
        infoRegex.setText("Sigla Provincia Civico: Inserire 2 caratteri alfabetici");
    }
    @FXML public void viewRegex7(){
        infoRegex.setText("CAP: Inserire 5 caratteri numerici");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GestioneCentriVaccinali.getInstance().verificaFile();
    }
}
