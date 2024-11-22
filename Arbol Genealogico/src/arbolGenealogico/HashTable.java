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


    public HashTable(int hashSize){
        this.array = new Lista[hashSize];
        this.hashSize = hashSize;
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
    
    public int getAsciiValue(String nombre) {
        int suma = 0;

        for (int i = 0; i < nombre.length(); i++) {
            char character = nombre.charAt(i);
            int ascii = (int) character;
            suma += ascii;
        }
        return suma;
    }
        
    public int hashCode(String nombre) {
        int clave;
        nombre = nombre.toLowerCase();
        clave = getAsciiValue(nombre) % getHashSize();
        return clave;
    }
    
    public void printHashTable() {
        for (int i = 0; i < hashSize; i++) {
            if (array[i] != null) {
                System.out.println("key: " + i);
                array[i].toString();
            }

        }
    }
    
    public void insertInHashtable(Integrante value) {

        String name = value.getNombreCompleto();
        int key = hashCode(name);
        Lista subLista = new Lista();
        subLista.insertarUltimo(name);
        Lista valorArreglo = getArray()[key];
        if (valorArreglo != null) {
            valorArreglo.insertarUltimo(name);
        } else {
            getArray()[key] = subLista;
        }
    }
    
}
