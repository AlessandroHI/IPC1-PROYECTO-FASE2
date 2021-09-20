package manejoDatos;

import crudUsuarios.CrearUsuario;
import interfazClase.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LogErrores {

    public static void escribirLogErrores(String ruta,  String archivoError, String errorLine){
        Calendar calendario = new GregorianCalendar();

        File archivo=new File(ruta);
        try {
            PrintWriter salida=new PrintWriter(new FileWriter(archivo, true));
            if(archivoError.equals("WRONGPASSWORD")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + errorLine + ": Inicio de sesion fallido.");
            }
            else if(archivoError.equals("USERNOTFOUND")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + "No se encontro el usuario " + errorLine +".");
            }
            else if(archivoError.equals("LOGINTRUE")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + errorLine + ": Inicio de sesion exitoso.");

            }else if (archivoError.equals("CREARUSER")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Se creo el usuario." + errorLine +".");

            }else if (archivoError.equals("ACTUALIZAR")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Se actualizo el usuario." + errorLine +".");

            }
            else if(archivoError.equals("REMOVEUSER")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Elimino al manejoDatos.Usuario \""+ JPanelUser.userRemove+"\"");
           /////////////////////////////////////
            }else if (archivoError.equals("CREARPRODUCTO")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Se creo el producto." + errorLine +".");

            }else if (archivoError.equals("ACTUALIZARPRODUCTO")){//Falta actualizar producto
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Se actualizo el producto." + errorLine +".");

            }
            else if(archivoError.equals("REMOVEPRODUCTO")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Elimino el producto \""+ JPanelProducto.removeProd.getName()+"\" con ID "+JPanelProducto.removeProd.getId());
            }///////////////////////Cliente
            else if (archivoError.equals("CREARCLIENTE")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Se creo el cliente." + errorLine +".");

            }else if (archivoError.equals("ACTUALIZARCLIENTE")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Se actualizo el usuario." + errorLine +".");

            }
            else if(archivoError.equals("REMOVECLIENTE")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Elimino al manejoDatos.Cliente \""+ JPanelCliente.removeCliente.getName()+"\"con ID "+ JPanelCliente.removeCliente.getId());
            }///////////////// Factura
            else if (archivoError.equals("CREARFACTURA")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Se creo la factura de." + errorLine +".");

            }

            else if(archivoError.equals("REMOVEFACTURA")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": Elimino la manejoDatos.Factura con ID \""+ JPanelFactura.removeFactura.getId());
            }
            else if(archivoError.equals("CERRARSESION")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" + Login.USUARIO + ": CERRO SESION ");
            }
            salida.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
