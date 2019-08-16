package com.cartsystem.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table
@NamedQueries ({
@NamedQuery (query = "SELECT e FROM Items e", name = "queryItemsAll"),
@NamedQuery (query = "Select e From Items e WHERE e.title = :ptitle", name = "queryItemsByTitle"),
@NamedQuery (query = "Select e From Items e WHERE e.categoryId = :pcategoryId", name = "queryItemsByCategory")
})
public class Items {
	
	@Id
	int id;
	
	@Column
	@Basic
	String title;
	
	@Column
	@Basic
	String description;
	
	@Column
	@Basic
	double price;
	
	@Column
	@Basic
	int quantity;
	
	@Column
	@Basic
	int categoryId;
	
	@Column
	@Basic
	int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Items(int id, String title, String description, double price, int quantity, int categoryId, int status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryId = categoryId;
		this.status = status;
	}

	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + ", categoryId=" + categoryId + ", status=" + status + "]";
	}
	
	

}
