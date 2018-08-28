package com.livraria.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.model.Livro;
import com.livraria.repository.Livros;

@CrossOrigin
@RestController
@RequestMapping("/api/livro")
public class ApiLivroController {

	@Autowired
	private Livros livros;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public Collection<Livro> listaLivros(){
		return this.livros.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Optional<Livro> getLivro(@PathVariable("id") Long id){
		return this.livros.findById(id);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar(){
		return new ResponseEntity<List<Livro>>(new ArrayList<Livro>(livros.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> removeLivro(@PathVariable("id") Long id){
		Optional<Livro> l = livros.findById(id);
		if( l == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		livros.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<?> saveLivro(@RequestBody Livro livro){
		return new ResponseEntity<Livro>(livros.save(livro), HttpStatus.OK);
	}
}
