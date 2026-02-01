import java.util.Scanner;

/*
 * Proyecto del Módulo 1: Sistema Básico de Gestión de Productos
 * Autor: Santiago Morales
 * Descripción: Aplicación de consola que permite registrar,
 * consultar y gestionar la información de un producto.
 */

public class SistemaGestionProductos {

    // ===== VARIABLES ESTÁTICAS =====
    static Scanner sc = new Scanner(System.in);

    static String nombreUsuario;
    static String nombreProducto = "N/A";
    static double precioUnitario = 0.0;
    static int cantidadInventario = 0;
    static double valorTotal = 0.0;

    // ===== MAIN =====
    public static void main(String[] args) {

        loginUsuario(); // 👈 LOGIN AL INICIO

        boolean activo = true;

        while (activo) {
            mostrarMenu();
            System.out.print("Ingrese su opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    mostrarInformacionProducto();
                    break;
                case 3:
                    mostrarValorInventario();
                    break;
                case 4:
                    mostrarResumenCompleto();
                    break;
                case 5:
                    limpiarDatosProducto();
                    break;
                case 0:
                    activo = false;
                    System.out.println("Hasta luego " + nombreUsuario + ". Cerrando el sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            System.out.println();
        }

        sc.close();
        System.out.println("Programa finalizado correctamente.");
    }

    // ===== LOGIN =====
    static void loginUsuario() {
        System.out.print("Ingrese su nombre: ");
        nombreUsuario = sc.nextLine();

        System.out.println();
        System.out.println("Hola " + nombreUsuario + ", bienvenido  al Sistema de Gestión de Productos");
        System.out.println("¿Qué deseas hacer hoy?");
        System.out.println();
    }

    // ===== MENÚ =====
    static void mostrarMenu() {
        System.out.println("""
                --- Sistema de Gestión de Productos ---

                1. Registrar nuevo producto
                2. Mostrar información del producto actual
                3. Calcular valor total del inventario
                4. Mostrar resumen completo del producto
                5. Limpiar datos del producto actual
                0. Salir
                """);
    }

    // ===== REGISTRAR PRODUCTO =====
    static void registrarProducto() {

        if (!nombreProducto.equals("N/A")) {
            System.out.print("Ya existe un producto. ¿Desea sobrescribirlo? (s/n): ");
            String respuesta = sc.nextLine();

            if (!respuesta.equalsIgnoreCase("s")) {
                System.out.println("Registro cancelado.");
                return;
            }
        }

        // Nombre
        do {
            System.out.print("Ingrese el nombre del producto: ");
            nombreProducto = sc.nextLine();
        } while (!validarNombre(nombreProducto));

        // Precio
        do {
            System.out.print("Ingrese el precio unitario: ");
            precioUnitario = sc.nextDouble();
        } while (!validarPrecio(precioUnitario));

        // Cantidad
        do {
            System.out.print("Ingrese la cantidad en inventario: ");
            cantidadInventario = sc.nextInt();
        } while (!validarCantidad(cantidadInventario));

        sc.nextLine();
        System.out.println("Producto registrado correctamente.");
    }

    // ===== MOSTRAR INFORMACIÓN =====
    static void mostrarInformacionProducto() {
        if (nombreProducto.equals("N/A")) {
            System.out.println("No hay datos de producto registrados actualmente.");
        } else {
            System.out.println("Nombre: " + nombreProducto);
            System.out.printf("Precio Unitario: $%.2f%n", precioUnitario);
            System.out.println("Cantidad en Inventario: " + cantidadInventario);
        }
    }

    // ===== VALOR INVENTARIO =====
    static void mostrarValorInventario() {
        if (nombreProducto.equals("N/A")) {
            System.out.println("No hay productos registrados.");
        } else {
            valorTotal = precioUnitario * cantidadInventario;
            System.out.printf("Valor total del inventario: $%.2f%n", valorTotal);
        }
    }

    // ===== RESUMEN COMPLETO =====
    static void mostrarResumenCompleto() {
        if (nombreProducto.equals("N/A")) {
            System.out.println("No hay productos registrados.");
        } else {
            valorTotal = precioUnitario * cantidadInventario;

            System.out.println("--- Resumen del Producto ---");
            System.out.println("Nombre: " + nombreProducto);
            System.out.printf("Precio Unitario: $%.2f%n", precioUnitario);
            System.out.println("Cantidad en Inventario: " + cantidadInventario);
            System.out.printf("Valor Total en Inventario: $%.2f%n", valorTotal);
            System.out.println("Estado del Stock: " + estadoStock());
        }
    }

    // ===== LIMPIAR DATOS =====
    static void limpiarDatosProducto() {
        nombreProducto = "N/A";
        precioUnitario = 0.0;
        cantidadInventario = 0;
        valorTotal = 0.0;

        System.out.println("Los datos del producto actual han sido borrados exitosamente.");
    }

    // ===== VALIDACIONES =====
    static boolean validarNombre(String nombre) {
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }
        return true;
    }

    static boolean validarPrecio(double precio) {
        if (precio <= 0) {
            System.out.println("El precio debe ser mayor que 0.");
            return false;
        }
        return true;
    }

    static boolean validarCantidad(int cantidad) {
        if (cantidad < 0) {
            System.out.println("La cantidad no puede ser negativa.");
            return false;
        }
        return true;
    }

    // ===== ESTADO DEL STOCK =====
    static String estadoStock() {
        if (cantidadInventario < 5) {
            return "Stock bajo";
        } else if (cantidadInventario <= 20) {
            return "Stock suficiente";
        } else {
            return "Stock alto";
        }
    }
}
