
package centrivaccinali;
import java.io.*;

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
        CreaFile();
        Lettura();


    }

    public static boolean Verifica() {
       File f = new File("../project_labA_uninsubria_2020_21/data/CentriVaccinali.dati.csv");
       if(f.exists()){
           return true;
       }else{
           return false;
       }

    }


    public static void CreaFile(){
        try {
            File file = new File("../project_labA_uninsubria_2020_21/data/CentriVaccinali.dati.csv");
            if(!Verifica()){
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

    public static void Lettura() {
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

    public static void Scrittura (CentroVaccinale centro) {
        File file = new File("../project_labA_uninsubria_2020_21/data/CentriVaccinali.dati.csv");
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(centro.getNomeCentroVaccinale()+","+centro.getQualificatoreIndirizzo()+","+centro.getNomeIndirizzo()+","+centro.getComune()+","+centro.getSiglaProvincia()+","+centro.getTipologia()+","+centro.getNumeroCivico()+","+centro.getCap());
            bw.newLine();
            bw.close();
            System.out.println("Scrittura eseguita con successo");
        } catch (IOException e) {
            System.out.println("Errore");
            e.printStackTrace();
        }
    }




}






