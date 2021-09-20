package manejoDatos;

import java.io.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LogErrores2 {

    public static void escribirLogErrores(String ruta,  String archivoError, String errorLine){
        Calendar calendario = new GregorianCalendar();

        File archivo=new File(ruta);
        try {
            PrintWriter salida=new PrintWriter(new FileWriter(archivo, true));
            if (archivoError.equals("USERS")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" +
                        archivoError+ ": El username " + errorLine + " ya existe, se omitio el registro." + "\n");
            }
            else if (archivoError.equals("CLIENTS")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" +
                        archivoError+ ": El id " + errorLine + " ya existe, se omitio el registro." + "\n");
            }
            else if (archivoError.equals("PRODUCTS")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" +
                        archivoError+ ": El id " + errorLine + " ya existe, se omitio el registro." + "\n");
            }
            else if (archivoError.equals("INVOICES")){
                salida.println(LocalDate.now() +" "+ calendario.get(Calendar.HOUR_OF_DAY)+":"+calendario.get(Calendar.MINUTE) + "\t" +
                        archivoError+ ": El id " + errorLine + " ya existe, se omitio el registro." + "\n");
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
