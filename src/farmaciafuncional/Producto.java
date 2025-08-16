/*
* Creado Por Hector Mauricio Delgado Diaz
* 
* Profesora: Yury Montoya Pérez (Lenguaje de Programación 1 - 2507B04G1
*
* Uniremington 2025
 */
package farmaciafuncional; // Declara el paquete al que pertenece esta clase

/**
 *
 * @author Hector Mauricio Delgado Diaz
 */

// Clase que representa un producto en el sistema de farmacia
public class Producto {
    
    // Atributos privados de la clase Producto
    
    // ID único del producto (String para permitir códigos alfanuméricos como "P001", "MED001", etc.)
    private String id;
    
    // Nombre del producto (ej: "Paracetamol", "Ibuprofeno")
    private String nombre;
    
    // Precio del producto en dólares (double para manejar decimales como $5.99)
    private double precio;
    
    // Descripción detallada del producto (ej: "Analgésico y antipirético")
    private String descripcion;
    
    // Cantidad disponible en stock (número entero)
    private int cantidad;

    // Constructor de la clase Producto
    // Recibe todos los parámetros necesarios para crear un producto completo
    public Producto(String id, String nombre, double precio, String descripcion, int cantidad) {
        // Asigna el valor del parámetro id al atributo id de la instancia
        this.id = id;
        // Asigna el valor del parámetro nombre al atributo nombre de la instancia
        this.nombre = nombre;
        // Asigna el valor del parámetro precio al atributo precio de la instancia
        this.precio = precio;
        // Asigna el valor del parámetro descripcion al atributo descripcion de la instancia
        this.descripcion = descripcion;
        // Asigna el valor del parámetro cantidad al atributo cantidad de la instancia
        this.cantidad = cantidad;
    }

    // Métodos getters y setters para acceder y modificar los atributos privados
    
    // GETTERS: Métodos para obtener los valores de los atributos
    
    // Obtiene el ID del producto
    public String getId() { 
        return id; // Retorna el valor del atributo id
    }
    
    // SETTERS: Métodos para modificar los valores de los atributos
    
    // Establece un nuevo ID para el producto
    public void setId(String id) { 
        this.id = id; // Asigna el nuevo valor al atributo id
    }
    
    // Obtiene el nombre del producto
    public String getNombre() { 
        return nombre; // Retorna el valor del atributo nombre
    }
    
    // Establece un nuevo nombre para el producto
    public void setNombre(String nombre) { 
        this.nombre = nombre; // Asigna el nuevo valor al atributo nombre
    }
    
    // Obtiene el precio del producto
    public double getPrecio() { 
        return precio; // Retorna el valor del atributo precio
    }
    
    // Establece un nuevo precio para el producto
    public void setPrecio(double precio) { 
        this.precio = precio; // Asigna el nuevo valor al atributo precio
    }
    
    // Obtiene la descripción del producto
    public String getDescripcion() { 
        return descripcion; // Retorna el valor del atributo descripcion
    }
    
    // Establece una nueva descripción para el producto
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; // Asigna el nuevo valor al atributo descripcion
    }
    
    // Obtiene la cantidad en stock del producto
    public int getCantidad() { 
        return cantidad; // Retorna el valor del atributo cantidad
    }
    
    // Establece una nueva cantidad en stock para el producto
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; // Asigna el nuevo valor al atributo cantidad
    }

    // Método que sobrescribe el método toString() de la clase Object
    // Se ejecuta automáticamente cuando se imprime un objeto Producto
    @Override
    public String toString() {
        // Retorna una representación en texto del producto con formato legible
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precio + 
               ", Descripción: " + descripcion + ", Cantidad: " + cantidad;
    }
}