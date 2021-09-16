
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


}

