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
    
    public NodoArbol(Integrante integrante){
        this.integrante = integrante;
        this.hijos = new Lista();
    }
    
    public Integrante getIntegrante(){
        return integrante;
    }

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
    
    public void agregarHijo(Integrante hijo){
        getHijos().insertarUltimo(hijo);
    }
}
