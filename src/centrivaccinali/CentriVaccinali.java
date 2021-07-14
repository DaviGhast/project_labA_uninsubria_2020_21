
package centrivaccinali;

import gestionefile.GestioneCsv;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

/**
 * CentriVaccinali è la classe principale della sotto applicazione Centri Vaccinali.
 *
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */

public class CentriVaccinali {

    public static void main() {

        loopMenu();

    }

    /**
     *
     * @throws IOException
     */
    public static void loopMenu () {
        Scanner in = new Scanner(System.in);
        GestioneCentrivaccinali gestioneCentrivaccinali;
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
                    gestioneCentrivaccinali = new GestioneCentrivaccinali();
                    gestioneCentrivaccinali.verificaFile();
                    gestioneCentrivaccinali.scriviCentriVaccinali(
                            gestioneCentrivaccinali.registraCentroVaccinale());
                    gestioneCentrivaccinali.letturaFile();
                    break;
                case 1:
                    gestioneCentrivaccinali = new GestioneCentrivaccinali();
                    gestioneVaccinati = new GestioneVaccinati(gestioneCentrivaccinali.cercaCentro());
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


}

