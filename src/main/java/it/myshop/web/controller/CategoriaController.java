package it.myshop.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.myshop.web.model.Categoria;
import it.myshop.web.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	//definiamo l'oggetto repository
	//che useremo per le operazioni CRUD
	@Autowired
	private CategoriaRepository repository;
	
	@ResponseBody
	@GetMapping("/add")
	public String add() {
		Categoria c = new Categoria();
		c.setNome("ALIMENTARI");
		c.setDescrizione("Prodotti da frigo");
		repository.save(c);
		return "Categoria " + c.getNome() + " inserita correttamente";
	}
	
	@ResponseBody
	@GetMapping("/")
	public String get() {
		Iterable<Categoria> categorie = repository.findAll();
		
		categorie.forEach((Categoria c) -> {
			System.out.println(c.getId() + " - " + c.getNome());
		});
		return "Tutte le categorie sono state recuperate!";
	}
	
	@ResponseBody
	@GetMapping("/getById")
	public String getById() {
		Optional<Categoria> categoria = repository.findById(2);
		
		return "La categoria " + categoria.get().getNome() + " con Id 2 è stata recuperata!";
	}
	
	@ResponseBody
	@GetMapping("/getByNome")
	public String getByNome() {
		Categoria categoria1 = repository.findByNome("SMARTPHONE");
		Categoria categoria2 = repository.findByNome("TV LED");
		
		System.out.println(categoria1.getNome() + " - " + categoria2.getNome());
		
		return "Le categoria SMARTPHONE e TV LED sono state recuperate!";
	}
	
	@ResponseBody
	@GetMapping("/getByNomeLike")
	public String getByNomeLike() {
		List<Categoria> categorie = repository.getByNomeLike("E%");
		
		System.out.println(categorie.size());
		
		return "Le categoria che iniziano con la E sono state recuperate!";
	}
}
