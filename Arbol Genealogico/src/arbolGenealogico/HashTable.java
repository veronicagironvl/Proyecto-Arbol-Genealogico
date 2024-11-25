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
        
        // Inicizaliza las listas en cada posicion
        for( int i = 0; i < hashSize; i++){
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
    public int hashCode(String nombre) {
        int hash = 0;
        for (int i = 0; i < nombre.length(); i++){
            hash = (31 * hash + nombre.charAt(i)) % hashSize;
        }
        return hash;
    }
    
    public void printHashTable() {
        for (int i = 0; i < hashSize; i++) {
            if (!array[i].esVacia()) {
                System.out.println("key: " + i);
                array[i].toString();
            }

        }
    }
    
    // Inserta un integrante en la tabla hash
    public void insertInHashTable(Integrante value) {
        int key = hashCode(value.getNombreCompleto());
        Lista lista = array[key];
        if(!lista.seEncuentra(value)){
            lista.insertarUltimo(value);
        }
    }
    
   // BUscar un integrante por nombre completo
    public Integrante buscar(String nombre){
        int key = hashCode(nombre);
        Lista lista = array[key];
        Nodo actual = lista.getInicio();
        while(actual != null){
           Integrante integrante = (Integrante) actual.getInfo();
           if(integrante.getNombreCompleto().equalsIgnoreCase(nombre)){
               return integrante;
           }
           actual = actual.getSiguiente();
        }
        return null; // No encontrado
    }
}
