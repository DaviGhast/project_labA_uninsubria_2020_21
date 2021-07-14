import centrivaccinali.CentriVaccinali;
import centrivaccinali.GestioneCentrivaccinali;
import centrivaccinali.GestioneVaccinati;

import java.io.IOException;
import java.util.Scanner;

/**
 * La MainClass è la classe principale del programma.
 * <p>
 *     Questa classe ha lo scopo di lanciare la pl'applicazione.
 * </p>
 * <p>
 *     La prima finestra della GUI, è la Homepage
 * </p>
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */

public class MainClass {

    public static void main(String[] args) {
        loopMenu();
    }

    /**
     *
     * @throws IOException
     */
    public static void loopMenu () {
        Scanner in = new Scanner(System.in);
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
                    CentriVaccinali.main();
                    break;
                case 1:

                    break;
                case 2:
                    exit = true;
                    break;
            }
        }
    }

}