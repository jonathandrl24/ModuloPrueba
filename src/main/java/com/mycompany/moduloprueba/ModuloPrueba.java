/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.moduloprueba;

/**
 *
 * @author jonathan
 */
public class ModuloPrueba {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();    
        
        // 1. Insertar un nuevo cliente
        System.out.println("===== INSERTAR CLIENTE =====");
        Cliente nuevoCliente = new Cliente();  // Usamos el constructor vacío
        nuevoCliente.setNombre("Carlos Pérez");
        nuevoCliente.setDireccion("Calle 123");
        nuevoCliente.setTelefono("123456789");
        nuevoCliente.setCorreoElectronico("carlos.perez@example.com");
        nuevoCliente.setContrasena("password123");

        clienteDAO.insertCliente(nuevoCliente);

        // 2. Consultar cliente por nombre
        System.out.println("\n===== CONSULTAR CLIENTE POR NOMBRE =====");
        Cliente clienteConsultado = clienteDAO.consultarCliente("Carlos Pérez");

        if (clienteConsultado != null) {
            System.out.println("Cliente encontrado: " + clienteConsultado.getNombre() + " , email: " + clienteConsultado.getCorreoElectronico() + " , Dirección: " + clienteConsultado.getDireccion() + " , Telefono: " + clienteConsultado.getTelefono() + " , Contraseña: " + clienteConsultado.getContrasena());
        } else {
            System.out.println("Cliente no encontrado");
        }

        // 3. Actualizar los datos del cliente
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1); // ID del cliente que quieres actualizar
        cliente.setNombre("Eduardo Gómez");
        cliente.setDireccion("Avenida Siempre Viva 742");
        cliente.setTelefono("987654321");
        cliente.setCorreoElectronico("Eduardo.gomez@example.com");
        cliente.setContrasena("nuevaPassword123");

        // Llamando al método actualizarCliente para actualizar los datos del cliente
        clienteDAO.actualizarCliente(cliente);
    
        // 4. Eliminar cliente
        clienteDAO.eliminarCliente(1);

    }
}