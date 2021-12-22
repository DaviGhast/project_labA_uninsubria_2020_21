package centrivaccinali;

/**
 * la classe si occupa degli eventi avversi
 *  @author Davide Mainardi
 *  * @author Marc Cepraga
 *  * @author Luca Muggiasca
 *  * @author Brenno Re
 */
public class EventoAvverso {

    /**
     *l' <code>evento</code> indica l'evento avverso riscontrato dal vaccinato
     */
    private String evento;

    /**
     *la <code>severita</code> indica la severita' dell'evento avverso
     */
    private byte severita;

    /**
     *le <code>note</code> riportano ulteriori informazioni circa l'evento avverso
     */
    private String note;

    /**
     *@return evento indica l'evento avverso riscontrato dal cittadino
     */
    public String getEvento() {
        return evento;
    }

    /**
     *@param  evento indica l'evento avverso riscontrato dal cittadino
     */
    public void setEvento(String evento) {
        this.evento = evento;
    }

    /**
     *@return severità' riporta la severità degli eventi avversi
     */
    public byte getSeverita() {
        return severita;
    }

    /**
     *@param  severita riporta la severità degli eventi avversi
     */
    public void setSeverita(byte severita) {
        this.severita = severita;
    }

    /**
     *@return note riportano ulteriori informazioni circa l'evento avverso
     */
    public String getNote() {
        return note;
    }
    /**
     *@param  note riportano ulteriori informazioni circa l'evento avverso
     */
    public void setNote(String note) {
        this.note = note;
    }
}
