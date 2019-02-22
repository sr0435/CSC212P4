package edu.smith.cs.csc212.p4;

import java.util.Objects;

public class SecretExit extends Exit{
	/*
	 * basically made the same way as Exit
	 */
	public SecretExit(String target, String description) {
		super(target, description);
	}

	/*
	 * makes the exit hidden until the boolean is changed (when the user searches)
	 */
	private boolean hidden = true;

	/*
	 * are basically the same as Exit
	 */
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

	/*
	 * overrides Exit's isSecret method to only show the exit if the user searches
	 */
	@Override
	public boolean isSecret() {
		return this.hidden;
	}

	/*
	 * changes the exit to be not hidden
	 */
	@Override
	public void search() {
		this.hidden = false;
	}
	
	
	
	

}
