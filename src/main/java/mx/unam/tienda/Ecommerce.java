package mx.unam.tienda;

/**
 *
 * @author HP
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mx.unam.tienda.Usuario;
/**
 *
 * @author HP
 */
public class Ecommerce {
    private List<Usuario> listaUsuarios;
    private List<Producto> listaProductos;

    public Ecommerce() {
        listaUsuarios = new ArrayList<>();
        cargarUsuarios();
        listaProductos = new ArrayList<>();
    }

    public boolean autenticarUsuario(String nombreUsuario, String contraseña) {
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Contraseña: " + contraseña);
        System.out.println(listaUsuarios);
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContraseña().equals(contraseña)) {
                System.out.println("Usuario encontrado!");
                return true;
            }
        }
        return false;
    }

    public void cargarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split("\\|");
                if (datos.length == 6) {
                    Usuario usuario = new Usuario();
                    usuario.setNombreUsuario(datos[0]);
                    usuario.setContraseña(datos[1]);
                    usuario.setCorreoElectronico(datos[2]);
                    usuario.setTelefono(datos[3]);
                    usuario.setEntidadFederativa(datos[4]);
                    usuario.setTipoUsuario(datos[5]);
                    listaUsuarios.add(usuario);
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario obtenerUsuario(String nombre) {
        return listaUsuarios.stream()
                .filter(u -> u.getNombreUsuario().equals(nombre))
                .findFirst()
                .orElse(null);
    }
    public List<Producto> cargarProductos() {

        try (BufferedReader reader = new BufferedReader(new FileReader("productos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split("\\|");
                if (datos.length == 4) {
                    Producto producto = new Producto();
                    producto.setEan(datos[0]);
                    producto.setDescripcion(datos[1]);
                    producto.setPrecio(datos[2]);
                    producto.setTipo(datos[3]);
                    listaProductos.add(producto);
                    
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println(listaProductos);
         // Mostrar productos antes de ordenar
        System.out.println("Productos antes de ordenar:");
        listaProductos.forEach(System.out::println);

        // Convertir la lista a un arreglo
        Producto[] productosArray = listaProductos.toArray(new Producto[0]);

        // Ordenar el arreglo de productos usando Arrays.sort()
        Arrays.sort(productosArray);

        // Actualizar la lista original con los productos ordenados
        listaProductos = new ArrayList<>(Arrays.asList(productosArray));

        // Mostrar productos después de ordenar
        System.out.println("\nProductos después de ordenar:");
        System.out.println(listaProductos.get(0));
        listaProductos.forEach(System.out::println);
        return listaProductos;
    }
   
}
