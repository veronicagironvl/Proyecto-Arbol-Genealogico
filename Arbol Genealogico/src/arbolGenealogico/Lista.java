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

    public void insertarUltimo(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            Nodo ultimo = buscarUltimo();
            ultimo.setSiguiente(nuevo);
        }
    }

    public void insertarPrimero(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
    }

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

    public Nodo eliminarPrimero() {
        if (esVacia()) {
            return null;
        }
        Nodo eliminado = inicio;
        inicio = inicio.getSiguiente();
        eliminado.setSiguiente(null);
        return eliminado;
    }

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

    public int longitud() {
        int contador = 0;
        Nodo actual = inicio;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

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
        return null; // Si el índice está fuera de los límites de la lista
    }
}
