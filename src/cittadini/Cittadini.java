package cittadini;
import java.io.*;

/**
 * Classe <code>Cittadini</code>
 * <p>
 *     Questa classe ha lo scopo di contenere i metodi e il main della sotto applicazione per i cittadini
 * </p>
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */
public class Cittadini {
    public void registraCittadino(String nomeCittadino, String cognomeCittadino, String codiceFiscale, String email, String userId, String password, Integer idUnivocoVaccinazione )

    {
        File csvFile = new File("Cittadini_Registrati.dati.csv");
        if (csvFile.isFile()) {
            BufferedWriter b;
            b=new BufferedWriter (csvFile);

            b.write(nomeCittadino);

            b.write(",");

            b.write(cognomeCittadino);


            b.write(",");

            b.write(codiceFiscale);

            b.write(",");

            b.write(email);

            b.write(",");

            b.write(userId);

            b.write(",");

            b.write(password);

            b.write(",");

            b.write(idUnivocoVaccinazione);

            b.write(",");


            b.flush();
            // create BufferedReader and read data from csv
        }
    }

    /**
     * Metodo <code>main</code>
     * <p>
     *     ha lo scopo di lanciare la sotto applicazione per i cittadini
     * </p>
     */
    public static void main () {

    }

}
