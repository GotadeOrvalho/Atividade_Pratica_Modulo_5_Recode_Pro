package com.modulo5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.modulo5.model.Destino;
import com.modulo5.repository.DestinoRepository;

@Controller
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoRepository destinoRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("destinos/homedestio");

        modelAndView.addObject("destinos", destinoRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("destino/formulariodestino");

        modelAndView.addObject("destino", new Destino());

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("destino/formulariodestino");

        modelAndView.addObject("destino", destinoRepository.findById(id).get());

        return modelAndView;
    }

    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Destino destino) {
        destinoRepository.save(destino);

        return "redirect:/destinos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
    	destinoRepository.deleteById(id);

        return "redirect:/destinos";
    }
    
}