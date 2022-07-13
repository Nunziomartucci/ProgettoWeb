package it.myshop.web.dao;

import it.myshop.web.model.Cliente;

public interface ClienteDao {
	public void add(Cliente c);
	public void update(Cliente c);
	public void delete(int id);
	public void delete(Cliente c);
	public Cliente getById(int id);
}
