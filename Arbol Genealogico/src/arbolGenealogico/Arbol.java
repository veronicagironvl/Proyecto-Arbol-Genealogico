/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolGenealogico;

/**
 *
 * @author aiannelli
 */

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Arbol {
    private NodoArbol raiz;
    private String linaje;
    
    public Arbol(){
        this.raiz = null;
    }
    
    
    public void insertar(Integrante integrante, String nombrePadre){
        NodoArbol nuevoNodo = new NodoArbol(integrante);
        if (raiz == null){
            raiz = nuevoNodo; // Si el a rol esta vacio, este nodo sera la raiz
        }else{
            NodoArbol padre = buscarNodo(raiz, nombrePadre);
            if(padre != null){
                padre.agregarHijo(nuevoNodo);
            }else{
                throw new IllegalArgumentException("Padre no encontrado:" + nombrePadre);
            }
        }
    }
    
    private NodoArbol buscarNodo(NodoArbol nodo, String nombre){
        if(nodo == null) return null;
        if (nodo.getIntegrante().getNombreCompleto().equals(nombre)) return nodo;
        
        Nodo actual = nodo.getHijos().getInicio();
        while(actual != null){
            NodoArbol encontrado = buscarNodo((NodoArbol) actual.getInfo(), nombre);
            if(encontrado != null) return encontrado;
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public NodoArbol getRaiz(){
        return raiz;
    }
    
     /**
     * @param raiz the raiz to set
     */
    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }
    
    public Graph generarGrafo(){
        Graph graph = new SingleGraph(linaje);
        agregarNodoGrafico(graph, raiz);
        return graph;
    }
    
    private void agregarNodoGrafico(Graph graph, NodoArbol nodo){
        if(nodo == null) return;
        
        String nombreNodo = nodo.getIntegrante().getNombreCompleto();
       
        // Agrregar nodo al grafo si no existe
        if(graph.getNode(nombreNodo) == null){
            Node graphNode = graph.addNode(nombreNodo);
            graphNode.setAttribute("ui.label", nombreNodo);
        }
        
        // Recorre los hijos del nodo y agrega sus conexiones
        Nodo actual = nodo.getHijos().getInicio();
        while(actual != null){
            NodoArbol hijo = (NodoArbol) actual.getInfo();
            agregarNodoGrafico(graph, hijo);
            
            String nombreHijo = hijo.getIntegrante().getNombreCompleto();
            
            // Agrega la arista entre el nodo y su hijo si no existe
            if(graph.getEdge(nombreNodo + "->" + nombreHijo) == null){
                graph.addEdge(nombreNodo + "->" + nombreHijo, nombreNodo, nombreHijo);
            }
            actual = actual.getSiguiente();
        }
        
    }

    /**
     * @return the linaje
     */
    public String getLinaje() {
        return linaje;
    }

    /**
     * @param linaje the linaje to set
     */
    public void setLinaje(String linaje) {
        this.linaje = linaje;
    }
}
