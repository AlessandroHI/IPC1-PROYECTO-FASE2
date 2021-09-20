package interfazClase;

import com.google.gson.Gson;
import crudCliente.VerCliente;
import crudUsuarios.ActualizarUsuario;
import crudUsuarios.CrearUsuario;
import crudUsuarios.VerUsuario;
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

public class JPanelUser extends JPanel implements ActionListener {
JFrame VentanaAdministracion, JFrameUser;

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;


    JButton buttonLogin;
    JTextField username, txt, txt2;
    JPasswordField password, passwordComprobacion;

    public static String user = "", userRemove;
    JTable tableProfesores;
    JButton ver, crear, agregar, eliminar, actualizar, guardar, cerrarSesion;

    JScrollPane table;
    JTable t1;


    public JPanelUser(Restaurante restaurante, ArrayList<Usuario> usuarios, JFrame VentanaAdministracion, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes) {
        this.restaurante = restaurante;
        this.usuarios = usuarios;
        this.VentanaAdministracion = VentanaAdministracion;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;


        setPanel();
        tablaUsers();
        ver = addButton("Ver Usuario ", 750, 50, 140, 50);
        crear = addButton("Crear ", 750, 120, 140, 50);
        actualizar = addButton("Actualizar", 750, 190, 140, 50);
        eliminar = addButton("Eliminar", 750, 260, 140, 50);
        guardar = addButton("Guardar", 750, 330, 140, 50);
        cerrarSesion = addButton("Cerrar sesion", 750, 475, 150, 50);
        this.setVisible(true);
    }

    public void setPanel() {
        setLayout(null);
        setBounds(50, 50, 300, 300);
        Color myColor = Color.decode("#1C2833");
        setBackground(myColor);
    }

    public JLabel addLabel1(String titulo, int x, int y, int width, int height) {
        JLabel anadirLabel = new JLabel(titulo);
        anadirLabel.setBounds(x, y, width, height);
        anadirLabel.setFont(new Font("Arial", Font.BOLD, 25));
        Color myColor = Color.decode("#FBFCFC");
        anadirLabel.setForeground(myColor);
        add(anadirLabel);
        repaint();
        return anadirLabel;

    }

    public void tablaUsers() {

        Object[][] datos = (ConvertirUsuarios());
        String[] columnas = {"Username", "Password"};
        t1 = new JTable(datos, columnas);
        t1.repaint();
        add(t1);
        table = new JScrollPane(t1);
        table.setBounds(20, 20, 550, 540);
        table.setVisible(true);
        add(table);

    }


    public Object[][] ConvertirUsuarios() {
        Object[][] arreglo = new Object[usuarios.size()][2];
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) != null) {
                arreglo[i][0] = usuarios.get(i).getUsername();
                arreglo[i][1] = usuarios.get(i).getPassword();
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
            new CrearUsuario(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios);

        } else if(e.getSource() == ver){
            String user = JOptionPane.showInputDialog(this,"Ingrese el Username");
            if (user!=null) {

                boolean existe=false;
                for (int i=0; i<usuarios.size();i++){
                    if (usuarios.get(i).getUsername().equals(user)){
                        existe = true;
                    }

                }
                if (existe == true) {
                    this.VentanaAdministracion.dispose();
                    this.VentanaAdministracion.setVisible(false);
                    new VerUsuario(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios, user);
                }else{
                    JOptionPane.showMessageDialog(this,"No existe ese Usuario ");

                }
            }
        }
        else if (e.getSource() == actualizar) {


            boolean usuarioEncontrado = false;
            String actualizarUsername = JOptionPane.showInputDialog(this, "Ingrese el username del usuario a actualizar");
            if (actualizarUsername != null) {
                for (Usuario usuario : usuarios) {

                    if (usuario.getUsername().equals(actualizarUsername)) {
                        usuarioEncontrado = true;
                        break;
                    } else if (!(usuario.getUsername().equals(actualizarUsername))) {
                        continue;
                    }
                }


                if (usuarioEncontrado == false) {
                    JOptionPane.showMessageDialog(this, "No se encontro el username");

                } else {
                    this.VentanaAdministracion.dispose();
                    this.VentanaAdministracion.setVisible(false);
                    new ActualizarUsuario(this.restaurante, this.facturas, this.productos, this.clientes, this.usuarios, actualizarUsername);

                }
            }

        } else if (e.getSource() == guardar) {
            JOptionPane.showMessageDialog(this, "Se han guardado los cambios correctamente");
            SerealizacionJson();
        }else if (e.getSource() == cerrarSesion) {
            this.VentanaAdministracion.dispose();
            LogErrores.escribirLogErrores("log.log", "CERRARSESION", "");
            JOptionPane.showMessageDialog(this, "                 Feliz dia");
            new Login(restaurante, facturas, productos, usuarios, clientes);
        }
        else if (e.getSource() == eliminar) {
            String eliminar = JOptionPane.showInputDialog(this, "Ingrese el usuario a eliminar");
            if (eliminar != null) {
                Buscar_EliminarUsuarios(eliminar);
                this.VentanaAdministracion.dispose();
                VentanaAdministracion llamada = new VentanaAdministracion(restaurante, facturas, productos, usuarios, clientes);
            }
        }
    }

    /////////// Eliminacion de usuarios////////////
    public void Buscar_EliminarUsuarios(String indice) {

        Iterator<Usuario> iteradorUsuario = usuarios.iterator();
        boolean userEncontrado = false;
        int eliminar = 1;

        eliminar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el usuario?",
                "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        switch (eliminar) {
            case 0:
                while (iteradorUsuario.hasNext()) {

                    Usuario usuario = iteradorUsuario.next();

                    if (usuario.getUsername().equals(indice)) {
                        userRemove = usuario.getUsername();
                        LogErrores.escribirLogErrores("log.log", "REMOVEUSER", user);
                        iteradorUsuario.remove();
                        userEncontrado = true;


                        JOptionPane.showMessageDialog(null, "Usuario eliminado");
                        //Se llena el log
                    }
                }

                if (userEncontrado == false) {

                    JOptionPane.showMessageDialog(null, "No se encontro un usuario");
                }
                break;

            case 1:
                break;
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


