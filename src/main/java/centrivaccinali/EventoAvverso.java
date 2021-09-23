package centrivaccinali;

public class EventoAvverso {
    private String evento;
    private byte severita;
    private String note;

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public byte getSeverita() {
        return severita;
    }

    public void setSeverita(byte severita) {
        this.severita = severita;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
