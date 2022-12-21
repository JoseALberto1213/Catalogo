/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.*;

public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEx {
        var archivo= new File(nombreRecurso);//se crea variable de archivo.
        return archivo.exists(); //revisa si el archivo exite
    }
 
    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        var archivo= new File (nombreRecurso);
        List<Pelicula> peliculas=new ArrayList<>();
        try {
            var entrada= new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while(linea!=null){
                var pelicula= new Pelicula(linea);
                peliculas.add(pelicula);
                linea=entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listat péliculas:   "+ ex.getMessage());
        } catch (IOException ex) {
             ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listat péliculas:   "+ ex.getMessage());
        }
        return peliculas;
    }

    
    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        
        var archivo = new File(nombreRecurso);
        try { 
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("SE HA ESCRITO INFORMACION AL ARCHIVO" + pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir péliculas:   "+ ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombreRecurso);
        String resultado = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            var indice =1;
            while(linea != null){
            if(buscar != null && buscar.equalsIgnoreCase(linea)){
                resultado= "Pelicula" + linea + "encontrada en el indice" + indice;
                break;
            }
            linea= entrada.readLine();
            indice++;
            }
            entrada.close();
            
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
           throw new LecturaDatosEx("Excepcion al buscar péliculas:   "+ ex.getMessage());
        } catch (IOException ex) {
           ex.printStackTrace();
           throw new LecturaDatosEx("Excepcion al buscar péliculas:   "+ ex.getMessage());
        }
                return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        try { 
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.append("se ha creado el archivo");
        } catch (IOException ex) {
           ex.printStackTrace();
           throw new AccesoDatosEx("Excepcion al crear archivo:   "+ ex.getMessage());
        }
    }

    @Override
    public Void borrar(String nombreRecurso) {
        var archivo= new File(nombreRecurso);
        if (archivo.exists()){
            archivo.delete();
        }
        System.out.println("SE HA Borrado el ARCHIVO");
        return null;
    }
    
}
