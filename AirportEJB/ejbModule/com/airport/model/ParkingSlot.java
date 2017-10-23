package com.airport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQuery(name= "parkslot.findall", query= "select s from ParkingSlot s")

@Entity
public class ParkingSlot {
 
	
	@Id
	@GeneratedValue
	private int slotNumber;
	
	@OneToOne
	private Airplane airplane;
	
	public ParkingSlot() {
		airplane = null;
	}
	
	public Airplane getAirplane(){
		return airplane;
	}
	
	public void setAirplane(Airplane plane) {
		airplane = plane;
	}
	public int getSlotNumber() {
		return slotNumber;
	}
	
	public void setSlotNumber(int number) {
		slotNumber = number;
	}
	
	public String toString() {
		String slotNum = Integer.toString(slotNumber);
		if(airplane == null) {
			return slotNum + " Parking Slot is empty"; 
		}else {
			return slotNum + " " + airplane.getName();
		}
	}
	
}
