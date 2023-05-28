class Nodo {
    int dato;
    boolean esRojo;
    Nodo izquierdo, derecho, padre;

    public Nodo(int dato) {
        this.dato = dato;
        this.esRojo = true;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }
}
