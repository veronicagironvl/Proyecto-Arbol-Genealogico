/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolGenealogico;

/**
 *
 * @author aiannelli
 */

import javax.swing.*;
import java.awt.event.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Arbol {
   private NodoArbol raiz;
    private String linaje;
    private HashTable hashTable;
    
    public Arbol(){
        this.raiz = null;
        this.hashTable = new HashTable(500);
    }
    
    
    public void insertar(Integrante integrante, String nombrePadre){
        NodoArbol nuevoNodo = new NodoArbol(integrante);
        if (raiz == null){
            raiz = nuevoNodo; // Si el a rol esta vacio, este nodo sera la raiz
            hashTable.insertInHashTable(integrante);
        }else{
            NodoArbol padre = buscarNodo(raiz, nombrePadre);
            if(padre != null){
                padre.agregarHijo(nuevoNodo);
                hashTable.insertInHashTable(integrante);
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
    
    public Integrante buscarIntegrantePorNombre(String nombre) {
        return hashTable.buscar(nombre); // Buscar en la tabla hash
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
    
    // Método para generar el grafo
    public Graph generarGrafo() {
        if (raiz == null) {
            throw new IllegalStateException("El árbol está vacío, no se puede generar el grafo.");
        }

        Graph graph = new SingleGraph(linaje); // Crea un grafo con el nombre del linaje
        agregarNodoGrafico(graph, raiz); // Agrega los nodos y aristas al grafo
        return graph;
    }
    
    private void agregarNodoGrafico(Graph graph, NodoArbol nodo) {
        if (nodo == null) return;

        String nombreNodo = nodo.getIntegrante().getNombreCompleto();

        // Agregar nodo al grafo si no existe
        if (graph.getNode(nombreNodo) == null) {
            Node graphNode = graph.addNode(nombreNodo);
            graphNode.setAttribute("ui.label", nombreNodo);

            // Agregar un listener para eventos de clic
            graphNode.setAttribute("ui.class", "clickable"); // Clase visual para indicar interactividad
            graphNode.setAttribute("integrante", nodo.getIntegrante()); // Asociar el integrante al nodo

            graphNode.setAttribute("ui.clicked", false); // Estado inicial del nodo
        }

        // Agregar hijos y sus conexiones
        Nodo actual = nodo.getHijos().getInicio();
        while (actual != null) {
            NodoArbol hijo = (NodoArbol) actual.getInfo();
            agregarNodoGrafico(graph, hijo); // Agrega al nodo hijo

            String nombreHijo = hijo.getIntegrante().getNombreCompleto();

            // Agregar la arista entre el nodo actual y su hijo
            String edgeId = nombreNodo + "->" + nombreHijo;
            if (graph.getEdge(edgeId) == null) {
                graph.addEdge(edgeId, nombreNodo, nombreHijo, true); // Arista dirigida
            }
            actual = actual.getSiguiente();
        }
    }
    
    public HashTable getHastTable(){
        return hashTable;
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
    
    /**
     * Devuelve todos los nodos del árbol en una instancia de Lista.
     */
    public Lista getTodosLosNodos() {
        Lista nodos = new Lista();
        agregarNodosRecursivamente(raiz, nodos);
        return nodos;
    }
    
        /**
     * Método recursivo para agregar todos los nodos a una instancia de Lista.
     */
    private void agregarNodosRecursivamente(NodoArbol nodo, Lista nodos) {
        if (nodo == null) return;
        nodos.insertarUltimo(nodo);

        Nodo actual = nodo.getHijos().getInicio();
        while (actual != null) {
            NodoArbol hijo = (NodoArbol) actual.getInfo();
            agregarNodosRecursivamente(hijo, nodos);
            actual = actual.getSiguiente();
        }
    }
}