package com.airport.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQuery(name="airplane.findAll", query="select a from Airplane a order by a.name")

@Entity
public class Airplane {
	
	@Id
	@GeneratedValue
	private int id;
	private boolean flying;
	private String name;

	public Airplane() {
		setFlying(true);
	}
	
	@OneToOne(mappedBy="airplane")
	private ParkingSlot slot;
	
	@OneToOne(mappedBy="airplane", cascade=CascadeType.ALL)
	private Runway runway;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	} 
}
