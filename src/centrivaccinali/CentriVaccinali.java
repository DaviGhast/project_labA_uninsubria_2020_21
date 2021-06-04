
package centrivaccinali;
import gestionefile.GestioneCSV;

import java.io.*;
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

    public static void main(String[] args) {
       /* if(!Verifica()){
            System.out.println("Non esiste");
        }else{
            System.out.println("Esiste");
        }*/
        creaFile();
        lettura();


    }

    public static boolean verifica() {
       File f = new File("../project_labA_uninsubria_2020_21/data/CentriVaccinali.dati.csv");
       if(f.exists()){
           return true;
       }else{
           return false;
       }

    }


    public static void creaFile(){
        try {
            File file = new File("../project_labA_uninsubria_2020_21/data/CentriVaccinali.dati.csv");
            if(!verifica()){
                file.createNewFile();
                System.out.println("Il file è stato creato");

            }else{
                System.out.println("Il file esiste");
            }

        }catch(IOException e){
            System.out.println("Errore");
            e.printStackTrace();
        }
    }

    public static void lettura() {
        String file = "../project_labA_uninsubria_2020_21/data/CentriVaccinali.dati.csv";
        BufferedReader reader = null;
        String line = "";

        try{
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String [] row = line.split(",");
                for(String index : row) {
                    System.out.printf("%-10s", index);
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scrittura (CentroVaccinale centro) {
        File file = new File("../project_labA_uninsubria_2020_21/data/CentriVaccinali.dati.csv");
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(centro.getNomeCentroVaccinale()+","+centro.getQualificatoreIndirizzo()+","+centro.getNomeIndirizzo()+","+centro.getComune()+","+centro.getSiglaProvincia()+","+centro.getTipologia()+","+centro.getNumeroCivico()+","+centro.getCap());
            bw.newLine();
            bw.close();
            System.out.println("Scrittura eseguita con successoo");
        } catch (IOException e) {
            System.out.println("Errore");
            e.printStackTrace();
        }
    }


}

class GestioneCentrivaccinali extends GestioneCSV {

    /**
     * Metodo costruttore <code>GestioneCSV</code>
     *
     */
    public GestioneCentrivaccinali() {
        super("CentriVaccinali.dati", new String[]{});
    }

    public void scriviCentriVaccinali(Vector<CentroVaccinale> centriVaccinali) {
        for (CentroVaccinale centroVaccinale : centriVaccinali) {
            StringBuffer linea = new StringBuffer();
            linea
        }
    }

    public void inserisciCentriVaccinali(){

    }

}







