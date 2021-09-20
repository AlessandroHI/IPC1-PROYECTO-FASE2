package ProductosAux;

import interfazClase.VentanaAdministracion;
import manejoDatos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class VerProducto extends JFrame implements ActionListener{

    Restaurante restaurante;
    ArrayList<Factura> facturas;
    ArrayList<Producto> productos;
    ArrayList<Cliente> clientes;
    ArrayList<Usuario> usuarios;
    int Indice;
    JButton ingredientes;
    JFrame ingredient;
    JScrollPane table2;




    public VerProducto(Restaurante restaurante, ArrayList<Factura> facturas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, ArrayList<Usuario> usuarios,int indice) throws HeadlessException {

        setInicio();
        this.restaurante = restaurante;
        this.facturas = facturas;
        this.productos = productos;
        this.clientes = clientes;
        this.usuarios = usuarios;
        this.Indice=indice;



        addLabel1("ID",30, 30, 200, 30);

        addLabel1("Nombre",30, 60, 200, 30);

        addLabel1("Descripcion",30, 90, 200, 30);

        addLabel1("Costo",30, 120, 200, 30);

        addLabel1("Precio",30, 150, 200, 30);

        addLabel1("Ingredientes: ",30, 180, 200, 30);



        ingredientes=addButton("Ingredientes",200, 180, 200, 30);

        BuscarProducto(this.Indice);

    }
    public void MostrarIngredientes(int indice) {

        ingredient = new JFrame("Lista de Ingredientes " + productos.get(indice).getName());
        ingredient.setBounds(0, 0, 345, 200);
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

    public void setInicio(){
        Toolkit screen=Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();
        int altura=screenSize.height;
        int ancho=screenSize.width;
        setTitle("Ver Cliente");
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

    public void BuscarProducto(int indice){
        for (int i=0; i<productos.size();i++){
            if (productos.get(i).getId()==indice){
                addLabel1(String.valueOf(productos.get(i).getId()),200, 30, 200, 30);

                addLabel1(productos.get(i).getName(),200, 60, 400, 30);

                addLabel1(productos.get(i).getDescription(),200, 90, 400, 30);

                addLabel1(String.valueOf(productos.get(i).getCost()),200, 120, 400, 30);

                addLabel1(String.valueOf(productos.get(i).getPrice()),200, 150, 400, 30);

            }

        }

    }

    public int Buscarproductoingre(int indice) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == indice) {
                return i;
            }

        }
        return -1;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==ingredientes){
            this.dispose();
            int encontrar=Buscarproductoingre(this.Indice);
            MostrarIngredientes(encontrar);
        }
    }
}
