package crudFactura;

import interfazClase.VentanaAdministracion;
import jdk.nashorn.internal.scripts.JO;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CrearFactura extends JFrame implements ActionListener {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;
    JTextField username;
    JPasswordField password;
    JPasswordField passwordComprobation;
    JButton crearFactura, agregarFactura;
    JTextField  ID, Nombre, cliente,fecha;
    Factura facturaAux=new Factura();
    public ArrayList<Products> productsArrayList = new ArrayList<Products>();
    boolean ingresoFactura=false;

    JTextField name, price;


   /* public static void main(String[] args) {

        Restaurante restaurante=new Restaurante();
        ArrayList<Factura> facturas= new ArrayList<Factura>();
        ArrayList<Producto> productos= new ArrayList<Producto>();
        ArrayList<Cliente> clientes=new ArrayList<Cliente>();
        ArrayList<Usuario> usuarios=new ArrayList<Usuario>();

        new CrearFactura(restaurante,facturas,productos,clientes,usuarios);

    }*/


    public CrearFactura(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios) throws HeadlessException {

        setInicio();
        this.restaurante = restaurante;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;
        this.usuarios = usuarios;
        addLabel("Creacion Factura", 330, 15, 350, 50);
        addLabel1("Datos producto",30, 90, 200, 30);
        addLabel1("Agregar Ingredientes",525, 90, 250, 30);
        addLabel1("ID",30, 160, 200, 30);
        ID=addTextfield("", 230, 160, 200, 30);
        addLabel1("Cliente",30, 220, 200, 30);
        cliente=addTextfield("",230, 220, 200, 30);
        addLabel1("Fecha",30, 280, 200, 30);
        fecha=addTextfield("", 230, 280, 200, 30);
        crearFactura=addButton("Crear Factura", 370, 420, 200, 40);

        //Datos Ingredientes
        addLabel1("Nombre", 525, 160, 200, 30);
        name=addTextfield("",665, 160, 200, 30);
        addLabel1("Precio", 525, 220, 200, 30);
        price=addTextfield("",665, 220, 200, 30);
        agregarFactura=addButton("Agregar producto", 665, 280, 200, 50);

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
        setBounds((ancho/4)-125, ((altura/4)-120), 950, 580);
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
            if (true){

            } else {
                JOptionPane.showMessageDialog(this, "El cliente ya existe");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Ingresar numeros enteros en el ID y en el numero");

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==crearFactura){
            try{
                boolean existeFactura=false;

                for (Factura factura:facturas){

                    if (factura.getId() == Integer.parseInt(ID.getText())){
                        existeFactura=true;
                        JOptionPane.showMessageDialog(this, "Ya existe la factura");
                        break;
                    }
                    else {
                        continue;
                    }
                }

                if (existeFactura==false){
                    try{
                        if (ingresoFactura==true){
                            LogErrores.escribirLogErrores("log.log", "CREARFACTURA", fecha.getText());
                            facturaAux=new Factura(Integer.parseInt(ID.getText()), Integer.parseInt(cliente.getText()), fecha.getText(), this.productsArrayList);

                            this.facturas.add(facturaAux);

                            JOptionPane.showMessageDialog(this, "Factura creada con exito");
                            this.dispose();
                            this.setVisible(false);
                            new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);

                        }else {

                            facturaAux=new Factura(Integer.parseInt(ID.getText()), Integer.parseInt(cliente.getText()), fecha.getText(), this.productsArrayList);

                            this.facturas.add(facturaAux);

                            JOptionPane.showMessageDialog(this, "Factura creado con exito");
                            this.dispose();
                            this.setVisible(false);
                            new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);

                        }


                    }catch (Exception ea){
                        JOptionPane.showMessageDialog(this, "        Dato erroneo! ");
                        ea.printStackTrace();
                    }
                }
            }catch (Exception a){
                JOptionPane.showMessageDialog(this, "        Dato erroneo! ");
            }
        }
        else if (e.getSource()==agregarFactura){
            try{
                ingresoFactura=true;
                Products products=new Products(name.getText(), Integer.parseInt(price.getText()));
                this.productsArrayList.add(products);
                JOptionPane.showMessageDialog(this, "Producto agregado con exito!");

            }catch (Exception ea){
                JOptionPane.showMessageDialog(this, "        Dato erroneo! ");
            }
        }

    }
}
