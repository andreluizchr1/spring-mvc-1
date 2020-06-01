package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Cacheable(value = "produtosHome")
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("home").addObject("produtos", produtoDao.listar());
	}
}
