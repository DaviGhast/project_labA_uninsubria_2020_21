package ui.controllers;

import centrivaccinali.CentroVaccinale;
import centrivaccinali.GestioneCentriVaccinali;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegistraCentroVaccinaleController {

    private CentroVaccinale centroVaccinale;
    private final String REGISTRA_CENTRO_DESC = "Registra un nuovo Centro Vaccinale";
    private final String REGISTRA_VACCINATO_DESC = "Pannello di controllo per gli operatori sanitari";

    @FXML private RadioButton ospedaliero, aziendale, hub;
    @FXML private TextField nomeCentro, qualificatore, nomeIndirizzo, nCivico;
    @FXML private Label description, infoRegex;
    @FXML private Button confirm;
    @FXML private ImageView cross, checkmark, cross1, checkmark1, cross2, checkmark2;

    @FXML void check_info() {
        if (!nomeCentro.getText().equals("") & !qualificatore.getText().equals("") & !nomeIndirizzo.getText().equals("") & !nCivico.getText().equals("") ){
            if (ospedaliero.isSelected()) {
                confirm.setDisable(false);
            } else if (aziendale.isSelected()) {
                confirm.setDisable(false);
            }
            else if (hub.isSelected()) {
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

    @FXML public void confirm_selection(ActionEvent actionEvent) throws IOException {
        if (validatorfield1() & validatorfield2() & validatorfield3and4()){
            centroVaccinale = new CentroVaccinale();
            centroVaccinale.setId(GestioneCentriVaccinali.getInstance().nextId());
            centroVaccinale.setNomeCentroVaccinale(nomeCentro.getText());
            centroVaccinale.setQualificatoreIndirizzo(qualificatore.getText());
            centroVaccinale.setNomeIndirizzo(nomeIndirizzo.getText());
            centroVaccinale.setNumeroCivico(Integer.parseInt(nCivico.getText()));


            GestioneCentriVaccinali.getInstance().registraCentriVaccinali(centroVaccinale);
            centroVaccinale = null;
        }
    }

    @FXML public void viewRegex3(){
        infoRegex.setText("Indirizzo: Inserireda 2 a 30 caratteri");
    }
    @FXML public void viewRegex4(){
        infoRegex.setText("Numero Civico: Inserire da 1 a 4 caratteri numerici");
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
