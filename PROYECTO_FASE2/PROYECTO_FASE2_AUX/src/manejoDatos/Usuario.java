package manejoDatos;

public class Usuario {

    private String username;
    private String password;

    //constructor de parametros
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;

    }

    //constructor vacio
    public Usuario() {

    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void MostrarUsuarios() {
        System.out.println("--------------------------");
        System.out.println("manejoDatos.Usuario: " + username);
        System.out.println("Contraseña: "+password );
        System.out.println("--------------------------");

    }
}
