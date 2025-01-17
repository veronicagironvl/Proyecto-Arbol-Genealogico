
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolGenealogico;

import java.util.Set;
import java.util.HashSet;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Adrian
 */
public class JSON {

    private String archivo;

    public JSON(String rutaArchivo) {
        this.archivo = rutaArchivo;
    }
/**
 * Carga datos de un archivo JSON y construye un &aacute;rbol genealógico.
 *
 * Esta funci&oacute;n lee un archivo JSON que contiene informaci&oacute;n sobre un &aacute;rbol geneal&oacute;gico, 
 * crea una estructura de &aacute;rbol y una tabla hash de los integrantes.
 * 
 * Lee el archivo JSON y parsea los datos.
 * Itera sobre los miembros de cada linaje.
 * Crea objetos Integrante y los agrega a una lista.
 * Resuelve las relaciones padre-hijo y construye el &aacute;rbol.
 * Crea una tabla hash de los integrantes para un acceso m&aacute;s r&aacute;pido.
 *
 * @return Una tabla hash de los integrantes cargados.
 * @throws IllegalArgumentException Si el formato del JSON es inválido.
 */
    public HashTable cargarDesdeJSON() {
        JSONParser parser = new JSONParser();
        Arbol arbol = new Arbol();
        Lista listaIntegrantes = new Lista(); // lista de integrantes 

        Set<String> nodosDefinidos = new HashSet<>(); // Nombre de nodos ya procesados
        Lista pendientes = new Lista(); // Nodos con padres no encontrados
        boolean raizDefinida = false; // identificador de si existe una raiz definida

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(archivo));

            for (Object casaNombre : jsonObject.keySet()) {
                String linaje = (String) casaNombre;
                arbol.setLinaje(linaje); // Asigna el nombre del linaje
                System.out.println("Linaje cargado: " + linaje);

                JSONArray miembros = validarArr(jsonObject.get(casaNombre), "La casa no contiene un array");

                for (Object miembroObj : miembros) {
                    JSONObject miembro = validarObj(miembroObj, "Los miembros no son objetos JSON");

                    Integrante integrante = new Integrante();

                    for (Object miembroData : miembro.keySet()) {

                        String nombreCompleto = (String) miembroData;
                        integrante.setNombreCompleto(nombreCompleto);

                        JSONArray detalles = validarArr(miembro.get(miembroData), "La data de un miembro no es un array");

                        integrante = procesarDetalles(nombreCompleto, detalles);

                        listaIntegrantes.insertarUltimo(integrante);

//
//                        String rawPadre = integrante.getPadre();
//                        String padreResuelto = resolverPadre(rawPadre, arbol, integrante.getNumeral());
                        // Verifica si el padre resuelto es el propio nodo
//                        if (padreResuelto != null && padreResuelto.equals(nombreCompleto)) {
//                            System.err.println("Error: El nodo " + nombreCompleto + " no puede ser su propio padre.");
//                            continue; // Saltar este nodo para evitar ciclos
//                        }
                        // Verifica si el nodo es la raiz
//                        if ("[Unknown]".equalsIgnoreCase(rawPadre)) {
//                            if (!raizDefinida) {
//                                // Si no hay raiz, este nodo sera la raiz
//                                arbol.insertar(integrante, null);
//                                nodosDefinidos.add(integrante.getNombreCompleto());
//                                raizDefinida = true;
//                            } else {
//                                throw new IllegalArgumentException("Ya existe una raiz definida: " + arbol.getRaiz().getIntegrante().getNombreCompleto());
//                            }
//                        } else if (nodosDefinidos.contains(padreResuelto)) {
//                            // Insertar si el padre ya esta definido
//                            arbol.insertar(integrante, padreResuelto);
//                            nodosDefinidos.add(integrante.getNombreCompleto());
//                        } else {
//                            // Si el padre no está definido, guardar como pendiente
//                            System.out.println("Advertencia: Padre no encontrado: " + integrante.getPadre());
//                            pendientes.insertarUltimo(integrante);
//                        }
                    }
                }
            }

            // Reintentar insertar los nodos pendientes
//            procesarPendientes(pendientes, arbol, nodosDefinidos);
//            if (!raizDefinida) {
//                throw new IllegalArgumentException("El archivo JSON no tiene un nodo raiz.");
//            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error al parsear el archivo JSON: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de formato en el archivo JSON: " + e.getMessage());
        }

        HashTable hashTable = establecerHashes(listaIntegrantes);
        
        return hashTable;
    }

    // Método para procesar los nodos pendientes
//    private void procesarPendientes(Lista pendientes, Arbol arbol, Set<String> nodosDefinidos) {
//        boolean huboCambios;
//        do {
//            huboCambios = false;
//            Nodo actual = pendientes.getInicio();
//
//            while (actual != null) {
//                Integrante pendiente = (Integrante) actual.getInfo();
//                String padreResuelto = resolverPadre(pendiente.getPadre(), arbol, pendiente.getNumeral());
//
//                // Verifica si el padre ya esta definido
//                if (padreResuelto != null && nodosDefinidos.contains(padreResuelto)) {
//                    // Intentar insertar si el padre ya está definido
//                    try {
//                        arbol.insertar(pendiente, padreResuelto);
//                        nodosDefinidos.add(pendiente.getNombreCompleto());
//                        pendientes.eliminar(pendiente); // Eliminar si fue insertado correctamente
//                        huboCambios = true;
//                    } catch (IllegalArgumentException e) {
//                        System.err.println("Error al insertar nodo pendiente: " + pendiente.getNombreCompleto());
//                    }
//                } else if (!nodosDefinidos.contains(padreResuelto)) {
//                    System.err.println("El padre de " + pendiente.getNombreCompleto() + " (" + pendiente.getPadre() + ") no existe. Nodo eliminado.");
//                    pendientes.eliminar(pendiente);
//                }
//                actual = actual.getSiguiente();
//            }
//        } while (huboCambios && !pendientes.esVacia());
//
//        // Reportar los nodos restantes
//        if (!pendientes.esVacia()) {
//            System.err.println("No se pudieron insertar algunos nodos debido a padres inexistentes.");
//            Nodo actual = pendientes.getInicio();
//            while (actual != null) {
//                Integrante pendiente = (Integrante) actual.getInfo();
//                System.err.println("Nodo pendiente: " + pendiente.getNombreCompleto() + ", Padre: " + pendiente.getPadre());
//                actual = actual.getSiguiente();
//            }
//        }
//    }
//
//    }
 /**
 * Busca un nodo en el &aacute;rbol por su nombre completo.
 *
 * Recorre el &aacute;rbol para encontrar el nodo cuyo integrante tenga el nombre completo especificado.
 *
 * @param nodo El nodo actual en la b&uacute;squeda.
 * @param nombre El nombre completo a buscar.
 * @return El nodo encontrado, o null si no se encuentra.
 */
    private NodoArbol buscarNodoPorNombre(NodoArbol nodo, String nombre) {
        if (nodo == null) {
            return null;
        }

        if (nodo.getIntegrante().getNombreCompleto().equalsIgnoreCase(nombre)) {
            return nodo;
        }

        Nodo actual = nodo.getHijos().getInicio();

        while (actual != null) {
            NodoArbol encontrado = buscarNodoPorNombre((NodoArbol) actual.getInfo(), nombre);
            if (encontrado != null) {
                return encontrado;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
/**
 * Busca un nodo en el &aacute;rbol por su mote.
 *
 * Recorre el &aacute;rbol para encontrar el nodo cuyo integrante tenga el mote especificado.
 *
 * @param nodo El nodo actual en la b&uacute;squeda.
 * @param mote El mote a buscar.
 * @return El nodo encontrado, o null si no se encuentra.
 */
    private NodoArbol buscarNodoPorMote(NodoArbol nodo, String mote) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getIntegrante().getMote() != null && nodo.getIntegrante().getMote().equalsIgnoreCase(mote)) {
            return nodo;
        }

        Nodo actual = nodo.getHijos().getInicio();
        while (actual != null) {
            NodoArbol encontrado = buscarNodoPorMote((NodoArbol) actual.getInfo(), mote);
            if (encontrado != null) {
                return encontrado;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
/**
 * Busca un nodo en el &aacute;rbol por su nombre completo y numeral.
 *
 * Recorre el &aacute;rbol para encontrar el nodo cuyo integrante tenga el nombre completo y numeral especificados.
 *
 * @param nodo El nodo actual en la b&uacute;squeda.
 * @param nombrePadre El nombre completo y numeral del padre del nodo buscado.
 * @return El nodo encontrado, o null si no se encuentra.
 */
    private NodoArbol buscarNodoPorCombinacion(NodoArbol nodo, String nombrePadre, String numeral) {
        if (nodo == null) {
            return null;
        }
        String nombreCombinado = nodo.getIntegrante().getNombreCompleto() + ", " + nodo.getIntegrante().getNumeral() + " of his name";

        if (nombreCombinado.equalsIgnoreCase(nombrePadre)) {
            return nodo;
        }

        Nodo actual = nodo.getHijos().getInicio();
        while (actual != null) {
            NodoArbol encontrado = buscarNodoPorCombinacion((NodoArbol) actual.getInfo(), nombrePadre, numeral);
            if (encontrado != null) {
                return encontrado;
            }
            actual = actual.getSiguiente();

        }
        return null;
    }

/**
 * Valida si un objeto es un arreglo JSON.
 *
 * @param objeto El objeto a validar.
 * @param mensaje El mensaje de error a lanzar si la validaci&oacute;n falla.
 * @return El objeto validado como JSONArray.
 * @throws IllegalArgumentException Si el objeto no es un JSONArray.
 */
    private JSONArray validarArr(Object objeto, String mensaje) {
        if (!(objeto instanceof JSONArray)) {
            throw new IllegalArgumentException("Formato incorrecto: " + mensaje);
        }
        return (JSONArray) objeto;
    }

/**
 * Valida si un objeto es un objeto JSON.
 *
 * @param objeto El objeto a validar.
 * @param mensaje El mensaje de error a lanzar si la validaci&oacute;n falla.
 * @return El objeto validado como JSONObject.
 * @throws IllegalArgumentException Si el objeto no es un JSONObject.
 */
    private JSONObject validarObj(Object objeto, String mensaje) {
        if (!(objeto instanceof JSONObject)) {
            throw new IllegalArgumentException("Formato incorrecto: " + mensaje);
        }
        return (JSONObject) objeto;
    }
/**
 * Procesa los detalles de un integrante a partir de un objeto JSON.
 *
 * Extrae informaci&oacute;n como el numeral, padres, mote, t&iacute;tulos, etc., del objeto JSON y la asigna al objeto Integrante.
 *
 * @param nombreCompleto El nombre completo del integrante.
 * @param detalles Los detalles del integrante en formato JSON.
 * @return El objeto Integrante creado.
 * @throws IllegalArgumentException Si ocurre un error al procesar los detalles.
 */
    private Integrante procesarDetalles(String nombreCompleto, JSONArray detalles) {
        Integrante integrante = new Integrante();
        integrante.setNombreCompleto(nombreCompleto);
        
        for (Object atributoObj : detalles) {
            JSONObject atributo = validarObj(atributoObj, "los detalles no son objetos JSON");
            String key = (String) atributo.keySet().iterator().next(); // La clave del atributo (e.g., "Of his name")
            Object value = atributo.get(key); // El valor asociado a la clave

            switch (key) {
                case "Of his name":
                    integrante.setNumeral(validateNonEmptyString(value, "Of his name"));
                    break;
                case "Born to":
                    if (integrante.getPadre() == null) {
                        integrante.setPadre(validateBornTo(value));
                    } else {
                        integrante.setMadre(validateBornTo(value));
                    }
                    break;
                case "Known throughout as":
                    integrante.setMote(validateNonEmptyString(value, "Known throughout as"));
                    break;
                case "Held title":
                    integrante.setTitulo(validateNonEmptyString(value, "Held title"));
                    break;
                case "Wed to":
                    integrante.setEsposa(validateNonEmptyString(value, "Wed to"));
                    break;
                case "Of eyes":
                    integrante.setColorOjos(validateNonEmptyString(value, "Of eyes"));
                    break;
                case "Of hair":
                    integrante.setColorPelo(validateNonEmptyString(value, "Of hair"));
                    break;
                case "Father to":
                    integrante.setHijos(validateChildren(value));
                    break;
                case "Notes":
                    integrante.setNotas(validateNonEmptyString(value, "Notes"));
                    break;
                case "Fate":
                    integrante.setDestino(validateNonEmptyString(value, "Fate"));
                    break;
                default:
                    throw new IllegalArgumentException("Campo desconocido: " + key);
            }
        }
        return integrante;
    }
/**
 * Valida el campo "Born to" de un integrante.
 *
 * Verifica que el valor del campo sea una cadena no vac&iacute;a.
 *
 * @param value El valor del campo "Born to".
 * @return El valor validado.
 * @throws IllegalArgumentException Si el valor no es una cadena no vac&iacute;a.
 */
    private String validateBornTo(Object value) {
        if (!(value instanceof String) || ((String) value).isEmpty()) {
            throw new IllegalArgumentException("El campo 'Born to' debe ser una cadena no vacía.");
        }
        return (String) value;
    }
/**
 * Valida un campo de texto no vac&iacute;o.
 *
 * Verifica que el valor del campo sea una cadena no vac&iacute;a.
 *
 * @param value El valor del campo.
 * @param fieldName El nombre del campo.
 * @return El valor validado.
 * @throws IllegalArgumentException Si el valor no es una cadena no vac&iacute;a.
 */
    private String validateNonEmptyString(Object value, String fieldName) {
        if (!(value instanceof String) || ((String) value).isEmpty()) {
            throw new IllegalArgumentException("El campo '" + fieldName + "' debe ser una cadena no vacía.");
        }
        return (String) value;
    }
/**
 * Valida y procesa la lista de hijos de un integrante.
 *
 * Verifica que el valor del campo "Father to" sea un arreglo de cadenas y crea una lista de hijos.
 *
 * @param value El valor del campo "Father to".
 * @return La lista de hijos.
 * @throws IllegalArgumentException Si el valor no es un arreglo de cadenas no vac&iacute;as.
 */
    private Lista validateChildren(Object value) {
        if (!(value instanceof JSONArray)) {
            throw new IllegalArgumentException("El campo 'Father to' debe ser un array.");
        }

        Lista hijos = new Lista();
        JSONArray children = (JSONArray) value;
        for (Object child : children) {
            if (!(child instanceof String) || ((String) child).isEmpty()) {
                throw new IllegalArgumentException("Cada hijo debe ser una cadena no vacía.");
            }

            hijos.insertarUltimo(child);
        }
        return hijos;
    }
/**
 * Establece el hash para cada integrante en la lista y los inserta en una tabla hash.
 *
 * @param listaIntegrantes La lista de integrantes.
 * @return La tabla hash creada.
 */
    private HashTable establecerHashes(Lista listaIntegrantes) {
        int longitud = listaIntegrantes.longitud();
        HashTable hashtable = new HashTable(longitud);
        
        for (int i = 0; i < longitud; i++) {
            
            Object integrante = listaIntegrantes.buscarPorIndice(i);
            integrante = (Integrante) integrante;
            hashtable.insertInHashtable((Integrante) integrante);            
        }

        return hashtable;
    }

}
