
package com.sena.vacantes;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);
    private static final VacanteDAO dao = new VacanteDAO();

    public static void main(String[] args) {

        int opcion;

        System.out.println("======================================");
        System.out.println("       SISTEMA DE VACANTES");
        System.out.println("======================================");

        do {
            System.out.println("\n--------- MENÚ PRINCIPAL ---------");
            System.out.println("1. Registrar vacante");
            System.out.println("2. Consultar todas las vacantes");
            System.out.println("3. Buscar vacante por ID");
            System.out.println("4. Actualizar vacante");
            System.out.println("5. Eliminar vacante");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            try {
                switch (opcion) {

                    case 1:
                        registrarVacante();
                        break;

                    case 2:
                        consultarVacantes();
                        break;

                    case 3:
                        buscarVacante();
                        break;

                    case 4:
                        actualizarVacante();
                        break;

                    case 5:
                        eliminarVacante();
                        break;

                    case 6:
                        System.out.println("Gracias por utilizar el sistema.");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (SQLException e) {
                System.out.println("Error al trabajar con MySQL: "
                        + e.getMessage());
            }

        } while (opcion != 6);

        teclado.close();
    }

    // REGISTRAR VACANTE
    private static void registrarVacante() throws SQLException {

        System.out.println("\n--- REGISTRO DE VACANTE ---");

        System.out.print("Título: ");
        String titulo = teclado.nextLine();

        System.out.print("Empresa: ");
        String empresa = teclado.nextLine();

        System.out.print("Ubicación: ");
        String ubicacion = teclado.nextLine();

        System.out.print("Salario: ");
        double salario = leerDecimal();

        System.out.print("Modalidad: ");
        String modalidad = teclado.nextLine();

        System.out.print("Descripción: ");
        String descripcion = teclado.nextLine();

        Vacante vacante = new Vacante(
                titulo, empresa, ubicacion,
                salario, modalidad, descripcion
        );

        if (dao.registrar(vacante)) {
            System.out.println("Vacante registrada correctamente.");
        } else {
            System.out.println("No fue posible registrar la vacante.");
        }
    }

    // CONSULTAR VACANTES
    private static void consultarVacantes() throws SQLException {

        System.out.println("\n--- LISTADO DE VACANTES ---");

        List<Vacante> vacantes = dao.listar();

        if (vacantes.isEmpty()) {
            System.out.println("No hay vacantes registradas.");
        } else {
            for (Vacante vacante : vacantes) {
                System.out.println(vacante);
            }
        }
    }

    // BUSCAR POR ID
    private static void buscarVacante() throws SQLException {

        System.out.println("\n--- BUSCAR VACANTE ---");

        System.out.print("Ingrese el ID: ");
        int id = leerEntero();

        Vacante vacante = dao.buscarPorId(id);

        if (vacante != null) {
            System.out.println(vacante);
        } else {
            System.out.println("No se encontró una vacante con ese ID.");
        }
    }

    // ACTUALIZAR VACANTE
    private static void actualizarVacante() throws SQLException {

        System.out.println("\n--- ACTUALIZAR VACANTE ---");

        System.out.print("Ingrese el ID de la vacante: ");
        int id = leerEntero();

        Vacante existente = dao.buscarPorId(id);

        if (existente == null) {
            System.out.println("No se encontró la vacante.");
            return;
        }

        System.out.println("Ingrese los nuevos datos:");

        System.out.print("Título: ");
        String titulo = teclado.nextLine();

        System.out.print("Empresa: ");
        String empresa = teclado.nextLine();

        System.out.print("Ubicación: ");
        String ubicacion = teclado.nextLine();

        System.out.print("Salario: ");
        double salario = leerDecimal();

        System.out.print("Modalidad: ");
        String modalidad = teclado.nextLine();

        System.out.print("Descripción: ");
        String descripcion = teclado.nextLine();

        Vacante vacante = new Vacante(
                id, titulo, empresa, ubicacion,
                salario, modalidad, descripcion
        );

        if (dao.actualizar(vacante)) {
            System.out.println("Vacante actualizada correctamente.");
        } else {
            System.out.println("No fue posible actualizar la vacante.");
        }
    }

    // ELIMINAR VACANTE
    private static void eliminarVacante() throws SQLException {

        System.out.println("\n--- ELIMINAR VACANTE ---");

        System.out.print("Ingrese el ID de la vacante: ");
        int id = leerEntero();

        Vacante vacante = dao.buscarPorId(id);

        if (vacante == null) {
            System.out.println("No se encontró la vacante.");
            return;
        }

        System.out.println(vacante);
        System.out.print("¿Desea eliminarla? (S/N): ");

        String confirmacion = teclado.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            if (dao.eliminar(id)) {
                System.out.println("Vacante eliminada correctamente.");
            } else {
                System.out.println("No fue posible eliminar la vacante.");
            }

        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // LEER NÚMEROS ENTEROS
    private static int leerEntero() {

        while (true) {
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    // LEER SALARIOS
    private static double leerDecimal() {

        while (true) {
            try {
                String valor = teclado.nextLine().replace(",", ".");
                return Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un salario válido: ");
            }
        }
    }
}
