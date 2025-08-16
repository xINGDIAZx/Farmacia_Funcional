/*
* Creado Por Hector Mauricio Delgado Diaz
* 
* Profesora: Yury Montoya Pérez (Lenguaje de Programación 1 - 2507B04G1
*
* Uniremington 2025
 */
package farmaciafuncional; // Declara el paquete al que pertenece esta clase
import java.time.LocalDate; // Importa la clase para manejar fechas de manera moderna

/**
 *
 * @author Hector Mauricio Delgado Diaz
 */

// Clase que representa una venta en el sistema de farmacia
public class Venta {
    
    // Atributos privados de la clase Venta
    
    // Código único de la venta (ej: "V001", "V002", etc.)
    private String codigo;
    
    // ID del producto que se vendió (referencia al producto del inventario)
    private String idProducto;
    
    // Fecha en que se realizó la venta (usando LocalDate para manejo moderno de fechas)
    private LocalDate fecha;
    
    // Cantidad de unidades vendidas del producto
    private int cantidad;
    
    // Valor total de la venta en dólares (precio unitario × cantidad)
    private double total;

    // Constructor de la clase Venta
    // Recibe todos los parámetros necesarios para crear una venta completa
    public Venta(String codigo, String idProducto, LocalDate fecha, int cantidad, double total) {
        // Asigna el valor del parámetro codigo al atributo codigo de la instancia
        this.codigo = codigo;
        // Asigna el valor del parámetro idProducto al atributo idProducto de la instancia
        this.idProducto = idProducto;
        // Asigna el valor del parámetro fecha al atributo fecha de la instancia
        this.fecha = fecha;
        // Asigna el valor del parámetro cantidad al atributo cantidad de la instancia
        this.cantidad = cantidad;
        // Asigna el valor del parámetro total al atributo total de la instancia
        this.total = total;
    }

    // Métodos getters y setters para acceder y modificar los atributos privados
    
    // GETTERS: Métodos para obtener los valores de los atributos
    
    // Obtiene el código de la venta
    public String getCodigo() { 
        return codigo; // Retorna el valor del atributo codigo
    }
    
    // SETTERS: Métodos para modificar los valores de los atributos
    
    // Establece un nuevo código para la venta
    public void setCodigo(String codigo) { 
        this.codigo = codigo; // Asigna el nuevo valor al atributo codigo
    }
    
    // Obtiene el ID del producto vendido
    public String getIdProducto() { 
        return idProducto; // Retorna el valor del atributo idProducto
    }
    
    // Establece un nuevo ID de producto para la venta
    public void setIdProducto(String idProducto) { 
        this.idProducto = idProducto; // Asigna el nuevo valor al atributo idProducto
    }
    
    // Obtiene la fecha de la venta
    public LocalDate getFecha() { 
        return fecha; // Retorna el valor del atributo fecha
    }
    
    // Establece una nueva fecha para la venta
    public void setFecha(LocalDate fecha) { 
        this.fecha = fecha; // Asigna el nuevo valor al atributo fecha
    }
    
    // Obtiene la cantidad vendida
    public int getCantidad() { 
        return cantidad; // Retorna el valor del atributo cantidad
    }
    
    // Establece una nueva cantidad para la venta
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; // Asigna el nuevo valor al atributo cantidad
    }
    
    // Obtiene el total de la venta
    public double getTotal() { 
        return total; // Retorna el valor del atributo total
    }
    
    // Establece un nuevo total para la venta
    public void setTotal(double total) { 
        this.total = total; // Asigna el nuevo valor al atributo total
    }

    // Método que sobrescribe el método toString() de la clase Object
    // Se ejecuta automáticamente cuando se imprime un objeto Venta
    @Override
    public String toString() {
        // Retorna una representación en texto de la venta con formato legible
        return "Código: " + codigo + ", ID Producto: " + idProducto + 
               ", Fecha: " + fecha + ", Cantidad: " + cantidad + ", Total: $" + total;
    }
}
