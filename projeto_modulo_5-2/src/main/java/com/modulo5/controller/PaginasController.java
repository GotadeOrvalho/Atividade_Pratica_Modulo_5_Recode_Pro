package com.modulo5.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.modulo5.enums.UF;
import com.modulo5.model.Cliente;
import com.modulo5.model.Destino;
import com.modulo5.model.Mensagem;
import com.modulo5.repository.ClienteRepository;
import com.modulo5.repository.DestinoRepository;
import com.modulo5.repository.MensagemRepository;

@Controller
public class PaginasController {

	@Autowired
	private MensagemRepository mensagemRepository;

	@Autowired
	private DestinoRepository destinoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/index")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("areacliente/paginas/index");

		return modelAndView;
	}
	
	@GetMapping("/logincliente")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("usuario/login");

		return modelAndView;
	}

	@GetMapping("/destino")
	public ModelAndView destinos() {
		ModelAndView modelAndView = new ModelAndView("areacliente/paginas/destinos");

		List<Destino> destinos = destinoRepository.findAll(Sort.by(Sort.Order.asc("cidade")));
		modelAndView.addObject("destino", destinos);

		return modelAndView;
	}

	@GetMapping("/promocoes")
	public ModelAndView promocoes() {
		ModelAndView modelAndView = new ModelAndView("areacliente/paginas/promocoes");

		List<Destino> destinos = destinoRepository.findAll(Sort.by(Sort.Order.asc("cidade")));
		modelAndView.addObject("destino", destinos);

		return modelAndView;
	}

	@GetMapping("/enviarmensagem")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("areacliente/cliente/contato");
		modelAndView.addObject("mensagem", new Mensagem());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public ModelAndView salvar(Mensagem mensagem) {
		// Defina um valor padrão para o campo msg se estiver vazio
		if (mensagem.getMsg() == null || mensagem.getMsg().isEmpty()) {
			mensagem.setMsg("Mensagem padrão");
		}

		mensagemRepository.save(mensagem);

		ModelAndView modelAndView = new ModelAndView("redirect:/enviarmensagem");
		modelAndView.addObject("mensagemEnviada", true);

		return modelAndView;
	}

	@GetMapping("/perfil{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("areacliente/cliente/perfil");

		modelAndView.addObject("cliente", clienteRepository.findById(id).get());

		return modelAndView;
	}

	@GetMapping("/cadastrarcliente")
	public ModelAndView cadastrarcliente() {
		ModelAndView modelAndView = new ModelAndView("areacliente/cliente/cadastrocliente");

		modelAndView.addObject("cliente", new Cliente());
		modelAndView.addObject("ufs", UF.values());

		return modelAndView;
	}

	@PostMapping({ "/cadastrarcliente" })
	public String salvar(Cliente cliente, @PathVariable(required = false) Long id,
			@RequestParam("fileCliente") MultipartFile file) {
		try {
			cliente.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		clienteRepository.save(cliente);

		return "redirect:/index";
	}

	@GetMapping("/imagem/{id}")
	@ResponseBody
	public byte[] exibirImagen(@PathVariable("id") Long id) {
		Cliente cliente = this.clienteRepository.findById(id).orElse(null);
		return cliente != null ? cliente.getImagem() : null;
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("areacliente/cliente/cadastrocliente");

		modelAndView.addObject("cliente", clienteRepository.findById(id).get());
		modelAndView.addObject("ufs", UF.values());

		return modelAndView;
	}

	@PostMapping("/{id}/editar")
	public String editar(Cliente cliente, @PathVariable(required = false) Long id,
			@RequestParam("fileCliente") MultipartFile file) {
		
		try {
			cliente.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);

		if (clienteOptional.isPresent()) {
			Cliente existingCliente = clienteOptional.get();
			String senhaAtual = existingCliente.getSenha();

			cliente.setSenha(senhaAtual);
			clienteRepository.save(cliente);

			// Corrija a linha de redirecionamento para "/perfil" + id
			return "redirect:/perfil" + id;
		} else {
			// Cliente não encontrado, você pode lidar com isso de acordo com seus
			// requisitos
			return "redirect:/perfil" + id; // ou redirecione para uma página de erro, por exemplo
		}
	}
	
	@GetMapping("/cliente{id}/excluir")
	public String excluir(@PathVariable Long id) {
		clienteRepository.deleteById(id);

		return "redirect:/index";
	}
}