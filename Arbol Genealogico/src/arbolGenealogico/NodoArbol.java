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
    
    public void agregarHijo(NodoArbol hijo){
        hijos.insertarUltimo(hijo);
    }
}
