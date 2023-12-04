package com.modulo5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.modulo5.model.Passagem;
import com.modulo5.repository.ClienteRepository;
import com.modulo5.repository.DestinoRepository;
import com.modulo5.repository.PassagemRepository;

@Controller
@RequestMapping("/projetos")
public class PassagemController {

    @Autowired
    private PassagemRepository passagemRepository;

    @Autowired
    private DestinoRepository destinoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("passagem/homepassagem");

        modelAndView.addObject("passagem", passagemRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("passagem/detalhespassagem");

        modelAndView.addObject("passagem", passagemRepository.findById(id).get());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("passagem/formulariopassagem");

        modelAndView.addObject("passagem", new Passagem());
        modelAndView.addObject("clientes", clienteRepository.findAll());
        modelAndView.addObject("destino", destinoRepository.findAll());
  

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("passagem/formulariopassagem");

        modelAndView.addObject("passagem", passagemRepository.findById(id).get());
        modelAndView.addObject("clientes", clienteRepository.findAll());
        modelAndView.addObject("destino", destinoRepository.findAll());

        return modelAndView;
    }

    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Passagem passagem) {
        passagemRepository.save(passagem);

        return "redirect:/passagem";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        passagemRepository.deleteById(id);

        return "redirect:/passagem";
    }
    
}
