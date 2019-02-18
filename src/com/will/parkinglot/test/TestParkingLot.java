package com.will.parkinglot.test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.will.parkinglot.Customer;
import com.will.parkinglot.OutofOrderException;
import com.will.parkinglot.ParkingLot;
import com.will.parkinglot.ParkingSpot.SpotType;
import com.will.parkinglot.Ticket;

class TestParkingLot {

	@Test
	void test() {
		ParkingLot lot = new ParkingLot();
		lot.addFloor(1).generateSpot(SpotType.MOTORCYCLE, 5)
			.generateSpot(SpotType.HANDICAP, 10)
			.generateSpot(SpotType.COMPACT, 10)
			.generateSpot(SpotType.LARGE, 10).addGate("w-gate", true).addGate("e-gate", true).addGate("s-gate",false).addGate("n-gate", false);
		
		lot.addFloor(2).generateSpot(SpotType.MOTORCYCLE, 5)
			.generateSpot(SpotType.HANDICAP, 10)
			.generateSpot(SpotType.COMPACT, 10)
			.generateSpot(SpotType.LARGE, 10).addGate("w-gate", true).addGate("e-gate", true).addGate("s-gate",false).addGate("n-gate", false);
		
		lot.addFloor(3).generateSpot(SpotType.MOTORCYCLE, 5)
			.generateSpot(SpotType.HANDICAP, 10)
			.generateSpot(SpotType.COMPACT, 10)
			.generateSpot(SpotType.LARGE, 10).addGate("w-gate", true).addGate("e-gate", true).addGate("s-gate",false).addGate("n-gate", false);
		
		
		List<Customer> customers = IntStream.range(0, 1000).boxed().map(i -> {
			if (Math.random() > 0.6) {
				return new Customer(SpotType.LARGE, i);
			} else if (Math.random() > 0.3) {
				return new Customer(SpotType.COMPACT, i);
			} else if (Math.random() > 0.1) {
				return new Customer(SpotType.HANDICAP, i);
			} else {
				return new Customer(SpotType.MOTORCYCLE, i);
			}
		}).collect(Collectors.toList());
		
		customers.parallelStream().forEach(customer->{
			try {
				Ticket ticket = lot.getFloor(1).getGate("id").enter(customer);
				System.out.println("checkin " + lot.getFloor(1).getDashbard());
			} catch (OutofOrderException e) {
				System.out.println("Parking Lot Is Full, sorry");
			}
		});
		
		customers.parallelStream().forEach(customer->{
			if (Math.random() > 0.5) { 
				lot.getFloor(1).getGate("id").exitAutoCheckout(customer.getTicket());
				System.out.println("checkout " + lot.getFloor(1).getDashbard());
			} else {
				lot.getFloor(1).getGate("id").exitManualCheckout(customer.getTicket(), customer.getCc());
				System.out.println("checkout " + lot.getFloor(1).getDashbard());
			}
		});
		
		
		
//		lot.getFloor(1).getBoard()
		
	}

}
