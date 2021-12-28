package controllers;

public class FixInput<dataBuffer> {

    private static FixInput istanza;
    private Object dataBuffer;

    private FixInput() {}

    public static FixInput getInstance(){
        if (istanza == null) {
            istanza = new FixInput();
        }
        return istanza;
    }

    public String fixString(String string){
        String[] array = string.split(" ");
        StringBuffer newString = new StringBuffer();
        for (String str: array) {
            newString.append(str.substring(0, 1).toUpperCase() +
                    str.substring(1).toLowerCase());
            newString.append(" ");
        }
        newString.deleteCharAt(newString.length()-1);
        return newString.toString();
    }

    public Object getDataBuffer() {
        return dataBuffer;
    }

    public void setDataBuffer(Object dataBuffer) {
        this.dataBuffer = dataBuffer;
    }
}
