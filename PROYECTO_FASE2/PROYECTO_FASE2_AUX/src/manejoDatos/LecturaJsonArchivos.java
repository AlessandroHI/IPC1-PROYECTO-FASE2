package manejoDatos;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class LecturaJsonArchivos {

    ArrayList<Factura> arregloFacturas = new ArrayList<Factura>();
    ArrayList<Producto> arregloProductos = new ArrayList<Producto>();
    ArrayList<Usuario> arregloUsuarios = new ArrayList<Usuario>();
    ArrayList<Cliente> arregloClientes = new ArrayList<Cliente>();

    public Restaurante lecturaJsonRestaurantes(String archivoJson) {

        Restaurante restaurante=new Restaurante();

        String jsonRestaurante = "";
        File lector = new File(archivoJson);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(lector));
            String lectura = entrada.readLine();
            while (lectura != null) {
                jsonRestaurante += lectura + "\n";
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();

        }

        JsonParser parser = new JsonParser();

        JsonObject gsonArrRestaurante=parser.parse(jsonRestaurante).getAsJsonObject();

        JsonElement nameAux=gsonArrRestaurante.get("name");
        JsonElement addresAux=gsonArrRestaurante.get("address");
        JsonElement phoneAux=gsonArrRestaurante.get("phone");
        JsonElement loadAux=gsonArrRestaurante.get("load");

        String name=nameAux.getAsString();
        String address=addresAux.getAsString();
        int phone=phoneAux.getAsInt();
        String load=loadAux.getAsString();

        restaurante=new Restaurante(name, address, phone, load);

        // System.out.println("hola");


        return restaurante;

    }

    public ArrayList lecturaJsonUsers(String archivoJson) {

        ArrayList<Usuario> arregloUsuariosAux = new ArrayList<Usuario>();
        String jsonUsers = "";
        File lector = new File(archivoJson);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(lector));
            String lectura = entrada.readLine();
            while (lectura != null) {
                jsonUsers += lectura + "\n";
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();

        }

        JsonParser parser = new JsonParser();

        JsonArray gsonArrUsers = parser.parse(jsonUsers).getAsJsonArray();

        for (JsonElement obj : gsonArrUsers) {

            JsonObject gsonObj = obj.getAsJsonObject();

            String userName = gsonObj.get("username").getAsString();
            String password = gsonObj.get("password").getAsString();
            arregloUsuariosAux.add(new Usuario(userName, password));

        }

        for (Usuario usuarioAux : arregloUsuariosAux) {
            boolean repitenciaUsername=true;

            if (arregloUsuarios.size()==0){
                arregloUsuarios.add(usuarioAux);
            }else {
                int contador=0;
                for (Usuario usuario : arregloUsuarios) {

                    if(!(usuarioAux.getUsername().equals(usuario.getUsername()))){
                        contador++;
                        continue;
                    }
                    else if(usuarioAux.getUsername().equals(usuario.getUsername())){
                        LogErrores2.escribirLogErrores("errores.log", "USERS", usuarioAux.getUsername());
                        break;
                    }
                }

                if (contador==arregloUsuarios.size() && !(arregloUsuarios.get(arregloUsuarios.size()-1).getUsername().equals(usuarioAux.getUsername()))){
                    repitenciaUsername=false;
                }
            }

            if (repitenciaUsername==false){
                arregloUsuarios.add(usuarioAux);
            }
        }

        return arregloUsuarios;
    }

    public ArrayList lecturaJsonClientes(String archivoJson) {

        LogErrores logErrores=new LogErrores();
        ArrayList<Cliente> arregloClientesAux = new ArrayList<Cliente>();

        String jsonClientes = "";
        File lector = new File(archivoJson);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(lector));
            String lectura = entrada.readLine();
            while (lectura != null) {
                jsonClientes += lectura + "\n";
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();

        }

        JsonParser parser = new JsonParser();

        JsonArray gsonArrClientes = parser.parse(jsonClientes).getAsJsonArray();

        for (JsonElement obj : gsonArrClientes) {

            JsonObject gsonObj = obj.getAsJsonObject();

            int id = gsonObj.get("id").getAsInt();
            String name = gsonObj.get("name").getAsString();
            String address = gsonObj.get("address").getAsString();
            int phone = gsonObj.get("phone").getAsInt();
            String nit = gsonObj.get("nit").getAsString();
            arregloClientesAux.add(new Cliente(id, name, address, phone, nit));

        }

        //Manejo repitencias
        for (Cliente clienteAux : arregloClientesAux) {
            boolean repitenciaId=true; //Validacion si se repite id o no.

            if (arregloClientes.size()==0){
                arregloClientes.add(clienteAux);
            }
            else {
                int contador=0;
                for (Cliente cliente : arregloClientes) {

                    if(clienteAux.getId()!=cliente.getId()){
                        contador++;
                        continue;
                    }
                    else if(clienteAux.getId()==cliente.getId()){
                        LogErrores2.escribirLogErrores("errores.log", "CLIENTS", ""+clienteAux.getId());
                        break;
                    }
                }

                if (contador==arregloClientes.size() && arregloClientes.get(arregloClientes.size()-1).getId()!=clienteAux.getId()){
                    repitenciaId=false;
                }
            }

            if (repitenciaId==false){
                arregloClientes.add(clienteAux);
            }
        }
        return arregloClientes;
    }

    public ArrayList lecturaJsonProductos(String archivoJson) {

        ArrayList<Producto> arregloProductosAux = new ArrayList<Producto>();

        String jsonProductos = "";
        File lector = new File(archivoJson);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(lector));
            String lectura = entrada.readLine();
            while (lectura != null) {
                jsonProductos += lectura + "\n";
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();

        }

        Gson gson=new Gson();
        Producto [] productos=gson.fromJson(jsonProductos, Producto[].class);

        for (Producto producto: productos){
            arregloProductosAux.add(producto);
        }

        for (Producto productoAux: arregloProductosAux){
            boolean repitenciaIdProducto=true;

            if (arregloProductos.size()==0){
                arregloProductos.add(productoAux);
            }else {
                int contador=0;
                for (Producto producto: arregloProductos){

                    if (productoAux.getId()==producto.getId()){
                        LogErrores2.escribirLogErrores("errores.log", "PRODUCTS", ""+productoAux.getId());
                        break;
                    }
                    else {
                        contador++;
                        continue;
                    }
                }
                if (contador==arregloProductos.size() && arregloProductos.get(arregloProductos.size()-1).getId()!=productoAux.getId()){
                    repitenciaIdProducto=false;
                }
            }

            if (repitenciaIdProducto==false){
                arregloProductos.add(productoAux);
            }
        }
        return arregloProductos;
    }

    public ArrayList lecturaJsonFacturas(String archivoJson) {

        ArrayList<Factura> arregloFacturasAux = new ArrayList<Factura>();

        String jsonFacturas = "";
        File lector = new File(archivoJson);

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(lector));
            String lectura = entrada.readLine();
            while (lectura != null) {
                jsonFacturas += lectura + "\n";
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();

        }

        Gson gson=new Gson();
        Factura [] facturas=gson.fromJson(jsonFacturas, Factura[].class);

        for (Factura factura: facturas){
            arregloFacturasAux.add(factura);
        }

        for (Factura facturaAux:arregloFacturasAux){
            boolean repitenciaIdFactura=true;

            if (arregloFacturas.size()==0){
                arregloFacturas.add(facturaAux);
            }else {

                int contador=0;
                for (Factura factura: arregloFacturas){

                    if (facturaAux.getId()==factura.getId()){
                        LogErrores2.escribirLogErrores("errores.log", "INVOICES", ""+facturaAux.getId());
                        break;
                    }
                    else {
                        contador++;
                        continue;
                    }
                }
                if (contador==arregloFacturas.size() && arregloFacturas.get(arregloFacturas.size()-1).getId()!=facturaAux.getId()){
                    repitenciaIdFactura=false;
                }
            }
            if (repitenciaIdFactura==false){
                arregloFacturas.add(facturaAux);
            }
        }
        return arregloFacturas;
    }
}
