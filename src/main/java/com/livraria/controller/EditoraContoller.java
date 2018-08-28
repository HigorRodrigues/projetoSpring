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

import com.livraria.model.Editora;
import com.livraria.repository.Editoras;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
@RequestMapping("/editoras")
public class EditoraContoller {

	@Autowired
	private Editoras editoras;
	
	@RequestMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaEditora");
		mv.addObject(new Editora());
		mv.addObject("editoras", editoras.findAll());
		return mv;
	}

	@PostMapping
	public String salvar( Editora e ) {
		this.editoras.save( e );
		return "redirect:/editoras";
	}
	
	@RequestMapping(value="/excluir/{id}")
	public String excluirEditora(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response ) {
		this.editoras.deleteById(id);
		return "redirect:/editoras";
	}
	
	@RequestMapping(value="/alterar/{id}")
	public ModelAndView alterarEditora( @PathVariable Long id, HttpServletRequest request, HttpServletResponse response ) {
		ModelAndView mv = new ModelAndView("ListaEditora");
		Editora e = editoras.getOne(id);
		mv.addObject( e );
		mv.addObject("editoras", editoras.findAll());
		return mv;
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	
}