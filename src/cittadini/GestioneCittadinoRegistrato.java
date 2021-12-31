package cittadini;

import criptazione.AlgoritmoMD5;
import gestionefile.GestioneCsv;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * contiene tutti i metodi per la gestione del CittadinoRegistrato
 */
public class GestioneCittadinoRegistrato extends GestioneCsv {

    /**
     * il nome dell'<code>oggetto</code> del cittadino
     */
    private static GestioneCittadinoRegistrato istanza;

    /**
     * Metodo costruttore <code>GestioneCSV</code>
     */
    private GestioneCittadinoRegistrato() throws URISyntaxException {
        super("Cittadini_Registrati.dati", new String[]{"Nome","Cognome","Email","Userid","Password","idVaccinazione"});
        verificaFile();
    }
    /**
     * pattern singleton del metodo <code>getistance</code>
     * @return l'oggetto della classe GestioneCittadinoRegistrato
     */
    public static GestioneCittadinoRegistrato getInstance() throws URISyntaxException {
        if (istanza == null) {
            istanza = new GestioneCittadinoRegistrato();
        }
        return istanza;
    }
    /**
     * il metodo inserisce gli oggetti cittadinoVanello StringBuffer per poi inserirli in Vaccinati_NomeCentroVaccinale.csv
     *  @param cittadinoRegistrato e' l'oggetto della classe CittadinoRegistrato
     */
    public void registraCittadino(CittadinoRegistrato cittadinoRegistrato) {
            StringBuffer linea = new StringBuffer();
            linea.append(cittadinoRegistrato.getNome());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoRegistrato.getCognome());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoRegistrato.getEmail());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoRegistrato.getUserid());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoRegistrato.getPassword());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoRegistrato.getIdVaccinazione());
            //linea.append(SEPARATORE_CSV);
            scritturaFile(linea.toString());
    }
    /**
     * questo metodo inserisce gli oggetti cittadinoRegistrato nello stringbuffer per poi inserirli in Cittadini_Registrati.csv
     * @return listaCittadiniRegistrati e' la lista dei cittadini registrati
     */
    public ArrayList<CittadinoRegistrato> getCittadiniRegistrati(){
        ArrayList<CittadinoRegistrato> listaCittadiniRegistrati = new ArrayList<>();
        ArrayList<String> listaRigheFile = letturaFile();
        /*
        Ricostruzione oggetti scritti nel file
         */
        for (String line: listaRigheFile) {
            //System.out.println(line);
            String[] rawObject = line.split(SEPARATORE_CSV);
            CittadinoRegistrato cittadinoRegistrato = new CittadinoRegistrato();
            cittadinoRegistrato.setNome(rawObject[0]);
            cittadinoRegistrato.setCognome(rawObject[1]);
            cittadinoRegistrato.setEmail(rawObject[2]);
            cittadinoRegistrato.setUserid(rawObject[3]);
            cittadinoRegistrato.setPassword(rawObject[4]);
            cittadinoRegistrato.setIdVaccinazione(Short.parseShort(rawObject[5]));
            listaCittadiniRegistrati.add(cittadinoRegistrato);
        }
        return listaCittadiniRegistrati;
    }

    /**
     * il metodo restituisce un cittadino registrato tramite id
     * @param userid e' la userid del cittadino registrato
     * @return cittadinoRegistrato l'oggetto cittadinoRegistrato
     */
    public CittadinoRegistrato getCittadinoRegistrato (String userid){
        CittadinoRegistrato cittadinoRegistrato = null;
        ArrayList<CittadinoRegistrato> lista = getCittadiniRegistrati();
        for (CittadinoRegistrato cittadinoRegistratoDaLista: lista) {
            if (cittadinoRegistratoDaLista.getUserid().equals(userid))
                cittadinoRegistrato = cittadinoRegistratoDaLista;
        }
        return cittadinoRegistrato;
    }

    /**
     * al momento dell'autenticazione controlla che lo userid del cittadino esista o meno
     * @param userid e' lo userid
     * @param password e la password
     * @return risposta riguardo l'esistenza dello userid
     */
    public boolean rispostaCittadinoEsiste(String userid, String password){
        boolean risposta = false;
        // TODO: 30/12/2021  aggiungere login con email
        if (ricercaAttrEsiste(userid, "userid")){
            CittadinoRegistrato cittadinoRegistrato = getCittadinoRegistrato(userid);
            if (cittadinoRegistrato.getPassword().equals(AlgoritmoMD5.converti(password))){
                System.out.print("login");
                risposta = true;
            } else {
                risposta = false;
            }
        } else {
            risposta = false;
        }
        return risposta;
    }
}
