package manejoDatos;

public class Ingredients {

    private String name;
    private double quantity;
    private String units;


    //constructor de parametros
    public Ingredients(String name, double quantity, String units) {
        this.name = name;
        this.quantity = quantity;
        this.units = units;
    }

    //constructor vacio
    public Ingredients() {

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
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the units
     */
    public String getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(String units) {
        this.units = units;
    }
}
