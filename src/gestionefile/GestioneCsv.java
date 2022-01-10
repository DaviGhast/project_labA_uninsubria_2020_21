package gestionefile;

import centrivaccinali.CentriVaccinali;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;

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
     * il nome dell'<code>arrayNomiColonne</code> del file.CSV
     */
    private String[] arrayNomiColonne = null;

    /**
     *<code>File</code> è la struttura file che conterrà il file CSV
     */
    private final File file;

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
     * @throws URISyntaxException nel caso la sisntassi URi sia sbagliata
     */
    public GestioneCsv(String filename, String[] arrayNomiColonne) throws URISyntaxException {
        CodeSource codeSource = CentriVaccinali.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
        String jarDir1 = jarFile.getParentFile().getParentFile().getPath();
        System.out.println("jarDir: "+jarDir);
        //int index = jarDir.indexOf("project_labA_uninsubria_2020_21");
        //String dir = jarDir.substring(0, index);
        //System.out.println(dir);
        //System.out.println(jarDir1);

        //String currentWorkingDir = System.getProperty("user.dir");
        //System.out.println("currentWorkingDir: "+currentWorkingDir);

        String pathFile = findfile(filename + ESTENSIONE_CSV, new File(/*currentWorkingDir*/jarDir1));
        if (pathFile.isEmpty()) {
            System.out.println("dati inesisteni");
            pathFile = jarDir + "/data";
            File directory = new File(pathFile);
            if (directory.mkdir()) {
                System.out.println("directory creata:" + directory.getName());
            } else {
                System.out.println("Il file esiste già.");
            }
            pathFile = pathFile + "/" + filename + ESTENSIONE_CSV;
            creaFile(new File(pathFile));
        }
        System.out.println(pathFile);
        file = new File(pathFile);
        this.arrayNomiColonne = arrayNomiColonne;
        controllaNomiColonne();
    }

    /**
     * metodo per la creazione e ricerca di un id libero
     * @return id
     */
    public short nextId() {
        //creazione e ricerca id libero
        short id = 0;
        while (ricercaAttrEsiste(String.valueOf(id), "id")){
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
        creaFile(file);
        controllaNomiColonne();
    }

    public String findfile (String namefile, File directory) {
        File[] list = directory.listFiles();
        String dirpath = "";
        if(list!=null)
            for (File fil : list) {
                if (!fil.getName().startsWith(".")){
                    if (fil.isDirectory()) {
                        System.out.println("is directory");
                        System.out.println(fil.getPath());
                        dirpath = findfile(namefile,fil);
                        if (dirpath.contains(namefile))
                            break;
                    }
                    else if (namefile.equalsIgnoreCase(fil.getName())) {
                        dirpath = fil.getPath();
                        System.out.println(dirpath);
                        break;
                    }
                }
            }
        return dirpath;
    }

    /**
     * Metodo <code>creaFile</code>
     * <p>
     *     questo metodo prova a creare il file
     * </p>
     * @param file file da creare
     */
    public void creaFile(File file) {
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
                bw.flush();
                bw.close();
            } else if (line.equals(strNomiColonne())) {
                System.out.println("Il nome delle colonne esiste");
                bw.flush();
                bw.close();
            } else {
                System.out.println("Il nome delle colonne non esiste, ma la riga è già scritta.");
                ArrayList<String> rows_data = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    if (!line.equals(strNomiColonne())) {
                        rows_data.add(line);
                    }
                }
                FileWriter fw1 = new FileWriter(file);
                BufferedWriter bw1 = new BufferedWriter(fw1);
                bw1.write(strNomiColonne());
                bw1.newLine();
                bw.flush();
                bw.close();
                for (String row:rows_data) {
                    bw1.write(row);
                    bw1.newLine();
                }
                bw1.flush();
                bw1.close();
            }
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
     * @return listaRighe righe lette
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
     */
    public void scritturaFile(String dato) {
        try {
            System.out.println(file.getName());
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
     * Metodo <code>ricercaAttrEsiste</code>
     * <p>
     * ricerca e restituisce se l'attibuto esiste
     * </p>
     * @param dato dato da crecare
     * @param attr attributo da cercare
     * @return exist
     */
    public boolean ricercaAttrEsiste(String dato, String attr) {
        boolean exist = false;
        String line = "";
        int index = 0;
        if (attr.equals("id")){
            index = 0;
        } else if (attr.equals("userid")){
            index = 3;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(SEPARATORE_CSV);
                if (row[index].equals(dato)){
                    exist = true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * il metodo recupera il nome del centro vaccinale attraverso l'id del vaccinato
     * @param idVaccinato l'id del vaccinato
     * @return  nomeCentro
     */
    public String getNomeCentroByIdVaccinato(Short idVaccinato){
        String line = "";
        String nomeCentro = "";
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