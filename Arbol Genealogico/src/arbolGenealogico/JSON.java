package arbolGenealogico;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Set;
import java.util.HashSet;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Adrian
 */
public class JSON {

    private String archivo;

    public JSON(String rutaArchivo) {
        this.archivo = rutaArchivo;
    }

    public Arbol cargarDesdeJSON() {
        JSONParser parser = new JSONParser();
        Arbol arbol = new Arbol();
        Set<String> uniqueNamesAndNumbers = new HashSet<>();
        Set<String> uniqueMotes = new HashSet<>();
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
                    System.out.println("------------");
                    System.out.println(miembro);
                    System.out.println("------------");
                    
                    for (Object miembroData : miembro.keySet()) {
                        System.out.println("    " + (String) miembroData);
                        JSONArray detalles = validarArr(miembro.get(miembroData), "La data de un miembro no es un array");

                        String nombreCompleto = (String) miembroData;
                        Integrante integrante = crearIntegrante(nombreCompleto, detalles, uniqueNamesAndNumbers, uniqueMotes);
                        
                        String rawPadre = integrante.getPadre();
                        String padreResuelto = resolverPadre(rawPadre, arbol, integrante.getNumeral());
                        
                        // Verifica si el padre resuelto es el propio nodo
                        if (padreResuelto != null && padreResuelto.equals(nombreCompleto)) {
                            System.err.println("Error: El nodo " + nombreCompleto + " no puede ser su propio padre.");
                            continue; // Saltar este nodo para evitar ciclos
                        }

                        // Verifica si el nodo es la raiz
                        if ("[Unknown]".equalsIgnoreCase(rawPadre)) {
                            if (!raizDefinida) {
                                // Si no hay raiz, este nodo sera la raiz
                                arbol.insertar(integrante, null);
                                nodosDefinidos.add(integrante.getNombreCompleto());
                                raizDefinida = true;
                            }else{
                                throw new IllegalArgumentException("Ya existe una raiz definida: " + arbol.getRaiz().getIntegrante().getNombreCompleto());
                            }
                        } else if (nodosDefinidos.contains(padreResuelto)){
                            // Insertar si el padre ya esta definido
                            arbol.insertar(integrante, padreResuelto);
                            nodosDefinidos.add(integrante.getNombreCompleto());
                        } else {
                            // Si el padre no está definido, guardar como pendiente
                            System.out.println("Advertencia: Padre no encontrado: " + integrante.getPadre());
                            pendientes.insertarUltimo(integrante);
                        }
                    }
                }
            }

            // Reintentar insertar los nodos pendientes
            procesarPendientes(pendientes, arbol, nodosDefinidos);
            if (!raizDefinida){
                throw new IllegalArgumentException("El archivo JSON no tiene un nodo raiz.");
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error al parsear el archivo JSON: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de formato en el archivo JSON: " + e.getMessage());
        }
        return arbol;
    }

    // Método para procesar los nodos pendientes
    private void procesarPendientes(Lista pendientes, Arbol arbol, Set<String> nodosDefinidos) {
        boolean huboCambios;
        do {
            huboCambios = false;
            Nodo actual = pendientes.getInicio();

            while (actual != null) {
                Integrante pendiente = (Integrante) actual.getInfo();
                String padreResuelto = resolverPadre(pendiente.getPadre(), arbol, pendiente.getNumeral());
                
                // Verifica si el padre ya esta definido
                if (padreResuelto != null && nodosDefinidos.contains(padreResuelto)) {
                    // Intentar insertar si el padre ya está definido
                    try {
                        arbol.insertar(pendiente, padreResuelto);
                        nodosDefinidos.add(pendiente.getNombreCompleto());
                        pendientes.eliminar(pendiente); // Eliminar si fue insertado correctamente
                        huboCambios = true;
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error al insertar nodo pendiente: " + pendiente.getNombreCompleto());
                    }
                } else if (!nodosDefinidos.contains(padreResuelto)){
                        System.err.println("El padre de " + pendiente.getNombreCompleto() + " (" + pendiente.getPadre() + ") no existe. Nodo eliminado.");
                        pendientes.eliminar(pendiente);
                }
                actual = actual.getSiguiente();
            }
        } while (huboCambios && !pendientes.esVacia());

        // Reportar los nodos restantes
        if (!pendientes.esVacia()) {
            System.err.println("No se pudieron insertar algunos nodos debido a padres inexistentes.");
            Nodo actual = pendientes.getInicio();
            while (actual != null) {
                Integrante pendiente = (Integrante) actual.getInfo();
                System.err.println("Nodo pendiente: " + pendiente.getNombreCompleto() + ", Padre: " + pendiente.getPadre());
                actual = actual.getSiguiente();
            }
        }
    }

    private String resolverPadre(String rawPadre, Arbol arbol, String numeral){
        if(rawPadre == null || rawPadre.equalsIgnoreCase("[Unknown]")){
            return null;
        }
        
        // Buscar padre por combinacion 
        NodoArbol nodoPadre = buscarNodoPorCombinacion(arbol.getRaiz(), rawPadre, numeral);
        if(nodoPadre != null){
            return nodoPadre.getIntegrante().getNombreCompleto();
        }
        
        // Buscar padre por nombre 
        nodoPadre = buscarNodoPorNombre(arbol.getRaiz(), rawPadre);
        if(nodoPadre != null){
            return nodoPadre.getIntegrante().getNombreCompleto();
        }
      
        // Buscar padre por mote
        nodoPadre = buscarNodoPorMote(arbol.getRaiz(), rawPadre);
        if(nodoPadre != null){
            return nodoPadre.getIntegrante().getNombreCompleto();
        }
        
        
        
        // Si no se encuentra el padre, devolver el rawPadre como está
        return rawPadre;
    }

    private NodoArbol buscarNodoPorNombre(NodoArbol nodo, String nombre){
        if(nodo == null) return null;
        
        if (nodo.getIntegrante().getNombreCompleto().equalsIgnoreCase(nombre)) return nodo;
        
        Nodo actual = nodo.getHijos().getInicio();
        
        while(actual != null){
            NodoArbol encontrado = buscarNodoPorNombre((NodoArbol) actual.getInfo(), nombre);
            if(encontrado != null) return encontrado;
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    private NodoArbol buscarNodoPorMote(NodoArbol nodo, String mote){
        if(nodo == null) return null;
        if(nodo.getIntegrante().getMote() != null && nodo.getIntegrante().getMote().equalsIgnoreCase(mote)) return nodo;
         
        Nodo actual = nodo.getHijos().getInicio();
        while(actual !=null){
            NodoArbol encontrado = buscarNodoPorMote((NodoArbol) actual.getInfo(), mote);
            if(encontrado != null) return encontrado;
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    private NodoArbol buscarNodoPorCombinacion(NodoArbol nodo, String nombrePadre, String numeral){
        if(nodo == null) return null;
        String nombreCombinado = nodo.getIntegrante().getNombreCompleto() + ", " + nodo.getIntegrante().getNumeral() + " of his name";
        

       
        if(nombreCombinado.equalsIgnoreCase(nombrePadre)) return nodo;
        
        Nodo actual = nodo.getHijos().getInicio();
        while(actual != null){
            NodoArbol encontrado = buscarNodoPorCombinacion((NodoArbol) actual.getInfo(), nombrePadre, numeral);
            if(encontrado != null) {
                return encontrado;
            }
            actual = actual.getSiguiente();
            

        }
        return null;
    }
    /**
     * @throws IllegalArgumentException Valida que el objeto procesado es un
     * JSONArray.
     */
    private JSONArray validarArr(Object objeto, String mensaje) {
        if (!(objeto instanceof JSONArray)) {
            throw new IllegalArgumentException("Formato incorrecto: " + mensaje);
        }
        return (JSONArray) objeto;
    }

    /**
     * @throws IllegalArgumentException Valida que el objeto procesado es un
     * JSONArray.
     */
    private JSONObject validarObj(Object objeto, String mensaje) {
        if (!(objeto instanceof JSONObject)) {
            throw new IllegalArgumentException("Formato incorrecto: " + mensaje);
        }
        return (JSONObject) objeto;
    }

    private Integrante crearIntegrante(String nombreCompleto, JSONArray detalles, Set<String> uniqueNamesAndNumbers, Set<String> uniqueMotes) {
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
                    if(integrante.getPadre() == null){
                        integrante.setPadre(validateNonEmptyString(value, "Born to"));
                    } else {
                        integrante.setMadre(validateNonEmptyString(value, "Born to"));
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
                    integrante.setColorOjos(validateNonEmptyString(value, "Of hair"));
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
        String uniqueIdentifier = nombreCompleto + ", " + integrante.getNumeral();
        if(!uniqueNamesAndNumbers.add(uniqueIdentifier)){
            throw new IllegalArgumentException("El nombre completo y el numero deben ser unicos: " + uniqueIdentifier);
        }
        return integrante;
    }

    private String validateNumeral(Object value) {
    if (!(value instanceof String) || ((String) value).isEmpty()) {
        throw new IllegalArgumentException("El campo 'Of his name' debe ser una cadena no vacía.");
    }
    return (String) value;
    }

    private String validateBornTo(Object value) {
    if (!(value instanceof String) || ((String) value).isEmpty()) {
        return null; // Si no hay padre, devuelve null
    }
    return (String) value;
    }

    private String validateUniqueMote(Object value, Set<String> uniqueMotes) {
    if (!(value instanceof String) || ((String) value).isEmpty()) {
        throw new IllegalArgumentException("El mote debe ser una cadena no vacía.");
    }
    if (!uniqueMotes.add((String) value)) {
        throw new IllegalArgumentException("El mote debe ser único: " + value);
    }
    return (String) value;
    }

    private String validateNonEmptyString(Object value, String fieldName) {
        if (!(value instanceof String) || ((String) value).isEmpty()) {
            throw new IllegalArgumentException("El campo '" + fieldName + "' debe ser una cadena no vacía.");
        }
        return (String) value;
    }
    
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
}
