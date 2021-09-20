package interfazClase;

import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaInicio extends JFrame implements ActionListener {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Usuario> usuarios;
    ArrayList<Cliente> clientes;
    JButton buttonMenuAdministracion, buttonModificarRestaurante;

    public PantallaInicio(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Usuario> usuarios, ArrayList<Cliente> clientes){
        setInicio();
        this.restaurante=restaurante;
        this.usuarios=usuarios;
        this.clientes=clientes;
        this.productos=productos;
        this.facturas=facturas;

        //Labels
        addLabel("Menu Princial", 225, 15, 250, 50);
        addLabel1("Informacion Restaurante:", 50, 65, 300, 50);
        addLabel1("Nombre:", 75, 100, 300, 50);
        addLabel1(restaurante.getName(), 180, 100, 350, 50);
        addLabel1("Numero de telefono:", 75, 130, 300, 50);
        addLabel1(""+restaurante.getPhone(), 320, 130, 350, 50);
        addLabel1("Direccion:", 75, 160, 300, 50);
        addLabel1(restaurante.getAddress(), 200, 160, 400, 50);

        //Button
        buttonMenuAdministracion=addButton("Menu Administracion", 140, 280, 400, 50);
        buttonModificarRestaurante=addButton("Modificar Restaurante", 140, 370, 400, 50);

    }

    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Menu Principal");
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#1C2833"));
        setLocation(ancho/4, ((altura/4)-130));
        setSize(680, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void addLabel(String titulo, int x, int y, int width, int height){
        JLabel anadirLabel=new JLabel(titulo);
        anadirLabel.setBounds(x,y,width,height);
        anadirLabel.setFont(new Font("Arial", Font.BOLD, 35));
        Color myColor = Color.decode("#345FE3");
        anadirLabel.setForeground(myColor);
        add(anadirLabel);
        repaint();
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

    public JButton addButton(String title, int x, int y, int width, int height){
        JButton button=new JButton(title);
        button.setBounds(x,y,width,height);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        Color myColor = Color.decode("#345FE3");
        button.setBackground(myColor);
        Color myColor2 = Color.decode("#FBFCFC");
        button.setForeground(myColor2);
        button.addActionListener(this);
        add(button);
        repaint();
        return button;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==buttonMenuAdministracion){
            this.dispose();
            this.setVisible(false);
            new VentanaAdministracion(restaurante, facturas, productos,usuarios, clientes);


        }else if (e.getSource()==buttonModificarRestaurante){
            this.dispose();
            this.setVisible(false);
            new ActualizarRestaurante(restaurante, facturas, productos, usuarios, clientes);

        }
    }
}
