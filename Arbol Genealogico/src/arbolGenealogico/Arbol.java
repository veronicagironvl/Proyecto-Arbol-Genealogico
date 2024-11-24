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

    public Arbol() {
        this.raiz = null;
    }

    public Arbol(HashTable hashTable) {
        
        // Agrega el hash respectivo a cada integrante y encuentra la raiz
        for (int i = 0; i < hashTable.getHashSize(); i++) {
            Integrante integrante = hashTable.obtenerIntegrante(i);
            integrante.setHash(i);
            hashTable.devolverIntegrante(i, integrante);
            if ((integrante.getPadre()).equals("[Unknown]")) {
                Integrante raiz = integrante;
            }
        }
    }

    public void insertar(Integrante integrante, String nombrePadre) {
        NodoArbol nuevoNodo = new NodoArbol(integrante);
        if (raiz == null) {
            raiz = nuevoNodo; // Si el a rol esta vacio, este nodo sera la raiz
        } else {
            NodoArbol padre = buscarNodo(raiz, nombrePadre);
            if (padre != null) {
                padre.agregarHijo(nuevoNodo);
            } else {
                throw new IllegalArgumentException("Padre no encontrado:" + nombrePadre);
            }
        }
    }

    private NodoArbol buscarNodo(NodoArbol nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getIntegrante().getNombreCompleto().equals(nombre)) {
            return nodo;
        }

        Nodo actual = nodo.getHijos().getInicio();
        while (actual != null) {
            NodoArbol encontrado = buscarNodo((NodoArbol) actual.getInfo(), nombre);
            if (encontrado != null) {
                return encontrado;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
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

    public Graph generarGrafo() {
        Graph graph = new SingleGraph(linaje);
        agregarNodoGrafico(graph, raiz);
        return graph;
    }

    private void agregarNodoGrafico(Graph graph, NodoArbol nodo) {
        if (nodo == null) {
            return;
        }

        String nombreNodo = nodo.getIntegrante().getNombreCompleto();

        // Agregar nodo al grafo si no existe
        if (graph.getNode(nombreNodo) == null) {
            Node graphNode = graph.addNode(nombreNodo);
            graphNode.setAttribute("ui.label", nombreNodo);
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

    private Integrante buscarPadre(String nombrePadre, HashTable hashTable){
        
        for (int i = 0; i < hashTable.getHashSize(); i++) {
            Integrante aux = hashTable.obtenerIntegrante(i);
            if (aux.getIdentificadorUnico().equalsIgnoreCase(nombrePadre)){
                return aux;
            } else if (aux.getMote().equalsIgnoreCase(nombrePadre)){
                return aux;
            } else if (aux.getNombreCompleto().equalsIgnoreCase(nombrePadre)){
                return aux;
            }   
        }
        return null;
    }   
        
//        //String nombreCombinado = nodo.getIntegrante().getNombreCompleto() + ", " + nodo.getIntegrante().getNumeral() + " of his name";
//        String nombreCombinado = integrante.getIdentificadorUnico();
//       
//        System.out.println("leer aquiiiiiiiiiiiiiiii");
//        System.out.println("Nombre completo: "+integrante.getNombreCompleto());
//        System.out.println("Mote: "+integrante.getMote());
//        System.out.println("ID unico: "+(integrante.getNombreCompleto()+", "+integrante.getNumeral()+" of his name"));
//        System.out.println("Nombre padre: "+nombrePadre);
//        String mote = "";
//        if (integrante.getMote() != null){
//            mote = integrante.getMote();
//        }
//        if (integrante.getNombreCompleto().equalsIgnoreCase(nombrePadre) || mote.equalsIgnoreCase(nombrePadre) || (integrante.getNombreCompleto()+", "+integrante.getNumeral()+" of his name").equalsIgnoreCase(nombrePadre)){
//        //if (nombreCombinado.equalsIgnoreCase(nombrePadre + ", ")) return nodo;
//        //if(nombreCombinado.equalsIgnoreCase(nombrePadre)) return nodo;
//       
//        return integrante;
//        }
//        Nodo actual = integrante.getHijos().getInicio();
//        while(actual != null){
//            Integrante encontrado = buscarPadre((Integrante) actual.getInfo(), nombrePadre);
//            if(encontrado != null) {
//                return encontrado;
//            }
//            actual = actual.getSiguiente();
//           
//
//        }
//        return null;
//    }

//    private Integrante buscarIntegrante(Integrante integrante, String nombre) {
//        if (integrante == null) {
//            return null;
//        }
//
//        if (integrante.getNombreCompleto().equalsIgnoreCase(nombre)) {
//            return integrante;
//        }
//
//        Nodo actual = integrante.getHijos().getInicio();
//
//        while (actual != null) {
//            NodoArbol encontrado = buscarNodo((NodoArbol) actual.getInfo(), nombre);
//            if (encontrado != null) {
//                return encontrado;
//            }
//            actual = actual.getSiguiente();
//        }
//        return null;
//    }

}
