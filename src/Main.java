import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Caso casoActual = null;
        int opcion = 0;

        try {
            while (casoActual == null) {
                try {
                    System.out.println("AGENCIA DE DETECTIVES");
                    System.out.print("Nombre del caso: ");
                    String nombre = entrada.nextLine();
                    System.out.print("Codigo del caso: ");
                    String codigo = entrada.nextLine();
                    System.out.print("Detective responsable: ");
                    String detective = entrada.nextLine();
                    casoActual = new Caso(nombre, codigo, detective);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (opcion != 13) {
                try {
                    System.out.println("\n1. Nuevo caso");
                    System.out.println("2. Registrar ubicacion");
                    System.out.println("3. Consultar ubicaciones");
                    System.out.println("4. Consultar una ubicacion");
                    System.out.println("5. Modificar ubicacion");
                    System.out.println("6. Descartar ubicacion");
                    System.out.println("7. Registrar pista");
                    System.out.println("8. Consultar pistas");
                    System.out.println("9. Buscar pista");
                    System.out.println("10. Modificar pista");
                    System.out.println("11. Eliminar pista");
                    System.out.println("12. Mostrar reporte de investigacion");
                    System.out.println("13. Salir");
                    System.out.print("Opcion: ");
                    opcion = entrada.nextInt();
                    entrada.nextLine();

                    switch (opcion) {
                        case 1: {
                            System.out.print("Nombre del caso: ");
                            String nombre = entrada.nextLine();
                            System.out.print("Codigo del caso: ");
                            String codigo = entrada.nextLine();
                            System.out.print("Detective responsable: ");
                            String detective = entrada.nextLine();
                            casoActual = new Caso(nombre, codigo, detective);
                            System.out.println("Nuevo caso creado.");
                            break;
                        }
                        case 2: {
                            System.out.print("Posicion (0 a 4): ");
                            int posicion = entrada.nextInt();
                            entrada.nextLine();
                            if (casoActual.buscarUbicacion(posicion) != null) {
                                throw new IllegalArgumentException("La posicion ya esta ocupada.");
                            }
                            System.out.print("Codigo: ");
                            String codigo = entrada.nextLine();
                            System.out.print("Nombre: ");
                            String nombre = entrada.nextLine();
                            System.out.print("Direccion: ");
                            String direccion = entrada.nextLine();
                            System.out.print("Nivel de riesgo (1 a 10): ");
                            int riesgo = entrada.nextInt();
                            entrada.nextLine();
                            System.out.print("Estado: ");
                            String estado = entrada.nextLine();
                            Ubicacion ubicacion = new Ubicacion(codigo, nombre, direccion, riesgo, estado);
                            casoActual.registrarUbicacion(posicion, ubicacion);
                            System.out.println("Ubicacion registrada.");
                            break;
                        }
                        case 3: {
                            System.out.println(casoActual.consultarUbicaciones());
                            break;
                        }
                        case 4: {
                            System.out.print("Posicion (0 a 4): ");
                            int posicion = entrada.nextInt();
                            entrada.nextLine();
                            Ubicacion ubicacion = casoActual.buscarUbicacion(posicion);
                            if (ubicacion == null) {
                                System.out.println("La posicion esta vacia.");
                            } else {
                                System.out.println(ubicacion.toString());
                            }
                            break;
                        }
                        case 5: {
                            System.out.print("Posicion (0 a 4): ");
                            int posicion = entrada.nextInt();
                            entrada.nextLine();
                            if (casoActual.buscarUbicacion(posicion) == null) {
                                throw new IllegalArgumentException("La posicion esta vacia.");
                            }
                            System.out.print("Nuevo riesgo (1 a 10): ");
                            int riesgo = entrada.nextInt();
                            entrada.nextLine();
                            System.out.print("Nuevo estado: ");
                            String estado = entrada.nextLine();
                            casoActual.modificarUbicacion(posicion, riesgo, estado);
                            System.out.println("Ubicacion modificada.");
                            break;
                        }
                        case 6: {
                            System.out.print("Posicion (0 a 4): ");
                            int posicion = entrada.nextInt();
                            entrada.nextLine();
                            casoActual.descartarUbicacion(posicion);
                            System.out.println("Ubicacion descartada.");
                            break;
                        }
                        case 7: {
                            System.out.print("Codigo: ");
                            String codigo = entrada.nextLine();
                            if (casoActual.buscarPista(codigo) != null) {
                                throw new IllegalArgumentException("Ya existe una pista con ese codigo.");
                            }
                            System.out.print("Descripcion: ");
                            String descripcion = entrada.nextLine();
                            System.out.print("Tipo de evidencia: ");
                            String tipo = entrada.nextLine();
                            System.out.print("Importancia (1 a 10): ");
                            int importancia = entrada.nextInt();
                            entrada.nextLine();
                            System.out.print("Confiabilidad (0 a 100): ");
                            int confiabilidad = entrada.nextInt();
                            entrada.nextLine();
                            Pista pista = new Pista(codigo, descripcion, tipo, importancia, confiabilidad);
                            casoActual.registrarPista(pista);
                            System.out.println("Pista registrada.");
                            break;
                        }
                        case 8: {
                            System.out.println(casoActual.consultarPistas());
                            break;
                        }
                        case 9: {
                            System.out.print("Codigo de la pista: ");
                            String codigo = entrada.nextLine();
                            Pista pista = casoActual.buscarPista(codigo);
                            if (pista == null) {
                                System.out.println("No existe una pista con ese codigo.");
                            } else {
                                System.out.println(pista.toString());
                            }
                            break;
                        }
                        case 10: {
                            System.out.print("Codigo de la pista: ");
                            String codigo = entrada.nextLine();
                            if (casoActual.buscarPista(codigo) == null) {
                                throw new IllegalArgumentException("No existe una pista con ese codigo.");
                            }
                            System.out.print("Nueva descripcion: ");
                            String descripcion = entrada.nextLine();
                            System.out.print("Nuevo tipo de evidencia: ");
                            String tipo = entrada.nextLine();
                            System.out.print("Nueva importancia (1 a 10): ");
                            int importancia = entrada.nextInt();
                            entrada.nextLine();
                            System.out.print("Nueva confiabilidad (0 a 100): ");
                            int confiabilidad = entrada.nextInt();
                            entrada.nextLine();
                            casoActual.modificarPista(codigo, descripcion, tipo, importancia, confiabilidad);
                            System.out.println("Pista modificada.");
                            break;
                        }
                        case 11: {
                            System.out.print("Codigo de la pista: ");
                            String codigo = entrada.nextLine();
                            casoActual.eliminarPista(codigo);
                            System.out.println("Pista eliminada.");
                            break;
                        }
                        case 12: {
                            System.out.println(casoActual.generarReporte());
                            break;
                        }
                        case 13: {
                            System.out.println("Programa finalizado.");
                            break;
                        }
                        default: {
                            System.out.println("Selecciona una opcion entre 1 y 13.");
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes ingresar un numero entero.");
                    entrada.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } finally {
            entrada.close();
        }
    }
}
