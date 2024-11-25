package arbolGenealogico;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Adrian
 */
public class Integrante {
    private String nombreCompleto;
    private String numeral; // "Of this name"
    private String padre; // "Born to: padre"
    private String madre; // "Born to: madre"
    private String mote; // " Known throghout as"
    private String titulo; // "Held title"
    private String esposa; // "Wed to" (si existe)
    private String colorOjos; // "Of eyes"
    private String colorPelo; // "Of hair"
    private Lista hijos; // "Father to" (lista de nombres)
    private String notas; // "Notes"
    private String destino; // "Fate"
    private String fatherNameKey;
    private String fatherMoteKey;

    public Integrante(){
        this.hijos = new Lista(); //Inicializa la lista de hijos como vacia
    }
    
    // Constructor completo
    public Integrante(String nombreCompleto, String numeral, String padre,
            String madre, String mote, String titulo, String esposa, 
            String colorOjos, String colorPelo, Lista hijos, String notas, String destino){
        this.nombreCompleto = nombreCompleto;
        this.numeral = numeral;
        this.padre = padre;
        this.madre = madre;
        this.mote = mote;
        this.titulo = titulo;
        this.esposa = esposa;
        this.colorOjos = colorOjos;
        this.colorPelo = colorPelo;
        this.hijos = hijos != null ? hijos : new Lista(); // Asegura que la lista no sea nula
        this.notas = notas;
        this.destino = destino;
        
    }
    
    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeral() {
        return numeral;
    }

    public void setNumeral(String numeral) {
        this.numeral = numeral;
    }

    /**
     * @return the padre
     */
    public String getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(String padre) {
        this.padre = padre;
    }

    /**
     * @return the madre
     */
    public String getMadre() {
        return madre;
    }

    /**
     * @param madre the madre to set
     */
    public void setMadre(String madre) {
        this.madre = madre;
    }

    /**
     * @return the mote
     */
    public String getMote() {
        return mote;
    }

    /**
     * @param mote the mote to set
     */
    public void setMote(String mote) {
        this.mote = mote;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the esposa
     */
    public String getEsposa() {
        return esposa;
    }

    /**
     * @param esposa the esposa to set
     */
    public void setEsposa(String esposa) {
        this.esposa = esposa;
    }

    /**
     * @return the colorOjos
     */
    public String getColorOjos() {
        return colorOjos;
    }

    /**
     * @param colorOjos the colorOjos to set
     */
    public void setColorOjos(String colorOjos) {
        this.colorOjos = colorOjos;
    }

    /**
     * @return the colorPelo
     */
    public String getColorPelo() {
        return colorPelo;
    }

    /**
     * @param colorPelo the colorPelo to set
     */
    public void setColorPelo(String colorPelo) {
        this.colorPelo = colorPelo;
    }

    /**
     * @return the hijos
     */
    public Lista getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(Lista hijos) {
        this.hijos = hijos != null ? hijos : new Lista(); // Asegura que la lista no este vacia
    }

    /**
     * @return the notas
     */
    public String getNotas() {
        return notas;
    }

    /**
     * @param notas the notas to set
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public String getIdentificadorUnico() {
        return nombreCompleto + ", " + numeral; // Crea un identificador único
    }
    public String fatherNameKey() {
        return nombreCompleto + ", " + numeral; // Crea un identificador único
    }

}


