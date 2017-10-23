package com.airport.session;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.airport.model.Airplane;
import com.airport.model.ParkingSlot;
import com.airport.model.Runway;

@Stateless
public class AirportEJB {

	@PersistenceContext(unitName="airport")
	private EntityManager entityManager;
	
	
	public AirportEJB() {

	}
	
	public List<Airplane> getAirplanes() {
		Query query = entityManager.createNamedQuery("airplane.findAll");
		
		@SuppressWarnings("unchecked")
		List<Airplane> airplanes = query.getResultList();
		return airplanes;
	}
	
	public void store(Airplane airplane) {
		entityManager.persist(airplane);
	}
	
	public void initRunways() {
		for(int i = 0; i<3;i++) {
			entityManager.persist(new Runway());
		}
	}
	
	public void initParkingSlots() {
		for(int i=0; i<5;i++) {
			entityManager.persist(new ParkingSlot());
		}
	}
	
	
	public List<Runway> getRunways() {
		Query query = entityManager.createNamedQuery("runway.findall");
		
		@SuppressWarnings("unchecked")
		List<Runway> runways = query.getResultList();
		//System.out.println(runways.get(0).getNumber());
		return runways;
	}
	
	public Runway getEmptyRunway() {
		// query to find empty runnway
		Query query = entityManager.createQuery("SELECT r from Runway r where airplane = null");
		
		//return empty runway
		@SuppressWarnings("unchecked")
		List<Runway> runway = query.getResultList();
	    if(runway.isEmpty()) {
	    	return null;
	    }else {	    	
	    	return runway.get(0);
	    }
		
		//return new Runway();
	}
	
	
	public void landPlane(int id) throws InterruptedException {
	
		//get empty runway
    	Runway runway = getEmptyRunway();
        // if there is no empty runway do nothing
    	  if (runway != null) {
    	  // this can be done much shorter with entityManager.find()
    	  String sid = Integer.toString(id);
    	  System.out.println("sid = " + sid);
	      Query query = entityManager.createQuery("Select a from Airplane a where id =" + sid);
    	  //not the most intellifent way to get a single result
	      List<Airplane> templist = query.getResultList();
	      Airplane airplane = templist.get(0);	      
	      runway.setAirplane(airplane);
	      // set flying to false so the land button is not clickable anymore
		  airplane.setFlying(false);
	      //simulate the time the landing takes ...dunno if this is truely needed
	      TimeUnit.SECONDS.sleep(5);
    	}
   
	}
	
	
	
	public List<ParkingSlot> getParkingSlots(){
		
		Query query = entityManager.createNamedQuery("parkslot.findall");
		@SuppressWarnings("unchecked")
		List<ParkingSlot> parkingSlots = query.getResultList();
		//System.out.println(parkingSlots.get(0).getSlotNumber());
		return parkingSlots;
	}
	
	public ParkingSlot getEmptyParkSlot() {
		// query to find empty ParkSLot
		Query query = entityManager.createQuery("SELECT s from ParkingSlot s where airplane=null");
		@SuppressWarnings("unchecked")
		List<ParkingSlot> slot = query.getResultList();
		if (slot.isEmpty()) {
			return null;
		}else {
	    return slot.get(0);
		//return new ParkingSlot();
		}
	}
	
	public void parkPlane(Runway runway, int id) throws InterruptedException {
		//find empty parking slot
		ParkingSlot slot = getEmptyParkSlot();	
		if (slot != null) {
	      //Delay to simmulate the landing proccess
		  TimeUnit.SECONDS.sleep(2);
		  //Assigne Airplae to Parkslot
		  slot.setAirplane(runway.getAirplane());
		  System.out.println(runway.getNumber());
		  //get to runway object from the database so it actually update the runway in the database at runnway.setAirplane(null) 
		  Runway runnway = entityManager.find(Runway.class, runway.getNumber());
		  runnway.setAirplane(null);
		  entityManager.persist(runnway);
		  System.out.println(runnway.toString());
		
		}
	}
	
	
}
