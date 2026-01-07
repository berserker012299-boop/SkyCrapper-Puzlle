import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] argumentos) {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        int dificultadActual = 1;

        while (opcion != 5) {
            mostrarMenuPrincipal(dificultadActual);
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    dificultadActual = 1;
                    menuDificultad("Fácil", 4, sc);
                    break;
                case 2:
                    dificultadActual = 2;
                    menuDificultad("Intermedio", 5, sc);
                    break;
                case 3:
                    dificultadActual = 3;
                    menuDificultad("Difícil", 6, sc);
                    break;
                case 4:
                    menuInstrucciones(sc);
                    break;
                case 5:
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }

    public static void mostrarMenuPrincipal(int dificultad) {
        System.out.println("\n--------- Puzzle Rascacielos ---------");
        System.out.println("Bienvenido al juego de Puzzle Rascacielos. Selecciona una opción:");
        System.out.println("1. Fácil");
        System.out.println("2. Intermedio");
        System.out.println("3. Difícil");
        System.out.println("4. ¿Cómo jugar?");
        System.out.println("5. Salir del juego");
        System.out.print("Seleccione una opción: ");
    }

    public static void menuDificultad(String nombre, int tamaño, Scanner sc) {
        int opcion;

        do {
            System.out.println("\n--- Dificultad " + nombre + " ---");
            System.out.println("Se genera una cuadrícula de " + tamaño + "x" + tamaño + ".");
            System.out.println("1. Empezar juego");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(sc);

            if (opcion == 1) {
                jugar(tamaño, sc);
            } else if (opcion != 2) {
                System.out.println("Opción no válida.");
            }
        } while (opcion != 2);
    }

    public static void menuInstrucciones(Scanner sc) {
        int opcion;

        do {
            System.out.println("\n--- Instrucciones ---");
            System.out.println("1. ¿Qué es Rascacielos?");
            System.out.println("2. ¿Cómo se juega?");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    System.out.println("\n=== ¿QUÉ ES RASCACIELOS? ===");
                    System.out.println("Rascacielos es un rompecabezas lógico donde debe rellenarse una cuadrícula");
                    System.out.println("con números del 1 al tamaño de la cuadrícula, de manera que las filas y");
                    System.out.println("columnas contengan todos los números sin repetir.");
                    System.out.println("Cada número representa un edificio, siendo más alto cuanto más grande sea.");
                    System.out.println("En los bordes de la cuadrícula, se muestran pistas de visibilidad que");
                    System.out.println("indican cuántos edificios se ven desde esa posición.");
                    System.out.println("Un edificio más alto tapa a los más bajos detrás de él.");
                    break;

                case 2:
                    System.out.println("\n=== ¿CÓMO SE JUEGA? ===");
                    System.out.println("1. Selecciona una dificultad (Fácil, Intermedio o Difícil).");
                    System.out.println("2. Aparecerá un tablero con pistas de visibilidad en los bordes.");
                    System.out.println("3. Ingresa coordenadas para llenar casillas (ejemplo: AE = fila A, columna E).");
                    System.out.println("4. Introduce números del 1 al tamaño del tablero.");
                    System.out.println("5. Cada fila y columna debe contener todos los números sin repetición.");
                    System.out.println("6. Las pistas en los bordes deben coincidir con la visibilidad real.");
                    System.out.println("7. Para salir del juego actual, ingresa 'X'.");
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    public static void jugar(int tamaño, Scanner sc) {
        int[][] tableroUsuario = new int[tamaño][tamaño];
        int[][] tableroSolucion = generarSolucion(tamaño);

        ArrayList<String> coordenadas = generarCoordenadas(tamaño);

        int[][] pistas = generarPistas(tamaño, tableroSolucion);

        boolean terminado = false;

        while (!terminado) {
            mostrarTablero(tableroUsuario, pistas, tamaño);

            System.out.print("\nIngresa una coordenada (ejemplo: AE) o X para terminar: ");
            String coordenada = sc.next().toUpperCase();

            if (coordenada.equals("X")) {
                System.out.println("Has salido del puzzle.");
                return;
            }

            if (!coordenadas.contains(coordenada)) {
                System.out.println("Coordenada inválida.");
                continue;
            }

            int columna = coordenada.charAt(0) - 'A';
            int fila = coordenada.charAt(1) - 'A' - tamaño;

            if (fila < 0 || fila >= tamaño || columna < 0 || columna >= tamaño) {
                System.out.println("Coordenada fuera de rango.");
                continue;
            }

            System.out.print("Introduce número (1-" + tamaño + "): ");
            int valor = leerEntero(sc);

            if (valor < 1 || valor > tamaño) {
                System.out.println("Valor inválido.");
                continue;
            }

            tableroUsuario[fila][columna] = valor;

            if (tableroCompleto(tableroUsuario)) {
                terminado = true;
            }
        }

        if (compararTableros(tableroUsuario, tableroSolucion)) {
            System.out.println("\n¡Felicidades! ¡Has resuelto el puzzle correctamente!");
        } else {
            System.out.println("\nIncorrecto. La solución no coincide.");
            System.out.println("¿Quieres ver la solución? (S/N): ");
            String respuesta = sc.next().toUpperCase();
            if (respuesta.equals("S")) {
                System.out.println("\nSolución correcta:");
                mostrarTableroSolucion(tableroSolucion, tamaño);
            }
        }
    }

    public static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next(); // Descartar valor no numérico
            System.out.print("Introduce un número válido: ");
        }
        return sc.nextInt();
    }

    public static int[][] generarSolucion(int tamaño) {
        int[][] solucion = new int[tamaño][tamaño];
        for (int fila = 0; fila < tamaño; fila++)
            for (int columna = 0; columna < tamaño; columna++)
                solucion[fila][columna] = (columna + fila) % tamaño + 1;
        return solucion;
    }

    public static ArrayList<String> generarCoordenadas(int tamaño) {
        ArrayList<String> lista = new ArrayList<>();

        for (int columna = 0; columna < tamaño; columna++) {
            for (int fila = tamaño; fila < tamaño * 2; fila++) {
                lista.add("" + (char) ('A' + columna) + (char) ('A' + fila));
            }
        }
        return lista;
    }

    public static boolean tableroCompleto(int[][] tablero) {
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero[fila].length; columna++) {
                if (tablero[fila][columna] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean compararTableros(int[][] tablero1, int[][] tablero2) {
        for (int fila = 0; fila < tablero1.length; fila++) {
            for (int columna = 0; columna < tablero1[fila].length; columna++) {
                if (tablero1[fila][columna] != tablero2[fila][columna]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int contarVisibles(int[] linea) {
        int visibles = 0;
        int maxAltura = 0;

        for (int i = 0; i < linea.length; i++) {
            int altura = linea[i];
            if (altura > maxAltura) {
                visibles++;
                maxAltura = altura;
            }
        }
        return visibles;
    }

    public static int[][] generarPistas(int tamaño, int[][] solucion) {
        int[][] pistas = new int[4][tamaño];

        for (int i = 0; i < tamaño; i++) {
            // Pista IZQUIERDA (fila i normal)
            pistas[2][i] = contarVisibles(solucion[i]);

            // Pista DERECHA (fila i al revés)
            int[] filaReversa = new int[tamaño];
            for (int j = 0; j < tamaño; j++) {
                filaReversa[j] = solucion[i][tamaño - 1 - j];
            }
            pistas[3][i] = contarVisibles(filaReversa);

            // Pista ARRIBA (columna i)
            int[] columna = new int[tamaño];
            for (int j = 0; j < tamaño; j++) {
                columna[j] = solucion[j][i];
            }
            pistas[0][i] = contarVisibles(columna);

            // Pista ABAJO (columna i al revés)
            int[] columnaReversa = new int[tamaño];
            for (int j = 0; j < tamaño; j++) {
                columnaReversa[j] = solucion[tamaño - 1 - j][i];
            }
            pistas[1][i] = contarVisibles(columnaReversa);
        }
        return pistas;
    }

    public static void mostrarTablero(int[][] tablero, int[][] pistas, int tamaño) {
        System.out.print("\n    ");
        for (int columna = 0; columna < tamaño; columna++)
            System.out.print((char) ('A' + columna) + " ");
        System.out.println();

        System.out.print("    ");
        for (int i = 0; i < tamaño; i++)
            System.out.print(pistas[0][i] + " ");
        System.out.println();

        for (int fila = 0; fila < tamaño; fila++) {
            System.out.print((char) ('A' + tamaño + fila) + " " + pistas[2][fila] + " ");
            for (int columna = 0; columna < tamaño; columna++) {
                if (tablero[fila][columna] == 0)
                    System.out.print("- ");
                else
                    System.out.print(tablero[fila][columna] + " ");
            }
            System.out.println(pistas[3][fila]);
        }

        System.out.print("    ");
        for (int i = 0; i < tamaño; i++)
            System.out.print(pistas[1][i] + " ");
        System.out.println();
    }

    public static void mostrarTableroSolucion(int[][] solucion, int tamaño) {
        System.out.print("\n    ");
        for (int columna = 0; columna < tamaño; columna++)
            System.out.print((char) ('A' + columna) + " ");
        System.out.println();

        for (int fila = 0; fila < tamaño; fila++) {
            System.out.print((char) ('A' + tamaño + fila) + "   ");
            for (int columna = 0; columna < tamaño; columna++) {
                System.out.print(solucion[fila][columna] + " ");
            }
            System.out.println();
        }
    }
}