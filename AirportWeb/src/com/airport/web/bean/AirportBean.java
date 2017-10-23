package com.airport.web.bean;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airport.model.Airplane;
import com.airport.model.ParkingSlot;
import com.airport.model.Runway;
import com.airport.session.AirportEJB;

@ManagedBean(name="airportBean")
@SessionScoped
public class AirportBean implements Serializable {
	private static final long serialVersionUID = 1665363412715858198L;

	@EJB
	private AirportEJB airportEJB;
	
	private Airplane airplane;
	
	private List<Runway> runway;
	
	private List<ParkingSlot> parkingSlot;
	public AirportBean() {
		
		System.out.println("AIRPORT: " + UUID.randomUUID());
	}
	
	@PostConstruct
	private void init() {
		airplane = new Airplane();
		airportEJB.initRunways();
		this.runway = getRunways();
		airportEJB.initParkingSlots();
		this.parkingSlot = getParkingSlots();

	}
	
	public List<Airplane> getAirplanes() {
		return airportEJB.getAirplanes();
	}
	
	public Airplane getAirplane() {
		return airplane;
	}
	
	public void store() {
		airportEJB.store(airplane);
		airplane = new Airplane();
	}
	
	public void landPlane(int id) throws InterruptedException {
		airportEJB.landPlane(id);
	}
	
	public void parkPlane(Runway runway, int id) throws InterruptedException {
		airportEJB.parkPlane(runway,id);
		this.runway = getRunways();
	}
	
	public List<Runway> getRunways(){
		runway = airportEJB.getRunways();
		return runway;
	}
	
	public List<ParkingSlot> getParkingSlots(){
		parkingSlot = airportEJB.getParkingSlots();
		return parkingSlot;
	}
	
	public String sayHello() {
		return "Say Hello";
	}
}

