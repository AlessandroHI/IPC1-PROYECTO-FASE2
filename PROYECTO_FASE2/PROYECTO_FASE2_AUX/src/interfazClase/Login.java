package interfazClase;

import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Usuario> usuarios;
    ArrayList<Cliente> clientes;
    JButton buttonLogin;
    JTextField username;
    JPasswordField password;
    public static String USUARIO;

    public Login(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Usuario> usuarios, ArrayList<Cliente> clientes){
      
        setInicio();
        this.restaurante=restaurante;
        this.usuarios=usuarios;
        this.clientes=clientes;
        this.productos=productos;
        this.facturas=facturas;
        addLabel("Restaurant Manager",180, 0, 420, 100);
        addLabel1("Username", 90, 105, 150, 50);
        username=addTextfield("",230, 110, 350, 50);
        addLabel1("Password", 90, 195, 150, 50);
        password=addPasswordField("",230, 190, 350, 50);
        buttonLogin=addButton("Iniciar Sesion", 250, 280, 170, 50);

    }

    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Login");
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#1C2833"));
        setLocation(ancho/4, altura/4);
        setSize(680, 450);
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



    @Override
    public void actionPerformed(ActionEvent e) {

        boolean usuarioEncontrado=false;
        boolean usuarioEncontradoWrongPass=false;

        if (e.getSource()==buttonLogin){

            for (Usuario usuarioLogin : usuarios) {

                if (usuarioLogin.getUsername().equals(username.getText()) && usuarioLogin.getPassword().equals(password.getText())) {
                    usuarioEncontrado=true;
                    USUARIO = username.getText();
                    LogErrores.escribirLogErrores("log.log", "LOGINTRUE", username.getText());
                    System.out.println(username.getText() + " " + password.getText());
                }else if (!usuarioLogin.getUsername().equals(username.getText())){
                    continue;
                }
                else {
                    usuarioEncontradoWrongPass=true;
                    LogErrores.escribirLogErrores("log.log", "WRONGPASSWORD", username.getText());
                    JOptionPane.showMessageDialog(this, "Password incorrecta!");
                    break;
                }
            }

            if (usuarioEncontrado==true){
                this.dispose();
                this.setVisible(false);
                new PantallaInicio(restaurante, facturas, productos,usuarios, clientes);

            }

            else if (usuarioEncontrado==false && usuarioEncontradoWrongPass==false){

                LogErrores.escribirLogErrores("log.log", "USERNOTFOUND", username.getText());
                JOptionPane.showMessageDialog(this, "No se encontro el usuario!");

            }
        }

    }

}
