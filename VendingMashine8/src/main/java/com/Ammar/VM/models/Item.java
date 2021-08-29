package com.Ammar.VM.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="items")
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2,max=100)
    private String name;
	
	@NotNull
	@Min(0)
    private int cost;

	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<Position> positions;
	
	@NotNull
	@Min(0)
    private int quantity;
	
	@Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;
	public Item() {
		super();
	}
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public List<Position> getPositions() {
		return positions;
	}
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

	@PrePersist
	    protected void onCreate() {
	        this.createdAt = new Date();
	    }

    @PostPersist
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
