/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolGenealogico;

/**
 * Una tabla hash que almacena y recupera objetos de tipo Integrante.
 * 
 * La función hash calcula un c&oacute;digo hash basado en el nombre y el numeral de un Integrante.
 * Las colisiones se manejan utilizando sondeo lineal.
 * @author aiannelli
 */
public class HashTable {

    private Lista[] array;
    private int hashSize;

    public HashTable(int hashSize) {
        this.array = new Lista[hashSize];
        this.hashSize = hashSize;

        // Inicizaliza las listas en cada posicion
        for (int i = 0; i < hashSize; i++) {
            array[i] = new Lista();
        }
    }

    /**
     * @return the array
     */
    public Lista[] getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(Lista[] array) {
        this.array = array;
    }

    /**
     * @return the hashSize
     */
    public int getHashSize() {
        return hashSize;
    }

/**
 * Crea una nueva tabla hash con el tamano especificado.
 *
 * @param hashSize El tamano deseado para la tabla hash.
 */
    public void setHashSize(int hashSize) {
        this.hashSize = hashSize;
    }

    // Calcula el hash segun los caracteres del nombre
    /**
 * Calcula el c&oacute;digo hash para un Integrante basado en su nombre y numeral.
 *
 * @param name El nombre del Integrante.
 * @param numeral El numeral del Integrante.
 * @return El c&oacute;digo hash calculado.
 */
    public int hashCode1(String name, String numeral) {
        int clave;
        name = name.toLowerCase();
        numeral = numeral.toLowerCase();
        String junto = name + numeral;
        clave = getAsciiValue1(junto) % hashSize;
        return clave;
    }

 /**
 * Calcula la suma de los valores ASCII de los caracteres en una cadena.
 *
 * Esta funci&oacute;n itera sobre cada car&aacute;cter de la cadena, convierte su valor a un n&uacute;mero entero 
 * correspondiente a su c&oacute;digo ASCII, y acumula estos valores en una suma total.
 *
 * @param palabra La cadena de caracteres a procesar.
 * @return La suma total de los valores ASCII de los caracteres en la cadena.
 */
    public int getAsciiValue1(String palabra) {
        int suma = 0;

        for (int i = 0; i < palabra.length(); i++) {
            char character = palabra.charAt(i);
            int ascii = (int) character;
            suma += ascii;
        }
        return suma;
    }

    /**
 * Imprime el contenido de la tabla hash en la consola.
 *
 * Recorre cada posici&oacute;n de la tabla hash y, si la lista en esa posici&oacute;n no est&aacute; vac&iacute;a, 
 * imprime el nombre completo del primer elemento de la lista (asumiendo que todos los elementos son de tipo Integrante).
 */
    public void printHashTable() {
        for (int i = 0; i < hashSize; i++) {
            if (!array[i].esVacia()) {
                Lista lista = array[i];
                Object contenido = lista.buscarPorIndice(0);
                Integrante miembro = (Integrante) contenido;
                System.out.println(miembro.getNombreCompleto());
            } else {
                System.out.println("vacia");
            }
        }
    }

    // Inserta un integrante en la tabla hash
    /**
    * Inserta un Integrante en la tabla hash.
    *
    * @param value El Integrante a insertar.
    */
    public void insertInHashtable(Integrante value) {

        String name = value.getNombre();
        String numeral = value.getNumeral();

        int key = hashCode1(name, numeral);

        Lista sublista = new Lista();
        sublista.insertarUltimo(value);        
        Lista valorArreglo = array[key];

        while (!valorArreglo.esVacia()) {
            key = (key + 1) % hashSize;
            valorArreglo = array[key];
        }
        valorArreglo.insertarUltimo(value);
    }
    /**
 * Obtiene el primer Integrante de la lista en la posici&oacute;n indicada de la tabla hash.
 *
 * Esta funci&oacute;n asume que cada posici&oacute;n de la tabla hash contiene una lista enlazada,
 * y devuelve el primer elemento (el "inicio") de esa lista, siempre y cuando no est&eacute; vac&iacute;a.
 * 
 * @param indice El &iacute;ndice de la posici&oacute;n en la tabla hash.
 * @return El primer Integrante de la lista en la posici&oacute;n indicada, o null si la lista est&aacute; vac&iacute;a.
 */
    public Integrante obtenerIntegrante(int indice){
        return (Integrante) array[indice].getInicio().getInfo();
    }
/**
 * Reemplaza el primer elemento de la lista en la posici&oacute;n indicada de la tabla hash.
 *
 * Esta funci&oacute;n asume que cada posici&oacute;n de la tabla hash contiene una lista enlazada.
 * El nuevo Integrante se convierte en el nuevo inicio de la lista, reemplazando al elemento existente.
 * @param indice El &iacute;ndice de la posici&oacute;n en la tabla hash.
 * @param integrante El nuevo Integrante a colocar al inicio de la lista.
 */    
    public void devolverIntegrante(int indice, Integrante integrante){
        array[indice].setInicio(integrante);
    }

    // buscar un integrante por nombre completo
//    public Integrante buscar(String nombre) {
//        int key = hashCode1(nombre); // por cambiar
//        Lista lista = array[key];
//        Nodo actual = lista.getInicio();
//        while (actual != null) {
//            Integrante integrante = (Integrante) actual.getInfo();
//            if (integrante.getNombreCompleto().equalsIgnoreCase(nombre)) {
//                return integrante;
//            }
//            actual = actual.getSiguiente();
//        }
//        return null; // No encontrado
//    }
}
