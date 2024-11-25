/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolGenealogico;

/**
 *
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
     * @param hashSize the hashSize to set
     */
    public void setHashSize(int hashSize) {
        this.hashSize = hashSize;
    }

    // Calcula el hash segun los caracteres del nombre
    public int hashCode1(String name, String numeral) {
        int clave;
        name = name.toLowerCase();
        numeral = numeral.toLowerCase();
        String junto = name + numeral;
        clave = getAsciiValue1(junto) % hashSize;
        return clave;
    }

    // Retorna la suma de valores ascii de una palabra
    public int getAsciiValue1(String palabra) {
        int suma = 0;

        for (int i = 0; i < palabra.length(); i++) {
            char character = palabra.charAt(i);
            int ascii = (int) character;
            suma += ascii;
        }
        return suma;
    }

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
    
    public Integrante obtenerIntegrante(int indice){
        return (Integrante) array[indice].getInicio().getInfo();
    }
    
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
