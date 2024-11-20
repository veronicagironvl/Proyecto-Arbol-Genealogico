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
    
}
