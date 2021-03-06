package com.livraria.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.livraria.model.Autor;
import com.livraria.model.Editora;
import com.livraria.model.Livro;
import com.livraria.repository.Autores;
import com.livraria.repository.Editoras;
import com.livraria.repository.Livros;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private Livros livros;
	@Autowired
	private Autores autores;
	@Autowired
	private Editoras editoras;
	
	public Editoras getEditoras() {
		return editoras;
	}

	public void setEditoras(Editoras editoras) {
		this.editoras = editoras;
	}

	public Autores getAutores() {
		return autores;
	}

	public void setAutores(Autores autores) {
		this.autores = autores;
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("listaLivros");
		mv.addObject(new Autor());
		mv.addObject("autores",autores.findAll());
		mv.addObject( new Editora() );
		mv.addObject("editoras", editoras.findAll() );
		mv.addObject(new Livro());
		mv.addObject("livros", livros.findAll() );
		return mv;
	}
	
	@PostMapping
	public String salvar( Livro livro ) {
		this.livros.save( livro );
		return "redirect:/livros";
	}

	@RequestMapping(value="/excluir/{id}")
	public String excluir( @PathVariable Long id, HttpServletRequest request, HttpServletResponse response ) {
		this.livros.deleteById(id);
		return "redirect:/livros";
	}
	
	@RequestMapping(value="/alterar/{id}")
	public ModelAndView alterar( @PathVariable Long id, HttpServletRequest request, HttpServletResponse response ) {
		ModelAndView mv = new ModelAndView("listaLivros");
		mv.addObject(new Autor());
		mv.addObject("autores",autores.findAll());
		mv.addObject("editoras",editoras.findAll());
		mv.addObject("livros", livros.findAll());
		Livro livro = livros.findById(id).get();
		mv.addObject(livro);
		return mv;
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

	public Livros getLivros() {
		return livros;
	}

	public void setLivros(Livros livros) {
		this.livros = livros;
	}
}