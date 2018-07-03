package com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.livraria.model.Autor;

public interface Autores extends JpaRepository<Autor, Long>{

}
