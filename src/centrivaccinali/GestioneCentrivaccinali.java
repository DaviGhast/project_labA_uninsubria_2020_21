package centrivaccinali;

import gestionefile.GestioneCsv;

import java.util.Scanner;
import java.util.Vector;

public class GestioneCentrivaccinali extends GestioneCsv {

        /**
         * Metodo costruttore <code>GestioneCSV</code>
         *
         */
    public GestioneCentrivaccinali() {
            super("CentriVaccinali.dati", new String[]{"Id","Nome Centro","Qual Indirizzo","Nome Indirizzo","Civico","Comune","Provincia","Cap","Tipologia"});
        }

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
                centriVaccinaliVector.add(new CentroVaccinale(id,nomeCentroVaccinale,qualificatoreIndirizzo,nomeIndirizzo,comune,siglaProvincia,tipologia,Integer.parseInt(numeroCivico),Integer.parseInt(cap)));
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

        public String cercaCentro (){
            Scanner in = new Scanner(System.in);
            boolean esci = false;
            String nomeCentroVaccinale = "";
            while (!esci){
                System.out.print("Inserisci nome Centro Vaccinale: ");
                nomeCentroVaccinale = in.nextLine();
                if (ricercaCentroEsiste(nomeCentroVaccinale) == true){
                    esci = true;
                } else {
                    System.out.print("Centro inesistente, magari hai sbagliato a scrivere");
                }
            }
            return nomeCentroVaccinale;
        }

        public boolean ricercaCentroEsiste(String nomeCentroVaccinale) {
            boolean exist = false;
            int count = numRisultatiPerTutto(nomeCentroVaccinale);
            Vector<String[]> rows = ricercaRighePerTutto(nomeCentroVaccinale);
            for (int i = 0; i < count; i++) {
                String[] row = rows.elementAt(i);
                if (row[1].equals(nomeCentroVaccinale)){
                    exist = true;
                }
            }
            return exist;
        }

}

