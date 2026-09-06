class ListaCircularBasicaService {
    private Nodo cabeza;
    private Nodo cola;
    private int tamano;

    public ListaCircularBasicaService() {
        this.cabeza = null;
        this.cola = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int obtenerTamano() {
        return tamano;
    }

    public void insertarInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = cabeza;
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza;
        }
        tamano++;
    }

    public void insertarFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = cabeza;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
        tamano++;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Lista vacia");
            return;
        }
        Nodo actual = cabeza;
        System.out.print("Lista: ");
        do {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(vuelve a " + cabeza.dato + ")");
    }
}

class ListaControladaService {
    private Nodo cabeza = null;
    private Nodo cola = null;
    private int tamano = 0;

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("[ Lista Vacia ]");
            return;
        }
        Nodo actual = cabeza;
        do {
            System.out.print("[" + actual.dato + "] -> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(Inicio)");
    }

    public void insertarEnPosicion(int dato, int pos) {
        if (pos < 0 || pos > tamano) {
            System.out.println("Posicion invalida.");
            return;
        }

        System.out.println("--- Insertar " + dato + " en pos " + pos + " ---");
        System.out.print("Antes: ");
        mostrar();

        Nodo nuevo = new Nodo(dato);

        if (pos == 0) {
            if (cabeza == null) {
                cabeza = nuevo;
                cola = nuevo;
                nuevo.siguiente = cabeza;
            } else {
                nuevo.siguiente = cabeza;
                cabeza = nuevo;
                cola.siguiente = cabeza;
            }
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < pos - 1; i++) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
            if (actual == cola) {
                cola = nuevo;
            }
        }
        tamano++;

        System.out.print("Despues: ");
        mostrar();
    }

    public void eliminarPorPosicion(int pos) {
        if (cabeza == null || pos < 0 || pos >= tamano) {
            System.out.println("Posicion invalida o lista vacia.");
            return;
        }

        System.out.println("--- Eliminar en pos " + pos + " ---");
        System.out.print("Antes: ");
        mostrar();

        if (tamano == 1) {
            cabeza = null;
            cola = null;
        } else if (pos == 0) {
            cabeza = cabeza.siguiente;
            cola.siguiente = cabeza;
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < pos - 1; i++) {
                actual = actual.siguiente;
            }
            actual.siguiente = actual.siguiente.siguiente;
            if (pos == tamano - 1) {
                cola = actual;
            }
        }
        tamano--;

        System.out.print("Despues: ");
        mostrar();
    }

    public void eliminarPorValor(int valor) {
        if (cabeza == null) {
            System.out.println("Lista vacia.");
            return;
        }

        System.out.println("--- Eliminar valor " + valor + " ---");
        System.out.print("Antes: ");
        mostrar();

        Nodo actual = cabeza;
        Nodo previo = cola;
        boolean encontrado = false;

        do {
            if (actual.dato == valor) {
                encontrado = true;
                if (tamano == 1) {
                    cabeza = null;
                    cola = null;
                } else {
                    previo.siguiente = actual.siguiente;
                    if (actual == cabeza) cabeza = actual.siguiente;
                    if (actual == cola) cola = previo;
                }
                tamano--;
                break;
            }
            previo = actual;
            actual = actual.siguiente;
        } while (actual != cabeza);

        if (!encontrado) {
            System.out.println("Valor no encontrado.");
        }

        System.out.print("Despues: ");
        mostrar();
    }
}

class RoundRobinService {
    private NodoProceso cabeza = null;
    private NodoProceso cola = null;

    public void agregarProceso(String nombre, int tiempo) {
        NodoProceso nuevo = new NodoProceso(new Proceso(nombre, tiempo));
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = cabeza;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
    }

    public void simular(int quantum) {
        if (cabeza == null) {
            System.out.println("No hay procesos en la cola.");
            return;
        }

        NodoProceso actual = cabeza;
        NodoProceso anterior = cola;
        int turno = 1;

        while (cabeza != null) {
            System.out.println("Turno " + turno + " -> Ejecutando: " + actual.proceso.nombre + " (Restante: " + actual.proceso.tiempoRestante + ")");

            if (actual.proceso.tiempoRestante <= quantum) {
                System.out.println("Proceso " + actual.proceso.nombre + " finalizado.");
                actual.proceso.tiempoRestante = 0;

                if (actual == cabeza && actual == cola) {
                    cabeza = null;
                    cola = null;
                    actual = null;
                } else {
                    anterior.siguiente = actual.siguiente;
                    if (actual == cabeza) cabeza = actual.siguiente;
                    if (actual == cola) cola = anterior;
                    actual = actual.siguiente;
                }
            } else {
                actual.proceso.tiempoRestante -= quantum;
                anterior = actual;
                actual = actual.siguiente;
            }

            imprimirEstado();
            turno++;
        }
    }

    private void imprimirEstado() {
        System.out.print("Estado lista: ");
        if (cabeza == null) {
            System.out.println("[Vacia]\n");
            return;
        }
        NodoProceso temp = cabeza;
        do {
            System.out.print("[" + temp.proceso.nombre + ":" + temp.proceso.tiempoRestante + "] -> ");
            temp = temp.siguiente;
        } while (temp != cabeza);
        System.out.println("(Inicio)\n");
    }
}

class JosephusService {
    public void resolver(int n, int k) {
        if (n <= 0 || k <= 0) return;

        Persona cabeza = new Persona(1);
        Persona actual = cabeza;
        for (int i = 2; i <= n; i++) {
            actual.siguiente = new Persona(i);
            actual = actual.siguiente;
        }
        actual.siguiente = cabeza;

        System.out.println("\nJosephus (n = " + n + ", k = " + k + ")");
        System.out.print("Orden de eliminacion: ");

        Persona prev = actual;
        actual = cabeza;

        while (actual.siguiente != actual) {
            for (int count = 1; count < k; count++) {
                prev = actual;
                actual = actual.siguiente;
            }

            System.out.print(actual.id + " ");
            prev.siguiente = actual.siguiente;
            actual = prev.siguiente;
        }

        System.out.println("\nSuperviviente final: Persona " + actual.id + "\n");
    }
}

class PlaylistService {
    private NodoCancion cabeza = null;
    private NodoCancion cola = null;
    private NodoCancion reproduciendoActual = null;

    public void agregarInicio(String titulo) {
        NodoCancion nuevo = new NodoCancion(new Cancion(titulo));
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = cabeza;
            reproduciendoActual = cabeza;
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza;
        }
    }

    public void agregarFinal(String titulo) {
        NodoCancion nuevo = new NodoCancion(new Cancion(titulo));
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = cabeza;
            reproduciendoActual = cabeza;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
    }

    public void mostrarPlaylist() {
        if (cabeza == null) {
            System.out.println("Playlist vacia.");
            return;
        }
        NodoCancion aux = cabeza;
        System.out.println("--- PLAYLIST ---");
        do {
            System.out.println("-> " + aux.cancion.titulo + (aux == reproduciendoActual ? " [Sonando]" : ""));
            aux = aux.siguiente;
        } while (aux != cabeza);
        System.out.println("----------------");
    }

    public void reproducirSiguiente() {
        if (reproduciendoActual == null) {
            System.out.println("La playlist esta vacia.");
            return;
        }
        System.out.println("Reproduciendo ahora: " + reproduciendoActual.cancion.titulo);
        reproduciendoActual = reproduciendoActual.siguiente;
    }

    public void eliminarCancion(String titulo) {
        if (cabeza == null) return;

        NodoCancion actual = cabeza;
        NodoCancion previo = cola;

        do {
            if (actual.cancion.titulo.equalsIgnoreCase(titulo)) {
                if (cabeza == cola) {
                    cabeza = null;
                    cola = null;
                    reproduciendoActual = null;
                } else {
                    previo.siguiente = actual.siguiente;
                    if (actual == cabeza) cabeza = actual.siguiente;
                    if (actual == cola) cola = previo;
                    if (actual == reproduciendoActual) reproduciendoActual = actual.siguiente;
                }
                System.out.println("Cancion '" + titulo + "' eliminada.");
                break;
            }
            previo = actual;
            actual = actual.siguiente;
        } while (actual != cabeza);
    }
}