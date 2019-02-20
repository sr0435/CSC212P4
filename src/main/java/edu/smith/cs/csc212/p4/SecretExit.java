package edu.smith.cs.csc212.p4;

import java.util.Objects;

public class SecretExit extends Exit{

	public SecretExit(String target, String description) {
		super(target, description);
		

		// TODO Auto-generated constructor stub
	}
	
	private boolean hidden = true;
	
	public String getTarget() {
		return super.getTarget();

	}
	
	public String getDescription() {
		return super.getDescription();
	}
	
	public String toString() {
		return super.toString();
		}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean goesToSamePlace(Exit other) {
		return super.goesToSamePlace(other);
	}
	public boolean equals(Object other) {
		return super.equals(other);
	}
	@Override
	public boolean isSecret() {
		return hidden;
	}
	@Override
	public void search() {
		this.hidden = false;
		//isSecret();
	}
	
	
	
	

}
