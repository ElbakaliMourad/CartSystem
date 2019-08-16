package com.cartsystem.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.cartsystem.DaoI.ItemsServicesDaoI;
import com.cartsystem.entities.Items;

public class ItemsServices implements ItemsServicesDaoI {
	
	@Override
	public boolean insertItem(Items item) {
		boolean result = true;
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("CartSystem");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		try {		
		entitymanager.getTransaction().begin();		
		entitymanager.persist(item);		
		entitymanager.getTransaction().commit();

		} catch(PersistenceException e) {
				e.getMessage();
				System.out.println(e.toString());
				result = false;
		}
		finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}		
		return result;
	}

	@Override
	public Items findItem(Items item) {
		Items foundItem = null;
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("CartSystem");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		try {		
			foundItem = entitymanager.find(Items.class, item.getId());
		} catch(PersistenceException e) {
				e.getMessage();
		}
		finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}		
		return foundItem;
	}
	
	@Override
	public Items findItemByCategory(Items item) {
		Items foundItem = null;
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("CartSystem");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		try {
			foundItem = entitymanager.find(Items.class, item.getCategoryId());
		} catch(PersistenceException e) {
			e.getMessage();
		}
		finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}		
		return foundItem;
	}


	@Override
	public boolean updateItem(Items item) {
		boolean result = true;
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("CartSystem");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		try {		
		entitymanager.getTransaction().begin();	
		Items itemFound = entitymanager.find(Items.class, item.getId());
		itemFound.setTitle(item.getTitle());
		itemFound.setDescription(item.getDescription());
		itemFound.setPrice(item.getPrice());
		itemFound.setQuantity(item.getQuantity());
		itemFound.setCategoryId(item.getCategoryId());
		itemFound.setStatus(item.getStatus());

//		entitymanager.refresh(foundItem);		
		entitymanager.getTransaction().commit();

		} catch(PersistenceException e) {
				e.getMessage();
				result = false;
		}
		finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}		
		return result;
	}

	@Override
	public boolean deleteItem(Items item) {
		boolean result = true;
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("CartSystem");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		try {		
		entitymanager.getTransaction().begin();	
		Items itemFound = entitymanager.find(Items.class, item.getId());
		entitymanager.remove(itemFound);
		entitymanager.getTransaction().commit();

		} catch(PersistenceException e) {
				e.getMessage();
				result = false;
		}
		finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}		
		return result;
	}

	@Override
	public List<Items> getAllItems() {		
	List<Items> itemsList = new ArrayList<>();		
	EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("CartSystem");
	EntityManager entitymanager = entitymanagerfactory.createEntityManager();
	
//	Query query = entitymanager.createQuery("SELECT e FROM Items e");
	Query query = entitymanager.createNamedQuery("queryItemsAll");
	itemsList = query.getResultList();
	
	entitymanager.close();
	entitymanagerfactory.close();
	
	return itemsList;
	}

	@Override
	public List<Items> getItemsByCategory(Items item) {
		List<Items> itemsList = new ArrayList<>();		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("CartSystem");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		
//		Query query = entitymanager.createQuery("Select e From Items e WHERE e.categoryId = :pcategoryId");
		Query query = entitymanager.createNamedQuery("queryItemsByCategory");
		query.setParameter("pcategoryId", item.getCategoryId());
		itemsList = query.getResultList();
		
		entitymanager.close();
		entitymanagerfactory.close();
		
		return itemsList;
	}

	@Override
	public List<Items> getItemByName(Items item) {

		List<Items> itemList = new ArrayList<>();
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("CartSystem");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		
//		Query query = entitymanager.createQuery("Select e From Items e WHERE e.title = :ptitle");
		Query query = entitymanager.createNamedQuery("queryItemsByTitle");

		query.setParameter("ptitle", item.getTitle());
		itemList = query.getResultList();
		
		entitymanager.close();
		entitymanagerfactory.close();
		
		return itemList;
	}
}
