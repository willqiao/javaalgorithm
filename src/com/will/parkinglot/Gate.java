package com.will.parkinglot;

public class Gate {
	boolean isExit;
	String gateId;
	Attendant attendant;
	
	public double exitAutoCheckout(Ticket ticket) {
		return 0;
	}
	
	public double exitManualCheckout(Ticket ticket, Creditcard cc) {
		return 0;
	}
	
	public Ticket enter(Customer customer) throws OutofOrderException {
		return null;
	}
}
