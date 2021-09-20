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

public class CrearCliente extends JFrame implements ActionListener {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;
    JTextField username;
    JPasswordField password;
    JPasswordField passwordComprobation;
    JButton crearCliente;
    JTextField  ID, Nombre,Direccion,Numero,NIT;



    public CrearCliente(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios) throws HeadlessException {

        setInicio();
        this.restaurante = restaurante;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;
        this.usuarios = usuarios;
        addLabel("Creacion Usuario", 75, 15, 300, 50);
        addLabel1("ID",30, 120, 200, 30);
        ID=addTextfield("", 230, 120, 200, 30);
        addLabel1("Nombre",30, 180, 200, 30);
        Nombre=addTextfield("",230, 180, 200, 30);
        addLabel1("Direccion",30, 240, 200, 30);
        Direccion=addTextfield("", 230, 240, 200, 30);
        addLabel1("Numero",30, 300, 200, 30);
        Numero=addTextfield("", 230, 300, 200, 30);
        addLabel1("NIT",30, 360, 200, 30);
        NIT=addTextfield("", 230, 360, 200, 30);
        crearCliente=addButton("Crear Cliente", 175, 475, 150, 40);

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
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // JOptionPane.showMessageDialog(null, "");

                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
            //llamo a la ventana
            }
        });
        setBounds((ancho/4)+100, ((altura/4)-150), 500, 650);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                JOptionPane.showMessageDialog(this, "El usuario se creo correctamente " + user);
                this.dispose();
                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);

            }

        } else {
            JOptionPane.showMessageDialog(this, "El usuario ya existe");
        }

    }

    public void FuncionBotonAgregar() {
        boolean existe = false;
        try {
            int id = Integer.parseInt(ID.getText());
            String name = Nombre.getText();
            String direccion = Direccion.getText();
            int numero = Integer.parseInt(Numero.getText());
            String nit = NIT.getText();
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getId()==id) {
                    existe = true;
                }
            }

            if (existe == false) {
                if ((name.equals("")) || (direccion.equals(""))||(numero == 0)||(nit.equals(""))||(id==0)) {
                    JOptionPane.showMessageDialog(this, "Faltan datos");
                } else {
                    Cliente creacion = new Cliente(id, name,direccion,numero,nit);
                    clientes.add(creacion);
                    LogErrores.escribirLogErrores("log.log", "CREARCLIENTE", name);
                    JOptionPane.showMessageDialog(this, "El cliente: "+name+" se creo correctamente ");
                    this.dispose();
                    VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);

                }

            } else {
                JOptionPane.showMessageDialog(this, "El cliente ya existe");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Ingresar numeros enteros en el ID y en el numero");

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==crearCliente){
            FuncionBotonAgregar();
        }

    }


}
