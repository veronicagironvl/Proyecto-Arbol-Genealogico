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

    /**
     * Constructor por defecto que crea una lista vac&iacute;a.
     */
    public Lista() {
        this.inicio = null;
    }

//----------getters y setters--------------
    /**
     * Devuelve el nodo inicial de la lista.
     *
     * @return El nodo inicial.
     */
    public Nodo getInicio() {
        return inicio;
    }

    /**
     * Establece el nodo inicial de la lista.
     *
     * @param inicio El nuevo nodo inicial.
     */
    public void setInicio(Integrante inicio) {

        this.inicio = new Nodo(inicio);
    }

    // ----------- Metodos Principales ----------------
    /**
     * Verifica si la lista est&aacute; vac&iacute;a.
     *
     * @return `true` si la lista est&aacute; vac&iacute;a, `false` en caso
     * contrario.
     */
    public boolean esVacia() {
        return inicio == null;
    }
/**
 * Inserta un nuevo elemento al final de la lista.
 *
 * Crea un nuevo nodo con el dato proporcionado y lo enlaza al final de la lista.
 *
 * @param dato El dato a insertar.
 */
    public void insertarUltimo(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            Nodo ultimo = buscarUltimo();
            ultimo.setSiguiente(nuevo);
        }
    }
/**
 * Inserta un nuevo elemento al principio de la lista.
 *
 * Crea un nuevo nodo con el dato proporcionado y lo enlaza al inicio de la lista.
 *
 * @param dato El dato a insertar.
 */
    public void insertarPrimero(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
    }
/**
 * Inserta un nuevo elemento despu&eacute;s de un elemento de referencia.
 *
 * Busca el elemento de referencia y si lo encuentra, inserta el nuevo elemento despu&eacute;s de &eacute;l.
 *
 * @param dato El dato a insertar.
 * @param referencia El dato de referencia.
 */
    public void insertarDespuesDe(Object dato, Object referencia) {
        Nodo actual = inicio;
        while (actual != null && !actual.getInfo().equals(referencia)) {
            actual = actual.getSiguiente();
        }
        if (actual != null) {
            Nodo nuevo = new Nodo(dato);
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        } else {
            System.out.println("El nodo de referencia no fue encontrado.");
        }
    }
/**
 * Busca el &uacute;ltimo elemento de la lista.
 *
 * Recorre la lista hasta encontrar el &uacute;ltimo nodo y lo devuelve.
 *
 * @return El &uacute;ltimo nodo de la lista, o null si la lista est&aacute; vac&iacute;a.
 */
    public Nodo buscarUltimo() {
        Nodo actual = inicio;
        if (esVacia()) {
            return null;
        }
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual;
    }
/**
 * Verifica si un elemento se encuentra en la lista.
 *
 * Recorre la lista buscando el elemento especificado.
 *
 * @param dato El dato a buscar.
 * @return true si el elemento se encuentra, false en caso contrario.
 */
    public boolean seEncuentra(Object dato) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.getInfo().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
/**
 * Elimina el primer elemento de la lista.
 *
 * Si la lista no est&aacute; vac&iacute;a, elimina el primer nodo y devuelve el dato que conten&iacute;a.
 *
 * @return El dato del nodo eliminado, o null si la lista estaba vac&iacute;a.
 */
    public Nodo eliminarPrimero() {
        if (esVacia()) {
            return null;
        }
        Nodo eliminado = inicio;
        inicio = inicio.getSiguiente();
        eliminado.setSiguiente(null);
        return eliminado;
    }
/**
 * Elimina un elemento de la lista.
 *
 * Busca el elemento en la lista y si lo encuentra, lo elimina.
 *
 * @param dato El dato a eliminar.
 */
    public void eliminar(Object dato) {
        if (esVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        if (inicio.getInfo().equals(dato)) {
            eliminarPrimero();
            return;
        }

        Nodo actual = inicio;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getInfo().equals(dato)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() != null) {
            Nodo eliminado = actual.getSiguiente();
            actual.setSiguiente(eliminado.getSiguiente());
            eliminado.setSiguiente(null);
        } else {
            System.out.println("El dato no fue encontrado en la lista.");
        }
    }
/**
 * Devuelve la longitud de la lista.
 *
 * Cuenta el n&uacute;mero de elementos en la lista.
 *
 * @return La longitud de la lista.
 */
    public int longitud() {
        int contador = 0;
        Nodo actual = inicio;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }
/**
 * Busca un elemento por su &iacute;ndice.
 *
 * Recorre la lista hasta encontrar el elemento en la posici&oacute;n indicada por el &iacute;ndice.
 *
 * @param indice El &iacute;ndice del elemento a buscar.
 * @return El dato del elemento en la posici&oacute;n indicada, o null si el &iacute;ndice est&aacute; fuera de rango.
 */
    public Object buscarPorIndice(int indice) {
        Nodo actual = inicio;
        int contador = 0;
        while (actual != null) {
            if (contador == indice) {
                return actual.getInfo();
            }
            actual = actual.getSiguiente();
            contador++;
        }
        return null; // Si el &iacute;ndice est&aacute; fuera de los l&iacute;mites de la lista
    }
}
