package manejoDatos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Factura implements Serializable {

    private int id;
    private int client;
    private String date;
    public ArrayList<Products> products = new ArrayList<Products>();

    //constructor de parametros
    public Factura(int id,int client, String date, ArrayList<Products> products) {
        this.id=id;
        this.client = client;
        this.date = date;
        this.products = products;
    }

    //constructor vacio
    public Factura() {

    }

    /**
     * @return the client
     */
    public int getClient() {
        return client;
    }

    /**
     * @param client the clie nt to set
     */
    public void setClient(int client) {
        this.client = client;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public void Informacion() {
        System.out.println("---------------------------------------------------");
        System.out.println("ID: " + id);
        System.out.println("manejoDatos.Cliente: " + client);
        System.out.println("Fecha: " + date);
        System.out.println("Productos: ");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("--------------------------");
            System.out.println("Nombre: " + products.get(i).getName());
            System.out.println("Precio: " + products.get(i).getPrice());
            System.out.println("--------------------------");
        }
        System.out.println("---------------------------------------------------");

    }


}
