package crudCliente;

import interfazClase.VentanaAdministracion;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VerCliente extends JFrame {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;
    int Indice;




    public VerCliente(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios,int indice) throws HeadlessException {

        setInicio();
        this.restaurante = restaurante;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;
        this.usuarios = usuarios;
        this.Indice=indice;



        addLabel1("ID",30, 60, 200, 30);

        addLabel1("Nombre",30, 90, 200, 30);

        addLabel1("Direccion",30, 120, 200, 30);

        addLabel1("Numero",30, 150, 200, 30);

        addLabel1("NIT",30, 180, 200, 30);
        BuscarCliente(this.Indice);


    }

    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Ver Cliente");
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#1C2833"));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // JOptionPane.showMessageDialog(null, "");

                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });
        setBounds((ancho/4)+100, ((altura/4)-150), 500, 300);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }



    public void addLabel1(String titulo, int x, int y, int width, int height){
        JLabel anadirLabel=new JLabel(titulo);
        anadirLabel.setBounds(x,y,width,height);
        anadirLabel.setFont(new Font("Arial", Font.BOLD, 25));
        Color myColor = Color.decode("#FBFCFC");
        anadirLabel.setForeground(myColor);
        add(anadirLabel);
        repaint();
    }

    public void BuscarCliente(int indice){


        for (int i=0; i<clientes.size();i++){

            if (clientes.get(i).getId()==indice){


                addLabel1(String.valueOf(clientes.get(i).getId()),160, 60, 200, 30);

                addLabel1(clientes.get(i).getName(),160, 90, 400, 30);

                addLabel1(clientes.get(i).getAddress(),160, 120, 400, 30);

                addLabel1(String.valueOf(clientes.get(i).getPhone()),160, 150, 400, 30);

                addLabel1(clientes.get(i).getNit(),160, 180, 400, 30);

            }

        }

    }
}
