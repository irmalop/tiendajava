package mx.unam.tienda;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class Archivo {
    private String nombre;
    public Archivo(String nombre){
        this.nombre = nombre;
    }
    public boolean guardarUsuario(Usuario miUsuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre, true))) {
            writer.append(miUsuario.getNombreUsuario());
            writer.append("|");
            writer.append(miUsuario.getContraseña());
            writer.append("|");
            writer.append(miUsuario.getCorreoElectronico());
            writer.append("|");
            writer.append(miUsuario.getTelefono());
            writer.append("|");
            writer.append(miUsuario.getEntidadFederativa());
            writer.append("|");
            writer.append(miUsuario.getTipoUsuario());
            writer.newLine(); 
        }catch(IOException ex){
            return false;
        }
        return true;
    }
    public boolean guardarProducto(Producto miProducto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre, true))) {
            writer.append(miProducto.getEan());
            writer.append("|");
            writer.append(miProducto.getDescripcion());
            writer.append("|");
            writer.append(miProducto.getPrecio());
            writer.append("|");
            writer.append(miProducto.getTipo());
            writer.newLine(); 
        }catch(IOException ex){
            return false;
        }
        return true;
    }
    public boolean guardarCarrito(ProductoCarrito miProductoCarrito) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre, true))) {
            writer.append(miProductoCarrito.getProducto().getEan()).append("|")
                  .append(miProductoCarrito.getProducto().getDescripcion()).append("|")
                  .append(String.valueOf(miProductoCarrito.getProducto().getPrecio())).append("|")
                  .append(miProductoCarrito.getProducto().getTipo()).append("|")
                  .append(String.valueOf(miProductoCarrito.getCantidad())).append("\n");
        } catch(IOException ex){
            return false;
        }
        return true;
    }

    
}
