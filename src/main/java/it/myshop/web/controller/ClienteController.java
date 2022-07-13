package it.myshop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.myshop.web.dao.ClienteDao;
import it.myshop.web.model.Cliente;

@Controller
@RequestMapping("/")
public class ClienteController {
	@Autowired
	private ClienteDao clienteService;
	
	@ResponseBody
	@GetMapping("/add")
	public String add() {
		Cliente c = new Cliente();
		c.setCognome("Pirrello");
		c.setNome("Dario");
		c.setEmail("dariopirrello.job@gmail.com");
		c.setTelefono("333333333");
		c.setUsername("Dario");
		c.setPassword("DDDQQQ");
		clienteService.add(c);
		return "Utente " + c.getUsername() + " aggiunto nel db";
	}
	
	@ResponseBody
	@GetMapping("/getById")
	public String getById() {
		Cliente c = clienteService.getById(3);
		return c.getCognome() + " " + c.getNome();
	}
	
	@ResponseBody
	@GetMapping("/update")
	public String update() {
		Cliente c = clienteService.getById(4);
		c.setEmail("cristiano.ronaldo85@gmail.com");
		clienteService.update(c);
		return "Utente " + c.getUsername() + " aggiornato nel db";
	}
	
	@ResponseBody
	@GetMapping("/delete")
	public String delete() {
		clienteService.delete(5);
		return "Utente con id 6 cancellato nel db";
	}
	
	@ResponseBody
	@GetMapping("/deleteOggetto")
	public String delete2() {
		clienteService.delete(clienteService.getById(6));
		return "Utente con id 6 cancellato nel db";
	}
}
