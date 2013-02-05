package com.example.lolbuilder;

public class Item {
	
	private String name;
	private double stat;
	
	public Item(){
		name = "Default";
		stat = 0;
	}
	public Item(String n, int s) {
		name = n;
		stat = s;
	}
	
	public String getName() {
		return name;
	}
	
	public double getStat() {
		return stat;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setStat(double s) {
		stat = s;
	}

}
