class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class Proceso {
    String nombre;
    int tiempoRestante;

    public Proceso(String nombre, int tiempoRestante) {
        this.nombre = nombre;
        this.tiempoRestante = tiempoRestante;
    }
}

class NodoProceso {
    Proceso proceso;
    NodoProceso siguiente;

    public NodoProceso(Proceso proceso) {
        this.proceso = proceso;
        this.siguiente = null;
    }
}

class Persona {
    int id;
    Persona siguiente;

    public Persona(int id) {
        this.id = id;
        this.siguiente = null;
    }
}

class Cancion {
    String titulo;

    public Cancion(String titulo) {
        this.titulo = titulo;
    }
}

class NodoCancion {
    Cancion cancion;
    NodoCancion siguiente;

    public NodoCancion(Cancion cancion) {
        this.cancion = cancion;
        this.siguiente = null;
    }
}