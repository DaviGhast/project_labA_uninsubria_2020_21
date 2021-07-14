package cittadini;

import criptazione.AlgoritmoMD5;
import gestionefile.GestioneCsv;

import java.util.Scanner;
import java.util.Vector;

public class GestioneCittadinoRegistrato extends GestioneCsv {
    /**
     * Metodo costruttore <code>GestioneCSV</code>
     */
    public GestioneCittadinoRegistrato() {
        super("Cittadini_Registrati.dati", new String[]{"Nome","Cognome","Email","Userid","Password","idVaccinazione"});
        verificaFile();
    }

    public CittadinoRegistrato registraCittadino(){
        Scanner in = new Scanner(System.in);
            //richieste info in input
            System.out.print("Inserisci il tuo nome: ");
            String nome = in.nextLine();
            System.out.print("Inserisci il tuo cognome: ");
            String cognome = in.nextLine();
            System.out.print("Inserisci la tua email: ");
            String email = in.nextLine();
            System.out.print("Inserisci un userid: ");
            String userid = in.nextLine();
            System.out.print("Inserisci una password: ");
            String password = in.nextLine();
            System.out.print("Inserisci idVaccinazione: ");
            String idVaccinazione = in.nextLine();
            while (useidEsistente(userid)) {
                System.out.print("Inserisci un diverso userid: ");
                userid = in.nextLine();
            }
        return new CittadinoRegistrato(nome,cognome,email,userid, AlgoritmoMD5.converti(password),Short.parseShort(idVaccinazione));
    }

    public void scriviCittadinoRegistrato(CittadinoRegistrato cittadinoRegistrato) {
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
