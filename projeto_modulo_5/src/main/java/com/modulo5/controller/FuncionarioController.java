package com.modulo5.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.modulo5.model.Funcionario;
import com.modulo5.enums.UF;
import com.modulo5.repository.FuncionarioRepository;
import com.modulo5.utils.SenhaUtils;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/funcionario/homefunc");

        modelAndView.addObject("funcionarios", funcionarioRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/funcionario/detalhesfunc");

        modelAndView.addObject("funcionario", funcionarioRepository.findById(id).get());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/funcionario/formulariofunc");

        modelAndView.addObject("funcionario", new Funcionario());
        modelAndView.addObject("ufs", UF.values());

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/funcionario/formulariofunc");

        modelAndView.addObject("funcionario", funcionarioRepository.findById(id).get());
        modelAndView.addObject("ufs", UF.values());

        return modelAndView;
    }

    @PostMapping({"/cadastrar"})
    public String salvar(Funcionario funcionario) {
    	String senhaEncriptada = SenhaUtils.encode(funcionario.getSenha());
    	 
        funcionario.setSenha(senhaEncriptada);
        funcionarioRepository.save(funcionario);

        return "redirect:/funcionarios";
    }
    
    @PostMapping("/{id}/editar")
    public String editar(Funcionario funcionario, @PathVariable Long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
       
        if (funcionarioOptional.isPresent()) {
            Funcionario existingFuncionario = funcionarioOptional.get();
            String senhaAtual = existingFuncionario.getSenha();
            
            funcionario.setSenha(senhaAtual);
            funcionarioRepository.save(funcionario);
            
            return "redirect:/funcionarios";
        } else {
            // Cliente não encontrado, você pode lidar com isso de acordo com seus requisitos
            return "redirect:/funcionarios"; // ou redirecione para uma página de erro, por exemplo
        }
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);

        return "redirect:/funcionarios";
    }
    
}

