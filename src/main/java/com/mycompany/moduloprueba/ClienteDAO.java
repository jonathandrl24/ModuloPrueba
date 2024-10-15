/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.moduloprueba;

/**
 *
 * @author jonathan
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {

    // Definir un logger para registrar errores y eventos
    private static final Logger logger = Logger.getLogger(ClienteDAO.class.getName());

    // 1. Método para insertar un cliente en la base de datos
    public void insertCliente(Cliente cliente) {
        // Consulta SQL para insertar un cliente
        String sql = "INSERT INTO cliente (nombre, direccion, telefono, correo_electronico, contrasena) VALUES (?, ?, ?, ?, ?)";
        
        // Conexión y declaración preparada
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Verificar si la conexión es nula
        if (connection == null) {
            System.out.println("Error: la conexión a la base de datos es nula.");
            return; // Salir del método si no hay conexión
        }

            // Asignar los valores del objeto 'cliente' a la consulta
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getDireccion());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getCorreoElectronico());
            statement.setString(5, cliente.getContrasena());

            // Ejecutar la consulta
            statement.executeUpdate();
            System.out.println("Cliente insertado correctamente");
        
        } catch (SQLException e) {
            // Registrar el error con el logger 
            logger.log(Level.SEVERE, "Error al insertar cliente", e);
        }
    }
    
    // 2. Método para actualizar un cliente en la base de datos
    public void actualizarCliente(Cliente cliente) {
        // Consulta SQL para actualizar los datos de un cliente
        String sql = "UPDATE cliente SET nombre = ?, direccion = ?, telefono = ?, correo_electronico = ?, contrasena = ? WHERE id_cliente = ?";

        // Conexión y declaración preparada
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Asignar los nuevos valores del objeto 'cliente' a la consulta
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getDireccion());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getCorreoElectronico());
            statement.setString(5, cliente.getContrasena());
            statement.setInt(6, cliente.getIdCliente());

            // Ejecutar la consulta de actualización
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Cliente actualizado correctamente");
            } else {
                System.out.println("No se encontró el cliente con el ID proporcionado");
            }

        } catch (SQLException e) {
            // Registrar el error con el logger 
            logger.log(Level.SEVERE, "Error al actualizar cliente", e);
        }
    }
    
    // 3. Método para eliminar un cliente de la base de datos
    public void eliminarCliente(int idCliente) {
        // Consulta SQL para eliminar un cliente según su ID
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        // Conexión y declaración preparada
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Asignar el ID del cliente que se va a eliminar
            statement.setInt(1, idCliente);

            // Ejecutar la consulta de eliminación
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Cliente eliminado correctamente");
            } else {
                System.out.println("No se encontró el cliente con el ID proporcionado");
            }

        } catch (SQLException e) {
            // Registrar el error con el logger 
            logger.log(Level.SEVERE, "Error al eliminar cliente", e);
        }
    }
    
    // 4. Método para buscar un cliente por su nombre
    public Cliente consultarCliente(String nombre) {
        // Consulta SQL para buscar un cliente según su nombre
        String sql = "SELECT * FROM cliente WHERE nombre = ?";
        Cliente cliente = null;

        // Conexión y declaración preparada
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Asignar el nombre del cliente que se va a buscar
            statement.setString(1, nombre);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Si el cliente existe en la base de datos, llenar el objeto Cliente con los datos
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setCorreoElectronico(resultSet.getString("correo_electronico"));
                cliente.setContrasena(resultSet.getString("contrasena"));
                System.out.println("Cliente encontrado: " + cliente.getNombre());
            } else {
                System.out.println("No se encontró el cliente con el nombre proporcionado");
            }

        } catch (SQLException e) {
            // Registrar el error con el logger 
            logger.log(Level.SEVERE, "Error al consultar cliente por nombre", e);
        }

        // Devolver el objeto Cliente o null si no se encontró
        return cliente;
    }
}

