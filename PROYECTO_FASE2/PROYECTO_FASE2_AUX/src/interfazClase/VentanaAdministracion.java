package interfazClase;

import manejoDatos.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VentanaAdministracion extends JFrame {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Usuario> usuarios;
    ArrayList<Cliente> clientes;

    JTabbedPane t1,t2;
    JTabbedPane tabbedPane;


    public VentanaAdministracion(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Usuario> usuarios, ArrayList<Cliente> clientes){

        this.restaurante=restaurante;
        this.usuarios=usuarios;
        this.clientes=clientes;
        this.productos=productos;
        this.facturas=facturas;
        setInicio();


    }

    public void setInicio(){
        setTitle("Modulo Administracion");
        setVisible(true);
        //setLayout(null);
        setLocation(125, 50);
        setSize(1100, 650);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // JOptionPane.showMessageDialog(null, "");
                new PantallaInicio(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });
        setResizable(false);

        t1 = new JTabbedPane();

        JPanelUser user = new JPanelUser(this.restaurante,this.usuarios,this,this.facturas,this.productos,this.clientes);
        t1.addTab("Usuarios",user);

        JPanelProducto producto = new JPanelProducto(this.restaurante,this.usuarios,this,this.facturas,this.productos,this.clientes);
        t1.addTab("Productos",producto);

        JPanelCliente clients = new JPanelCliente(this.restaurante,this.usuarios,this,this.facturas,this.productos,this.clientes);
        t1.addTab("Clientes",clients);

        JPanelFactura factura = new JPanelFactura(this.restaurante,this.usuarios,this,this.facturas,this.productos,this.clientes);
        t1.addTab("Facturas",factura);
        t1.setVisible(true);




        this.add(t1);


    }




}
