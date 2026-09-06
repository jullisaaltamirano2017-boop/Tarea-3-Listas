import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionPrincipal = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("        MENU PRINCIPAL - LISTAS CIRCULARES");
            System.out.println("==========================================");
            System.out.println("1. Lista Circular Basica");
            System.out.println("2. Insercion y Eliminacion Controlada");
            System.out.println("3. Simulacion Round-Robin");
            System.out.println("4. Problema de Josephus");
            System.out.println("5. Playlist Musical Circular");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            
            if (scanner.hasNextInt()) {
                opcionPrincipal = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                continue;
            }

            switch (opcionPrincipal) {
                case 1:
                    menuEjercicio1(scanner);
                    break;
                case 2:
                    menuEjercicio2(scanner);
                    break;
                case 3:
                    menuEjercicio3(scanner);
                    break;
                case 4:
                    ejecutarJosephus(scanner);
                    break;
                case 5:
                    menuEjercicio5(scanner);
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcionPrincipal != 6);

        scanner.close();
    }

    private static void menuEjercicio1(Scanner sc) {
        ListaCircularBasicaService service = new ListaCircularBasicaService();
        int op = 0;
        do {
            System.out.println("\n--- EJERCICIO 1: LISTA BASICA ---");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Mostrar elementos");
            System.out.println("4. Verificar si esta vacia");
            System.out.println("5. Contar elementos");
            System.out.println("6. Explicacion teorica");
            System.out.println("7. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Ingrese valor: ");
                    service.insertarInicio(sc.nextInt());
                    break;
                case 2:
                    System.out.print("Ingrese valor: ");
                    service.insertarFinal(sc.nextInt());
                    break;
                case 3:
                    service.mostrar();
                    break;
                case 4:
                    System.out.println("¿Esta vacia?: " + service.estaVacia());
                    break;
                case 5:
                    System.out.println("Total elementos: " + service.obtenerTamano());
                    break;
                case 6:
                    System.out.println("\nEXPLICACION:");
                    System.out.println("El ultimo nodo debe apuntar al primero para cerrar el ciclo.");
                    System.out.println("Si apuntara a null, el recorrido se detendria al llegar al final.");
                    System.out.println("Al mantener ultimo.siguiente = cabeza, se permite la iteracion continua.");
                    break;
            }
        } while (op != 7);
    }

    private static void menuEjercicio2(Scanner sc) {
        ListaControladaService service = new ListaControladaService();
        int op = 0;
        do {
            System.out.println("\n--- EJERCICIO 2: CONTROLADA ---");
            System.out.println("1. Insertar en posicion");
            System.out.println("2. Eliminar por posicion");
            System.out.println("3. Eliminar por valor");
            System.out.println("4. Mostrar lista");
            System.out.println("5. Analisis de casos");
            System.out.println("6. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Ingrese valor: ");
                    int v = sc.nextInt();
                    System.out.print("Ingrese posicion: ");
                    int p = sc.nextInt();
                    service.insertarEnPosicion(v, p);
                    break;
                case 2:
                    System.out.print("Ingrese posicion a eliminar: ");
                    service.eliminarPorPosicion(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Ingrese valor a eliminar: ");
                    service.eliminarPorValor(sc.nextInt());
                    break;
                case 4:
                    service.mostrar();
                    break;
                case 5:
                    System.out.println("\nANALISIS DE CASOS:");
                    System.out.println("1. Lista Vacia: cabeza y cola son null. Insercion hace que el nodo apunte a si mismo.");
                    System.out.println("2. Un solo nodo: cabeza y cola son iguales. Eliminarlo deja ambos en null.");
                    System.out.println("3. Varios nodos: Modificar extremos requiere actualizar cola.siguiente = cabeza.");
                    break;
            }
        } while (op != 6);
    }

    private static void menuEjercicio3(Scanner sc) {
        RoundRobinService service = new RoundRobinService();
        int op = 0;
        do {
            System.out.println("\n--- EJERCICIO 3: ROUND-ROBIN ---");
            System.out.println("1. Agregar proceso");
            System.out.println("2. Ejecutar simulacion (Quantum = 2)");
            System.out.println("3. Cargar prueba automatica");
            System.out.println("4. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nombre del proceso: ");
                    String nom = sc.nextLine();
                    System.out.print("Tiempo restante: ");
                    int t = sc.nextInt();
                    service.agregarProceso(nom, t);
                    break;
                case 2:
                    service.simular(2);
                    break;
                case 3:
                    service = new RoundRobinService();
                    service.agregarProceso("P1", 5);
                    service.agregarProceso("P2", 2);
                    service.agregarProceso("P3", 4);
                    System.out.println("Procesos P1(5), P2(2) y P3(4) cargados.");
                    service.simular(2);
                    break;
            }
        } while (op != 4);
    }

    private static void ejecutarJosephus(Scanner sc) {
        JosephusService service = new JosephusService();
        System.out.println("\n--- EJERCICIO 4: PROBLEMA DE JOSEPHUS ---");
        System.out.println("Ejecutando pruebas requeridas:");
        service.resolver(5, 2);
        service.resolver(7, 3);

        System.out.println("EXPLICACION:");
        System.out.println("La lista circular es adecuada porque representa naturalmente un circulo.");
        System.out.println("Permite avanzar indefinidamente sin reiniciar punteros y eliminar en O(1).");
    }

    private static void menuEjercicio5(Scanner sc) {
        PlaylistService service = new PlaylistService();
        int op = 0;
        do {
            System.out.println("\n--- EJERCICIO 5: PLAYLIST CIRCULAR ---");
            System.out.println("1. Agregar cancion al inicio");
            System.out.println("2. Agregar cancion al final");
            System.out.println("3. Mostrar playlist");
            System.out.println("4. Reproducir siguiente cancion");
            System.out.println("5. Eliminar cancion por nombre");
            System.out.println("6. Analisis de ventajas");
            System.out.println("7. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Titulo de la cancion: ");
                    service.agregarInicio(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Titulo de la cancion: ");
                    service.agregarFinal(sc.nextLine());
                    break;
                case 3:
                    service.mostrarPlaylist();
                    break;
                case 4:
                    service.reproducirSiguiente();
                    break;
                case 5:
                    System.out.print("Titulo a eliminar: ");
                    service.eliminarCancion(sc.nextLine());
                    break;
                case 6:
                    System.out.println("\nVENTAJAS:");
                    System.out.println("1. Recorrido infinito sin reiniciar indices manualmente.");
                    System.out.println("2. Sin excepciones de NullPointerException al llegar al final.");
                    System.out.println("3. Inserciones/eliminaciones eficientes sin desplazar memoria.");
                    break;
            }
        } while (op != 7);
    }
}