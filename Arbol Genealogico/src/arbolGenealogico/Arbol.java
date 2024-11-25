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

    private Graph graph;
    private NodoArbol raiz;
    private String linaje;

/**
 * Crea un &aacute;rbol vac&iacute;o con un nodo ra&iacute;z nulo.
 */    
    public Arbol() {
        this.raiz = null;
    }
/**
 * Construye un &aacute;rbol a partir de una tabla hash.
 *
 * Este constructor itera sobre la tabla hash, asigna un hash a cada integrante,
 * y crea una estructura de &aacute;rbol basada en las relaciones padre-hijo. El nodo ra&iacute;z se identifica
 * por los integrantes cuyo padre es "[Unknown]".
 *
 * @param hashTable La tabla hash que contiene los objetos Integrante.
 */
    public Arbol(HashTable hashTable) {

        // Agrega el hash respectivo a cada integrante y agrega la raiz al arbol
        for (int i = 0; i < hashTable.getHashSize(); i++) {
            Integrante integrante = hashTable.obtenerIntegrante(i);
            integrante.setHash(i);
            hashTable.devolverIntegrante(i, integrante);
            if ((integrante.getPadre()).equals("[Unknown]")) {
                NodoArbol nodoRaiz = new NodoArbol(integrante);
                this.raiz = nodoRaiz;
            }
        }
        this.agregarHijos(raiz, hashTable);
        
        this.verPadre(raiz);
        
        this.graph = this.generarGrafo();
    }

//    public void insertar(Integrante integrante, String nombrePadre) {
//        NodoArbol nuevoNodo = new NodoArbol(integrante);
//        if (raiz == null) {
//            raiz = nuevoNodo; // Si el a rol esta vacio, este nodo sera la raiz
//        } else {
//            NodoArbol padre = buscarNodo(raiz, nombrePadre);
//            if (padre != null) {
//                padre.agregarHijo(nuevoNodo);
//            } else {
//                throw new IllegalArgumentException("Padre no encontrado:" + nombrePadre);
//            }
//        }
//    }

    /**
 * Busca un nodo en el &aacute;rbol cuyo integrante tenga el nombre completo especificado.
 * 
 * Este método realiza una b&uacute;squeda en profundidad primero (DFS) para encontrar el nodo deseado.
 * Comienza en el nodo proporcionado y recorre recursivamente todos sus hijos hasta encontrar un nodo
 * cuyo integrante tenga el nombre completo coincidente.
 *
 * @param nodo El nodo desde donde iniciar la b&uacute;squeda.
 * @param nombre El nombre completo del integrante a buscar.
 * @return El nodo encontrado, o null si no se encuentra.
 */
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
/**
 * Obtiene el nodo ra&iacute;z del &aacute;rbol.
 *
 * @return El nodo ra&iacute;z del &aacute;rbol.
 */
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
/**
 * Genera un grafo a partir del árbol genealógico.
 *
 * Este m&eacute;todo crea un nuevo grafo y agrega todos los nodos del &aacute;rbol geneal&oacute;gico al grafo.
 * Cada nodo en el grafo representa un integrante y las aristas representan las relaciones padre-hijo.
 * El linaje se utiliza para identificar el tipo de grafo a generar.
 *
 * @return El grafo generado.
 */
    public Graph generarGrafo() {
        graph = new SingleGraph(linaje);
        graph = agregarNodoGrafico(graph, raiz);
        return graph;
    }
/**
 * Agrega un nodo y sus descendientes al grafo de forma recursiva.
 *
 * Este m&eacute;todo agrega un nodo al grafo, utilizando el hash del integrante como identificador. 
 * Tambi&eacute;n agrega aristas dirigidas hacia los hijos del nodo. La funci&oacute;n se llama recursivamente para
 * agregar todos los descendientes del nodo.
 *
 * @param graph El grafo al que se agregar&aacute;n los nodos.
 * @param nodo El nodo del &aacute;rbol que se agregar&aacute; al grafo.
 */
    private Graph agregarNodoGrafico(Graph graph, NodoArbol nodo) {
        if (nodo == null) {
            return graph;
        }

        String idNodo = Integer.toString(nodo.getIntegrante().getHash());
        String nombreNodo = nodo.getIntegrante().getIdentificadorUnico();

        // Agregar nodo al grafo si no existe
        if (graph.getNode(idNodo) == null) {
            Node graphNode = graph.addNode(idNodo);
            graphNode.setAttribute("ui.label", nombreNodo);
        }

        // Agregar hijos y sus conexiones
        Nodo actual = nodo.getHijos().getInicio();
        while (actual != null) {
            NodoArbol hijo = new NodoArbol((Integrante)actual.getInfo());
            //NodoArbol hijo = (NodoArbol) actual.getInfo();
            agregarNodoGrafico(graph, hijo); // Agrega al nodo hijo
            
            String idHijo = Integer.toString(hijo.getIntegrante().getHash());
            String nombreHijo = hijo.getIntegrante().getNombreCompleto();

            // Agregar la arista entre el nodo actual y su hijo
            String edgeId = idNodo + "->" + idHijo;
            if (graph.getEdge(edgeId) == null) {
                graph.addEdge(edgeId, idNodo, idHijo, true); // Arista dirigida
            }
            actual = actual.getSiguiente();
        }
        return graph;
    }
/**
 * Imprime en consola los hijos de un nodo dado.
 *
 * Este m&eacute;todo recorre la lista de hijos de un nodo y imprime en consola la información de cada hijo.
 * Se utiliza principalmente para depuración y verificaci&oacute;n.
 *
 * @param padre El nodo cuyos hijos se imprimir&aacute;n.
 */ 
    private void verPadre(NodoArbol padre){
        Nodo actual = new Nodo(padre.getHijos().getInicio());
        while(actual != null){
            System.out.println(actual.getInfo().toString());
            actual = actual.getSiguiente();
        }
    }
/**
 * Agrega hijos a un nodo padre a partir de una tabla hash de integrantes.
 *
 * Este m&eacute;todo itera sobre los integrantes de la tabla hash y verifica si cada integrante es hijo del nodo padre.
 * Si es así, se agrega como hijo del nodo padre.
 *
 * @param padre El nodo padre al que se agregarán los hijos.
 * @param hashTable La tabla hash de integrantes.
 * @return El nodo padre con los hijos agregados.
 */        
    private NodoArbol agregarHijos(NodoArbol padre, HashTable hashTable) {
        for (int i = 0; i < hashTable.getHashSize(); i++) {
            Integrante aux = hashTable.obtenerIntegrante(i);
            String nombrePadre = aux.getPadre();
            System.out.println("-------------"+aux.getNombre()+" ,cuyo padre es: "+nombrePadre+"----------");
            if (esHijoDe(nombrePadre, hashTable)) {
//                Integrante hijo = aux;
                padre.agregarHijo(aux);
                this.verPadre(padre);
                //return aux;
            }else{
                System.out.println("skippeado");
            }
        }
        return padre;
    }
/**
 * Verifica si un integrante es hijo de otro integrante.
 *
 * Este m&eacute;todo compara el identificador &uacute;nico, el mote o el nombre completo del integrante con el nombre del padre.
 * Si hay una coincidencia, se considera que el integrante es hijo del padre.
 *
 * @param nombrePadre El nombre del padre.
 * @param hashTable La tabla hash de integrantes.
 * @return `true` si el integrante es hijo del padre, `false` en caso contrario.
 */
    private boolean esHijoDe(String nombrePadre, HashTable hashTable) {

        for (int i = 0; i < hashTable.getHashSize(); i++) {
            Integrante aux = hashTable.obtenerIntegrante(i);
            if (aux.getIdentificadorUnico().equalsIgnoreCase(nombrePadre)) {
                System.out.println("coincide id unico. "+aux.getIdentificadorUnico()+"="+nombrePadre);
                return true;
            } else if (aux.getMote() != null) {
                if (aux.getMote().equalsIgnoreCase(nombrePadre)) {
                    System.out.println("coincide mote. "+aux.getMote()+"="+nombrePadre);
                    return true;
                }
            } else if (aux.getNombreCompleto().equalsIgnoreCase(nombrePadre)) {
                System.out.println("coincide nombre completo. "+aux.getNombreCompleto()+"="+nombrePadre);
                return true;
            }else{
                return false;
            }           
        }
        return false;
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
    /**
     * @return the graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

}
