package crudProdutos;

import interfazClase.VentanaAdministracion;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ActualizarProducto extends JFrame implements ActionListener {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;
    JTextField username;
    JPasswordField password;
    JPasswordField passwordComprobation;
    JButton actualizarProducto, agregarIngrediente, eliminarIngrediente;
    JTextField  ID, nombre, descripcion, precio, costo;
    Producto productoAux=new Producto();
    public ArrayList<Ingredients> ingredientsArrayList = new ArrayList<Ingredients>();
    boolean ingresoIngredientes=false;
    int actualizarIdProducto;

    JTextField name, quantity, units;


    public ActualizarProducto(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios, int actualizarIdProducto) throws HeadlessException {

        setInicio();
        this.actualizarIdProducto=actualizarIdProducto;
        this.restaurante = restaurante;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;
        this.usuarios = usuarios;
        addLabel("Actualizacion Producto", 280, 15, 400, 50);
        addLabel1("Datos producto",30, 90, 200, 30);
        addLabel1("Agregar Ingredientes",525, 90, 250, 30);
        addLabel1("Nombre",30, 160, 200, 30);
        nombre=addTextfield("", 230, 160, 200, 30);
        addLabel1("Descripcion",30, 220, 200, 30);
        descripcion=addTextfield("",230, 220, 200, 30);
        addLabel1("Costo",30, 280, 200, 30);
        costo=addTextfield("", 230, 280, 200, 30);
        addLabel1("Precio",30, 340, 200, 30);
        precio=addTextfield("", 230, 340, 200, 30);
        actualizarProducto=addButton("Actualizar Producto", 370, 575, 200, 40);

        //Datos Ingredientes
        addLabel1("Nombre", 525, 160, 200, 30);
        name=addTextfield("",665, 160, 200, 30);
        addLabel1("Cantidad", 525, 220, 200, 30);
        quantity=addTextfield("",665, 220, 200, 30);
        addLabel1("Unidades", 525, 280, 200, 30);
        units=addTextfield("",665, 280, 200, 30);
        agregarIngrediente=addButton("Agregar ingrediente", 665, 340, 200, 30);

        addLabel1("Eliminar Ingredientes",525, 430, 300, 30);
        eliminarIngrediente=addButton("Eliminar ingrediente", 525, 480, 200, 30);


    }

    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Actualizar Producto");
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
        setBounds((ancho/4)-125, ((altura/4)-175), 950, 700);
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

        if (e.getSource()==actualizarProducto){
            for (Producto producto:productos){

                if (producto.getId()==actualizarIdProducto){
                    producto.setName(nombre.getText());
                    producto.setDescription(descripcion.getText());
                    producto.setCost(Double.parseDouble(costo.getText()));
                    producto.setPrice(Double.parseDouble(costo.getText()));

                    JOptionPane.showMessageDialog(this, "El producto se actualizo correctamente " );
                    this.dispose();
                    VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                    break;
                }
                else if (!(producto.getId()==actualizarIdProducto)){
                    continue;
                }


            }
        }
        else if (e.getSource()==agregarIngrediente){
            try{
                for (Producto producto:productos){

                    if (producto.getId()==actualizarIdProducto){
                        producto.agregarProducto(name.getText(), Double.parseDouble(quantity.getText()), units.getText());
                        JOptionPane.showMessageDialog(this, "Producto agregado con exito");
                    }
                    else if (!(producto.getId()==actualizarIdProducto)){
                        continue;
                    }


                }

            }catch (Exception ea){
                JOptionPane.showMessageDialog(this, "        Dato erroneo! ");
                ea.printStackTrace();
            }
        }
        else if (e.getSource()==eliminarIngrediente){
            try{
                String nombre=JOptionPane.showInputDialog(this, "Ingrese nombre del producto a elimniar");
                for (Producto producto:productos){

                    if (producto.getId()==actualizarIdProducto){
                        producto.removeIngrediente(nombre, this);
                    }
                    else if (!(producto.getId()==actualizarIdProducto)){
                        continue;
                    }


                }

            }catch (Exception ea){
                JOptionPane.showMessageDialog(this, "        Dato erroneo! ");
                ea.printStackTrace();
            }
        }

    }
}
