package manejoDatos;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int id;
    private String name;
    private String address;
    private int phone;
    private String nit;

    //constructor de parametros
    public Cliente(int id, String name, String address, int phone, String nit) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.nit = nit;
    }

    public void presentar() {
        System.out.println("--------------------------");
        System.out.println("ID: " +id);
        System.out.println("Nombre: " + name);
        System.out.println("Direccion: "+address);
        System.out.println("telefono: " + phone);
        System.out.println("Nit: " + nit);
        System.out.println("--------------------------");

    }

    //constructor vacio
    public Cliente() {

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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }
}
