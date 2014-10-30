package com.example.flashcard_reminder;

public class BundleOfCards {

	private int id;
	private String name;
	private int[] stat;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getStat() {
		return stat;
	}
	public void setStat(int[] stat) {
		this.stat = stat;
	}
	
	public BundleOfCards(int id, String name, int[] stat) {
		super();
		this.id = id;
		this.name = name;
		this.stat = stat;
	}
}
