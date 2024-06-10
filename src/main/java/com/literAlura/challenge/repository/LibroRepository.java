package com.literAlura.challenge.repository;

import com.literAlura.challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByIdiomaContaining(String idioma);

    Optional<Libro> findByTituloIgnoreCase(String titulo);


}
