package gestionefile;

import java.io.File;

/**
 * La classe <code>GestioneCSV</code> ha lo scopo di fornire e raggruppare tutti i metodi che servono per gestire il file CSV
 * <p>
 *     I metodi contenuti in questa classe gestiscono operazioni piccole e grandi per la gestione del file CSV,
 *     alcuni per fare operazioni grandi e complesse hanno bisogno di richiamare altre operazioni più piccole.
 * </p>
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */
public class GestioneCSV {

    private String pathFile = "../project_labA_uninsubria_2020_21/data/";
    private File file;

    public GestioneCSV (String filename) {
        pathFile.concat(filename);
        file = new File(pathFile);
    }

    public void Verifica () {

    }

}
