package com.eventosapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// dizendo que essa classe é o controle
@Controller
public class IndexController {

	// quando usuario digitar essa requisicao o controler vai direcionar essa pagina HTML
	@RequestMapping("/")
	public String index() {
		return "index";


	}



}
