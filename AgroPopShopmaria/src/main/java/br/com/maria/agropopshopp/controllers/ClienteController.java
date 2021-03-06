
package br.com.maria.agropopshopp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.maria.agropopshopp.model.Cliente;
import br.com.maria.agropopshopp.repository.ClienteRepository;



@Controller
@RequestMapping("/")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepo;
	@GetMapping
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/listarClientes")
	public ModelAndView listarClientes() {
		List<Cliente> lista = clienteRepo.findAll();
		ModelAndView modelAndView = new ModelAndView("listarClientes");
		modelAndView.addObject("clientes",lista);
		return modelAndView;
	}
	
	@GetMapping("/adicionarClientes")
	public ModelAndView formAdicionarPessoa(){
		ModelAndView modelAndView = new ModelAndView("adicionarClientes");
		modelAndView .addObject(new Cliente());
		return modelAndView ;
	}
	
	@PostMapping("/adicionarClientes")
	public String AdicionarCliente(Cliente p) {
		this.clienteRepo.save(p);
		return "redirect:/listarClientes";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView formEditarcliente(@PathVariable("id") long id) {
		Cliente cliente = clienteRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID invalido" + id));
		ModelAndView modelAndView = new ModelAndView("editarCliente");
		modelAndView.addObject(cliente);
		return modelAndView;
	}
	
	@PostMapping("/editar/{id}")
	public ModelAndView editarCliente(@PathVariable("id") long id, Cliente cliente) {
		this.clienteRepo.save(cliente);
		return new ModelAndView("redirect:/listarClientes");
	}
	
	
	@GetMapping("/remover/{id}")
	public ModelAndView removerCliente(@PathVariable("id") long id) {
		Cliente aRemover = clienteRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID invalido" + id));
		clienteRepo.delete(aRemover);
		return new ModelAndView("redirect:/listarClientes");
	}
}


