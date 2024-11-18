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

    public void cargarDesdeJSON() {
        JSONParser parser = new JSONParser();
        Set<String> uniqueNamesAndNumbers = new HashSet<>();
        Set<String> uniqueMotes = new HashSet<>();

        try {
            // Parsea el archivo JSON
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(archivo));

            // Procesa cada casa y valida que tenga una lista de miembros
            for (Object casaNombre : jsonObject.keySet()) {
                System.out.println((String)casaNombre);
                JSONArray miembros = validarArr(jsonObject.get(casaNombre), "la casa no contiene un array");

                // Procesa y valida el formato de la lista de miembros de la casa
                for (Object miembroObj : miembros) {
                    JSONObject miembro = validarObj(miembroObj, "los miembros no son objetos JSON");

                    // Procesa y valida los datos de cada miembro de la casa
                    for (Object miembroData : miembro.keySet()) {
                        System.out.println("    "+(String)miembroData);
                        JSONArray detalles = validarArr(miembro.get(miembroData), "la data de un miembro no es un array");
                        
                        // Valida e imprime los detalles individuales
                        procesarDetalles(detalles, (String) miembroData, uniqueNamesAndNumbers, uniqueMotes);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error al parsear el archivo JSON: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de formato el archivo JSON: " + e.getMessage());
        }
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

    private void procesarDetalles(JSONArray detalles, String nombreCompleto, Set<String>uniqueNamesAndNumbers, Set<String>uniqueMotes) {
        String anterior = null;
        String numeral = null;

        for (Object atributoObj : detalles) {
            JSONObject atributo = validarObj(atributoObj, "los detalles no son objetos JSON");

            String key = (String) atributo.keySet().iterator().next();
            Object value = atributo.get(key);

            switch (key) {
                case "Of his name":
                    numeral = validateNumeral(value);
                    break;
                case "Born to":
                    validateBornTo(value);
                    break;
                case "Known throughout as":
                    validateUniqueMote(value, uniqueMotes);
                    break;
                case "Held title":
                    validateNonEmptyString(value, "Título nobiliario");
                    break;
                case "Wed to":
                    validateNonEmptyString(value, "Esposa");
                    break;
                case "Of eyes":
                case "Of hair":
                    validateNonEmptyString(value, key + " (color)");
                    break;
                case "Father to":
                    validateChildren(value);
                    break;
                case "Notes":
                case "Fate":
                    validateNonEmptyString(value, key);
                    break;
                default:
                    throw new IllegalArgumentException("Campo desconocido: " + key);
            }
        }
        if (numeral != null) {
            String uniqueIdentifier = nombreCompleto + ", " + numeral;
            if (!uniqueNamesAndNumbers.add(uniqueIdentifier)) {
                throw new IllegalArgumentException("El nombre completo y numeral deben ser únicos: " + uniqueIdentifier);
            }
        }
    }

    private static String validateNumeral(Object value) {
        if (!(value instanceof String) || ((String) value).isEmpty()) {
            throw new IllegalArgumentException("El campo 'Of his name' debe ser una cadena no vacía.");
        }
        System.out.println("        "+String.valueOf(value));
        return String.valueOf(value);
    }

    private static void validateBornTo(Object value) {
        if (!(value instanceof String) || ((String) value).isEmpty()) {
            throw new IllegalArgumentException("El campo 'Born to' debe ser una cadena no vacía.");
        }
        System.out.println("        "+String.valueOf(value));
        // Aquí se puede agregar lógica para verificar si el padre/madre existe en el árbol.
    }

    private static void validateUniqueMote(Object value, Set<String> uniqueMotes) {
        if (!(value instanceof String) || ((String) value).isEmpty()) {
            throw new IllegalArgumentException("El mote debe ser una cadena no vacía.");
        }
        if (!uniqueMotes.add((String) value)) {
            throw new IllegalArgumentException("El mote debe ser único: " + value);
        }
        System.out.println("        "+String.valueOf(value));
    }

    private static void validateNonEmptyString(Object value, String fieldName) {
        if (!(value instanceof String) || ((String) value).isEmpty()) {
            throw new IllegalArgumentException("El campo '" + fieldName + "' debe ser una cadena no vacía.");
        }
        System.out.println("        "+String.valueOf(value));
    }

    private static void validateChildren(Object value) {
        if (!(value instanceof JSONArray)) {
            throw new IllegalArgumentException("El campo 'Father to' debe ser un array.");
        }

        JSONArray children = (JSONArray) value;
        for (Object child : children) {
            if (!(child instanceof String) || ((String) child).isEmpty()) {
                throw new IllegalArgumentException("Cada hijo debe ser una cadena no vacía.");
            }
            System.out.println("         - "+child); 
            // Aquí se puede agregar lógica para validar si el hijo existe en el árbol genealógico.
        }
    }
    
}
