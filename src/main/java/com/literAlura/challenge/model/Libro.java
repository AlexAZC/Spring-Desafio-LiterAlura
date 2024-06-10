package com.literAlura.challenge.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Entity
//@Table(name = "libros")
public class Libro {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id;
//    @Column(unique = true)
    private String titulo;
    private Autor autor;
    private String idioma;
    private Double numeroDeDescargas;
    private String nombreAutor;


    /* CONSTRUCTOR */
public Libro(DatosLibro datosLibro){
    this.titulo = datosLibro.titulo();
    this.idioma = datosLibro.idiomas().get(0);
    this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    var mappedAutor = datosLibro.autor().stream()
            .map(DatosAutor::nombre)
            .collect(Collectors.toList());

    this.nombreAutor = mappedAutor.get(0);
}


    /* TOSTRING */
    @Override
    public String toString() {

        return "-----LIBRO-----\n" +
                "Titulo: " + titulo +
                "\nAutor: " + nombreAutor +
                "\nIdioma: " + idioma +
                "\nNumero de Descargas: " + numeroDeDescargas +
                "\n---------------\n";
    }


    /* GETTERS Y SETTERS */

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
