package it.myshop.web.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import it.myshop.web.dao.ClienteDao;
import it.myshop.web.model.Cliente;

public class ClienteDaoImpl implements ClienteDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void add(Cliente c) {
		//è come se avessi fatto INSERT INTO cliente VALUES c.getNome()...
		em.persist(c);
	}
	
	public Cliente getById(int id) {
		//SELECT * FROM cliente WHERE id=parametro;
		return  em.find(Cliente.class, id);
	}

	@Override
	@Transactional
	public void update(Cliente c) {
		//UPDATE cliente SET ...
		em.merge(c);
	}

	@Override
	@Transactional
	public void delete(int id) {
		//DELETE FROM cliente WHERE id=...
		Cliente c = getById(id);
		em.remove(c);
	}

	@Override
	@Transactional
	public void delete(Cliente c) {
		Cliente daCancellare = null;
		if(em.contains(c))
			//vuol dire che siamo all'interno della transazione
			daCancellare = c;
		else
			//invochiamo prima l'update dell'oggetto in modo da essere all'interno della transazione
			//e poi associamo l'oggetto Cliente che torna dal merge() all'oggetto daCancellare
			daCancellare = em.merge(c);
		
		em.remove(daCancellare);
	}

}
