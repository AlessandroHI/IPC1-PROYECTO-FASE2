package interfazClase;//importantisima

import FacturaAux.VerFactura;
import ProductosAux.VerProducto;
import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.model.core.ID;
import crudFactura.CrearFactura;
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

public class JPanelFactura extends JPanel implements ActionListener {

    Restaurante restaurante;
    ArrayList<Usuario> usuarios;
    JFrame VentanaAdministracion, JFrameFactura, producto;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;

    ArrayList<Cliente> clientes;


    JTextField id, Cliente, Fecha, NombreProd, PrecioProd;


    JButton ver, crear, agregar, eliminar, prod, guardar, cerrarSesion;
    JScrollPane table, table2;


   public static Factura removeFactura = new Factura();// manejo del log
    String user;

    public JPanelFactura(Restaurante restaurante, ArrayList<Usuario> usuarios, JFrame VentanaAdministracion, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes) {
        this.restaurante = restaurante;
        this.usuarios = usuarios;
        this.VentanaAdministracion = VentanaAdministracion;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;


        setPanel();
        tablaFacturas();
        ver = addButton("Ver ", 825, 50, 140, 50);
        crear = addButton("Crear ", 825, 120, 140, 50);

        eliminar = addButton("Eliminar", 825, 190, 140, 50);
        prod = addButton("Productos", 825, 260, 140, 50);
        guardar = addButton("Guardar", 825, 330, 140, 50);
        cerrarSesion = addButton("Cerrar sesion", 825, 475, 150, 50);
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
        JFrameFactura.add(anadirLabel);
        repaint();
    }

    public void tablaFacturas() {

        Object[][] datos = (ConvertirFacturas());
        String[] columnas = {"ID", "Cliente", "Fecha", "Cantidad de Productos"};
        JTable t2 = new JTable(datos, columnas);
        t2.repaint();
        add(t2);
        table = new JScrollPane(t2);
        table.setBounds(20, 20, 700, 540);
        table.setVisible(true);
        add(table);

    }


    public Object[][] ConvertirFacturas() {

        Object[][] arreglo = new Object[facturas.size()][4];


        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i) != null) {
                arreglo[i][0] = facturas.get(i).getId();
                int idcliente = facturas.get(i).getClient();

                for (int j = 0; j < clientes.size(); j++) {
                    if (idcliente == clientes.get(j).getId()) {
                        arreglo[i][1] = clientes.get(j).getName();

            }
        }
        arreglo[i][2] = facturas.get(i).getDate();
                arreglo[i][3] = facturas.get(i).products.size();

            }

        }
        return arreglo;
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
            new CrearFactura(restaurante, facturas, productos, clientes, usuarios);
        } else if (e.getSource() == cerrarSesion) {
            LogErrores.escribirLogErrores("log.log", "CERRARSESION", "");
            JOptionPane.showMessageDialog(this, "                 Feliz dia");
            this.VentanaAdministracion.dispose();
            new Login(restaurante, facturas, productos, usuarios, clientes);
        }
        else if (e.getSource() == agregar) {
            FuncionBotonAgregar();
        }else if (e.getSource() == ver) {
            String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la factura");
            if (id != null) {

                boolean existe = false;
                for (int i = 0; i < facturas.size(); i++) {
                    if (facturas.get(i).getId() == Integer.parseInt(id)) {
                        existe = true;
                    }

                }
                if (existe == true) {
                    this.VentanaAdministracion.dispose();
                    this.VentanaAdministracion.setVisible(false);
                    new VerFactura(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios, Integer.parseInt(id));
                } else {
                    JOptionPane.showMessageDialog(this, "No existe esa factura");

                }
            }
        }

        else if (e.getSource() == eliminar) {
            try {
                String eliminar = JOptionPane.showInputDialog(this, "Ingrese el ID del cliente de la factura a eliminar");
                if (eliminar != null) {
                    Buscar_EliminarFactura(Integer.parseInt(eliminar));
                    this.VentanaAdministracion.dispose();
                    VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                }

            } catch (Exception ee) {
                JOptionPane.showMessageDialog(this, "Solo ingrese numeros enteros");

            }
        } else if (e.getSource() == prod) {
            try {
                String ID = JOptionPane.showInputDialog(this, "Ingrese el ID de la de la factura a mostrar");
                if (ID != null) {
                    int indice = BuscarFactura(Integer.parseInt(ID));
                    if (indice != -1) {
                        this.VentanaAdministracion.dispose();
                        MostraProd(indice);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe esa factura");
                    }
                }

            } catch (Exception ee) {
                JOptionPane.showMessageDialog(this, "Ingrese un numero entero");

            }
        }
    }

    /////// Ventana de creacion de un usuario ///////////
    public void crearFactura() {

        JFrameFactura = new JFrame("Crear Factura");
        JFrameFactura.setBounds(0, 0, 600, 650);
        JFrameFactura.setResizable(false);
        JFrameFactura.setLocationRelativeTo(null);
        JFrameFactura.setLayout(null);//Sistema de coordenadas
        Color myColor = Color.decode("#1C2833");
        JFrameFactura.setBackground(myColor);


        JFrameFactura.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JFrameFactura.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // JOptionPane.showMessageDialog(null, "");
                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });
        addLabel1("ID", 20, 50, 200, 30);
        addLabel1("Cliente", 20, 150, 200, 30);
        addLabel1("Fecha", 20, 250, 200, 30);
        addLabel1("Nombre del Producto", 20, 350, 200, 30);
        addLabel1("Precio", 20, 450, 200, 30);

        id = new JTextField();
        id.setLayout(null);
        id.setBounds(220, 50, 200, 30);

        Cliente = new JTextField();
        Cliente.setLayout(null);
        Cliente.setBounds(220, 150, 200, 30);

        Fecha = new JTextField();
        Fecha.setLayout(null);
        Fecha.setBounds(220, 250, 200, 30);

        NombreProd = new JTextField();
        NombreProd.setLayout(null);
        NombreProd.setBounds(220, 350, 200, 30);

        PrecioProd = new JTextField();
        PrecioProd.setLayout(null);
        PrecioProd.setBounds(220, 450, 200, 30);


        agregar = addButton("Agregar ", 180, 525, 120, 50);
        JFrameFactura.add(agregar);
        JFrameFactura.add(PrecioProd);
        JFrameFactura.add(NombreProd);
        JFrameFactura.add(Fecha);
        JFrameFactura.add(Cliente);
        JFrameFactura.add(id);

        JFrameFactura.setVisible(true);

    }

    /////////// Eliminacion de usuarios////////////
    public void Buscar_EliminarFactura(int indice) {

        Iterator<Factura> iteradorFactura = facturas.iterator();
        boolean userEncontrado = false;
        int eliminar = 1;

        eliminar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar esta factura?",
                "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        switch (eliminar) {
            case 0:
                while (iteradorFactura.hasNext()) {

                    Factura factura = iteradorFactura.next();

                    if (factura.getId() == indice) {
                        removeFactura = factura;
                        LogErrores.escribirLogErrores("log.log", "REMOVEFACTURA", user);
                        iteradorFactura.remove();
                        userEncontrado = true;


                        JOptionPane.showMessageDialog(null, "Factura eliminada");
                        //Se llena el log
                    }
                }

                if (userEncontrado == false) {

                    JOptionPane.showMessageDialog(null, "No se encontro un cliente");
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
            int id = Integer.parseInt(Cliente.getText());
            String fecha = Fecha.getText();
            String nombreprod = NombreProd.getText();
            double precio = Double.parseDouble(PrecioProd.getText());

            for (int i = 0; i < facturas.size(); i++) {
                if (facturas.get(i).getClient() == id) {
                    existe = true;
                }
            }

            if (existe == false) {
                if ((fecha.equals("")) || (nombreprod.equals("")) || (precio == 0) || (id == 0)) {
                    JOptionPane.showMessageDialog(this, "Faltan datos");
                } else {
                    //Cliente creacion = new Cliente(id, name,direccion,numero,nit);
                    // clientes.add(creacion);
                    JOptionPane.showMessageDialog(this, "La factura se creo correctamente ");
                    JFrameFactura.dispose();
                    VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);

                }

            } else {
                JOptionPane.showMessageDialog(this, "La factura ya existe");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingresar numeros en Cliente y en el Precio");

        }


    }

    //*//////////////// Todo lo relacionado con productos de la factura ///////////////////*/
    public Object[][] ConvertirProducto(int indice) {
        Object[][] arregloproductos = new Object[facturas.get(indice).products.size()][2];
        for (int i = 0; i < facturas.get(indice).getProducts().size(); i++) {
            if (facturas.get(indice).getProducts().size() != 0) {
                arregloproductos[i][0] = facturas.get(indice).products.get(i).getName();
                arregloproductos[i][1] = facturas.get(indice).products.get(i).getPrice();

            }

        }
        return arregloproductos;

    }

    public int BuscarFactura(int indice) {
        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i).getId() == indice) {
                return i;
            }

        }
        return -1;
    }

    public void tablaProductos(int indice) {

        Object[][] datos = ((ConvertirProducto(indice)));
        String[] columnas = {"Nombre", "Precio"};
        JTable t2 = new JTable(datos, columnas);
        t2.repaint();
        add(t2);
        table2 = new JScrollPane(t2);
        table2.setBounds(10, 10, 310, 140);
        table2.setVisible(true);
        producto.add(table2);

    }

    public void MostraProd(int indice) {

        producto = new JFrame("Lista de productos " + facturas.get(indice).getDate());
        producto.setBounds(0, 0, 350, 200);
        producto.setResizable(false);
        producto.setLocationRelativeTo(null);
        producto.setLayout(null);//Sistema de coordenadas
        Color myColor = Color.decode("#1C2833");
        producto.setBackground(myColor);
        producto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        producto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });

        tablaProductos(indice);
        producto.setVisible(true);

    }
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


