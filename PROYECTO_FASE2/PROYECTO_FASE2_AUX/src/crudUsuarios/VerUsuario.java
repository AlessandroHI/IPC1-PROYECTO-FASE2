package crudUsuarios;

import interfazClase.VentanaAdministracion;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VerUsuario extends JFrame {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;
    String user ;




    public VerUsuario(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios,String username) throws HeadlessException {

        setInicio();
        this.restaurante = restaurante;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;
        this.usuarios = usuarios;
        this.user=username;



        addLabel1("Usuario",30, 60, 200, 30);

        addLabel1("Contraseña",30, 90, 200, 30);

        BuscarUser(this.user);


    }

    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Ver Usuario");
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
        setBounds((ancho/4)+100, ((altura/4)-150), 400, 200);
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

    public void BuscarUser(String user){

        for (int i=0; i<usuarios.size();i++){
            if (usuarios.get(i).getUsername().equals(user)){


                addLabel1(usuarios.get(i).getUsername(),200, 60, 400, 30);

                addLabel1(usuarios.get(i).getPassword(),200, 90, 400, 30);



            }

        }

    }
}
