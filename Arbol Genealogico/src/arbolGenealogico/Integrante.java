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
    private String nombreCompleto; // Nombre + Apellido
    private String nombre; // solo el primer nombre del nombre completo
    private String numeral; // "Of this name"
    private String padre; // "Born to: padre"
    private String madre; // "Born to: madre" (opcional)
    private String mote; // " Known throghout as" (opcional)
    private String titulo; // "Held title" (opcional)
    private String esposa; // "Wed to" (opcional)
    private String colorOjos; // "Of eyes"
    private String colorPelo; // "Of hair"
    private Lista hijos; // "Father to" (lista de nombres) (opcional)
    private String notas; // "Notes" (opcional)
    private String destino; // "Fate" (opcional)
    private int hash; // Identificador unico

    public Integrante(){
        this.nombreCompleto = null;
        this.nombre = null;
        this.numeral = null;
        this.padre = null;
        this.madre = null;
        this.mote = null;
        this.titulo = null;
        this. esposa = null;
        this.colorOjos = null;
        this.colorPelo = null;
        this.hijos = new Lista(); //Inicializa la lista de hijos como vacia
        this.notas = null;
        this.destino = null;
        this.hash = 0;
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
        this.setNombre(extraerNombre(nombreCompleto));
    }

    /**
     * @return the numeral
     */
    public String getNumeral() {
        return numeral;
    }

    /**
     * @param numeral the numeral to set
     */
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

    /**
     * @return the hash
     */
    public int getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(int hash) {
        this.hash = hash;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getIdentificadorUnico(){
        return nombreCompleto+", "+numeral+" of his name";
    }
    
    public String extraerNombre(String nombreCompleto) {
        String[] partes = nombreCompleto.split(" ");
        return partes[0];
    }
}


