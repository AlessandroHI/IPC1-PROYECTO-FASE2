package FacturaAux;

import interfazClase.VentanaAdministracion;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VerFactura extends JFrame implements ActionListener {

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;
    int Indice;
    JButton Productos;
    JFrame producto;
    JScrollPane table2;




    public VerFactura(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios,int indice) throws HeadlessException {

        setInicio();
        this.restaurante = restaurante;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;
        this.usuarios = usuarios;
        this.Indice=indice;



        addLabel1("ID",30, 30, 200, 30);

        addLabel1("Cliente",30, 60, 200, 30);

        addLabel1("Fecha",30, 90, 200, 30);

        addLabel1("Productos",30, 120, 200, 30);

        Productos=addButton("productos",200, 120, 200, 30);

        BuscarFactura(this.Indice);

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


    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Ver Factura");
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
        setBounds((ancho/4)+100, ((altura/4)-150), 600, 300);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }


    public JButton addButton(String title, int x, int y, int width, int height){
        JButton button=new JButton(title);
        button.setBounds(x,y,width,height);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        Color myColor = Color.decode("#345FE3");
        button.setBackground(myColor);
        Color myColor2 = Color.decode("#FBFCFC");
        button.setForeground(myColor2);
        button.addActionListener( this);
        add(button);
        repaint();
        return button;
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


    public void BuscarFactura(int indice){
        String nombre = null;
        for (int i=0; i<facturas.size();i++){
            if (facturas.get(i).getId()==indice){

                int idcliente = facturas.get(i).getClient();

                for (int j = 0; j < clientes.size(); j++) {
                    if (idcliente == clientes.get(j).getId()) {
                         nombre = clientes.get(j).getName();

                    }
                }

                addLabel1(String.valueOf(facturas.get(i).getId()),200, 30, 200, 30);

                addLabel1(nombre,200, 60, 400, 30);

                addLabel1(facturas.get(i).getDate(),200, 90, 400, 30);





            }

        }

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


    public int BuscaFactura(int indice) {
        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i).getId() == indice) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Productos){
            this.dispose();
            int encontrar=BuscaFactura(this.Indice);
            MostraProd(encontrar);
        }
    }
}
