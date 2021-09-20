package manejoDatos;

import crudProdutos.ActualizarProducto;

import javax.swing.*;
import java.util.ArrayList;

public class Producto {

    private int id;
    private String name;
    private String description;
    private double cost;
    private double price;
    public ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();

    //constructor de parametros
    public Producto(int id, String name, String description, double cost, double price, ArrayList<Ingredients> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.price = price;
        this.ingredients = ingredients;
    }

    //constructor vacio
    public Producto() {

    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
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

    public void agregarProducto(String name, double quantity, String units){

        ingredients.add(new Ingredients(name, quantity, units));

    }

    public void removeIngrediente(String name, ActualizarProducto actualizarProducto){

        boolean ingredienteEncontrado=false;
        int posicion=0;

        for (Ingredients ingredients:ingredients){
            if (ingredients.getName().equals(name)){
                ingredienteEncontrado=true;
                posicion=posicion;
            }else if (!(ingredients.getName().equals(name))){
                continue;
            }
        }

        if (ingredienteEncontrado==true){
            ingredients.remove(posicion);
            JOptionPane.showMessageDialog(actualizarProducto, "Ingrediente eliminado");
        }else {
            JOptionPane.showMessageDialog(actualizarProducto, "  El producto no fue encontrado! ");
        }
    }

    public void MostrarInfo() {
        System.out.println("---------------------------------------------------");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + name);
        System.out.println("Descripcion: " + description);
        System.out.println("Costo: " + cost);
        System.out.println("Precio: " + price);
        System.out.println("Ingredientes: ");
        for (int i = 0; i < ingredients.size(); i++) {
            System.out.println("--------------------------");
            System.out.println("Nombre: " + ingredients.get(i).getName());
            System.out.println("Cantidad: " + ingredients.get(i).getQuantity());
            System.out.println("Unidades: " + ingredients.get(i).getUnits());
            System.out.println("--------------------------");

        }
        System.out.println("---------------------------------------------------");

    }
}
