package com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.livraria.model.Livro;

public interface Livros extends JpaRepository<Livro, Long>{

}

