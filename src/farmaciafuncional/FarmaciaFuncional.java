/*
* Creado Por Hector Mauricio Delgado Diaz
* 
* Profesora: Yury Montoya Pérez (Lenguaje de Programación 1 - 2507B04G1
*
* Uniremington 2025
 */

package farmaciafuncional; // Declara el paquete al que pertenece esta clase
import java.time.LocalDate; // Importa la clase para manejar fechas
import java.util.*; // Importa todas las clases de utilidades (List, ArrayList, Scanner, etc.)
import java.util.stream.*; // Importa las clases para programación funcional con streams

/**
 *
 * @author Hector Mauricio Delgado Diaz
 */

public class FarmaciaFuncional { // Declara la clase principal del sistema de farmacia
    // Lista estática para almacenar todos los productos del inventario
    private static List<Producto> inventario = new ArrayList<>();
    // Lista estática para almacenar todas las ventas realizadas
    private static List<Venta> ventas = new ArrayList<>();
    // Scanner estático para leer entrada del usuario desde la consola
    private static Scanner scanner = new Scanner(System.in);

    // Método principal que se ejecuta al iniciar el programa
    public static void main(String[] args) {
        // Cargar algunos datos de ejemplo para probar el sistema
        cargarDatosEjemplo();
        
        // Mostrar el menú principal del sistema
        mostrarMenu();
    }

    /**
     * Método para cargar datos de ejemplo
     */
    private static void cargarDatosEjemplo() {
        // Agregar productos de ejemplo al inventario
        inventario.add(new Producto("P001", "Paracetamol", 5.99, "Analgésico y antipirético", 100));
        inventario.add(new Producto("P002", "Ibuprofeno", 7.50, "Antiinflamatorio no esteroideo", 80));
        inventario.add(new Producto("P003", "Amoxicilina", 12.75, "Antibiótico de amplio espectro", 50));
        inventario.add(new Producto("P004", "Omeprazol", 9.99, "Inhibidor de la bomba de protones", 60));
        
        // Obtener la fecha actual
        LocalDate hoy = LocalDate.now();
        // Agregar ventas de ejemplo
        ventas.add(new Venta("V001", "P001", hoy.minusDays(2), 2, 11.98));
        ventas.add(new Venta("V002", "P002", hoy.minusDays(1), 1, 7.50));
        ventas.add(new Venta("V003", "P001", hoy, 3, 17.97));
    }

    /**
     * Método que muestra el menú principal y maneja las opciones
     */
    private static void mostrarMenu() {
        // Mostrar el encabezado del menú principal
        System.out.println("\n=== SISTEMA DE GESTIÓN DE FARMACIA ===");
        // Mostrar todas las opciones disponibles
        System.out.println("1. Registrar nueva venta");
        System.out.println("2. Consultar información de ventas");
        System.out.println("3. Mostrar valor total de compras");
        System.out.println("4. Consultar información de productos");
        System.out.println("5. Cargar nuevo producto");
        System.out.println("6. Actualizar producto existente");
        System.out.println("7. Mostrar estadísticas de ventas");
        System.out.println("8. Mostrar productos más/menos vendidos");
        System.out.println("9. Mostrar productos sin stock");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        // Leer la opción seleccionada por el usuario
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner para evitar problemas de lectura

        // Crear un arreglo de funciones lambda para manejar cada opción del menú
        // Esto es un ejemplo de programación funcional
        Runnable[] opciones = {
            () -> System.exit(0), // Opción 0: Salir del programa
            FarmaciaFuncional::registrarVenta, // Opción 1: Referencia al método registrarVenta
            FarmaciaFuncional::consultarVentas, // Opción 2: Referencia al método consultarVentas
            FarmaciaFuncional::mostrarTotalCompras, // Opción 3: Referencia al método mostrarTotalCompras
            FarmaciaFuncional::consultarProductos, // Opción 4: Referencia al método consultarProductos
            FarmaciaFuncional::cargarProducto, // Opción 5: Referencia al método cargarProducto
            FarmaciaFuncional::actualizarProducto, // Opción 6: Referencia al método actualizarProducto
            FarmaciaFuncional::mostrarEstadisticas, // Opción 7: Referencia al método mostrarEstadisticas
            FarmaciaFuncional::mostrarProductosDestacados, // Opción 8: Referencia al método mostrarProductosDestacados
            FarmaciaFuncional::mostrarProductosSinStock // Opción 9: Referencia al método mostrarProductosSinStock
        };

        // Verificar si la opción seleccionada es válida
        if (opcion >= 0 && opcion < opciones.length) {
            opciones[opcion].run(); // Ejecutar la función correspondiente a la opción seleccionada
        } else {
            System.out.println("Opción no válida. Intente nuevamente.");
        }

        // Llamada recursiva para mostrar el menú nuevamente después de ejecutar una opción
        mostrarMenu();
    }

    /**
     * Función para registrar una nueva venta
     */
    private static void registrarVenta() {
        System.out.println("\n=== REGISTRAR NUEVA VENTA ===");
        
        // Mostrar todos los productos disponibles para la venta
        mostrarProductosDisponibles();
        
        // Solicitar al usuario que ingrese el ID del producto a vender
        System.out.print("Ingrese el ID del producto: ");
        String idProducto = scanner.nextLine();
        
        // Buscar el producto en el inventario usando streams y programación funcional
        // filter: filtra productos por ID (ignorando mayúsculas/minúsculas)
        // findFirst: obtiene el primer producto que coincida
        Optional<Producto> productoOpt = inventario.stream()
            .filter(p -> p.getId().equalsIgnoreCase(idProducto))
            .findFirst();
            
        // Verificar si se encontró el producto
        if (!productoOpt.isPresent()) {
            System.out.println("Producto no encontrado.");
            return; // Salir del método si no se encuentra el producto
        }
        
        // Obtener el producto del Optional
        Producto producto = productoOpt.get();
        
        // Solicitar la cantidad a vender
        System.out.print("Ingrese la cantidad a vender: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        // Verificar si hay suficiente stock disponible
        if (cantidad > producto.getCantidad()) {
            System.out.println("No hay suficiente stock disponible.");
            return; // Salir del método si no hay suficiente stock
        }
        
        // Crear un nuevo producto con la cantidad actualizada (principio de inmutabilidad)
        // En lugar de modificar el producto existente, creamos uno nuevo
        Producto productoActualizado = new Producto(
            producto.getId(),
            producto.getNombre(),
            producto.getPrecio(),
            producto.getDescripcion(),
            producto.getCantidad() - cantidad // Reducir la cantidad en stock
        );
        
        // Actualizar el inventario usando programación funcional
        // map: transforma cada producto, reemplazando el que coincide con el ID
        // collect: convierte el stream resultante de vuelta a una lista
        inventario = inventario.stream()
            .map(p -> p.getId().equalsIgnoreCase(idProducto) ? productoActualizado : p)
            .collect(Collectors.toList());
        
        // Generar un código único para la venta
        String codigoVenta = "V" + String.format("%03d", ventas.size() + 1);
        // Calcular el total de la venta
        double total = cantidad * producto.getPrecio();
        
        // Crear y registrar la nueva venta
        Venta nuevaVenta = new Venta(
            codigoVenta,
            producto.getId(),
            LocalDate.now(), // Fecha actual
            cantidad,
            total
        );
        
        // Agregar la venta a la lista de ventas
        ventas.add(nuevaVenta);
        
        // Mostrar confirmación de la venta registrada
        System.out.println("Venta registrada exitosamente:");
        System.out.println(nuevaVenta);
    }

    /**
     * Función para mostrar productos disponibles
     */
    private static void mostrarProductosDisponibles() {
        System.out.println("\nProductos disponibles:");
        // Usar streams para filtrar y mostrar solo productos con stock > 0
        inventario.stream()
            .filter(p -> p.getCantidad() > 0) // Filtrar productos con stock disponible
            .forEach(p -> System.out.println(p)); // Mostrar cada producto
    }

    /**
     * Función para consultar información de ventas
     */
    private static void consultarVentas() {
        System.out.println("\n=== CONSULTAR VENTAS ===");
        // Mostrar submenú de opciones de consulta
        System.out.println("1. Todas las ventas");
        System.out.println("2. Ventas por producto");
        System.out.println("3. Ventas por fecha");
        System.out.print("Seleccione una opción: ");
        
        // Leer la opción del submenú
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        // Usar switch para manejar las diferentes opciones de consulta
        switch (opcion) {
            case 1:
                // Mostrar todas las ventas usando forEach y referencia de método
                System.out.println("\nTodas las ventas:");
                ventas.forEach(System.out::println); // Equivale a: ventas.forEach(v -> System.out.println(v))
                break;
                
            case 2:
                // Solicitar ID del producto para filtrar ventas
                System.out.print("Ingrese el ID del producto: ");
                String idProducto = scanner.nextLine();
                
                // Filtrar ventas por producto usando streams
                System.out.println("\nVentas para el producto " + idProducto + ":");
                ventas.stream()
                    .filter(v -> v.getIdProducto().equalsIgnoreCase(idProducto)) // Filtrar por ID de producto
                    .forEach(System.out::println); // Mostrar cada venta filtrada
                break;
                
            case 3:
                // Solicitar fecha para filtrar ventas
                System.out.print("Ingrese la fecha (AAAA-MM-DD): ");
                LocalDate fecha = LocalDate.parse(scanner.nextLine()); // Convertir string a LocalDate
                
                // Filtrar ventas por fecha usando streams
                System.out.println("\nVentas para la fecha " + fecha + ":");
                ventas.stream()
                    .filter(v -> v.getFecha().equals(fecha)) // Filtrar por fecha exacta
                    .forEach(System.out::println); // Mostrar cada venta filtrada
                break;
                
            default:
                System.out.println("Opción no válida.");
        }
    }

    /**
     * Función para mostrar el valor total de compras
     */
    private static void mostrarTotalCompras() {
        // Calcular el total usando streams y operaciones funcionales
        // mapToDouble: convierte cada venta a su valor total (double)
        // sum: suma todos los valores
        double total = ventas.stream()
            .mapToDouble(Venta::getTotal) // Referencia de método para obtener el total
            .sum();
            
        // Mostrar el resultado formateado
        System.out.println("\nEl valor total de todas las compras es: $" + total);
    }

    /**
     * Función para consultar información de productos
     */
    private static void consultarProductos() {
        System.out.println("\n=== CONSULTAR PRODUCTOS ===");
        // Mostrar submenú de opciones de consulta de productos
        System.out.println("1. Información general (cantidad)");
        System.out.println("2. Información específica de un producto");
        System.out.print("Seleccione una opción: ");
        
        // Leer la opción del submenú
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        // Usar switch para manejar las diferentes opciones
        switch (opcion) {
            case 1:
                // Mostrar información general de todos los productos
                System.out.println("\nInformación general de productos:");
                // Usar forEach con lambda para mostrar información específica de cada producto
                inventario.forEach(p -> 
                    System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre() + 
                                       ", Cantidad: " + p.getCantidad()));
                break;
                
            case 2:
                // Solicitar ID del producto para mostrar información específica
                System.out.print("Ingrese el ID del producto: ");
                String idProducto = scanner.nextLine();
                
                // Buscar y mostrar producto específico usando streams
                // ifPresentOrElse: ejecuta una acción si se encuentra el producto, otra si no
                inventario.stream()
                    .filter(p -> p.getId().equalsIgnoreCase(idProducto)) // Filtrar por ID
                    .findFirst() // Obtener el primer producto que coincida
                    .ifPresentOrElse(
                        System.out::println, // Si se encuentra: mostrar el producto
                        () -> System.out.println("Producto no encontrado.") // Si no se encuentra: mostrar mensaje
                    );
                break;
                
            default:
                System.out.println("Opción no válida.");
        }
    }

    /**
     * Función para cargar un nuevo producto
     */
    private static void cargarProducto() {
        System.out.println("\n=== CARGAR NUEVO PRODUCTO ===");
        
        // Solicitar ID del nuevo producto
        System.out.print("Ingrese ID del producto: ");
        String id = scanner.nextLine();
        
        // Verificar si ya existe un producto con ese ID usando streams
        // anyMatch: devuelve true si algún producto coincide con la condición
        boolean existe = inventario.stream()
            .anyMatch(p -> p.getId().equalsIgnoreCase(id));
            
        // Si ya existe, mostrar mensaje y salir
        if (existe) {
            System.out.println("Ya existe un producto con ese ID.");
            return;
        }
        
        // Solicitar datos del nuevo producto
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        
        System.out.print("Ingrese descripción del producto: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Ingrese cantidad en stock: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        // Crear y agregar el nuevo producto al inventario
        Producto nuevoProducto = new Producto(id, nombre, precio, descripcion, cantidad);
        inventario.add(nuevoProducto);
        
        // Confirmar que se agregó exitosamente
        System.out.println("Producto agregado exitosamente.");
    }

    /**
     * Función para actualizar un producto existente
     */
    private static void actualizarProducto() {
        System.out.println("\n=== ACTUALIZAR PRODUCTO ===");
        
        // Solicitar ID del producto a actualizar
        System.out.print("Ingrese ID del producto a actualizar: ");
        String id = scanner.nextLine();
        
        // Buscar el producto usando streams
        Optional<Producto> productoOpt = inventario.stream()
            .filter(p -> p.getId().equalsIgnoreCase(id))
            .findFirst();
            
        // Si no se encuentra el producto, mostrar mensaje y salir
        if (!productoOpt.isPresent()) {
            System.out.println("Producto no encontrado.");
            return;
        }
        
        // Obtener el producto del Optional
        Producto producto = productoOpt.get();
        
        // Mostrar información actual del producto
        System.out.println("Producto actual:");
        System.out.println(producto);
        
        // Solicitar nuevos datos, permitiendo mantener valores actuales
        System.out.println("\nIngrese nuevos datos (deje en blanco para mantener el valor actual):");
        
        // Solicitar nuevo nombre
        System.out.print("Nombre [" + producto.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) producto.setNombre(nombre); // Solo actualizar si se ingresó algo
        
        // Solicitar nuevo precio
        System.out.print("Precio [" + producto.getPrecio() + "]: ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) producto.setPrecio(Double.parseDouble(precioStr)); // Convertir string a double
        
        // Solicitar nueva descripción
        System.out.print("Descripción [" + producto.getDescripcion() + "]: ");
        String descripcion = scanner.nextLine();
        if (!descripcion.isEmpty()) producto.setDescripcion(descripcion);
        
        // Solicitar nueva cantidad
        System.out.print("Cantidad [" + producto.getCantidad() + "]: ");
        String cantidadStr = scanner.nextLine();
        if (!cantidadStr.isEmpty()) producto.setCantidad(Integer.parseInt(cantidadStr)); // Convertir string a int
        
        // Confirmar que se actualizó exitosamente
        System.out.println("Producto actualizado exitosamente.");
    }

    /**
     * Función para mostrar estadísticas de ventas
     */
    private static void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DE VENTAS ===");
        
        // Obtener fecha actual para cálculos
        LocalDate hoy = LocalDate.now();
        
        // Calcular promedio de ventas en la última semana
        // filter: filtrar ventas de la última semana
        // mapToDouble: convertir a valores double
        // average: calcular promedio
        // orElse: valor por defecto si no hay ventas
        double promedioSemanal = ventas.stream()
            .filter(v -> v.getFecha().isAfter(hoy.minusWeeks(1))) // Ventas después de hace 1 semana
            .mapToDouble(Venta::getTotal) // Obtener total de cada venta
            .average() // Calcular promedio
            .orElse(0.0); // Si no hay ventas, usar 0.0
            
        System.out.println("Promedio de ventas en la última semana: $" + String.format("%.2f", promedioSemanal));
        
        // Calcular promedio de ventas en el último mes
        double promedioMensual = ventas.stream()
            .filter(v -> v.getFecha().isAfter(hoy.minusMonths(1))) // Ventas después de hace 1 mes
            .mapToDouble(Venta::getTotal)
            .average()
            .orElse(0.0);
            
        System.out.println("Promedio de ventas en el último mes: $" + String.format("%.2f", promedioMensual));
        
        // Calcular promedio de ventas en el último año
        double promedioAnual = ventas.stream()
            .filter(v -> v.getFecha().isAfter(hoy.minusYears(1))) // Ventas después de hace 1 año
            .mapToDouble(Venta::getTotal)
            .average()
            .orElse(0.0);
            
        System.out.println("Promedio de ventas en el último año: $" + String.format("%.2f", promedioAnual));
    }

    /**
     * Función para mostrar productos más y menos vendidos
     */
    private static void mostrarProductosDestacados() {
        System.out.println("\n=== PRODUCTOS DESTACADOS ===");
        
        // Agrupar ventas por producto y sumar cantidades usando streams
        // groupingBy: agrupa ventas por ID de producto
        // summingInt: suma las cantidades de cada grupo
        Map<String, Integer> ventasPorProducto = ventas.stream()
            .collect(Collectors.groupingBy(
                Venta::getIdProducto, // Clave: ID del producto
                Collectors.summingInt(Venta::getCantidad) // Valor: suma de cantidades
            ));
        
        // Encontrar el producto más vendido
        // max: encuentra la entrada con el valor máximo
        // comparingByValue: compara por el valor (cantidad vendida)
        ventasPorProducto.entrySet().stream()
            .max(Map.Entry.comparingByValue()) // Encontrar entrada con valor máximo
            .ifPresent(entry -> { // Si se encuentra, ejecutar la acción
                String id = entry.getKey(); // ID del producto
                int cantidad = entry.getValue(); // Cantidad total vendida
                
                // Buscar información del producto en el inventario
                inventario.stream()
                    .filter(p -> p.getId().equals(id)) // Filtrar por ID
                    .findFirst() // Obtener el primer producto que coincida
                    .ifPresent(p -> // Si se encuentra el producto, mostrar información
                        System.out.println("Producto más vendido: " + p.getNombre() + 
                                         ", Precio: $" + p.getPrecio() + 
                                         ", Cantidad vendida: " + cantidad)
                    );
            });
        
        // Encontrar el producto menos vendido
        // min: encuentra la entrada con el valor mínimo
        ventasPorProducto.entrySet().stream()
            .min(Map.Entry.comparingByValue()) // Encontrar entrada con valor mínimo
            .ifPresent(entry -> { // Si se encuentra, ejecutar la acción
                String id = entry.getKey(); // ID del producto
                int cantidad = entry.getValue(); // Cantidad total vendida
                
                // Buscar información del producto en el inventario
                inventario.stream()
                    .filter(p -> p.getId().equals(id)) // Filtrar por ID
                    .findFirst() // Obtener el primer producto que coincida
                    .ifPresent(p -> // Si se encuentra el producto, mostrar información
                        System.out.println("Producto menos vendido: " + p.getNombre() + 
                                         ", Precio: $" + p.getPrecio() + 
                                         ", Cantidad vendida: " + cantidad)
                    );
            });
    }

    /**
     * Función para mostrar productos sin stock
     */
    private static void mostrarProductosSinStock() {
        System.out.println("\n=== PRODUCTOS SIN STOCK ===");
        
        // Filtrar productos con cantidad <= 0 usando streams
        // filter: solo productos sin stock
        // collect: convertir el stream filtrado a una lista
        List<Producto> sinStock = inventario.stream()
            .filter(p -> p.getCantidad() <= 0) // Productos con stock <= 0
            .collect(Collectors.toList());
            
        // Verificar si hay productos sin stock
        if (sinStock.isEmpty()) {
            System.out.println("No hay productos sin stock.");
        } else {
            // Mostrar cada producto sin stock usando forEach
            sinStock.forEach(p -> 
                System.out.println("Nombre: " + p.getNombre() + ", Cantidad: " + p.getCantidad())
            );
        }
    }
}
