package arbolGenealogico;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Adrian
 */
public class Lista {
    private Nodo inicio;
    private int iN;
    
    /**
     * Constructor por defecto que crea una lista vac&iacute;a.
     */
    public Lista () {
    this.inicio = null;
    this.iN = 0;
    }
    
    /**
     * Constructor que crea una lista con un solo nodo, cuyo dato es el valor de `estacion`.
     * @param estacion El dato a almacenar en el primer nodo.
     */
    
    public Lista (String estacion) {
    this.inicio = new Nodo(estacion);
    this.iN = 0;
    }

//----------getters y setters--------------
    
    /**
     * Devuelve el nodo inicial de la lista.
     * @return El nodo inicial.
     */
    public Nodo getInicio() {
        return inicio;
    }

    /**
     * Establece el nodo inicial de la lista.
     * @param inicio El nuevo nodo inicial.
     */
    public void setInicio(String inicio) {
        
        this.inicio = new Nodo(inicio);
    }
    
    /**
     * Establece el n&uacute;mero de elementos en la lista.
     * @param iN El nuevo tamano de la lista.
     */
    public void setiN(int iN) {
        this.iN = iN;
    }

    /**
     * Devuelve el n&uacute;mero de elementos en la lista.
     * @return El tamano de la lista.
     */
    public int getiN() {
        return iN;
    }
    
    //A continuación se establecen los métodos para una lista simple, como verificar si es vacia, buscar, insertar y eliminar
    
    /**
     * Verifica si la lista est&aacute; vac&iacute;a.
     * @return `true` si la lista est&aacute; vac&iacute;a, `false` en caso contrario.
     */
    public boolean esVacia(){
        return inicio == null;
    }
    

    /**
     * Busca y devuelve el &uacute;ltimo nodo de la lista.
     * @return El &uacute;ltimo nodo de la lista, o `null` si la lista est&aacute; vac&iacute;a.
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = this.getInicio();
        
        // Agrega cada elemento a la cadena de texto
        sb.append("[");
        while (actual != null) {
            sb.append(actual.getInfo());
            actual = actual.getSiguiente();
            if (actual != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    
   
    public Nodo buscarUltimo(){
    Nodo aux = inicio;
    if (getInicio()==null){
        return null;
    }
    while(aux.getSiguiente()!=null){
            aux=aux.getSiguiente();
    }
    return aux;
    }
    
    /**
     * Inserta un nuevo nodo al final de la lista.
     * @param palabra El dato a almacenar en el nuevo nodo.
     */
    public void insertarUltimo(String palabra){  
        
        Nodo ult = buscarUltimo();                    
        Nodo nuevo = new Nodo(palabra); 
        if(ult == null){
           inicio = nuevo;
        }else{
            ult.setSiguiente(nuevo);
        }
        iN++;   
    }
    /**
     * Busca un nodo con el dato especificado.
     * @param palabra El dato a buscar.
     * @return `true` si el dato se encuentra en la lista, `false` en caso contrario.
     */
        public boolean seEncuentra(String palabra) {
        Nodo actual = inicio;

        // Lista vacia
        if (esVacia()) {
            return false;
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getInfo().equals(palabra)) {
                actual = actual.getSiguiente();
            }

            // Si se encuentra la parada, retornar verdadero
            return actual != null;
        }
    }
    /**
     * Elimina el primer nodo de la lista.
     * @return El nodo eliminado, o `null` si la lista est&aacute; vac&iacute;a.
     */    
    public Nodo eliminarPrimero(){
        if (esVacia()) {
            return null;
        }
        Nodo aux = inicio;
        inicio = aux.getSiguiente();
        aux.setSiguiente(null);
        iN--;
        return aux;
    }
    /**
     * Elimina el primer nodo que contiene la parada a eliminar.
     * @param parada Es la parada que se desea eliminar.
     */    
    public void eliminar(String parada) {
        Nodo actual = inicio;

        // Lista vacía
        if (esVacia()) {
            System.out.println("La lista esta vacia.");
        } else if (iN < 3){
            if (inicio.getInfo().equals(parada)){
                eliminarPrimero();
            } else {
                inicio.setSiguiente(null);
            }
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getSiguiente().getInfo().equals(parada)) {
                actual = actual.getSiguiente();
            }

            // Si se encontró parada, insertar después
            if (actual != null) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                actual.getSiguiente().setSiguiente(null);
                iN--;
            } else {
                // Manejar el caso donde pValor no se encuentra
                System.out.println("El valor " + parada + " no se encontró en la lista.");
            }
        }
    }
    public int longitud() {
    int contador = 0;
    Nodo actual = this.inicio;
    while (actual != null) {
        contador++;
        actual = actual.getSiguiente();
    }
    return contador;
}

    /*
        
    public void insertarPrimero(String parada){  
        Nodo nuevo = new Nodo(parada); 
        nuevo.setSiguiente(inicio);
        inicio=nuevo;
        iN++;
    }
        
    public void insertar1DespuesDe2(String nuevaParada, String parada) {
        Nodo nuevoNodo = new Nodo(nuevaParada);
        Nodo actual = inicio;

        // Lista vacía
        if (esVacia()) {
            inicio = nuevoNodo;
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getInfo().equals(parada)) {
                actual = actual.getSiguiente();
            }

            // Si se encontró parada, insertar después
            if (actual != null) {
                nuevoNodo.setSiguiente(actual.getSiguiente());
                actual.setSiguiente(nuevoNodo);
                iN++;
            } else {
                // Manejar el caso donde pValor no se encuentra
                System.out.println("El valor " + parada + " no se encontró en la lista.");
            }
        }
    }
    
*/
}