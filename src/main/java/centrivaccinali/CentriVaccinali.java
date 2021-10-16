
package centrivaccinali;

import cittadini.Cittadini;
import cittadini.GestioneCittadinoRegistrato;


import java.io.IOException;
import java.util.Scanner;

/**
 * CentriVaccinali e' la classe principale della sotto applicazione Centri Vaccinali.
 *
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 * @version 1.0
 */

public class CentriVaccinali {

    public static void main(String[] args) {
        //loopMenu();
        ui.controllers.MainUIController.launch(ui.controllers.MainUIController.class);
    }


}

