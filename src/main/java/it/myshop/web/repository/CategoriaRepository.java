package it.myshop.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.myshop.web.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer>{

	public Categoria findByNome(String nome);
	public Categoria findCategoriaByNome(String nome);
	public List<Categoria> getByNomeLike(String like);
}
