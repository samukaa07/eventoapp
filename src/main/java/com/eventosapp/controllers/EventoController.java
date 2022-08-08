package com.eventosapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.a.repository.ConvidadoRepository;
import com.eventosapp.a.repository.EventoRepository;
import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;

@Controller
public class EventoController {

	// toda vez que precisar utilizar essa interface ela vai ter criado automaticamente uma nova instancia
	// injeção de dependencia da interface EventoRepository
	@Autowired
	private EventoRepository er;

	// vamos fazer uma injeção de depndencia da interface ConvidadoRepository
	@Autowired
	private ConvidadoRepository cr;


	//esse endereço coloco depois do localhost:8080/cadastrarEvento
	//() o que esta dentro do parentes é definição do método no caso get vai retornar o fomulario
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
		// acima eu quis dizer pra retornar o que esta dentro do templates evento
	}

	//() o que esta dentro do parentes é definição do método no caso post quando eu apertar salvar dados ele vai mandar pra essa requizicao no DB
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarEvento";
		}

		//variavel do eventoRepository, er.save agora ele vai salvar o evento no banco de dados
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
		return "redirect:/cadastrarEvento";
		// acima eu quis dizer pra retornar para o metodo get cadastrar evento

	}

	// criando um método para retornar a lista de eventos
	// o /evetos vai enviar a requisição e vai receber a lista de eventos
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
	//usamos o ModelAndView para renderizar usar o (index.htlm)
		ModelAndView mv = new ModelAndView("index");
	//aqui metodo para fazer busca no banco de dados, interable pq e uma lista de eventos e funcao findall buscar todos eventos
		Iterable<Evento> eventos = er.findAll();
	// o valor ("eventos") é mesmo que esta na index.html, e aqui (eventos) a lista de eventos onde sera armazenado
		mv.addObject("eventos", eventos);
		return mv;

	}

	//{ aqui dentro ele vai retornar o codigo de cada evento, qd cliente clicar no evento vai redirecionar para o codigo e mostra os detlahes}
	// criar um método para receber via post esse formulario
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo")long codigo) {
		// fizemos um metodo na classe EventoRepositorio
		// variavel do tipo Evento , evento expecifico, e o codigo que agente recebeu
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		// abaixo vamos criar metodo para mostrar na tela do cadastro abaixo da lista de eventos
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		// vamos enviar essa lista para view evento/detalhesEvento do arquivo do HTMl detalhesEvento.html
		mv.addObject("convidados", convidados);

		return mv;

	}

	// metodo para deteletar Evento
	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo){ //agora vamos passar esse codigo para requisição,tem que criar um botão coluna deletar e passar o long codigo pro controller
		//a genbte vai pegar e encontrar esse evento
		Evento evento = er.findByCodigo(codigo);
		//agora vamos pegar esse er.delete o evento depois de fazer a busca
		er.delete(evento);
		// retronar para a lista de eventos
		return "redirect:/eventos";


	}


	// importamos o convidado pq é a entidade que agente criou
	// agora ele vai receber esse convidado e vai salvar esse convidado no banco
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable ("codigo")long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		// aqui vamos fazer o seguinte antes de salvar no banco vamos verificar pra ver se tem erros

		//exemplo se o usuario deixar um campo como rg em branco ele vai apresentar a msg abaixo que esta dentro desse if para view
		if(result.hasErrors()){
		// então se ele tiver erros ele vai pegar o parametro attributes e vai mostrar uma mensagem que esta em ()
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";

	}
		// vamos criar um objeto evento aqui pra fazer uma busca ele vai buscar por esse codigo q estamos recebendo como parametro
		Evento evento = er.findByCodigo(codigo);
		// depois que ele recebeu a gente vai passar aqui um convidado passa ele como setEvento
		convidado.setEvento(evento);
		//a gente acabou de fazer a relação desse convidado, esta relacionado a lista de convidado desse evento
		// agora vamos salvar esse convidado no banco
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";
	}

	@RequestMapping("/deletarConvidado")
	// deletarconvidado vai receber o ID da entidade convidado(StringRg) apagar pelo rg, classe interface CovidadoRepository
	public String deletarConvidado(String rg){
		Convidado convidado = cr.findByRg(rg);
		//qd encontrar esse convidado vai pegar convidadoRepository.delete convidado que achou a cima e vai ser deletado
		cr.delete(convidado);
		// agora após delete retornar a lista de convidados atualizada
		// pegar o codigo desse Evento pq vai redirecionar para o metodoso (/condigo)

		Evento evento = convidado.getEvento();
		//agora que temos evento acima conseguimos acessar esse evento
		long codigoLong = evento.getCodigo();
		// qd vamos redireconar, temos que passar esse codigo para String
		String codigo= "" + codigoLong;
		return "redirect:/" + codigo;

	}
}
