package cittadini;

import centrivaccinali.CentroVaccinale;
import centrivaccinali.GestioneCentriVaccinali;
import criptazione.AlgoritmoMD5;
import gestionefile.GestioneCsv;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class GestioneCittadinoRegistrato extends GestioneCsv {

    private static GestioneCittadinoRegistrato istanza;

    /**
     * Metodo costruttore <code>GestioneCSV</code>
     */
    private GestioneCittadinoRegistrato() {
        super("Cittadini_Registrati.dati", new String[]{"Nome","Cognome","Email","Userid","Password","idVaccinazione"});
        verificaFile();
    }

    public static GestioneCittadinoRegistrato getInstance(){
        if (istanza == null) {
            istanza = new GestioneCittadinoRegistrato();
        }
        return istanza;
    }

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
     * @param userid
     * @return cittadinoRegistrato
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

    public boolean rispostaCittadinoEsiste(String userid, String password){
        boolean risposta = false;
        if (useridEsistente(userid)){
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
