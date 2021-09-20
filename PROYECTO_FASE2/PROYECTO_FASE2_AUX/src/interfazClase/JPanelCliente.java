package interfazClase;

import com.google.gson.Gson;
import crudCliente.ActualizarCliente;
import crudCliente.CrearCliente;
import crudCliente.VerCliente;
import crudUsuarios.ActualizarUsuario;
import crudUsuarios.CrearUsuario;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class JPanelCliente extends JPanel implements ActionListener {

    Restaurante restaurante;
    ArrayList<Usuario> usuarios;
    JFrame VentanaAdministracion, JFrameCliente;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;

    ArrayList<Cliente> clientes;


    JTextField ID, Nombre, Direccion, Numero, NIT;


    JButton ver,crear, eliminar, actualizar,guardar, cerrarSesion;
    JScrollPane table;
    JTable t1;

   public static Cliente removeCliente = new Cliente();// manejo del log
    String user;


    public JPanelCliente(Restaurante restaurante, ArrayList<Usuario> usuarios, JFrame VentanaAdministracion, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes) {
        this.restaurante = restaurante;
        this.usuarios = usuarios;
        this.VentanaAdministracion = VentanaAdministracion;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;



        setPanel();
        tablaClientes();
        ver = addButton("Ver cliente", 825, 50, 140, 50);
        crear = addButton("Crear ", 825, 120, 140, 50);
        actualizar = addButton("Actualizar", 825, 190, 140, 50);
        eliminar = addButton("Eliminar", 825, 260, 140, 50);
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
        JFrameCliente.add(anadirLabel);
        repaint();
    }

    public void tablaClientes() {

        Object[][] datos = (ConvertirClientes());
        String[] columnas = {"ID", "Nombre", "Direccion", "Numero telefonico", "NIT"};
        JTable t2 = new JTable(datos, columnas);
        t2.repaint();
        add(t2);
        table = new JScrollPane(t2);
        table.setBounds(20, 20, 700, 540);
        table.setVisible(true);
        add(table);

    }


    public Object[][] ConvertirClientes() {
        Object[][] arreglo = new Object[clientes.size()][5];
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i) != null) {
                arreglo[i][0] = clientes.get(i).getId();
                arreglo[i][1] = clientes.get(i).getName();
                arreglo[i][2] = clientes.get(i).getAddress();
                arreglo[i][3] = clientes.get(i).getPhone();
                arreglo[i][4] = clientes.get(i).getNit();
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
            new CrearCliente(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios);

        } else if(e.getSource() == ver){
            String id = JOptionPane.showInputDialog(this,"Ingrese el ID del cliente");
            if (id!=null) {

                boolean existe=false;
                for (int i=0; i<clientes.size();i++){
                    if (clientes.get(i).getId()== Integer.parseInt(id)){
                        existe = true;
                    }

                }
                if (existe == true) {
                    this.VentanaAdministracion.dispose();
                    this.VentanaAdministracion.setVisible(false);
                    new VerCliente(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios, Integer.parseInt(id));
                }else{
                    JOptionPane.showMessageDialog(this,"No existe ese cliente");

                }
            }
        }else if (e.getSource() == actualizar) {
            boolean clienteEncontrado = false;
            String buscarId = null;

            try {
                buscarId = JOptionPane.showInputDialog(this, "Ingrese el id del cliente a actualizar");

            } catch (Exception ea) {
                JOptionPane.showMessageDialog(this, "Dato invalido!");
                ea.printStackTrace();
            }
            if (buscarId != null) {
                for (Cliente cliente : clientes) {

                    if (cliente.getId() == Integer.parseInt(buscarId)) {
                        clienteEncontrado = true;
                        break;
                    } else if (!(cliente.getId() == Integer.parseInt(buscarId))) {
                        continue;
                    }
                }

                if (clienteEncontrado == false) {
                    JOptionPane.showMessageDialog(this, "No se encontro el id en los registros");

                } else {
                    this.VentanaAdministracion.dispose();
                    this.VentanaAdministracion.setVisible(false);
                    new ActualizarCliente(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios, Integer.parseInt(buscarId));
                }
            } else {

            }


        } else if (e.getSource() == cerrarSesion) {
            LogErrores.escribirLogErrores("log.log", "CERRARSESION", "");
            JOptionPane.showMessageDialog(this, "                 Feliz dia");
            this.VentanaAdministracion.dispose();
            new Login(restaurante, facturas, productos, usuarios, clientes);
        }
        else if (e.getSource() == guardar) {
            JOptionPane.showMessageDialog(this, "Se han guardado los cambios correctamente");
            SerealizacionJson();
        }
        else if (e.getSource() == eliminar) {
            String eliminar = JOptionPane.showInputDialog(this, "Ingrese el cliente a eliminar");
            if (eliminar != null) {
                Buscar_EliminarClientes(Integer.parseInt(eliminar));
                this.VentanaAdministracion.dispose();
                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
            }
        }
    }

    /////// Ventana de creacion de un usuario ///////////
    public void crearCliente() {

        JFrameCliente = new JFrame("Crear Cliente");
        JFrameCliente.setBounds(0, 0, 450, 650);
        JFrameCliente.setResizable(false);
        JFrameCliente.setLocationRelativeTo(null);
        JFrameCliente.setLayout(null);//Sistema de coordenadas
        Color myColor = Color.decode("#1C2833");
        JFrameCliente.setBackground(myColor);


        JFrameCliente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JFrameCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // JOptionPane.showMessageDialog(null, "");
                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
                //llamo a la ventana
            }
        });

        addLabel1("ID", 20, 50, 200, 30);
        addLabel1("Nombre", 20, 150, 200, 30);
        addLabel1("Direccion", 20, 250, 200, 30);
        addLabel1("Numero", 20, 350, 200, 30);
        addLabel1("NIT", 20, 450, 200, 30);


        ID = new JTextField();
        ID.setLayout(null);
        ID.setBounds(175, 50, 200, 30);

        Nombre = new JTextField();
        Nombre.setLayout(null);
        Nombre.setBounds(175, 150, 200, 30);

        Direccion = new JTextField();
        Direccion.setLayout(null);
        Direccion.setBounds(175, 250, 200, 30);

        Numero = new JTextField();
        Numero.setLayout(null);
        Numero.setBounds(175, 350, 200, 30);

        NIT = new JTextField();
        NIT.setLayout(null);
        NIT.setBounds(175, 450, 200, 30);


        JFrameCliente.add(Nombre);
        JFrameCliente.add(ID);
        JFrameCliente.add(NIT);
        JFrameCliente.add(Direccion);
        JFrameCliente.add(Numero);
        JFrameCliente.setVisible(true);

    }

    /////////// Eliminacion de usuarios////////////
    public void Buscar_EliminarClientes(int indice) {

        Iterator<Cliente> iteradorCliente = clientes.iterator();
        boolean userEncontrado = false;
        int eliminar = 1;

        eliminar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el Cliente?",
                "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        switch (eliminar) {
            case 0:
                while (iteradorCliente.hasNext()) {

                    Cliente cliente = iteradorCliente.next();

                    if (cliente.getId() == indice) {
                        removeCliente = cliente;
                        LogErrores.escribirLogErrores("log.log", "REMOVECLIENTE", user);
                        iteradorCliente.remove();
                        userEncontrado = true;


                        JOptionPane.showMessageDialog(null, "Cliente eliminado");
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
            int id = Integer.parseInt(ID.getText());
            String name = Nombre.getText();
            String direccion = Direccion.getText();
            int numero = Integer.parseInt(Numero.getText());
            String nit = NIT.getText();
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getId() == id) {
                    existe = true;
                }
            }

            if (existe == false) {
                if ((name.equals("")) || (direccion.equals("")) || (numero == 0) || (nit.equals("")) || (id == 0)) {
                    JOptionPane.showMessageDialog(this, "Faltan datos");
                } else {
                    Cliente creacion = new Cliente(id, name, direccion, numero, nit);
                    clientes.add(creacion);
                    JOptionPane.showMessageDialog(this, "El cliente: " + name + " se creo correctamente ");
                    JFrameCliente.dispose();
                    VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);

                }

            } else {
                JOptionPane.showMessageDialog(this, "El cliente ya existe");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingresar numeros enteros en el ID y en el numero");

        }


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
