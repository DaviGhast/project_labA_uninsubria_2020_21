package gestionefile;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

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
    //CSV separator because "," is their digit separator
    private static final String SEPARATORE_CSV = ",";
    private static final String ESTENSIONE_CSV = ".csv";

    /**
     * Metodo costruttore <code>GestioneCSV</code>
     * @param filename nome del file che concatenato con <code>pathFile</code> da la path completa
     * @param arrayNomiColonne array di stringhe che descrive il nome delle varie colonne
     */
    public GestioneCSV (String filename, String[] arrayNomiColonne) {
        filename = filename.concat(ESTENSIONE_CSV);
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
                str = str.concat(SEPARATORE_CSV);
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
                bw1.flush();
                bw1.close();
            }
            bw.flush();
            bw.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo <code>controllaNomiColonne</code>
     * <p>
     *     permette di leggere il file velocemente senza ricreare gli oggetti
     * </p>
     * @throws IOException
     */
    public void letturaFile() {
        file = new File(pathFile);
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                if( line.trim().isEmpty() ) {
                    continue;
                }
                String[] row = line.split(SEPARATORE_CSV);
                stampaCSV(row);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo <code>controllaNomiColonne</code>
     * <p>
     *     permette di leggere il file velocemente senza ricreare gli oggetti
     * </p>
     * @param dato stringa da
     * @throws IOException
     */
    public void scritturaFile(String dato) {
        File file = new File(pathFile);
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dato);
            bw.newLine();
            bw.flush();
            bw.close();
            System.out.println("Scritto con successo nel file.");
        } catch (IOException e) {
            System.out.println("Si è verificato un errore.");
            e.printStackTrace();
        }
    }

    /**
     * Metodo <code>numRisultatiPerCampo</code>
     * @param dato
     * @return
     */
    public int numRisultatiPerTutto(String dato) {
        file = new File(pathFile);
        String line = "";
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(SEPARATORE_CSV);
                for (String index : row) {
                    if (index.equals(dato)) {
                        count++;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Metodo <code>numRisultatiPerCampo</code>
     * @param dato
     * @param indexCampo
     * @return
     */
    public int numRisultatiPerCampo(String dato, int indexCampo) {
        File file = new File(pathFile);
        String line = "";
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(SEPARATORE_CSV);
                if (row[indexCampo].equals(dato)){
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Metodo <code>ricercaRighePerTutto</code>
     * @param dato
     * @return
     */
    public Vector<String[]> ricercaRighePerTutto(String dato) {
        file = new File(pathFile);
        Vector<String[]> rows = new Vector<String[]>();
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(SEPARATORE_CSV);
                for (String index : row) {
                    if (index.equals(dato)) {
                        rows.add(row);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * Metodo <code>ricercaRighePerCampo</code>
     * @param dato
     * @param indexCampo
     * @return
     */
    public Vector<String[]> ricercaRighePerCampo(String dato, int indexCampo) {
        file = new File(pathFile);
        Vector<String[]> rows = new Vector<String[]>();
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(SEPARATORE_CSV);
                if (row[indexCampo].equals(dato)){
                    rows.add(row);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * Metodo <code>cercaPerTutto</code>
     * @param dato
     */
    public void cercaPerTutto (String dato) {
        System.out.println();
        int count = numRisultatiPerTutto(dato);
        System.out.println("Ci sono"+count+" risultati per "+dato+" nell'elenco.");
        stampaCSV(arrayNomiColonne);
        Vector<String[]> rows = ricercaRighePerTutto(dato);
        for (int i = 0; i < count; i++) {
            String[] row = rows.elementAt(i);
            stampaCSV(row);
        }
    }

    /**
     * Metodo <code>cercaPerCampo</code>
     * @param dato
     */
    public void cercaPerCampo(String dato, String campo, int indexCampo) {
        System.out.println();
        int count = numRisultatiPerCampo(dato,indexCampo);
        System.out.println("Ci sono "+count+" risultati per "+dato+" in "+campo+" .");
        stampaCSV(arrayNomiColonne);
        Vector<String[]> rows = ricercaRighePerCampo(dato,indexCampo);
        for (int i = 0; i < count; i++) {
            String[] row = rows.elementAt(i);
            stampaCSV(row);
        }
    }

    /**
     * Metodo <code>ricercaIdEsiste</code>
     * <p>
     *
     * </p>
     * @param id
     */
    public boolean ricercaIdEsiste(String id) {
        Boolean idExist = false;
        int count = numRisultatiPerTutto(id);
        Vector<String[]> rows = ricercaRighePerTutto(id);
        for (int i = 0; i < count; i++) {
            String[] row = rows.elementAt(i);
            if (row[0].equals(id)){
                idExist = true;
            }
        }
        return idExist;
    }

    /**
     * Metodo <code>cambiaDatiCampi</code>
     * @throws IOException
     */
    public void cambiaDatiCampi() throws IOException {
        letturaFile();
        System.out.println();
        Scanner in = new Scanner(System.in);
        file = new File(pathFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        Vector<String> rows = new Vector<>();
        String field = "", data = "", change = "";
        System.out.print("Inserisci l'id al quale devi fare la modifica: ");
        String id = in.next();
        if (ricercaIdEsiste(id)){
            try {
                int index = 0;
                String str = "";
                while ((line = reader.readLine()) != null) {
                    str = "";
                    if( line.trim().isEmpty() ) {
                        continue;
                    }
                    String[] row = line.split(SEPARATORE_CSV);
                    if (row[0].equals(id)){
                        System.out.print("Inserisci il campo da cambiare: ");
                        field = in.next();
                        field = field.substring(0,1).toUpperCase()+field.substring(1).toLowerCase();
                        System.out.print("Inserisci il dato da modificare: ");
                        data = in.next();
                        data = data.substring(0,1).toUpperCase()+data.substring(1).toLowerCase();
                        for (int i = 0; i < arrayNomiColonne.length; i++) {
                            if (arrayNomiColonne[i].equals(field)){
                                if (row[i].equals(data)){
                                    System.out.print("Inserisci il nuovo dato: ");
                                    change = in.next();
                                    change = change.substring(0,1).toUpperCase()+change.substring(1).toLowerCase();
                                    row[i] = change;
                                }
                            }
                        }
                    }
                    for (int j = 0; j < row.length; j++) {
                        if (j<row.length-1){
                            str = str.concat(row[j]);
                            str = str.concat(SEPARATORE_CSV);
                        } else {
                            str = str.concat(row[j]);
                        }
                    }
                    rows.add(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int x = 0; x < rows.size(); x++) {
                    bw.write(rows.get(x));
                    bw.newLine();
                }
                bw.close();
                System.out.println("Il Campo "+field+" dell'id "+id+" è stato cambiato da "+data+" a "+change);
                System.out.println();
            } catch (IOException e) {
                System.out.println("Si è verificato un errore.");
                e.printStackTrace();
            }

        } else {
            System.out.println("Errore l'id :"+id+" non esite.");
        }
        reader.close();
    }

    /**
     * Metodo <code>stampaCSV</code>
     * @param row
     */
    public void stampaCSV (String[] row){
        for (String index : row) {
            System.out.printf("%-20s", index);
        }
        System.out.println();
    }



}