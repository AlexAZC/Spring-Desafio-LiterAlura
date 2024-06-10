package com.literAlura.challenge.principal;

import com.literAlura.challenge.model.*;
import com.literAlura.challenge.repository.AutorRepository;
import com.literAlura.challenge.repository.LibroRepository;
import com.literAlura.challenge.service.ConsultaApi;
import com.literAlura.challenge.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsultaApi consultaApi = new ConsultaApi();
    private final String URL = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Autor> autores;
    private List<Libro> libros;
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;


    /* Utilizacion de inyecciones de dependencias constructora */
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository){
        this.repositorioLibro = libroRepository;
        this.repositorioAutor = autorRepository;
    }



    public void menu(){
        var opcion = -1;

        while (opcion != 0){
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir 
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosPorFecha();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción no disponible");

            }
        }
    }

    public void buscarLibroPorTitulo(){

        System.out.println("Escriba el nombre del libro que desea registrar");
        String libroBuscado = teclado.nextLine();
        var jsonApi = consultaApi.consumirApi(URL + libroBuscado.replace(" ", "+"));
        var datos = conversor.convertirDatos(jsonApi, Datos.class);

        if(datos == null || datos.datos().isEmpty()){
            System.out.println("Libro no encontrado");
        }

        DatosLibro datosLibro = datos.datos().get(0);

        Libro libro = new Libro(datosLibro);
        System.out.println(libro);

        Optional<Libro> libroEncontrado = repositorioLibro.findByTituloIgnoreCase(libro.getTitulo());

        if (libroEncontrado.isPresent()){

            System.out.println("\nEl libro ya esta registrado\n");

        } else {

            if(!datosLibro.autor().isEmpty()) {
                DatosAutor datosAutor = datosLibro.autor().get(0);
                Autor autor = new Autor(datosAutor);
                Optional<Autor> nombreAutor = repositorioAutor.findByNombre(autor.getNombre());

                        if(nombreAutor.isPresent()){
                            Autor autorExistente = nombreAutor.get();
                            libro.setAutor(autorExistente);
                            repositorioLibro.save(libro);

                        } else {
                            Autor autorGuardadoEnDb = repositorioAutor.save(autor);
                            libro.setAutor(autorGuardadoEnDb);
                            repositorioLibro.save(libro);

                        }

                Double numeroDeDescargas = libro.getNumeroDeDescargas() != null ? libro.getNumeroDeDescargas() : 0;
                System.out.println("-----LIBRO-----\n" +
                        "Titulo: " + libro.getTitulo() +
                        "\nAutor: " + autor.getNombre() +
                        "\nIdioma: " + libro.getIdioma() +
                        "\nNumero de Descargas: " + numeroDeDescargas +
                        "\n---------------\n");

            } else {

                System.out.println("Autor no encontrado");
            }
        }
    }


    private void mostrarLibrosRegistrados(){
        libros = repositorioLibro.findAll();

        if(!libros.isEmpty()){
            libros.stream()
                    .forEach(System.out::println);

        } else {
            System.out.println("No se encuentran libros registrados en su Base de Datos");

        }
    }


    private void mostrarAutoresRegistrados(){
        autores = repositorioAutor.findAll();

        if(!autores.isEmpty()){
            autores.stream()
                    .forEach(System.out::println);

        } else {
            System.out.println("No se encuentran autores registrados en su Base de Datos");

        }
    }


    private void mostrarAutoresVivosPorFecha(){
        System.out.println("Ingrese una año determinado");
        var anio = teclado.nextInt();
        autores = repositorioAutor.BuscarAutorVivoPorAnio(anio);

        if(!autores.isEmpty()){
            autores.stream()
                    .forEach(System.out::println);

        } else {
            System.out.println("No se encuentran autores vivos en determinado año");

        }
    }


    private void mostrarLibrosPorIdioma() {
        System.out.println("Seleccione un idioma para filtrar sus libros registrados");
        var opcionesDeIdioma = -1;
        while (opcionesDeIdioma != 0) {
            var opciones = """
                    1. en - Ingles
                    2. es - Español
                    3. fr - Francés
                    4. pt - Portugués
                                        
                    0. Volver a Las opciones anteriores
                    """;
            System.out.println(opciones);
           opcionesDeIdioma = teclado.nextInt();
           teclado.nextLine();

           switch (opcionesDeIdioma){
               case 1:
                   libros = repositorioLibro.findByIdiomaContaining("en");
                   if(!libros.isEmpty()){
                       libros.stream()
                               .forEach(System.out::println);

                   } else {
                       System.out.println("No hay ningun libro registrado en el idioma Ingles");
                   }
                   break;

               case 2:
                   libros = repositorioLibro.findByIdiomaContaining("es");
                   if(!libros.isEmpty()){
                       libros.stream()
                               .forEach(System.out::println);

                   } else {
                       System.out.println("No hay ningun libro registrado en el idioma Español y/o Castellano");
                   }
                   break;

               case 3:
                   libros = repositorioLibro.findByIdiomaContaining("fr");
                   if(!libros.isEmpty()){
                       libros.stream()
                               .forEach(System.out::println);

                   } else {
                       System.out.println("No hay ningun libro registrado en el idioma Frances");
                   }
                   break;

               case 4:
                   libros = repositorioLibro.findByIdiomaContaining("pt");
                   if(!libros.isEmpty()){
                       libros.stream()
                               .forEach(System.out::println);

                   } else {
                       System.out.println("No hay ningun libro registrado en el idioma Portugues");
                   }
                   break;

               case 0:
                   menu();
                   break;
               default:
                   System.out.println("Opcion no valida");
                   menu();
                   break;
           }
        }
    }














} //Clase principal
