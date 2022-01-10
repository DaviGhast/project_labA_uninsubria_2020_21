package centrivaccinali;

/**
 * Classe oggetto <code>InfoEventoAvversoAnonimo</code>
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class InfoEventoAvversoAnonimo {

    /**
     * il nome del <code>vaccinoSomministrato</code>
     */
    private String vaccinoSomministrato;

    /**
     * il nome dell' <code>evento</code>
     */
    private String evento;

    /**
     * l'indice di <code>severita</code>
     */
    private byte severita;

    /**
     * eventuali <code>note</code> opzionali
     */
    private String note;

    /**
     * @return vaccinoSomministrato è il nome del vaccino somministrato
     */
    public String getVaccinoSomministrato() {
        return vaccinoSomministrato;
    }

    /**
     * @param vaccinoSomministrato e' il nome del vaccino somminisstrato
     */
    public void setVaccinoSomministrato(String vaccinoSomministrato) {
        this.vaccinoSomministrato = vaccinoSomministrato;
    }

    /**
     * @return evento è il nome dell'evento avverso
     */
    public String getEvento() {
        return evento;
    }

    /**
     * @param evento è il nome dell'evento avverso
     */
    public void setEvento(String evento) {
        this.evento = evento;
    }

    /**
     * @return severita è l'indice di severità
     */
    public byte getSeverita() {
        return severita;
    }

    /**
     * @param severita  severita  è l'indice di severità
     */
    public void setSeverita(byte severita) {
        this.severita = severita;
    }

    /**
     * @return note sono le note opzionali
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note sono le note opzionali
     */
    public void setNote(String note) {
        this.note = note;
    }
}
