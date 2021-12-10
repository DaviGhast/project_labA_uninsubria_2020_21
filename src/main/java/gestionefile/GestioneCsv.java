package gestionefile;

import centrivaccinali.CentriVaccinali;

import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
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
public class GestioneCsv {
    /**
     *<code>pathFile</code> il percorso relativo della cartella del file CSV
     */
    private String pathFile = "project_labA_uninsubria_2020_21/data";

    /**
     * il nome dell'<code>arrayNomiColonne</code> del file.CSV
     */
    private String[] arrayNomiColonne = null;

    /**
     *<code>File</code> è la struttura file che conterrà il file CSV
     */
    private File file;

    /**
     *<code>SEPARATORE_CSV</code> contentente la " , " e' il separatore usato per separare un elemento da un altro, appartenente a colonne diverse
     */
    public static final String SEPARATORE_CSV = ",";

    /**
     *<code>ESTENSIONE_CSV</code> contentente la " .csv " e' il separatore usato per dare l'estensione e il formato del file
     */
    private static final String ESTENSIONE_CSV = ".csv";

    /**
     * Metodo costruttore <code>GestioneCSV</code>
     * @param filename nome del file che concatenato con <code>pathFile</code> da la path completa
     * @param arrayNomiColonne array di stringhe che descrive il nome delle varie colonne
     */
    public GestioneCsv(String filename, String[] arrayNomiColonne) throws URISyntaxException {
        CodeSource codeSource = CentriVaccinali.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
        System.out.println(jarDir);
        int index = jarDir.indexOf("project_labA_uninsubria_2020_21");
        String dir = jarDir.substring(0, index);
        System.out.println(dir);
        filename = "/" +filename.concat(ESTENSIONE_CSV);
        pathFile = dir.concat(pathFile.concat(filename));
        System.out.println(pathFile);
        file = new File(pathFile);
        this.arrayNomiColonne = arrayNomiColonne;
    }

    /**
     * metodo per la creazione e ricerca di un id libero
     * @return id
     */
    public short nextId() {
        //creazione e ricerca id libero
        short id = 0;
        while (ricercaIdEsiste(""+id)!=false){
            id++;
        }
        return id;
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
    public void verificaFile () {
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
     * Metodo <code>letturaFile</code>
     * <p>
     *     permette di leggere il file velocemente senza ricreare gli oggetti
     * </p>
     * @throws IOException
     */
    public ArrayList<String> letturaFile() {
        ArrayList<String> listaRighe = new ArrayList<String>();
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if( line.trim().isEmpty() ) {
                    continue;
                }
                //
                listaRighe.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaRighe;
    }

    /**
     * Metodo <code>controllaNomiColonne</code>
     * <p>
     *    Scrive sul file la stringa che contiene le informazioni da scrivere sul file
     * </p>
     * @param dato stringa da
     * @throws IOException
     */
    public void scritturaFile(String dato) {
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
     * Metodo <code>ricercaIdEsiste</code>
     * <p>
     * ricerca id esistente
     * </p>
     * @param id
     */
    public boolean ricercaIdEsiste(String id) {
        boolean idExist = false;
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(SEPARATORE_CSV);
                if (row[0].equals(id)){
                    idExist = true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idExist;
    }

    /**
     * il metodo verifica l'esistenza di un userid
     * @param userid
     * @return esiste o non esiste
     */
    public boolean useridEsistente(String userid){
        boolean idExist = false;
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(SEPARATORE_CSV);
                if (row[3].equals(userid)){
                    idExist = true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idExist;
    }

    /**
     * il metodo recupera il nome del centro vaccinale attraverso l'id del vaccinato
     * @param idVaccinato
     * @return  nomeCentro
     */
    public String getNomeCentroByIdVaccinato(Short idVaccinato){
        String line = "";
        String nomeCentro = new String();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(SEPARATORE_CSV);
                if (!row[0].contains("Id")) {
                    if (Short.parseShort(row[0]) == idVaccinato){
                        nomeCentro = row[1];
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nomeCentro;
    }

    /**
     * il metodo si occupa di cancellare e creare un file verificando se esista o meno
     */
    public void deleteAndCreate() {
        file.delete();
        verificaFile();
    }


}