package com.will.parkinglot;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.will.parkinglot.ParkingSpot.SpotType;

public class ParkingFloor {
	String floorId;
	int level;
	public ParkingFloor(int level) {
		super();
		this.level = level;
		floorId = "floor"+ level;
	}

	ConcurrentHashMap gates = new ConcurrentHashMap();
	ConcurrentHashMap spots = new ConcurrentHashMap();
	ConcurrentHashMap dashboard = new ConcurrentHashMap();
	
	public ParkingFloor generateSpot(SpotType type, int number) {
		IntStream.range(1, number+1).forEach(i-> {
			ParkingSpot spot = new ParkingSpot(type, this.level, i);
			spots.put(spot.spotId, spot);
		});
		return this;
	}
	
	public void payTicketAtPortal() {
		
	}

	public ParkingFloor addGate(String gateId, boolean isExit) {
		// TODO Auto-generated method stub
		return this;
	}
}
