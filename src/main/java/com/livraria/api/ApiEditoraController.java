package com.livraria.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.livraria.model.Editora;
import com.livraria.repository.Editoras;

@RestController
@RequestMapping("/api/editora")
public class ApiEditoraController {

	@Autowired
	private Editoras editoras;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public Collection<Editora> listaEditoras(){
		return this.editoras.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Optional<Editora> getEditora(@PathVariable("id") Long id){
		return this.editoras.findById(id);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Editora>> listar(){
		return new ResponseEntity<List<Editora>>(new ArrayList<Editora>(editoras.findAll()),HttpStatus.OK); 
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> removeEditora (@PathVariable("id") Long id) {
		Optional<Editora> e = editoras.findById(id);
		if( e == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		editoras.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<?> saveEditora(@RequestBody Editora editora){
		return new ResponseEntity<Editora>(editoras.save(editora), HttpStatus.OK);
	}

}