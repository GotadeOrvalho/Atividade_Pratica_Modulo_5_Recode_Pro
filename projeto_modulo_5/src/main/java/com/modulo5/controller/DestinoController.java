package com.modulo5.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modulo5.enums.UF;
import com.modulo5.model.Destino;
import com.modulo5.repository.DestinoRepository;

@Controller
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoRepository destinoRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/destino/homedestino");

        List<Destino> destinos = destinoRepository.findAll(Sort.by(Sort.Order.asc("cidade")));
        modelAndView.addObject("destino", destinos);

        return modelAndView;
    }
    
    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/destino/detalhesdestino");

        modelAndView.addObject("destino", destinoRepository.findById(id).get());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/destino/formulariodestino");
       

        modelAndView.addObject("destino", new Destino());
        modelAndView.addObject("ufs", UF.values());

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("areafuncionario/destino/formulariodestino");

        modelAndView.addObject("destino", destinoRepository.findById(id).get());
        modelAndView.addObject("ufs", UF.values());

        return modelAndView;
    }

    @GetMapping("/imagem/{id}")
    @ResponseBody
    public byte[] exibirImagen(@PathVariable("id") Long id) {
        Destino destino = this.destinoRepository.findById(id).orElse(null);
        return destino != null ? destino.getImagem() : null;
    }
    
    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(@PathVariable(required = false) Long id, Destino destino,@RequestParam("fileCidade") MultipartFile file) {
        // ... seu código de salvamento
    	try {
			destino.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

        destino.setValorFinal(); // Certifique-se de chamar o método aqui

        destinoRepository.save(destino);

        return "redirect:/destinos";
    }
   

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
    	destinoRepository.deleteById(id);

        return "redirect:/destinos";
    }
    
}