package centrivaccinali;

import gestionefile.GestioneCsv;

import java.io.*;
import java.util.*;

public class GestioneCentriVaccinali extends GestioneCsv {

    private static GestioneCentriVaccinali istanza;

        /**
         * Metodo costruttore <code>GestioneCSV</code>
         *
         */
    private GestioneCentriVaccinali() {
            super("CentriVaccinali.dati", new String[]{"Id","Nome Centro","Qual Indirizzo","Nome Indirizzo","Civico","Comune","Provincia","Cap","Tipologia"});
        }

    public static GestioneCentriVaccinali getInstance(){
        if (istanza == null) {
            istanza = new GestioneCentriVaccinali();
        }
        return istanza;
    }

    /**
     * <code>scrivi Centri Vaccinali</code>
     * <p>questo metodo converte l'oggetto centrovaccinale in una string per essere scritti in un file CSV</p>
     * @see CentroVaccinale
     * @param centriVaccinaliVector
     */
    public void scriviCentriVaccinali(Vector<CentroVaccinale> centriVaccinaliVector) {
            for (CentroVaccinale centroVaccinale : centriVaccinaliVector) {
                StringBuffer linea = new StringBuffer();
                linea.append(centroVaccinale.getId());
                linea.append(SEPARATORE_CSV);
                linea.append(centroVaccinale.getNomeCentroVaccinale());
                linea.append(SEPARATORE_CSV);
                linea.append(centroVaccinale.getQualificatoreIndirizzo());
                linea.append(SEPARATORE_CSV);
                linea.append(centroVaccinale.getNomeIndirizzo());
                linea.append(SEPARATORE_CSV);
                linea.append(centroVaccinale.getNumeroCivico());
                linea.append(SEPARATORE_CSV);
                linea.append(centroVaccinale.getComune());
                linea.append(SEPARATORE_CSV);
                linea.append(centroVaccinale.getSiglaProvincia());
                linea.append(SEPARATORE_CSV);
                linea.append(centroVaccinale.getCap());
                linea.append(SEPARATORE_CSV);
                linea.append(centroVaccinale.getTipologia());
                linea.append(SEPARATORE_CSV);
                scritturaFile(linea.toString());
            }
        }

    public void registraCentroVaccinale(CentroVaccinale centroVaccinale) {
            StringBuffer linea = new StringBuffer();
            linea.append(centroVaccinale.getId());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getNomeCentroVaccinale());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getQualificatoreIndirizzo());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getNomeIndirizzo());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getNumeroCivico());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getComune());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getSiglaProvincia());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getCap());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getTipologia());
            scritturaFile(linea.toString());
    }

    /**
     * il metodo permette di inserire nuovi centri vaccinali
     *
     *
     */
        public Vector<CentroVaccinale> registraCentroVaccinale(){
            Scanner in = new Scanner(System.in);
            Vector<CentroVaccinale> centriVaccinaliVector = new Vector<CentroVaccinale>();
            boolean exit = false;
            while (!exit){
                //creazione e ricerca id libero
                short id = 0;
                while (ricercaIdEsiste(""+id)!=false){
                    id++;
                }
                //richieste info in input
                System.out.print("Inserisci nome Centro Vaccinale: ");
                String nomeCentroVaccinale = in.nextLine();
                System.out.print("Inserisci qualificatoreIndirizzo: ");
                String qualificatoreIndirizzo = in.nextLine();
                System.out.print("Inserisci nomeIndirizzo: ");
                String nomeIndirizzo = in.nextLine();
                System.out.print("Inserisci numeroCivico: ");
                String numeroCivico = in.nextLine();
                System.out.print("Inserisci comune: ");
                String comune = in.nextLine();
                System.out.print("Inserisci siglaProvincia: ");
                String siglaProvincia = in.nextLine();
                siglaProvincia = siglaProvincia.toUpperCase();
                System.out.print("Inserisci cap: ");
                String cap = in.nextLine();
                System.out.print("Inserisci tipologia: ");
                String tipologia = in.nextLine();
                tipologia = tipologia.substring(0,1).toUpperCase()+tipologia.substring(1).toLowerCase();
                //inserimento oggetto CentroVaccinale nel vettore
                centriVaccinaliVector.add(new CentroVaccinale(id,nomeCentroVaccinale,qualificatoreIndirizzo,nomeIndirizzo,Integer.parseInt(numeroCivico),comune,siglaProvincia,Integer.parseInt(cap),tipologia));
                System.out.println("Oggetto caricato nel vettore");
                //vuoi continuare
                boolean controllo = false;
                String opzione = "";
                while (!controllo){
                    System.out.print("Vuoi inserire un nuovo Centro Vaccinale? [S/N] ");
                    opzione = in.next();
                    opzione = opzione.toUpperCase();
                    if (opzione.equals("S")||opzione.equals("SI")||opzione.equals("N")||opzione.equals("NO")){
                        controllo = true;
                    } else {
                        System.out.print("Errore di inserimento, ritenta");
                    }
                    System.out.println();
                }
                switch (opzione){
                    case "S":
                    case "SI":
                        exit = false;
                        break;
                    case "N":
                    case "NO":
                        exit = true;
                }
            }
            return centriVaccinaliVector;
        }

    /**
     * il metodo verifica l'esistenza di un centroVaccinale
     * @return esiste o non esiste
     */
        public Boolean cercaCentroEsiste(String nomeCentroVaccinale){
            Scanner in = new Scanner(System.in);
            boolean esci = false;
            while (!esci){
                if (ricercaCentroEsistePerNome(nomeCentroVaccinale) == true){
                    esci = true;
                }
            }
            return esci;
        }

    /**
     * il metodo verifica l'esistenza di un centro vaccinale tramite il suo nome
     * @param nomeCentroVaccinale
     * @return esiste o non esiste
     */
        public boolean ricercaCentroEsistePerNome(String nomeCentroVaccinale) {
            boolean exist = false;
            int count = numRisultatiPerCampo(nomeCentroVaccinale,1);
            Vector<String[]> rows = ricercaRighePerCampo(nomeCentroVaccinale,1);
            for (int i = 0; i < count; i++) {
                String[] row = rows.elementAt(i);
                if (row[1].equals(nomeCentroVaccinale)){
                    exist = true;
                }
            }
            return exist;
        }

    public ArrayList<CentroVaccinale> getCentriVaccinali(){
        ArrayList<CentroVaccinale> listaCentriVaccinali = new ArrayList<>();
        ArrayList<String> listaRigheFile = letturaFile();
        /*
        Ricostruzione oggetti scritti nel file
         */
        for (String line: listaRigheFile) {
            //System.out.println(line);
            String[] rawObject = line.split(SEPARATORE_CSV);
            CentroVaccinale centroVaccinale = new CentroVaccinale();
            centroVaccinale.setId(Short.parseShort(rawObject[0]));
            centroVaccinale.setNomeCentroVaccinale(rawObject[1]);
            centroVaccinale.setQualificatoreIndirizzo(rawObject[2]);
            centroVaccinale.setNomeIndirizzo(rawObject[3]);
            centroVaccinale.setNumeroCivico(Integer.parseInt(rawObject[4]));
            centroVaccinale.setComune(rawObject[5]);
            centroVaccinale.setSiglaProvincia(rawObject[6]);
            centroVaccinale.setCap(Integer.parseInt(rawObject[7]));
            centroVaccinale.setTipologia(rawObject[8]);
            listaCentriVaccinali.add(centroVaccinale);
        }
        return listaCentriVaccinali;
    }

    /**
     * creazione lista centriVaccinali tramite array
     * @return
     */
        public ArrayList<CentroVaccinale> getCentroVaccinalePerNome(String nomeCentroVaccinale){
            Scanner in = new Scanner(System.in);
            //System.out.print("Inserisci nome Centro Vaccinale: ");
            //String nomeCentroVaccinale = in.nextLine();
            ArrayList<CentroVaccinale> listaCentriVaccinali = new ArrayList<>();
            int count = numRisultatiPerCampoVisuliz(nomeCentroVaccinale,1);
            Vector<String[]> rows = ricercaRighePerCampoVisualiz(nomeCentroVaccinale,1);
            for (int i = 0; i < count; i++) {
                String[] row = rows.elementAt(i);
                CentroVaccinale centroVaccinale = new CentroVaccinale(Short.parseShort(row[0]), row[1], row[2], row[3],
                        Integer.parseInt(row[4]), row[5], row[6], Integer.parseInt(row[7]), row[8]);
                listaCentriVaccinali.add(centroVaccinale);
                listaCentriVaccinali.get(i).toString();
            }
            return listaCentriVaccinali;
        }

    public ArrayList<CentroVaccinale> searchCentroByName(String nomeCentroVaccinale) {
        ArrayList<CentroVaccinale> listaCentriVaccinali = getCentriVaccinali();
        ArrayList<CentroVaccinale> listaRisultati = new ArrayList<>();
        for (CentroVaccinale centroVaccinale: listaCentriVaccinali) {
            centroVaccinale.toString();
            if (centroVaccinale.getNomeCentroVaccinale().toLowerCase().contains(nomeCentroVaccinale.toLowerCase()))
                listaRisultati.add(centroVaccinale);
        }
        return listaRisultati;
    }

    /**
     * il metodo restituisce il centroVaccinale cercato per comune e tipologia
     * @return centroVaccinale
     */
    public ArrayList<CentroVaccinale> getCentroVaccinalePerComuneTipologia(){
        Scanner in = new Scanner(System.in);
        System.out.print("Inserisci comune Centro Vaccinale: ");
        String comune = in.nextLine();
        System.out.print("Inserisci tipologia Centro Vaccinale: ");
        String tipologia = in.nextLine();
        ArrayList<CentroVaccinale> listaCentriVaccinali = new ArrayList<CentroVaccinale>();
        int count = numRisultatiPerCampo(comune,4);
        Vector<String[]> rows = ricercaRighePerCampo(comune,4);
        for (int i = 0; i < count; i++) {
            String[] row = rows.elementAt(i);
            listaCentriVaccinali.add(new CentroVaccinale(Short.parseShort(row[0]), row[1], row[2], row[3],
                    Integer.parseInt(row[4]), row[5], row[6], Integer.parseInt(row[7]), row[8]));
        }
        count = numRisultatiPerCampo(tipologia,6);
        rows = ricercaRighePerCampo(tipologia,6);
        for (int i = 0; i < count; i++) {
            String[] row = rows.elementAt(i);
            listaCentriVaccinali.add(new CentroVaccinale(Short.parseShort(row[0]), row[1], row[2], row[3],
                    Integer.parseInt(row[4]), row[5], row[6], Integer.parseInt(row[7]), row[8]));
        }
        Set<CentroVaccinale> set = new HashSet<>(listaCentriVaccinali);
        listaCentriVaccinali.clear();
        listaCentriVaccinali.addAll(set);
        return listaCentriVaccinali;
    }

    public ArrayList<CentroVaccinale> searchCentroByComuneAndTipologia(String comune, String tipologia) {
        ArrayList<CentroVaccinale> listaCentriVaccinali = getCentriVaccinali();
        ArrayList<CentroVaccinale> listaRisultati = new ArrayList<>();
        for (CentroVaccinale centroVaccinale: listaCentriVaccinali) {
            if (centroVaccinale.getComune().toLowerCase().contains(comune.toLowerCase()) | centroVaccinale.getTipologia().toLowerCase().contains(tipologia.toLowerCase()))
                listaRisultati.add(centroVaccinale);
        }
        return listaRisultati;
    }

}

