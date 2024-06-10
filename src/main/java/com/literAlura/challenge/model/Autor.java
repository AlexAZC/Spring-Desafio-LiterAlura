package com.literAlura.challenge.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Autor {


    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;

    private List<Libro> libros;


    /* CONSTRUCTOR */
    public Autor(DatosAutor autor){
        this.nombre = autor.nombre();
        this.fechaDeNacimiento = autor.fechaDeNacimiento();
        this.fechaDeMuerte = autor.fechaDeMuerte();
    }


    /* GETTERS Y SETTERS */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return
                "nombre: " + nombre + "\n" +
                ", fechaDeNacimiento: " + fechaDeNacimiento + "\n" +
                ", fechaDeMuerte: " + fechaDeMuerte + "\n" +
                ", libros: " + this.libros.stream()
                        .map(Libro::getTitulo)
                        .collect(Collectors.toList())+ "\n"
                ;
    }
}
