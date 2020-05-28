package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoCompraController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		return modelAndView;
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		CarrinhoItem carrinhoItem = new CarrinhoItem(produtoDao.find(produtoId), tipoPreco);

		return carrinhoItem;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}
}