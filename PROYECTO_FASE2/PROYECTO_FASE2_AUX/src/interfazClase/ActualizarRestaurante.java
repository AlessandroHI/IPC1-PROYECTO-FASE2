package interfazClase;

import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ActualizarRestaurante extends JFrame implements ActionListener {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Usuario> usuarios;
    ArrayList<Cliente> clientes;
    JButton actualizarDatos;
    JTextField nombre, direccion, telefono;
    JPasswordField password;

    public ActualizarRestaurante(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Usuario> usuarios, ArrayList<Cliente> clientes){

        setInicio();
        this.restaurante=restaurante;
        this.usuarios=usuarios;
        this.clientes=clientes;
        this.productos=productos;
        this.facturas=facturas;
        addLabel("Actualizar Restaurante",180, 0, 420, 100);
        addLabel1("Nombre", 90, 105, 150, 50);
        nombre=addTextfield("",230, 110, 350, 50);
        addLabel1("Direccion", 90, 195, 150, 50);
        direccion=addTextfield("",230, 190, 350, 50);
        addLabel1("Telefono", 90, 275, 150, 50);
        telefono=addTextfield("",230, 270, 350, 50);
        actualizarDatos=addButton("Actualizar datos", 250, 365, 170, 50);

    }

    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Actualizar Restaurante");
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#1C2833"));
        setLocation(ancho/4, ((altura/4)-100));
        setSize(680, 550);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // JOptionPane.showMessageDialog(null, "");
                new PantallaInicio(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });
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

    public JTextField addTextfield(String texto, int x, int y, int width, int height){
        JTextField txtUser=new JTextField(texto);
        txtUser.setBounds(x,y,width,height);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 18));
        Color myColor = Color.decode("#1C2833");
        txtUser.setBackground(myColor);
        Color myColor2 = Color.decode("#FBFCFC");
        txtUser.setForeground(myColor2);
        add(txtUser);
        repaint();
        return txtUser;
    }

    public JPasswordField addPasswordField(String texto, int x, int y, int width, int height){
        JPasswordField passUser=new JPasswordField(texto);
        passUser.setBounds(x,y,width,height);
        passUser.setFont(new Font("Arial", Font.PLAIN, 18));
        Color myColor = Color.decode("#1C2833");
        passUser.setBackground(myColor);
        Color myColor2 = Color.decode("#FBFCFC");
        passUser.setForeground(myColor2);
        add(passUser);
        repaint();
        return passUser;
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

        boolean usuarioEncontrado=false;
        boolean usuarioEncontradoWrongPass=false;

        if (e.getSource()==actualizarDatos){

            try{
                this.restaurante.setName(nombre.getText());
                this.restaurante.setAddress(direccion.getText());
                this.restaurante.setPhone(Integer.parseInt(telefono.getText()));

            }catch (Exception ea){
                JOptionPane.showMessageDialog(this, "     Dato erroneo!");
            }
            this.dispose();
            this.setVisible(false);
            new PantallaInicio(restaurante, facturas, productos, usuarios, clientes);
        }

    }
}
