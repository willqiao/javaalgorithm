package com.will.parkinglot;

import java.time.Instant;
import java.util.UUID;

import com.will.parkinglot.ParkingSpot.SpotType;

public class Customer {
	public Customer(SpotType carType, int seed) {
		super();
		this.carType = carType;
		customerId = "CUSTOMER"+seed; 
	}
	boolean isInternal;//auto checkout/ otherwise guest
	SpotType carType;
	String customerId;
	Creditcard cc;
	public Ticket getTicket() {
		// TODO Auto-generated method stub
		return null;
	}
	public Creditcard getCc() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
