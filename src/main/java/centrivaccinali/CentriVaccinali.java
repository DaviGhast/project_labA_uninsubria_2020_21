
package centrivaccinali;

import cittadini.Cittadini;
import cittadini.GestioneCittadinoRegistrato;


import java.io.IOException;
import java.util.Scanner;

/**
 * CentriVaccinali è la classe principale della sotto applicazione Centri Vaccinali.
 *
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */

public class CentriVaccinali {

    public static void main(String[] args) {
        //loopMenu();
        ui.controllers.MainUIController.launch(ui.controllers.MainUIController.class);
    }

    public static void loopMenu () {
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while (!exit){
            System.out.println("MENU Applicazione:");
            System.out.println("0 - Operatori Vaccinali");
            System.out.println("1 - Cittadini");
            System.out.println("2 - Esci e chiudi il programma");
            System.out.print("Enter op: ");
            int op = in.nextInt();
            switch (op){
                case 0:
                    loopMenuOperatori();
                    break;
                case 1:
                    loopMenuCittadini();
                    break;
                case 2:
                    exit = true;
                    break;
            }
        }
    }


    public static void loopMenuOperatori () {
        Scanner in = new Scanner(System.in);
        GestioneCentriVaccinali gestioneCentrivaccinali;
        GestioneVaccinati gestioneVaccinati;
        boolean exit = false;
        while (!exit){
            System.out.println("MENU Operatori vaccinali:");
            System.out.println("0 - Registra nuovo centro vaccinale");
            System.out.println("1 - Registra nuovo vaccinato");
            System.out.println("2 - Esci e torna alla Homepage");
            System.out.print("Enter op: ");
            int op = in.nextInt();
            switch (op){
                case 0:
                    gestioneCentrivaccinali = new GestioneCentriVaccinali();
                    gestioneCentrivaccinali.verificaFile();
                    gestioneCentrivaccinali.scriviCentriVaccinali(
                            gestioneCentrivaccinali.registraCentroVaccinale());
                    gestioneCentrivaccinali.letturaFile();
                    break;
                case 1:
                    gestioneCentrivaccinali = new GestioneCentriVaccinali();
                    gestioneVaccinati = new GestioneVaccinati(gestioneCentrivaccinali.cercaCentroEsiste());
                    gestioneVaccinati.verificaFile();
                    gestioneVaccinati.scriviVaccinati(gestioneVaccinati.registraVaccinati());
                    gestioneVaccinati.letturaFile();
                    break;
                case 2:
                    exit = true;
                    break;
            }
        }
    }

    /**
     *
     */
    public static void loopMenuCittadini () {
        Scanner in = new Scanner(System.in);
        Cittadini cittadini = new Cittadini();

        boolean exit = false;
        while (!exit){
            System.out.println("MENU Cittadini:");
            System.out.println("0 - Cerca centro Vaccinale");
            System.out.println("1 - Registra nuovo vaccinato");
            System.out.println("2 - Inserisci EventiAvversi");
            System.out.println("3 - Esci e torna alla Homepage");
            System.out.print("Enter op: ");
            int op = in.nextInt();
            switch (op){
                case 0:
                    cittadini.cercaCentroVaccinale();
                    break;
                case 1:
                    GestioneCittadinoRegistrato gestioneCittadinoRegistrato = new GestioneCittadinoRegistrato();
                    gestioneCittadinoRegistrato.scriviCittadinoRegistrato(gestioneCittadinoRegistrato.registraCittadino());
                    break;
                case 2:
                    cittadini.login();
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }
    }

}

