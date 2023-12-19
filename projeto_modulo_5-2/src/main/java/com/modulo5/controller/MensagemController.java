package com.modulo5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



import com.modulo5.repository.MensagemRepository;

@Controller
@RequestMapping("/mensagens")
public class MensagemController {

    @Autowired
    private MensagemRepository mensagemRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/mensagem/homemensagem");

        modelAndView.addObject("mensagem", mensagemRepository.findAll());

        return modelAndView;
    }

    
   

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
    	mensagemRepository.deleteById(id);

        return "redirect:/mensagens";
    }
    
}