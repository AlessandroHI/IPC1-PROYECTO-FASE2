package manejoDatos;

import java.io.Serializable;

public class Products implements Serializable {

    private String name;
    private double price;

    //constructor de parametros
    public Products(String name, double price) {
        this.name = name;
        this.price = price;

    }

    //constructor vacio
    public Products() {

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
