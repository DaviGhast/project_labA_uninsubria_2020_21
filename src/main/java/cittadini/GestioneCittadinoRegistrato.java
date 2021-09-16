package cittadini;

import centrivaccinali.GestioneCentriVaccinali;
import criptazione.AlgoritmoMD5;
import gestionefile.GestioneCsv;

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
            linea.append(SEPARATORE_CSV);
            scritturaFile(linea.toString());
    }

    /**
     * il metodo restituisce un cittadino registrato tramite id
     * @param userid
     * @return cittadinoRegistrato
     */
    public CittadinoRegistrato getCittadinoRegistrato (String userid){
        CittadinoRegistrato cittadinoRegistrato = null;
        int count = numRisultatiPerCampo(userid,3);
        Vector<String[]> rows = ricercaRighePerCampo(userid, 3);
        for (int i = 0; i < count; i++) {
            String[] row = rows.elementAt(i);
            cittadinoRegistrato = new CittadinoRegistrato(row[0],row[1],row[2],row[3],row[4],Short.parseShort(row[5]));
        }
        return cittadinoRegistrato;
    }

    /**
     * il metodo verifica l'esistenza di un userid
     * @param userid
     * @return esiste o non esiste
     */
    public boolean useidEsistente(String userid){
        boolean exist = false;
        int count = numRisultatiPerCampo(userid,3);
        Vector<String[]> rows = ricercaRighePerCampo(userid, 3);
        for (int i = 0; i < count; i++) {
            String[] row = rows.elementAt(i);
            if (row[3].equals(userid)){
                exist = true;
            }
        }
        return exist;
    }

    public boolean rispostaCittadinoEsiste(String userid, String password){
        boolean risposta = false;
        if (useidEsistente(userid)){
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
