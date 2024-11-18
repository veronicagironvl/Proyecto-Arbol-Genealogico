package arbolGenealogico;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Adrian
 */
/**
 * Clase para la implementaci&oacute;n de los nodos de una lista enlazada
 * Se almacena la informaci&oacute;n denominada info y el nodo pr&oacute;ximo denominado siguiente
 * @author aiannelli
 */
public class Nodo {
    private String info;
    private Nodo siguiente;
    
    /**
     * Constructor que crea un nodo con un dato espec&iacute;fico y el siguiente nodo inicializado a null.
     *
     * @param data El dato a almacenar en el nodo.
     */
    public Nodo (String data) {
        this.info = data;
        this.siguiente = null;
    }    
    
    /**
     * Constructor que crea un nodo con un dato espec&iacute;fico y una referencia al siguiente nodo.
     *
     * @param data El dato a almacenar en el nodo.
     * @param siguiente El siguiente nodo en la lista.
     */
    
    public Nodo (String data, Nodo siguiente) {
        this.info = data;
        this.siguiente = siguiente;   
    }
    /**
     * Obtiene el dato almacenado en el nodo.
     * @return El dato del nodo.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Establece un nuevo valor para el dato del nodo.
     * @param info El nuevo valor del dato.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Obtiene el siguiente nodo en la lista.
     * @return El siguiente nodo en la lista, o null si no hay siguiente nodo.
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo en la lista.
     * @param siguiente El nuevo siguiente nodo.
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}


