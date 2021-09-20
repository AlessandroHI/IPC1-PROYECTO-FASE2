package interfazClase;

import ProductosAux.VerProducto;
import com.google.gson.Gson;
import crudCliente.VerCliente;
import crudProdutos.ActualizarProducto;
import crudProdutos.CrearProducto;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JPanelProducto extends JPanel implements ActionListener {

    Restaurante restaurante;
    ArrayList<Usuario> usuarios;
    JFrame VentanaAdministracion, JFrameProducto, ingredient;
    ;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;

    JTextField ID, Nombre, Descripcion, Costo, Precio, NombreIngre, Cantidad, Unidades;


    JButton ver, crear, agregar, eliminar, actualizar, ingredientes, guardar, cerrarSesion;
    JScrollPane table, table2;
    JTable t1;

    public static Producto removeProd = new Producto();
    String user;
    int indice;

    public JPanelProducto(Restaurante restaurante, ArrayList<Usuario> usuarios, JFrame VentanaAdministracion, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes) {
        this.restaurante = restaurante;
        this.usuarios = usuarios;
        this.VentanaAdministracion = VentanaAdministracion;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;


        setPanel();
        tablaProductos();

        ver = addButton("Ver producto ", 825, 50, 190, 50);
        crear = addButton("Crear ", 825, 120, 190, 50);
        actualizar = addButton("Actualizar", 825, 190, 190, 50);
        eliminar = addButton("Eliminar", 825, 260, 190, 50);
        ingredientes = addButton("Ingredientes", 825, 330, 190, 50);
        cerrarSesion = addButton("Cerrar sesion", 825, 475, 190, 50);
        this.setVisible(true);

    }

    public void setPanel() {
        setLayout(null);
        setBounds(50, 50, 300, 300);
        Color myColor = Color.decode("#1C2833");
        setBackground(myColor);
    }

    public void addLabel1(String titulo, int x, int y, int width, int height) {
        JLabel anadirLabel = new JLabel(titulo);
        anadirLabel.setBounds(x, y, width, height);
        anadirLabel.setFont(new Font(null, Font.PLAIN, 20));
        Color myColor = Color.decode("#FBFCFC");
        anadirLabel.setForeground(myColor);
        JFrameProducto.add(anadirLabel);
        repaint();
    }

    public void tablaProductos() {

        Object[][] datos = (ConvertirProductos());
        String[] columnas = {"ID", "Nombre", "Descripcion", "Costo en Q", "Precio en Q", "Cantidad de ingredientes"};
        JTable t2 = new JTable(datos, columnas);
        t2.repaint();
        add(t2);
        table = new JScrollPane(t2);
        table.setBounds(20, 20, 700, 540);
        table.setVisible(true);
        add(table);

    }

    public void tablaIngredientes(int indice) {

        Object[][] datos = ((ConvertirIngredientes(indice)));
        String[] columnas = {"Nombre", "Cantidad", "Unidades"};
        JTable t2 = new JTable(datos, columnas);
        t2.repaint();
        add(t2);
        table2 = new JScrollPane(t2);
        table2.setBounds(10, 10, 310, 140);
        table2.setVisible(true);
        ingredient.add(table2);

    }

    public Object[][] ConvertirProductos() {
        Object[][] arregloproductos = new Object[productos.size()][6];
        //Object[][] arregloingredientes = new Object[productos.][8];

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i) != null) {
                arregloproductos[i][0] = productos.get(i).getId();
                arregloproductos[i][1] = productos.get(i).getName();
                arregloproductos[i][2] = productos.get(i).getDescription();
                arregloproductos[i][3] = productos.get(i).getCost();
                arregloproductos[i][4] = productos.get(i).getPrice();
                arregloproductos[i][5] = productos.get(i).ingredients.size();


            }

        }
        return arregloproductos;

    }


    public Object[][] ConvertirIngredientes(int indice) {
        Object[][] arregloingredientes = new Object[productos.get(indice).ingredients.size()][3];
        for (int i = 0; i < productos.get(indice).ingredients.size(); i++) {
            if (productos.get(indice).ingredients.size() != 0) {
                arregloingredientes[i][0] = productos.get(indice).ingredients.get(i).getName();
                arregloingredientes[i][1] = productos.get(indice).ingredients.get(i).getQuantity();
                arregloingredientes[i][2] = productos.get(indice).ingredients.get(i).getUnits();
            }

        }
        return arregloingredientes;

    }

    public int BuscarProducto(int indice) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == indice) {
                return i;
            }

        }
        return -1;
    }


    public JButton addButton(String title, int x, int y, int width, int height) {
        JButton button = new JButton(title);
        button.setBounds(x, y, width, height);
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
        if (e.getSource() == crear) {//actualizar
            this.VentanaAdministracion.dispose();
            this.VentanaAdministracion.setVisible(false);
            new CrearProducto(restaurante, facturas, productos, clientes, usuarios);
        } else if (e.getSource() == ver) {
            String id = JOptionPane.showInputDialog(this, "Ingrese el ID del producto");
            if (id != null) {

                boolean existe = false;
                for (int i = 0; i < productos.size(); i++) {
                    if (productos.get(i).getId() == Integer.parseInt(id)) {
                        existe = true;
                    }

                }
                if (existe == true) {
                    this.VentanaAdministracion.dispose();
                    this.VentanaAdministracion.setVisible(false);
                    new VerProducto(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios, Integer.parseInt(id));
                } else {
                    JOptionPane.showMessageDialog(this, "No existe ese producto");

                }
            }
        } else if (e.getSource() == actualizar){

            boolean idProductoEncontrado = false;
            String actualizarIdProducto = JOptionPane.showInputDialog(this, "Ingrese el ID del producto a actualizar");
            if (actualizarIdProducto != null) {
                for (Producto producto : productos) {

                    if (producto.getId()==Integer.parseInt(actualizarIdProducto)) {
                        idProductoEncontrado = true;
                        break;
                    } else if (!(producto.getId()==Integer.parseInt(actualizarIdProducto))) {
                        continue;
                    }
                }


                if (idProductoEncontrado == false) {
                    JOptionPane.showMessageDialog(this, "No se encontro el id");

                } else {
                    this.VentanaAdministracion.dispose();
                    this.VentanaAdministracion.setVisible(false);
                    new ActualizarProducto(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios, Integer.parseInt(actualizarIdProducto));

                }
            }
        } else if (e.getSource() == eliminar) {
            try {
                String eliminar = JOptionPane.showInputDialog(this, "Ingrese el ID del producto a eliminar");
                if (eliminar != null) {
                    Buscar_EliminarProductos(Integer.parseInt(eliminar));
                    this.VentanaAdministracion.dispose();
                    VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                } else {

                }
            } catch (Exception aa) {
                JOptionPane.showMessageDialog(this, "Solo ingresar el ID");

            }
        } else if (e.getSource() == cerrarSesion) {
            JOptionPane.showMessageDialog(this, "                 Feliz dia");
            this.VentanaAdministracion.dispose();
            LogErrores.escribirLogErrores("log.log", "CERRARSESION", "");
            new Login(restaurante, facturas, productos, usuarios, clientes);
        }
        else if (e.getSource() == guardar) {
            JOptionPane.showMessageDialog(this, "Se han guardado los cambios correctamente");
            SerealizacionJson();
        } else if (e.getSource() == ingredientes) {
            try {
                String ID = JOptionPane.showInputDialog(this, "Ingrese el ID del producto a mostrar");
                if (ID != null) {
                    int indice = BuscarProducto(Integer.parseInt(ID));

                    if (indice != -1) {
                        this.VentanaAdministracion.dispose();
                        MostrarIngredientes(indice);

                    } else {
                        JOptionPane.showMessageDialog(this, "El id del producto ingresado no existe");
                    }

                }

            } catch (Exception ee) {
                JOptionPane.showMessageDialog(this, "Ingrese un numero entero");

            }
        }
    }

    /////// Ventana de creacion de un usuario ///////////
    public void crearProducto() {

        JFrameProducto = new JFrame("Crear Producto");
        JFrameProducto.setBounds(0, 0, 450, 650);
        JFrameProducto.setResizable(false);
        JFrameProducto.setLocationRelativeTo(null);
        JFrameProducto.setLayout(null);//Sistema de coordenadas
        Color myColor = Color.decode("#1C2833");
        JFrameProducto.setBackground(myColor);


        JFrameProducto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JFrameProducto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // JOptionPane.showMessageDialog(null, "");
                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });

        addLabel1("ID", 20, 50, 200, 30);
        addLabel1("Nombre", 20, 125, 200, 30);
        addLabel1("Descripcion", 20, 200, 200, 30);
        addLabel1("Costo", 20, 275, 200, 30);
        addLabel1("Precio", 20, 350, 200, 30);
        addLabel1("Nombre ingrediente", 20, 425, 200, 30);
        addLabel1("Cantidad", 20, 500, 200, 30);
        addLabel1("Unidades", 20, 575, 200, 30);


        ID = new JTextField();
        ID.setLayout(null);
        ID.setBounds(175, 50, 200, 30);

        Nombre = new JTextField();
        Nombre.setLayout(null);
        Nombre.setBounds(175, 125, 200, 30);

        Descripcion = new JTextField();
        Descripcion.setLayout(null);
        Descripcion.setBounds(175, 200, 200, 30);

        Costo = new JTextField();
        Costo.setLayout(null);
        Costo.setBounds(175, 275, 200, 30);

        Precio = new JTextField();
        Precio.setLayout(null);
        Precio.setBounds(175, 350, 200, 30);

        NombreIngre = new JTextField();
        NombreIngre.setLayout(null);
        NombreIngre.setBounds(175, 425, 200, 30);

        Cantidad = new JTextField();
        Cantidad.setLayout(null);
        Cantidad.setBounds(175, 500, 200, 30);

        Unidades = new JTextField();
        Unidades.setLayout(null);
        Unidades.setBounds(175, 575, 200, 30);


        agregar = addButton("Agregar ", 160, 615, 120, 50);
        JFrameProducto.add(agregar);
        JFrameProducto.add(Nombre);
        JFrameProducto.add(ID);
        JFrameProducto.add(Descripcion);
        JFrameProducto.add(Unidades);
        JFrameProducto.add(Cantidad);
        JFrameProducto.add(Costo);
        JFrameProducto.add(NombreIngre);
        JFrameProducto.add(Precio);
        JFrameProducto.setVisible(true);

    }

    /////////// Eliminacion de productos////////////
    public void Buscar_EliminarProductos(int indice) {

        Iterator<Producto> iteradorProducto = productos.iterator();
        boolean userEncontrado = false;
        int eliminar = 1;

        eliminar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el Producto?",
                "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        switch (eliminar) {
            case 0:
                while (iteradorProducto.hasNext()) {

                    Producto cliente = iteradorProducto.next();

                    if (cliente.getId() == indice) {
                        removeProd = cliente;
                        LogErrores.escribirLogErrores("log.log", "REMOVEPRODUCTO", user);
                        iteradorProducto.remove();
                        userEncontrado = true;


                        JOptionPane.showMessageDialog(null, "Producto eliminado");
                        //Se llena el log
                    }
                }

                if (userEncontrado == false) {

                    JOptionPane.showMessageDialog(null, "No se encontro el producto ");
                }
                break;

            case 1:
                break;
        }

    }

    ////// Funcionalidad del boton agregar usuario //////////////
    public void FuncionBotonAgregar() {
        boolean existe = false;
        try {

            int id = Integer.parseInt(ID.getText());
            String name = Nombre.getText();
            String descripcion = Descripcion.getText();
            double costo = Double.parseDouble(Costo.getText());
            double precio = Double.parseDouble(Precio.getText());


            String Nameingre = NombreIngre.getText();
            int cantidad = Integer.parseInt(Cantidad.getText());
            String unidades = Unidades.getText();


            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getId() == id) {
                    existe = true;
                }
            }

            if (existe == false) {
                if ((name.equals("")) || (unidades.equals("")) || (descripcion.equals("")) || (costo == 0) || (cantidad == 0) || (id == 0) || (Nameingre.equals("")) || (precio == 0)) {
                    JOptionPane.showMessageDialog(this, "Faltan datos");
                } else {
                    // Producto creacion = new Producto(id, name,descripcion,costo,precio,ArrayList<Ingredients>.ingredients );
                    //clientes.add(creacion);
                    JOptionPane.showMessageDialog(this, "El producto: " + name + " se creo correctamente ");
                    JFrameProducto.dispose();
                    VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);

                }

            } else {
                JOptionPane.showMessageDialog(this, "El producto ya existe");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingresar numeros enteros en el ID, en la cantidad y en el precio");

        }


    }

    public void MostrarIngredientes(int indice) {

        ingredient = new JFrame("Lista de Ingredientes " + productos.get(indice).getName());
        ingredient.setBounds(0, 0, 350, 200);
        ingredient.setResizable(false);
        ingredient.setLocationRelativeTo(null);
        ingredient.setLayout(null);//Sistema de coordenadas
        Color myColor = Color.decode("#1C2833");
        ingredient.setBackground(myColor);
        ingredient.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ingredient.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });

        tablaIngredientes(indice);
        ingredient.setVisible(true);

    }
    ////////////////////////////// Serealizacion ////////////////////


    public void SerealizacionJson() {
        Gson gson0 = new Gson();
        String jsonrestaurante = gson0.toJson(restaurante);
        Gson gson = new Gson();
        String jsonclientes = gson.toJson(clientes);
        Gson gson1 = new Gson();
        String jsonusuarios = gson1.toJson(usuarios);
        Gson gson2 = new Gson();
        String jsonproductos = gson2.toJson(productos);
        Gson gson3 = new Gson();
        String jsonfacturas = gson3.toJson(facturas);
        try {
            writeOnFile("config.json", jsonrestaurante, false);
            writeOnFile("clients.json", jsonclientes, false);
            writeOnFile("users.json", jsonusuarios, false);
            writeOnFile("products.json", jsonproductos, false);
            writeOnFile("invoices.json", jsonfacturas, false);

        } catch (Exception ez) {

        }
    }

    public void writeOnFile(String pathname, String content, boolean append) {
        File file;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            file = new File(pathname);
            fw = new FileWriter(file, append);
            bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
