package crudUsuarios;

import interfazClase.VentanaAdministracion;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CrearUsuario extends JFrame implements ActionListener {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;
    JTextField username;
    JPasswordField password;
    JPasswordField passwordComprobation;
    JButton crearUsuario;
    String Username;


    public CrearUsuario(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios) throws HeadlessException {

        setInicio();
        this.restaurante = restaurante;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;
        this.usuarios = usuarios;
        addLabel("Creacion Usuario", 200, 15, 300, 50);
        addLabel1("Username", 60, 150, 150, 50);
        username=addTextfield("", 200, 150, 300, 40 );
        addLabel1("Password", 60, 225, 150, 50);
        password=addPasswordField("", 200, 225, 300, 40 );
        addLabel1("Comprobacion Pass", 60, 300, 250, 50);
        passwordComprobation=addPasswordField("", 320, 300, 300, 40 );
        crearUsuario=addButton("Crear Usuario", 250, 390, 170, 50);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // JOptionPane.showMessageDialog(null, "");
                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });

    }

    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Crear usuario");
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#1C2833"));
        setLocation(ancho/4, ((altura/4)-100));
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

    public void funcionalidadBotonCrear(String username, String password) {
        boolean existe = false;
        String user = username;
        Username =user;
        String pass = password;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(user)) {
                existe = true;
            }
        }

        if (existe == false) {
            if ((user.equals("")) && (pass.equals(""))) {
                JOptionPane.showMessageDialog(this, "Falta un dato");
            } else {
                Usuario creacion = new Usuario(user, pass);
                usuarios.add(creacion);
                JOptionPane.showMessageDialog(this, "El usuario se creo correctamente ");
                this.dispose();
                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);

            }

        } else {
            JOptionPane.showMessageDialog(this, "El usuario ya existe");
        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==crearUsuario){

            if (!(password.getText().equals(passwordComprobation.getText()))){
                JOptionPane.showMessageDialog(this, "Las passwords no coinciden!");
            }
            else {
                funcionalidadBotonCrear(username.getText(), password.getText());

                LogErrores.escribirLogErrores("log.log", "CREARUSER", Username);
            }
        }

    }
}
