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

import com.livraria.model.Autor;
import com.livraria.repository.Autores;

@CrossOrigin
@RestController
@RequestMapping("/api/autor")
public class ApiAutorController {

	@Autowired
	private Autores autores;

	//retorna todos os autores existentes
	@RequestMapping(value="", method=RequestMethod.GET)
	public Collection<Autor> listaAutores(){
		return this.autores.findAll();
	}
	
	//retorna por determinado id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Optional<Autor> getAutor(@PathVariable("id") Long id){
		return this.autores.findById(id);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Autor>> listar(){
		return new ResponseEntity<List<Autor>>(new ArrayList<Autor>(autores.findAll()), HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> removeAutor(@PathVariable("id") Long id){
		Optional<Autor> a = autores.findById(id);
		if( a == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		autores.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<?> saveAutor(@RequestBody Autor autor){
		return new ResponseEntity<Autor>(autores.save(autor), HttpStatus.OK);
	}
	
}
