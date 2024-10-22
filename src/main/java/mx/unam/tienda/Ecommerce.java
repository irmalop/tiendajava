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
import java.util.List;

import mx.unam.tienda.Usuario;
/**
 *
 * @author HP
 */
public class Ecommerce {
    private List<Usuario> listaUsuarios;

    public Ecommerce() {
        listaUsuarios = new ArrayList<>();
        cargarUsuarios();
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
   
}
