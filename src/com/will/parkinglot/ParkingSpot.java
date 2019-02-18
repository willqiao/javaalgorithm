package com.will.parkinglot;

import java.util.List;



public class ParkingSpot {
	public enum SpotType {
		COMPACT, LARGE, HANDICAP, MOTORCYCLE
	}
	
	public ParkingSpot(SpotType type, int level, int seed) {
		super();
		this.spotId = "spot-f" + level + "-" + seed ;
		this.type = type;
		floor = level;
	}
	int floor;
	String spotId;
	SpotType type;
	boolean occupiced;
}
