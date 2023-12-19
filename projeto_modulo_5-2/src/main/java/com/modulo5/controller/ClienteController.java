package com.modulo5.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.modulo5.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("areafuncionario/cliente/detalhescliente");

		modelAndView.addObject("cliente", clienteRepository.findById(id).get());

		return modelAndView;
	}

	@GetMapping
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("areafuncionario/cliente/homecliente");

		modelAndView.addObject("cliente", clienteRepository.findAll());

		return modelAndView;
	}

	

}
