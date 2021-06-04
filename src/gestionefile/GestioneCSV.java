package gestionefile;

import java.io.*;

/**
 * Classe <code>GestioneCSV</code>
 * <p>
 *     Questa classe ha lo scopo di fornire e raggruppare tutti i metodi che servono per gestire il file CSV
 * </p>
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

    /**
     * Attributo <code>pathFile</code>
     * <p>
     *     è una stringa che contine la directory dei file CSV
     * </p>
     */
    private String pathFile = "../project_labA_uninsubria_2020_21/data/";
    private String[] arrayNomiColonne = null;
    private File file;

    /**
     * Metodo costruttore <code>GestioneCSV</code>
     * @param filename nome del file che concatenato con <code>pathFile</code> da la path completa
     * @param arrayNomiColonne array di stringhe che descrive il nome delle varie colonne
     */
    public GestioneCSV (String filename, String[] arrayNomiColonne) {
        pathFile.concat(filename);
        file = new File(pathFile);
        this.arrayNomiColonne = arrayNomiColonne;
    }

    /**
     * Metodo costruttore <code>strNomiColonne</code>
     * @return stringa dei nomi delle colonne, che sono stati estratti ed elaborati dall'array
     */
    public String strNomiColonne() {
        String str = "";
        for (int i = 0; i < arrayNomiColonne.length; i++) {
            if (i< arrayNomiColonne.length-1){
                str = str.concat(arrayNomiColonne[i]);
                str = str.concat(",");
            } else {
                str = str.concat(arrayNomiColonne[i]);
            }
        }
        return str;
    }

    /**
     * Metodo <code>VerificaFile</code>
     * <p>
     *     questo metodo richiama altri 2 metodi
     * </p>
     */
    public void VerificaFile () {
        creaFile();
        controllaNomiColonne();
    }

    /**
     * Metodo <code>creaFile</code>
     * <p>
     *     questo metodo prova a creare il file
     * </p>
     * @throws IOException
     */
    public void creaFile() {
        file = new File(pathFile);
        try {
            if (file.createNewFile()) {
                System.out.println("File creato: " + file.getName());
            } else {
                System.out.println("Il file esiste già.");
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore.");
            e.printStackTrace();
        }
    }

    /**
     * Metodo <code>controllaNomiColonne</code>
     * <p>
     *     questo metodo controlla se nella prima riga del file esistono o meno i nomi dei campi o colonne
     * </p>
     * @throws IOException
     */
    public void controllaNomiColonne() {
        file = new File(pathFile);
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            line = reader.readLine();
            System.out.println(line);
            if (line == null) {
                try {
                    bw.write(strNomiColonne());
                    System.out.println("Scritto con successo nel file.");
                } catch (IOException e) {
                    System.out.println("Si è verificato un errore.");
                    e.printStackTrace();
                }
                bw.newLine();
            } else if (line.equals(strNomiColonne())) {
                System.out.println("Il nome delle colonne esiste");
            } else {
                System.out.println("Il nome delle colonne non esiste, ma la riga è già scritta.");
                int count = 0;
                if ((line = reader.readLine()) == null) {
                    count++;
                }
                while ((line = reader.readLine()) != null) {
                    count++;
                }
                String[] rows = new String[count];
                int count1 = 0;
                while ((line = reader.readLine()) != null) {
                    count1++;
                    rows[count1] = line;
                }
                FileWriter fw1 = new FileWriter(file);
                BufferedWriter bw1 = new BufferedWriter(fw1);
                bw1.write(strNomiColonne());
                bw1.newLine();
                for (int i = 0; i < count; i++) {
                    bw1.write(rows[i]);
                    bw1.newLine();
                }
                bw1.close();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}