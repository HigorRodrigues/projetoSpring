package com.livraria.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.livraria.model.Autor;
import com.livraria.repository.Autores;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private Autores autores;
	
	@RequestMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaAutores");
		mv.addObject(new Autor());
		mv.addObject("autores", autores.findAll());
		return mv;
	}
	
	@PostMapping
	public String salvar(Autor a) {
		this.autores.save(a);
		return "redirect:/autores";
	}
	
	@RequestMapping(value="/excluir/{id}")
	public String excluirAutor(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response ) {
		this.autores.deleteById(id);
		return "redirect:/autores";
	}
	
	@RequestMapping(value="/alterar/{id}")
	public ModelAndView alterarAutor(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response ) {
		ModelAndView mv = new ModelAndView("ListaAutores");
		Autor autor = autores.getOne(id);
		mv.addObject(autor);
		mv.addObject("autores", autores.findAll());
		return mv;
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
