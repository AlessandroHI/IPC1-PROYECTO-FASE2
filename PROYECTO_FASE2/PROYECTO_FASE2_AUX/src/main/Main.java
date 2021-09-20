package main;

import crudCliente.ActualizarCliente;
import crudCliente.CrearCliente;
import interfazClase.Login;
import interfazClase.PantallaInicio;
import manejoDatos.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Restaurante restaurante = new Restaurante();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        ArrayList<Producto> productos = new ArrayList<Producto>();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();


        //Lectura Jsons
        LecturaJsonArchivos lecturaArchivos = new LecturaJsonArchivos();
        restaurante = lecturaArchivos.lecturaJsonRestaurantes("config.json");
        clientes = lecturaArchivos.lecturaJsonClientes("clients.json");
        usuarios = lecturaArchivos.lecturaJsonUsers("users.json");
        facturas = lecturaArchivos.lecturaJsonFacturas("invoices.json");
        productos = lecturaArchivos.lecturaJsonProductos("products.json");


        new Login(restaurante, facturas, productos, usuarios, clientes);
    }
}
