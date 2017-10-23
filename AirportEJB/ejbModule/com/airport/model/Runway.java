package com.airport.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQuery(name = "runway.findall", query = "select r from Runway r")

@Entity
public class Runway {

	@Id
	@GeneratedValue
	private int number;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Airplane airplane;
	
	public Runway() {
		airplane=null;
	}
	
	public Runway(Airplane airplane) {
		//this.airplane =airplane ;
	}
	
	public Airplane getAirplane() {
		return airplane;
	}
	public void setAirplane(Airplane plane) {
		airplane = plane;
	}
	
	public int getNumber(){
		return number;
	}	
		
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String toString() {
		String num = Integer.toString(number);
		
		if (airplane == null) {
			return num + " Runway is empty";
		}else {
			return num + " " + airplane.getId() +" " + airplane.getName();
		}
	}
}
	