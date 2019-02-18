package com.will.parkinglot;

import java.time.Duration;
import java.time.Instant;

import com.will.parkinglot.ParkingSpot.SpotType;

public class Ticket {
	String ticketId;
	
	String exitId;
	String customerId;
	
	Instant start;
	String entranceId;
	
	Instant end;
	SpotType type;
	
	public Ticket(String entranceId) {
		super();
		start = Instant.now();
		this.entranceId = entranceId;
	}
	double calculateCost() {
		end = start.plus(Duration.ofMinutes(190));
		int hours = (int)Math.ceil(Duration.between(start, end).toMinutes() / 60.0);
		double total = 4.0;
		hours--;
		if (hours != 0) {
			total += 3.5;
			hours--;
		}
		if (hours != 0) {
			total += 3.5;
			hours--;
		}
		if (hours != 0) {
			total += hours*2.5;
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println(new Ticket("test").calculateCost());
	}
}
