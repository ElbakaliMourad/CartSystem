package com.cartsystem.DaoI;

import java.util.List;

import com.cartsystem.entities.Items;

public interface ItemsServicesDaoI {

	boolean insertItem(Items item);

	Items findItem(Items item);

	boolean updateItem(Items item);

	boolean deleteItem(Items item);

	List<Items> getAllItems();

	List<Items> getItemByName(Items item);

	List<Items> getItemsByCategory(Items item);

	Items findItemByCategory(Items item);

}
