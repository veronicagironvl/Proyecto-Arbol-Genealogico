/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolGenealogico;

/**
 *
 * @author veron
 */
public class NodoArbol {
    private Integrante integrante;
    private Lista hijos;
/**
 * Crea un nuevo nodo del &aacute;rbol con un integrante asociado.
 *
 * Este constructor inicializa un nuevo nodo del &aacute;rbol, asignándole el integrante proporcionado y creando una lista vacía para almacenar sus hijos.
 *
 * @param integrante El integrante asociado a este nodo.
 */    
    public NodoArbol(Integrante integrante){
        this.integrante = integrante;
        this.hijos = new Lista();
    }
/**
 * Obtiene el integrante asociado a este nodo.
 *
 * @return El integrante asociado al nodo.
 */    
    public Integrante getIntegrante(){
        return integrante;
    }
/**
 * Obtiene la lista de hijos de este nodo.
 *
 * @return La lista de hijos del nodo.
 */
    public Lista getHijos(){
        return hijos;
    }
    

    /**
     * @param integrante the integrante to set
     */
    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(Lista hijos) {
        this.hijos = hijos;
    }
/**
 * Agrega un hijo a la lista de hijos del nodo.
 *
 * Este m&eacute;todo inserta el hijo proporcionado al final de la lista de hijos del nodo.
 *
 * @param hijo El hijo a agregar.
 */    
    public void agregarHijo(Integrante hijo){
        getHijos().insertarUltimo(hijo);
    }
}
