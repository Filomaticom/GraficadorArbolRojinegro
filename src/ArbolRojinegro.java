class ArbolRojinegro {
    public Nodo raiz;

    public ArbolRojinegro() {
        this.raiz = null;
    }

    public void insertar(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (raiz == null) {
            raiz = nuevoNodo;
        } else {
            Nodo nodoActual = raiz;
            Nodo nodoPadre;
            while (true) {
                nodoPadre = nodoActual;
                if (dato < nodoActual.dato) {
                    nodoActual = nodoActual.izquierdo;
                    if (nodoActual == null) {
                        nodoPadre.izquierdo = nuevoNodo;
                        nuevoNodo.padre = nodoPadre;
                        break;
                    }
                } else {
                    nodoActual = nodoActual.derecho;
                    if (nodoActual == null) {
                        nodoPadre.derecho = nuevoNodo;
                        nuevoNodo.padre = nodoPadre;
                        break;
                    }
                }
            }
        }
        balancearInsercion(nuevoNodo);
    }

    private void balancearInsercion(Nodo nodo) {
        Nodo padre, abuelo, tio;
        while (nodo != raiz && nodo.padre.esRojo) {
            padre = nodo.padre;
            abuelo = padre.padre;
            if (padre == abuelo.izquierdo) {
                tio = abuelo.derecho;
                if (tio != null && tio.esRojo) {
                    abuelo.esRojo = true;
                    padre.esRojo = false;
                    tio.esRojo = false;
                    nodo = abuelo;
                } else {
                    if (nodo == padre.derecho) {
                        nodo = padre;
                        rotarIzquierda(nodo);
                    }
                    nodo.padre.esRojo = false;
                    abuelo.esRojo = true;
                    rotarDerecha(abuelo);
                }
            } else {
                tio = abuelo.izquierdo;
                if (tio != null && tio.esRojo) {
                    abuelo.esRojo = true;
                    padre.esRojo = false;
                    tio.esRojo = false;
                    nodo = abuelo;
                } else {
                    if (nodo == padre.izquierdo) {
                        nodo = padre;
                        rotarDerecha(nodo);
                    }
                    nodo.padre.esRojo = false;
                    abuelo.esRojo = true;
                    rotarIzquierda(abuelo);
                }
            }
        }
        raiz.esRojo = false;
    }

    public void eliminar(int dato) {
        Nodo nodo = buscarNodo(raiz, dato);
        if (nodo != null) {
            eliminarNodo(nodo);
        }
    }

    private void eliminarNodo(Nodo nodo) {
        Nodo nodoReemplazo;
        if (nodo.izquierdo != null && nodo.derecho != null) {
            nodoReemplazo = obtenerMinimo(nodo.derecho);
            nodo.dato = nodoReemplazo.dato;
            nodo = nodoReemplazo;
        }

        Nodo hijo = (nodo.derecho == null) ? nodo.izquierdo : nodo.derecho;
        if (hijo != null) {
            hijo.padre = nodo.padre;
        }

        if (nodo.padre == null) {
            raiz = hijo;
        } else if (nodo == nodo.padre.izquierdo) {
            nodo.padre.izquierdo = hijo;
        } else {
            nodo.padre.derecho = hijo;
        }

        if (!nodo.esRojo) {
            balancearEliminacion(hijo, nodo.padre);
        }
    }

    private void balancearEliminacion(Nodo nodo, Nodo padre) {
        Nodo hermano;
        while (nodo != raiz && (nodo == null || !nodo.esRojo)) {
            if (nodo == padre.izquierdo) {
                hermano = padre.derecho;
                if (hermano.esRojo) {
                    hermano.esRojo = false;
                    padre.esRojo = true;
                    rotarIzquierda(padre);
                    hermano = padre.derecho;
                }
                if ((hermano.izquierdo == null || !hermano.izquierdo.esRojo) &&
                        (hermano.derecho == null || !hermano.derecho.esRojo)) {
                    hermano.esRojo = true;
                    nodo = padre;
                    padre = nodo.padre;
                } else {
                    if (hermano.derecho == null || !hermano.derecho.esRojo) {
                        if (hermano.izquierdo != null) {
                            hermano.izquierdo.esRojo = false;
                        }
                        hermano.esRojo = true;
                        rotarDerecha(hermano);
                        hermano = padre.derecho;
                    }
                    hermano.esRojo = padre.esRojo;
                    padre.esRojo = false;
                    if (hermano.derecho != null) {
                        hermano.derecho.esRojo = false;
                    }
                    rotarIzquierda(padre);
                    nodo = raiz;
                    break;
                }
            } else {
                hermano = padre.izquierdo;
                if (hermano.esRojo) {
                    hermano.esRojo = false;
                    padre.esRojo = true;
                    rotarDerecha(padre);
                    hermano = padre.izquierdo;
                }
                if ((hermano.derecho == null || !hermano.derecho.esRojo) &&
                        (hermano.izquierdo == null || !hermano.izquierdo.esRojo)) {
                    hermano.esRojo = true;
                    nodo = padre;
                    padre = nodo.padre;
                } else {
                    if (hermano.izquierdo == null || !hermano.izquierdo.esRojo) {
                        if (hermano.derecho != null) {
                            hermano.derecho.esRojo = false;
                        }
                        hermano.esRojo = true;
                        rotarIzquierda(hermano);
                        hermano = padre.izquierdo;
                    }
                    hermano.esRojo = padre.esRojo;
                    padre.esRojo = false;
                    if (hermano.izquierdo != null) {
                        hermano.izquierdo.esRojo = false;
                    }
                    rotarDerecha(padre);
                    nodo = raiz;
                    break;
                }
            }
        }
        if (nodo != null) {
            nodo.esRojo = false;
        }
    }

    private void rotarIzquierda(Nodo nodo) {
        Nodo hijoDerecho = nodo.derecho;
        nodo.derecho = hijoDerecho.izquierdo;
        if (hijoDerecho.izquierdo != null) {
            hijoDerecho.izquierdo.padre = nodo;
        }
        hijoDerecho.padre = nodo.padre;
        if (nodo.padre == null) {
            raiz = hijoDerecho;
        } else if (nodo == nodo.padre.izquierdo) {
            nodo.padre.izquierdo = hijoDerecho;
        } else {
            nodo.padre.derecho = hijoDerecho;
        }
        hijoDerecho.izquierdo = nodo;
        nodo.padre = hijoDerecho;
    }

    private void rotarDerecha(Nodo nodo) {
        Nodo hijoIzquierdo = nodo.izquierdo;
        nodo.izquierdo = hijoIzquierdo.derecho;
        if (hijoIzquierdo.derecho != null) {
            hijoIzquierdo.derecho.padre = nodo;
        }
        hijoIzquierdo.padre = nodo.padre;
        if (nodo.padre == null) {
            raiz = hijoIzquierdo;
        } else if (nodo == nodo.padre.izquierdo) {
            nodo.padre.izquierdo = hijoIzquierdo;
        } else {
            nodo.padre.derecho = hijoIzquierdo;
        }
        hijoIzquierdo.derecho = nodo;
        nodo.padre = hijoIzquierdo;
    }

    private Nodo obtenerMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    private Nodo obtenerAbuelo(Nodo nodo) {
        if (nodo != null && nodo.padre != null) {
            return nodo.padre.padre;
        }
        return null;
    }

    private Nodo obtenerTio(Nodo nodo) {
        Nodo abuelo = obtenerAbuelo(nodo);
        if (abuelo == null) {
            return null;
        }
        if (nodo.padre == abuelo.izquierdo) {
            return abuelo.derecho;
        } else {
            return abuelo.izquierdo;
        }
    }

    private Nodo buscarNodo(Nodo nodo, int dato) {
        if (nodo == null || nodo.dato == dato) {
            return nodo;
        }
        if (dato < nodo.dato) {
            return buscarNodo(nodo.izquierdo, dato);
        } else {
            return buscarNodo(nodo.derecho, dato);
        }
    }
}
