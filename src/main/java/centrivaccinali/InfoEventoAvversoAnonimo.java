package centrivaccinali;

public class InfoEventoAvversoAnonimo {
    private String vaccinoSomministrato;
    private String evento;
    private byte severita;
    private String note;

    public String getVaccinoSomministrato() {
        return vaccinoSomministrato;
    }

    public void setVaccinoSomministrato(String vaccinoSomministrato) {
        this.vaccinoSomministrato = vaccinoSomministrato;
    }

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
