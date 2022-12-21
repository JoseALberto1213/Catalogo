/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.com.gm.peliculas.servicio;

/**
 *
 * @author JoseAlbertoMendozaCo
 */
public interface ICatalogoPeliculas {
    String NOMBRE_RECURSO = "peliculas.txt";
    void agregarPelicula(String nombrePelicula);
    void listatPeliculas();
    void buscarPelicula(String buscar);
    void iniciarCatalogoPeliculas();

    public void listarPeliculas();
}
