package com.will.parkinglot;

import java.util.HashMap;
import java.util.List;

public class ParkingLot {
	
	HashMap<Integer, ParkingFloor> floors = new HashMap<Integer, ParkingFloor>();
	List<Gate> gates;
	
	public ParkingFloor addFloor(int level) {
		ParkingFloor floor = new ParkingFloor(level);
		floors.put(level, floor);
		return floor;
	}

	public ParkingLot addGate(String gateId, int floor, boolean isExit) {
		return null;
	}

	public Gate getGate(String gateId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ParkingLot getFloor(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDashbard() {
		// TODO Auto-generated method stub
		return null;
	}

}
